package com.example.bankaccount.controller;

import com.example.bankaccount.model.TransactionsModel;
import com.example.bankaccount.repository.StatementsImpl;
import com.example.bankaccount.repository.TransactionsImpl;
import com.example.bankaccount.service.CurrentBalanceService;
import com.example.bankaccount.service.HistoryValidatorService;
import com.example.bankaccount.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
public class TransactionsController {
  @Autowired
  TokenService tokenService;

  @Autowired
  TransactionsImpl transactions;

  @Autowired
  StatementsImpl statements;

  @Autowired
  CurrentBalanceService currentBalanceService;

  @Autowired
  HistoryValidatorService historyValidatorService;

  @CrossOrigin("http://localhost:3000")
  @GetMapping("api/v1/history/{Start}/{End}")
  public ResponseEntity<?> getTransactions(@RequestHeader(value = "Authorization") String Authorization, @PathVariable("Start") String start, @PathVariable("End") String end) {
    // bug prevention
    LocalDate Start = null;
    LocalDate End = null;
    try {
      Start = LocalDate.parse(start);
      End = LocalDate.parse(end);
//      System.out.println(start + end + Start + End);
    } catch (DateTimeParseException e) {
      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("message_code", 400);
      responseBody.put("message", e.getMessage());
      return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    if (!historyValidatorService.validate(Start, End)) {
      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("message_code", 400);
      responseBody.put("message", "Bad Requet");
      return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    String token = Authorization.split(" ")[1];
    boolean isVerified = this.tokenService.verify(token);

    if (isVerified) {
      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("message_code", 200);
      responseBody.put("message", "OK");

      String Account_Number = (String) this.tokenService.getClaim(token, "Account_Number");
      List<TransactionsModel> transactions = this.transactions.selectByStartAndEndDate(Account_Number, Start, End);
      BigDecimal Opening_Balance = this.statements.selectOpeningBalanceByAccountNumber(Account_Number);

      responseBody.put("Opening_Balance", Opening_Balance);
      responseBody.put("transactions", transactions);

      return new ResponseEntity<>(responseBody, HttpStatus.OK);
    } else {
      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("message_code", 401);
      responseBody.put("message", "Unauthorized");
      return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
    }
  }

  @CrossOrigin("http://localhost:3000")
  @PostMapping("api/v1/transaction")
  public ResponseEntity<?> transfer(@RequestHeader(value = "Authorization") String Authorization, @RequestBody TransactionsModel transactionsModel) {
    String token = Authorization.split(" ")[1];
    boolean isVerified = this.tokenService.verify(token);

    if (isVerified) {
      String Account_Number = (String) this.tokenService.getClaim(token, "Account_Number");

      // prevent bug
      if (Account_Number.equals(transactionsModel.getDestination())) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message_code", 400);
        responseBody.put("message", "Bad Request");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
      }

      BigDecimal Current_Balance = this.currentBalanceService.getCurrentBalanceByAccountNumber(Account_Number);
      int rowsAffected = this.transactions.insert(transactionsModel, Current_Balance, Account_Number);
      if (rowsAffected == 1) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message_code", 201);
        responseBody.put("message", "CREATED");
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
      } else {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message_code", 402);
        responseBody.put("message", "Payment Required");
        return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
      }
    } else {
      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("message_code", 401);
      responseBody.put("message", "Unauthorized");
      return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
    }
  }

  @CrossOrigin("http://localhost:3000")
  @GetMapping("api/v1/balance")
  public ResponseEntity<?> getCurrentBalance(@RequestHeader(value = "Authorization") String Authorization) {
    String token = Authorization.split(" ")[1];
    boolean isVerified = this.tokenService.verify(token);

    if (isVerified) {
      String Account_Number = (String) this.tokenService.getClaim(token, "Account_Number");
      BigDecimal Current_Balance = this.currentBalanceService.getCurrentBalanceByAccountNumber(Account_Number);
      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("message_code", 200);
      responseBody.put("message", "OK");
      responseBody.put("balance", Current_Balance);
      return new ResponseEntity<>(responseBody, HttpStatus.OK);
    } else {
      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("message_code", 401);
      responseBody.put("message", "Unauthorized");
      return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
    }
  }
}
