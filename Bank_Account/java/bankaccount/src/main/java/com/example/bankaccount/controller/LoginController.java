package com.example.bankaccount.controller;

import com.example.bankaccount.model.User_LoginModel;
import com.example.bankaccount.service.User_LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {
  @Autowired
  User_LoginService user_loginService;

  @CrossOrigin("http://localhost:3000")
  @PostMapping("api/v1/login")
  public ResponseEntity<?> login(@Valid @RequestBody User_LoginModel user_loginModel) {
    return this.user_loginService.login(user_loginModel);
  }
}
