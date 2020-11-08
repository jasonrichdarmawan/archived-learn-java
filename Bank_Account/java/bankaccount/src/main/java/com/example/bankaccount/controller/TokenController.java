/**
 * disabled due to security concern:
 * 1. in case of a stolen token, the only way to block the token is by restarting the server /
 * changing the jwtSecret.
 */
//package com.example.bankaccount.controller;
//
//import com.example.bankaccount.service.TokenService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//public class TokenController {
//  @Autowired
//  TokenService tokenService;
//
//  @PostMapping("api/v1/token")
//  public ResponseEntity<?> refreshToken(@RequestHeader(value="Authorization") String Authorization) {
//    String oldToken = Authorization.split(" ")[1];
//
//    Map<String, Object> responseBody = new HashMap<>();
//
//    if (tokenService.verify(oldToken)) {
//      responseBody.put("message_code", 200);
//      responseBody.put("message", "OK");
//
//      String newToken = tokenService.refresh(oldToken);
//      responseBody.put("token", newToken);
//
//      return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
//    } else {
//      responseBody.put("message_code", 201);
//      responseBody.put("message", "Unauthorized");
//      return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
//    }
//  }
//}
