package com.example.demo.controller;

import com.example.demo.service.StaffRabbitMQ;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * TODO: 27/10/2020 How to return value inside lamda? -> use case: StaffMQDB can return IDKaryawan for RESTController.
 * Current solution is to use final variable with while statement -> bug: final variable inside the method is persistent, so the method only can be used once.
 */
@RestController
@RequestMapping("/api/v2/staff")
public class StaffControllerV2 {
  @Autowired
  private StaffRabbitMQ staffRabbitMQ;

  @GetMapping
  public ResponseEntity<?> getStaffs() {
    JSONObject response = this.staffRabbitMQ.getStaffs();
    if ((boolean) response.get("response") == true) {
      return new ResponseEntity<>((JSONArray) response.get("payload"), HttpStatus.OK);
    } else {
      return new ResponseEntity<>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("{id}")
  public ResponseEntity<?> getStaffById(@PathVariable("id") int id) {
    JSONObject response = this.staffRabbitMQ.getStaffById(id);
    if ((boolean) response.get("response") == true) {
      return new ResponseEntity<>((JSONObject) response.get("payload"), HttpStatus.OK);
    } else {
      return new ResponseEntity<>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping
  public ResponseEntity<?> postStaff(@RequestBody String requestBody) {
    JSONObject response = this.staffRabbitMQ.postStaff(requestBody);
    if ((boolean) response.get("response") == true) {
      return new ResponseEntity<>((JSONObject) response.get("payload"), HttpStatus.OK);
    } else {
      return new ResponseEntity<>("INTERNAL SERVER ERROR", HttpStatus.OK);
    }
  }

  @PutMapping("{id}")
  public ResponseEntity<?> putStaffById(@PathVariable("id") int id, @RequestBody String requestBody) {
    // in SDR the IDKaryawan is defined by path. Hence why we use JSONParser to modify but convert it back to toJSONString.
    JSONObject requestStaff = null;
    try {
      requestStaff = (JSONObject) new JSONParser().parse(requestBody);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    requestStaff.put("IDKaryawan", id);

    JSONObject response = (JSONObject) this.staffRabbitMQ.putStaffById(requestStaff.toJSONString());
    if ((boolean) response.get("response") == true) {
      return new ResponseEntity<>("OK", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> deleteStaffById(@PathVariable("id") int id) {
    String requestBody = String.format("{\"IDKaryawan\": %d}", id);

    JSONObject response = (JSONObject) this.staffRabbitMQ.deleteStaffById(requestBody);
    if ((boolean) response.get("response") == true) {
      return new ResponseEntity<>("OK", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping
  public ResponseEntity<?> deleteStaffs() {
    JSONObject response = this.staffRabbitMQ.deleteStaffs();
    if ((boolean) response.get("response") == true) {
      return new ResponseEntity<>("OK", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
