package com.example.bankaccount.controller.admin;

import com.example.bankaccount.model.TransactionsModel;
import com.example.bankaccount.model.admin.Admin_TransactionsModel;
import com.example.bankaccount.repository.admin.Admin_LoginImpl;
import com.example.bankaccount.repository.admin.Admin_TransactionsImpl;
import com.example.bankaccount.service.TokenService;
import com.example.bankaccount.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminTransactionsController {
  @Autowired
  Admin_LoginImpl admin_login;

  @Autowired
  TokenService tokenService;

  @Autowired
  Admin_TransactionsImpl admin_transactions;

  @Autowired
  TransferService transferService;

  @CrossOrigin("http://localhost:3000")
  @PostMapping("api/v1/admin/transaction")
  public ResponseEntity<?> transfer(@RequestHeader(value = "Authorization") String Authorization, @RequestBody Admin_TransactionsModel admin_transactionsModel) {
    String token = Authorization.split(" ")[1];
    boolean isVerified = this.tokenService.verify(token);

    Map<String, Object> responseBody = new HashMap<>();

    if (isVerified) {
      String Account_Number = (String) this.tokenService.getClaim(token, "Account_Number");

      if (Account_Number.equals(admin_transactionsModel.getDestination())) {
        responseBody.put("message_code", 400);
        responseBody.put("message", "Bad Request");

        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
      }

      String User_ID = (String) this.tokenService.getClaim(token, "User_ID");

      if (!admin_login.exist(User_ID)) {
        responseBody.put("message_code", 404);
        responseBody.put("message", "NOT_FOUND");

        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
      }

      if (this.admin_transactions.insert(admin_transactionsModel, User_ID) != 1) {
        responseBody.put("message_code", 503);
        responseBody.put("message", "Service Unavailable");

        return new ResponseEntity<>(responseBody, HttpStatus.SERVICE_UNAVAILABLE);
      }

      TransactionsModel transactionsModel = new TransactionsModel(admin_transactionsModel.getDestination(), 1, admin_transactionsModel.getTransaction_Value());

      return this.transferService.transfer("SETORAN TUNAI", transactionsModel, transactionsModel.getTransaction_Value());
    } else {
      responseBody.put("message_code", 401);
      responseBody.put("message", "Unauthorized");
      return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
    }
  }
}