package com.example.demo.controller;

import com.example.demo.model.StaffWorkerModel;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {
  @Autowired
  private StaffService staffService;

  //- GET request to /staff/ returns a list of all staff
  @GetMapping
  public ResponseEntity<?> getStaffs() {
    List<StaffWorkerModel> staffs = this.staffService.getStaffs();
    if (staffs.size() > 0) {
      return new ResponseEntity<>(staffs, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  //  - GET request to /staff/3 returns the staff with ID 3
  @GetMapping("{id}")
  public ResponseEntity<?> getStaffById(@PathVariable("id") int id) {
    StaffWorkerModel staff = this.staffService.getStaffById(id);
    if (staff != null) {
      return new ResponseEntity<>(staff, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  //  - POST request to /staff/ with a JSON staff object in the request’s body will register staff
  @PostMapping
  public ResponseEntity<?> postStaff(@RequestBody StaffWorkerModel staff) {
    StaffWorkerModel responseStaff = this.staffService.postStaff(staff);
    if (staff != null) {
      return new ResponseEntity<>(responseStaff, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  //  - PUT request to /staff/5 with a JSON staff object in the request’s body will update the object with ID 5
  @PutMapping("{id}")
  public ResponseEntity<?> putStaffById(@PathVariable("id") int id, @RequestBody StaffWorkerModel staff) {
    staff.setIDKaryawan(id);
    if (this.staffService.putStaffById(staff) == 1) {
      return new ResponseEntity<>("OK", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  //  - DELETE request to /staff/7 deletes the staff with ID 7
  @DeleteMapping("{id}")
  public ResponseEntity<?> deleteStaffById(@PathVariable("id") int id) {
    if (this.staffService.deleteStaffById(id) == 1) {
      return new ResponseEntity<>("OK", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  //  - DELETE request to /staff/ deletes all the products
  @DeleteMapping
  public ResponseEntity<?> deleteStaffs() {
    if (this.staffService.deleteStaffs() > 0) {
      return new ResponseEntity<>("OK", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}