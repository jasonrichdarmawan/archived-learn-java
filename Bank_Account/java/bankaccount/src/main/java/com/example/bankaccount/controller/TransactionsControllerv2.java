package com.example.bankaccount.controller;

import com.example.bankaccount.RabbitMQ.Sender;
import com.example.bankaccount.model.TransactionsModel;
import com.example.bankaccount.service.CurrentBalanceService;
import com.example.bankaccount.service.TokenService;
import com.example.bankaccount.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TransactionsControllerv2 {
  @Autowired
  TokenService tokenService;

  @Autowired
  Sender sender;

  @CrossOrigin("http://localhost:3000")
  @PostMapping("api/v2/transaction")
  public ResponseEntity<?> transfer(@RequestHeader(value = "Authorization") String Authorization, @RequestBody TransactionsModel transactionsModel) {
    Map<String, Object> responseBody = new HashMap<>();

    String token = Authorization.split(" ")[1];
    boolean isVerified = this.tokenService.verify(token);

    if (isVerified) {
      String Account_Number = (String) this.tokenService.getClaim(token, "Account_Number");

      transactionsModel.setSource(Account_Number);
      sender.rabbitTemplate.convertAndSend("transactions", "transfer", transactionsModel);

      // @// TODO: 09/11/2020 refactor with HTTP callback, so the server can return a real response.
      responseBody.put("message_code", 200);
      responseBody.put("message", "On queue");
      return new ResponseEntity<>(responseBody, HttpStatus.OK);
    } else {
      responseBody.put("message_code", 401);
      responseBody.put("message", "Unauthorized");
      return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
    }
  }
}
