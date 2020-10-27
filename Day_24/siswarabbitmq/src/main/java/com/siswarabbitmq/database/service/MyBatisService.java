package com.siswarabbitmq.database.service;

import com.google.gson.Gson;
import com.siswarabbitmq.database.model.Siswa;
import com.siswarabbitmq.database.rabbitmq.Send;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Service
public class MyBatisService {

    SqlSession session;
    Send send = new Send();

    public void connectMyBatis() throws IOException {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        session = sqlSessionFactory.openSession();
    }

    public void insertSiswa(String siswaString) throws IOException, TimeoutException {
        System.out.println("Memulai insert siswa..");
        Siswa siswa = new Gson().fromJson(siswaString, Siswa.class);
        connectMyBatis();
        session.insert("Siswa.insertSiswa", siswa);
        //session.commit();

        String idSiswa = (String) session.selectOne(
                "Siswa.getId", siswa);
        siswa.setId_siswa(Long.parseLong(idSiswa));

        Map<String, Object> nilai = new HashMap<>();
        nilai.put("id_siswa", siswa.getId_siswa());
        nilai.put("fisika", siswa.getNilai().get("fisika"));
        nilai.put("kalkulus", siswa.getNilai().get("kalkulus"));
        nilai.put("biologi", siswa.getNilai().get("biologi"));

        int hasil = session.insert("Siswa.insertNilai", nilai);  //Proses insert ke tabel nilai
        session.commit();                                           //Eksekusi Query

        if(hasil == 1){
            System.out.println("Insert siswa berhasil");
            send.sendToRestApi("Insert siswa berhasil");
            //return new ResponseEntity<>("Insert Siswa Berhasil", HttpStatus.OK);
        } else {
            System.out.println("Insert siswa gagal...");
            send.sendToRestApi("Insert siswa gagal...");
        }
    }

    public void deleteSiswa(String idString) throws IOException, TimeoutException {
        System.out.println("Memulai delete siswa...");
        connectMyBatis();                                           //Membuat koneksi db MyBatis
        long id = Long.parseLong(idString);                         //Konversi String ke Long

        session.delete("Siswa.deleteNilaiById",id);              //Proses menghapus record nilai
        int hasil = session.delete("Siswa.deleteSiswaById",id);  //Proses menghapus record siswa
        session.commit();                                           //Ekseskusi query

        if(hasil==1){
            System.out.println("Delete siswa berhasil");
            send.sendToRestApi("Delete siswa berhasil");
        } else {
            System.out.println("Delete siswa gagal...");
            send.sendToRestApi("Delete siswa gagal...");
        }
    }

    public void updateSiswa(String siswaString) throws IOException, TimeoutException {

        connectMyBatis();

        Siswa siswa = new Gson().fromJson(siswaString, Siswa.class);

        System.out.println(siswa.getId_siswa());
        System.out.println(siswa.getNama());
        System.out.println(siswa.getAddress());
        System.out.println(siswa.getStatus());

        int hasil = session.update("Siswa.updateSiswaById", siswa);
        session.commit();

        if(hasil==1){
            System.out.println("Edit nama siswa berhasil");
            send.sendToRestApi("Edit nama siswa berhasil");
        } else {
            System.out.println("Edit nama siswa gagal....");
            send.sendToRestApi("Edit nama siswa gagal....");
        }
    }
}
