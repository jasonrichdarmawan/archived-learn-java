package com.example.bankaccount.dao;

import com.example.bankaccount.model.User_DetailModel;

public interface User_DetailDAO {
  void createTableIfNotExists();

  int insert(User_DetailModel user_detailModel);
  User_DetailModel select(String Account_Number);
}
