package com.example.bankaccount.service;

import com.example.bankaccount.model.TransactionsModel;
import com.example.bankaccount.repository.TransactionsImpl;
import com.example.bankaccount.repository.Transactions_ProgressImpl;
import com.example.bankaccount.repository.User_InfoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferServicev2 {

  @Autowired
  User_InfoImpl user_info;

  @Autowired
  TransactionsImpl transactions;

  @Autowired
  Transactions_ProgressImpl transactions_progress;

  public String transfer(String Source, TransactionsModel transactionsModel, BigDecimal Current_Balance) {
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
      return "400";
    }

    int rowsAffected = this.transactions.insert(transactionsModel, Current_Balance, Source);
    if (rowsAffected == 1) {
      return "201";
    } else {
      return "402";
    }
  }
}
