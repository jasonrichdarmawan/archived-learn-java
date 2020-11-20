package com.example.bankaccount.controller.admin;

import com.example.bankaccount.model.User_LoginModel;
import com.example.bankaccount.repository.admin.Admin_LoginImpl;
import com.example.bankaccount.service.User_LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminLoginController {
  @Autowired
  Admin_LoginImpl admin_login;

  @Autowired
  User_LoginService user_loginService;

  @CrossOrigin("http://localhost:3000")
  @PostMapping("api/v1/admin/login")
  public ResponseEntity<?> login(@Valid @RequestBody User_LoginModel user_loginModel) {
    boolean User_ID_isAdmin = this.admin_login.exist(user_loginModel.getUser_ID());
    if (User_ID_isAdmin) {
      return this.user_loginService.login(user_loginModel);
    } else {
      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("message_code", 404);
      responseBody.put("message", "NOT_FOUND");

      return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }
  }
}
