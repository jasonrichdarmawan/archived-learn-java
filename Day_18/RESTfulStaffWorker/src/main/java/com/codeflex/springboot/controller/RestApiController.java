package com.codeflex.springboot.controller;

import com.codeflex.springboot.model.Staff;
import com.codeflex.springboot.service.StaffService;
import com.codeflex.springboot.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

  public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

  @Autowired
  StaffService staffService; //Service which will do all data retrieval/manipulation work

  // -------------------Retrieve All Staffs--------------------------------------------

  @RequestMapping(value = "/staff/", method = RequestMethod.GET)
  public ResponseEntity<List<Staff>> ListAllStaffs() {
    List<Staff> staffs = staffService.findAllStaffs();
    if (staffs.isEmpty()) {
      return new ResponseEntity<>(staffs, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(staffs, HttpStatus.OK);
  }

  // -------------------Retrieve Single Staff------------------------------------------

  @RequestMapping(value = "/staff/{id}", method = RequestMethod.GET)
  public ResponseEntity<?> getStaff(@PathVariable("id") int id) {
    logger.info("Fetching Staff with id {}", id);
    Staff staff = staffService.findById(id);
    if (staff == null) {
      logger.error("Staff with id {} not found.", id);
      return new ResponseEntity<>(new CustomErrorType("Staff with id " + id + " not found"), HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(staff, HttpStatus.OK);
  }

  // -------------------Create a Staff-------------------------------------------

  @RequestMapping(value = "/staff/", method = RequestMethod.POST)
  public ResponseEntity<?> createStaff(@RequestBody Staff staff) {
    logger.info("Creating Staff : {}", staff);

    staffService.saveStaff(staff);

    return new ResponseEntity<>(staff, HttpStatus.CREATED);
  }

  // ------------------- Update a Staff ------------------------------------------------

  @RequestMapping(value = "/staff/{id}", method = RequestMethod.PUT)
  public ResponseEntity<?> updateStaff(@PathVariable("id") int id, @RequestBody Staff staff) {
    logger.info("Updating Staff with id {}", id);

    Staff currentStaff = staffService.findById(id);

    if (currentStaff == null) {
      logger.error("Unable to update. Staff with id {} not found.", id);
      return new ResponseEntity<>(new CustomErrorType("Unable to upate. Staff with id " + id + " not found."),
              HttpStatus.NOT_FOUND);
    }

    currentStaff.setNama(staff.getNama());
    currentStaff.setTunjanganPulsa(staff.getTunjanganPulsa());
    currentStaff.setGajiPokok(staff.getGajiPokok());
    currentStaff.setAbsensiHari(staff.getAbsensiHari());
    currentStaff.setEmail(staff.getEmail());

    staffService.updateStaff(currentStaff);
    return new ResponseEntity<>(currentStaff, HttpStatus.OK);
  }

  // ------------------- Delete a Staff-----------------------------------------

  @RequestMapping(value = "/staff/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteStaff(@PathVariable("id") int id) {
    logger.info("Fetching & Deleting Staff with id {}", id);

    Staff staff = staffService.findById(id);
    if (staff == null) {
      logger.error("Unable to delete. Staff with id {} not found.", id);
      return new ResponseEntity<>(new CustomErrorType("Unable to delete. Staff with id " + id + " not found."),
              HttpStatus.NOT_FOUND);
    }
    staffService.deleteStaffById(id);
    return new ResponseEntity<Staff>(HttpStatus.NO_CONTENT);
  }

  // ------------------- Delete All Staffs-----------------------------

  @RequestMapping(value = "/staff/", method = RequestMethod.DELETE)
  public ResponseEntity<Staff> deleteAllStaffs() {
    logger.info("Deleting All Staffs");

    staffService.deleteAllStaffs();
    return new ResponseEntity<Staff>(HttpStatus.NO_CONTENT);
  }
}