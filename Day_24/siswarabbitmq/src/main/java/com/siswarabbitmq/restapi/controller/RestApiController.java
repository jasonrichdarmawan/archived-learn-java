package com.siswarabbitmq.restapi.controller;

import com.siswarabbitmq.database.model.Siswa;
import com.siswarabbitmq.restapi.rabbitmq.RestApiSend;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;


@RestController
@RequestMapping("/SiswaRabbitMQ")
public class RestApiController {


    RestApiSend restApiSend = new RestApiSend();

    //Memasukan record siswa
    @PostMapping("/InsertSiswa")
    public ResponseEntity<?> insertSiswa(@RequestBody Siswa siswa){
        try {
            restApiSend.insertSiswa(new Gson().toJson(siswa));
        }catch (Exception e){
            System.out.println("error = " + e);
        }
        return new ResponseEntity<>("Insert Siswa Berhasil", HttpStatus.OK);
    }

    @PostMapping("/DeleteSiswa/{id}")
    public ResponseEntity<?> deleteSiswa(@PathVariable long id){
        try {
            restApiSend.deleteSiswaById(Long.toString(id));
        }catch (Exception e){
            System.out.println("error = " + e);
        }
        return new ResponseEntity<>("Delete Siswa Berhasil", HttpStatus.OK);
    }

    @PostMapping("/UpdateSiswa/{id}")
    public ResponseEntity<?> updateSiswa(@RequestBody Siswa siswa, @PathVariable long id){
        siswa.setId_siswa(id);
        try {
            restApiSend.updateSiswaById(new Gson().toJson(siswa));
        }catch (Exception e){
            System.out.println("error = " + e);
        }
        return new ResponseEntity<>("Update Siswa Berhasil", HttpStatus.OK);
    }
}

