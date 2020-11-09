package com.example.bankaccount.RabbitMQ;

import com.example.bankaccount.model.TransactionsModel;
import com.example.bankaccount.model.Transactions_ProgressModel;
import com.example.bankaccount.repository.Transactions_ProgressImpl;
import com.example.bankaccount.service.CurrentBalanceService;
import com.example.bankaccount.service.TransferServicev2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionsReceiver {

  @Autowired
  CurrentBalanceService currentBalanceService;

  @Autowired
  TransferServicev2 transferServicev2;

  @Autowired
  Transactions_ProgressImpl transactions_progress;

  @Autowired
  Sender sender;

  public void transfer(TransactionsModel transactionsModel) {
    String Message_Code = this.transferServicev2.transfer(transactionsModel.getSource(), transactionsModel, this.currentBalanceService.getCurrentBalanceByAccountNumber(transactionsModel.getSource()));

    Transactions_ProgressModel transactions_progressModel = new Transactions_ProgressModel();
    transactions_progressModel.setMessage_Code(Message_Code);
    transactions_progressModel.setProgress_ID(transactionsModel.getProgress_ID());
    transactions_progress.updateByProgress_ID(transactions_progressModel);
  }
}
