package com.example.bankaccount.service;

import com.example.bankaccount.model.TransactionsModel;
import com.example.bankaccount.repository.StatementsImpl;
import com.example.bankaccount.repository.TransactionsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

@Service
public class CurrentBalanceService {

  @Autowired
  StatementsImpl statements;

  @Autowired
  TransactionsImpl transactions;

  public BigDecimal getCurrentBalanceByAccountNumber(String account_number) {
    BigDecimal Balance = statements.selectOpeningBalanceByAccountNumber(account_number);

    LocalDate now = LocalDate.now();
    LocalDate Start = now.withDayOfMonth(1);
    LocalDate End = now.withDayOfMonth(now.lengthOfMonth());

    /**
     * TODO: refactor to one select sql statement, identify whether it is a debit / credit.
     * No shortcut, creating a redudant column e.g Transaction_Type.
     */
    List<TransactionsModel> credit = transactions.selectCreditByStartAndEndDate(account_number, Start, End);
    Iterator<TransactionsModel> iterator = credit.iterator();
    while (iterator.hasNext()) {
      TransactionsModel transaction = iterator.next();
      Balance = Balance.add(transaction.getTransaction_Value());
    }

    List<TransactionsModel> debit = transactions.selectDebitByStartAndEndDate(account_number, Start, End);
    iterator = debit.iterator();
    while (iterator.hasNext()) {
      TransactionsModel transaction = iterator.next();
      Balance = Balance.subtract(transaction.getTransaction_Value());
    }

    return Balance == null ? new BigDecimal(0) : Balance;
  }
}
