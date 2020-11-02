package com.example.bankaccount.dao;

import com.example.bankaccount.model.TransactionsModel;

import java.time.LocalDate;
import java.util.List;

public interface TransactionsDAO {
  void createTableIfNotExists();

  int insert(TransactionsModel transactionsModel, String account_number);

  List<TransactionsModel> selectByStartAndEndDate(String account_nubmer, LocalDate start, LocalDate end);

  List<TransactionsModel> selectDebitByStartAndEndDate(String account_number, LocalDate start, LocalDate End);

  List<TransactionsModel> selectCreditByStartAndEndDate(String source, LocalDate start, LocalDate end);
}
