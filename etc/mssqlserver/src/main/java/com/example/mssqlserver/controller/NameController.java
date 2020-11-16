package com.example.mssqlserver.controller;

import com.example.mssqlserver.mapper.NameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameController {

  @Autowired
  private NameMapper nameMapper;

  @GetMapping("api/v1/name")
  public ResponseEntity<?> selectAll() {
    return new ResponseEntity<>(nameMapper.selectAll(), HttpStatus.OK);
  }
}
