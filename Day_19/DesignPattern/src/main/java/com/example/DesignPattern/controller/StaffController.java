package com.example.designpattern.controller;

import com.example.designpattern.adapter.SocketToFTPAdapter;
import com.example.designpattern.builder.StaffBuilder;
import com.example.designpattern.model.StaffTotalSalaryResponseEntity;
import com.example.designpattern.model.StaffUnpaidLeaveResponseEntity;
import com.example.designpattern.model.StaffWorkerModel;
import com.example.designpattern.service.StaffService;
import com.example.designpattern.util.SocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {
  private final StaffService staffService;

  @Autowired
  public StaffController(StaffService staffService) {
    this.staffService = staffService;
  }

  @PostMapping
  public ResponseEntity<?> postStaffs(@RequestBody List<Map<String, String>> requestStaffs) {
    List<StaffWorkerModel> responseStaffs = this.staffService.postStaffsWithStaffBuilder(requestStaffs);
    if (responseStaffs.size() > 0) {
      return new ResponseEntity<List<StaffWorkerModel>>(responseStaffs, HttpStatus.CREATED);
    } else {
      return new ResponseEntity<String>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping
  public ResponseEntity<String> putStaffs(@RequestBody List<Map<String, String>> requestStaffs) {
    if (this.staffService.putStaffs(requestStaffs)) {
      return new ResponseEntity<String>("OK", HttpStatus.OK);
    } else {
      return new ResponseEntity<String>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/")
  public ResponseEntity<?> getStaffsWithTotalSalary() {
    List<StaffTotalSalaryResponseEntity> staffs = this.staffService.getStaffsWithTotalSalary();

    if (staffs.size() > 0) {
      return new ResponseEntity<List<StaffTotalSalaryResponseEntity>>(staffs, HttpStatus.OK);
    } else {
      return new ResponseEntity<String>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping
  public ResponseEntity<?> getStaffsByIdOrName(@RequestParam(required = false, name="id") Integer id, @RequestParam(required = false, name="nama") String nama) {
    List<StaffUnpaidLeaveResponseEntity> staffs = this.staffService.getStaffsByIdOrName(id, nama);

    if (staffs.size() > 0) {
      return new ResponseEntity<List<StaffUnpaidLeaveResponseEntity>>(staffs, HttpStatus.OK);
    } else {
      return new ResponseEntity<String>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/save")
  public ResponseEntity<String> saveToFTP() {
    SocketToFTPAdapter serverSocket = new SocketToFTPAdapter();
    Thread thread = new Thread(serverSocket);
    thread.start();
    new SocketClient().send(this.staffService.getStaffs());
    return new ResponseEntity<String>("OK", HttpStatus.OK);
  }
}
