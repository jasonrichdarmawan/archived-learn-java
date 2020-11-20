package com.example.bankaccount.controller;

import com.example.bankaccount.RabbitMQ.Sender;
import com.example.bankaccount.model.TransactionsModel;
import com.example.bankaccount.model.Transactions_ProgressModel;
import com.example.bankaccount.repository.Transactions_ProgressImpl;
import com.example.bankaccount.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TransactionsControllerv2 {
  @Autowired
  TokenService tokenService;

  @Autowired
  Transactions_ProgressImpl transactions_progress;

  @Autowired
  Sender sender;

  @CrossOrigin("http://localhost:3000")
  @GetMapping("api/v2/transaction/{Progress_ID}")
  public ResponseEntity<?> transaction_progress(@RequestHeader(value = "Authorization") String Authorization, @PathVariable("Progress_ID") String Progress_ID) {
    Map<String, Object> responseBody = new HashMap<>();

    if (!Authorization.contains("Bearer ")) {
      responseBody.put("message_code", 400);
      responseBody.put("message", "Bad Request");
      return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    String token = Authorization.split(" ")[1];
    boolean isVerified = this.tokenService.verify(token);

    if (isVerified) {
      String Account_Number = (String) this.tokenService.getClaim(token, "Account_Number");
      Transactions_ProgressModel transactions_progressModel = this.transactions_progress.selectByProgress_IDAndAccount_Number(Progress_ID, Account_Number);

      if (transactions_progressModel != null) {
        responseBody.put("message_code", 302);
        responseBody.put("message", "FOUND");
        responseBody.put("progress", transactions_progressModel);
        return new ResponseEntity<>(responseBody, HttpStatus.FOUND);
      } else {
        responseBody.put("message_code", 404);
        responseBody.put("message", "NOT FOUND");
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
      }

    } else {
      responseBody.put("message_code", 404);
      responseBody.put("message", "NOT_FOUND");
      return new ResponseEntity(responseBody, HttpStatus.NOT_FOUND);
    }
  }

  @CrossOrigin("http://localhost:3000")
  @PostMapping("api/v2/transaction")
  public ResponseEntity<?> transfer(@RequestHeader(value = "Authorization") String Authorization, @Valid @RequestBody TransactionsModel transactionsModel, UriComponentsBuilder uriComponentsBuilder) {
    Map<String, Object> responseBody = new HashMap<>();

    String token = Authorization.split(" ")[1];
    boolean isVerified = this.tokenService.verify(token);

    if (isVerified) {
      String Account_Number = (String) this.tokenService.getClaim(token, "Account_Number");

      Transactions_ProgressModel transactions_progressModel = new Transactions_ProgressModel();
      transactions_progressModel.setAccount_Number(Account_Number);
      transactions_progressModel.setMessage_Code("202");
      transactions_progressModel = this.transactions_progress.insert(transactions_progressModel);

      transactionsModel.setSource(Account_Number);
      transactionsModel.setProgress_ID(transactions_progressModel.getProgress_ID());
      sender.rabbitTemplate.convertAndSend("transactions", "transfer", transactionsModel);

      responseBody.put("message_code", 202);
      responseBody.put("message", "On queue");
      UriComponents uriComponents = uriComponentsBuilder.path("/api/v2/transaction/{Progress_ID}").buildAndExpand(transactions_progressModel.getProgress_ID());
      responseBody.put("location", uriComponents.toUri());

      return new ResponseEntity(responseBody, HttpStatus.OK);
    } else {
      responseBody.put("message_code", 401);
      responseBody.put("message", "Unauthorized");
      return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
    }
  }
}
