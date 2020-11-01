package com.example.bankaccount.dao;

import com.example.bankaccount.model.User_InfoModel;

public interface User_InfoDAO {
  void createTableIfNotExists();

  User_InfoModel insert(User_InfoModel user_infoModel);
  User_InfoModel select(String User_ID);
}
