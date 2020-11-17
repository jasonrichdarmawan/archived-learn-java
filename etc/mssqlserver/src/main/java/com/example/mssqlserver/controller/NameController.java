package com.example.mssqlserver.controller;

import com.example.mssqlserver.mapper.NameMapper;
import com.example.mssqlserver.model.NameModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class NameController {

  @Autowired
  private NameMapper nameMapper;

  @GetMapping("api/v1/name")
  public ResponseEntity<?> selectAll() {
    return ResponseEntity.ok().body(nameMapper.selectAll());
  }

  @PostMapping("api/v1/name")
  public ResponseEntity<?> insert(@Valid @RequestBody NameModel nameModel) {
    if (nameMapper.insert(nameModel) == 1) {
      return ResponseEntity.ok().body(nameModel);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }
}
