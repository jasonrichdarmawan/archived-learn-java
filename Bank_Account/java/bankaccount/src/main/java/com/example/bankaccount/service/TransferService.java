package com.example.bankaccount.service;

import com.example.bankaccount.model.TransactionsModel;
import com.example.bankaccount.repository.TransactionsImpl;
import com.example.bankaccount.repository.User_InfoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransferService {

  @Autowired
  User_InfoImpl user_info;

  @Autowired
  TransactionsImpl transactions;

  public ResponseEntity transfer(String Source, TransactionsModel transactionsModel, BigDecimal Current_Balance) {
    /**
     * prevent bug
     * return false if:
     * 1. Source === Destination
     * 2. Destination does not exists
     * @// TODO: 07/11/2020 refactor bug prevention to support different Destination_Type.
     *
     * TransactionModel has Source but we can't trust it, we get the Source from the decoded token.
     */
    if (Source.equals(transactionsModel.getDestination()) || !user_info.isAccount_NumberExists(transactionsModel.getDestination())) {
      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("message_code", 400);
      responseBody.put("message", "Bad Request");
      return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    int rowsAffected = this.transactions.insert(transactionsModel, Current_Balance, Source);
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
  }
}
