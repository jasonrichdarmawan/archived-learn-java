package com.example.bankaccount.controller;

import com.example.bankaccount.model.User_InfoModel;
import com.example.bankaccount.repository.User_InfoImpl;
import com.example.bankaccount.service.TokenService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
public class AccountInfoController {
  @Autowired
  User_InfoImpl user_info;

  @Autowired
  TokenService tokenService;

  @CrossOrigin("http://localhost:3000")
  @GetMapping("api/v1/account/{Account_Number}")
  public ResponseEntity<?> getAccountInfoByAccount_Number(@RequestHeader(value = "Authorization") String Authorization, @PathVariable("Account_Number") String Account_Number) {
    String token = Authorization.split(" ")[1];
    boolean isVerified = this.tokenService.verify(token);

    // expensive query prevention;
    if (!Pattern.matches("[0-9]{17}", Account_Number)) {
      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("message_code", 404);
      responseBody.put("message", "NOT_FOUND");
      return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

    if (isVerified) {
      User_InfoModel user_infoModel = this.user_info.selectByAccount_Number(Account_Number);

      if (user_infoModel != null) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message_code", 200);
        responseBody.put("message", "OK");

        Map<String, Object> account_info = new HashMap<>();
        account_info.put("Full_Name", user_infoModel.getFull_Name());
        account_info.put("ISO_4217", user_infoModel.getISO_4217());

        responseBody.put("account_info", account_info);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
      } else {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message_code", 404);
        responseBody.put("message", "NOT_FOUND");
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
      }
    } else {
      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("message_code", 401);
      responseBody.put("message", "Unauthorized");
      return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
    }
  }
}
