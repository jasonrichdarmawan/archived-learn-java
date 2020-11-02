package com.example.bankaccount.controller;

import com.example.bankaccount.model.RegisterUserModel;
import com.example.bankaccount.model.User_DetailModel;
import com.example.bankaccount.model.User_InfoModel;
import com.example.bankaccount.model.User_LoginModel;
import com.example.bankaccount.repository.User_DetailImpl;
import com.example.bankaccount.repository.User_InfoImpl;
import com.example.bankaccount.repository.User_LoginImpl;
import com.example.bankaccount.service.TokenService;
import com.example.bankaccount.service.GenerateUser_IDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RegisterController {

  @Autowired
  User_LoginImpl user_login;

  @Autowired
  User_InfoImpl user_info;

  @Autowired
  User_DetailImpl user_detail;

  @Autowired
  GenerateUser_IDService generateUser_idService;

  @Autowired
  TokenService tokenService;

  @PostMapping("api/v1/register")
  public ResponseEntity<?> register(@RequestBody RegisterUserModel registerUserModel) {
    String User_ID = null;
    if (registerUserModel.getUser_ID() == null) {
      User_ID = this.generateUser_idService.generate(registerUserModel.getFull_Name(), registerUserModel.getBirth_Date());
    }

    if (!this.user_login.exist(User_ID)) {
      User_LoginModel user_loginModel = new User_LoginModel();
      user_loginModel.setUser_ID(User_ID);
      user_loginModel.setHashed_PIN(registerUserModel.getHashed_PIN());

      /**
       * TODO: if you wish to refactor it to one line, use Gson. I am just old fashioned.
       */
      User_InfoModel user_infoModel = new User_InfoModel();
      user_infoModel.setUser_ID(User_ID);
      user_infoModel.setFull_Name(registerUserModel.getFull_Name());
      user_infoModel.setISO_4217(registerUserModel.getISO_4217());

      User_DetailModel user_detailModel = new User_DetailModel();
      user_detailModel.setBirth_Date(registerUserModel.getBirth_Date());
      user_detailModel.setAddress_3(registerUserModel.getAddress_3());
      user_detailModel.setAddress_4(registerUserModel.getAddress_4());
      user_detailModel.setAddress_1(registerUserModel.getAddress_1());
      user_detailModel.setAddress_2(registerUserModel.getAddress_2());
      user_detailModel.setZip_Code(registerUserModel.getZip_Code());
      user_detailModel.setISO_3166_1(registerUserModel.getISO_3166_1());

      // insert
      int user_loginRowsAffected = this.user_login.insert(user_loginModel);

      user_infoModel = this.user_info.insert(user_infoModel);

      user_detailModel.setAccount_Number(user_infoModel.getAccount_Number());

      int user_detailRowsAffected = this.user_detail.insert(user_detailModel);

      if (user_loginRowsAffected == 1 && user_infoModel.getAccount_Number() != null && user_detailRowsAffected == 1) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message_code", 201);
        responseBody.put("message", "CREATED");
        responseBody.put("User_ID", User_ID);

        String token = tokenService.generate(user_infoModel.getAccount_Number());
        responseBody.put("token", token);

        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
      } else {
        // TODO: rollback
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message_code", 500);
        responseBody.put("message", "Internal Server Error");
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } else {
      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("message_code", 400);
      responseBody.put("message", "The User ID already exists.");
      return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }
  }
}
