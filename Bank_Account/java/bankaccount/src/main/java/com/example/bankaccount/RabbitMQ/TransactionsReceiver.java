package com.example.bankaccount.RabbitMQ;

import com.example.bankaccount.model.TransactionsModel;
import com.example.bankaccount.service.CurrentBalanceService;
import com.example.bankaccount.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionsReceiver {

  @Autowired
  CurrentBalanceService currentBalanceService;

  @Autowired
  TransferService transferService;

  public void transfer(TransactionsModel transactionsModel) {
    BigDecimal Current_Balance = this.currentBalanceService.getCurrentBalanceByAccountNumber(transactionsModel.getSource());
    transferService.transfer(transactionsModel.getSource(), transactionsModel, Current_Balance);
  }
}
