package com.example.bankaccount.service;

import com.example.bankaccount.model.StatementsModel;
import com.example.bankaccount.repository.StatementsImpl;
import com.example.bankaccount.repository.User_InfoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class CreateStatementsService {

  @Autowired
  StatementsImpl statements;

  @Autowired
  User_InfoImpl user_info;

  @Autowired
  CurrentBalanceService currentBalanceService;

  public void createPreviousMonthStatements() {
    LocalDate now = LocalDate.now();

    if (now.getDayOfMonth() == 1) {
      List<String> account_numbers = user_info.getAccountNumbers();

      int previousMonth = now.getMonthValue() - 1;
      int year = now.getYear();
      if (previousMonth == 0) {
        previousMonth = 12;
        year -= 1;
      }

      for (String Account_Number : account_numbers) {
        BigDecimal Ending_Balance = currentBalanceService.getCurrentBalanceByAccountNumber(Account_Number);
        statements.insert(new StatementsModel(Account_Number, previousMonth, year, Ending_Balance));
      }
    }
  }
}
