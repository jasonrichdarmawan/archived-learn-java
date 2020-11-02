package com.example.bankaccount.dao;

import com.example.bankaccount.model.TransactionsModel;

import java.time.LocalDate;
import java.util.List;

public interface Transactions_DAO {
  void createTableIfNotExists();

  int insert(TransactionsModel transactionsModel);
  List<TransactionsModel> selectByStartEndDate(String source, LocalDate start, LocalDate End);
}
