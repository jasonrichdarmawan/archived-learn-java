package com.example.mssqlserver.controller;

import com.example.mssqlserver.mapper.NameMapper;
import com.example.mssqlserver.model.NameModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NameController {

  @Autowired
  private NameMapper nameMapper;

  @GetMapping("api/v1/name")
  public ResponseEntity<?> selectAll() {
    return new ResponseEntity<>(nameMapper.selectAll(), HttpStatus.OK);
  }

  @PostMapping("api/v1/name")
  public ResponseEntity<?> insert(@RequestBody NameModel nameModel) {

    // validator
    if (!nameModel.requestIsValid()) {
      return ResponseEntity.badRequest().build();
    }

    if (nameMapper.insert(nameModel) == 1) {
      return new ResponseEntity<>(nameModel, HttpStatus.OK);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }
}
