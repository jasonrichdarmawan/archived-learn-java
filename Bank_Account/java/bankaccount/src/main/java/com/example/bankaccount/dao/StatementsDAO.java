package com.example.bankaccount.dao;

import com.example.bankaccount.model.StatementsModel;

import java.math.BigDecimal;

public interface StatementsDAO {
  void createTableIfNotExists();

  int insert(StatementsModel statementsModel);

  BigDecimal selectByMonthAndYear(String account_number, int previousmonth, int year);
}
