package com.example.bankaccount.controller;

import com.example.bankaccount.model.User_InfoModel;
import com.example.bankaccount.model.User_LoginModel;
import com.example.bankaccount.repository.User_InfoImpl;
import com.example.bankaccount.repository.User_LoginImpl;
import com.example.bankaccount.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
  @Autowired
  User_LoginImpl user_login;

  @Autowired
  User_InfoImpl user_info;

  @Autowired
  TokenService tokenService;

  @CrossOrigin("http://localhost:3000")
  @PostMapping("api/v1/login")
  public ResponseEntity<?> login(@RequestBody User_LoginModel user_loginModel) {
    if (this.user_login.login(user_loginModel)) {
      User_InfoModel user_infoModel = this.user_info.selectByUser_ID(user_loginModel.getUser_ID());

      String token = tokenService.generate(user_infoModel.getAccount_Number());

      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("message_code", 200);
      responseBody.put("message", "OK");
      responseBody.put("Full_Name", user_infoModel.getFull_Name());
      responseBody.put("ISO_4217", user_infoModel.getISO_4217());
      responseBody.put("token", token);

      return new ResponseEntity<>(responseBody, HttpStatus.OK);
    } else {
      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("message_code", 404);
      responseBody.put("message", "NOT_FOUND");

      return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

  }
}
