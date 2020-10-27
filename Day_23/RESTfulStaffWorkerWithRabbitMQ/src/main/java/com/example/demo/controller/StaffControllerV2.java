package com.example.demo.controller;

import com.example.demo.service.StaffRabbitMQ;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/staff")
public class StaffControllerV2 {
  @Autowired
  private StaffRabbitMQ staffRabbitMQ;

  @PostMapping
  public ResponseEntity<?> postStaff(@RequestBody String requestBody) {
    this.staffRabbitMQ.postStaff(requestBody);
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

  @PutMapping("{id}")
  public ResponseEntity<?> putStaffById(@PathVariable("id") int id, @RequestBody String requestBody) {
    // in SDR the IDKaryawan is defined by path. Hence why we use JSONParser to modify but convert it back to toJSONString.
    JSONObject jsonObject = null;
    try {
      jsonObject = (JSONObject) new JSONParser().parse(requestBody);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    jsonObject.put("IDKaryawan", id);

    this.staffRabbitMQ.putStaffById(jsonObject.toJSONString());
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> deleteStaffById(@PathVariable("id") int id) {
    String requestBody = String.format("{\"IDKaryawan\": %d}", id);
    this.staffRabbitMQ.deleteStaffById(requestBody);
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity<?> deleteStaffs() {
    this.staffRabbitMQ.deleteStaffs();
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }
}
