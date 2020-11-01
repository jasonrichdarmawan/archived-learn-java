package com.example.bankaccount.dao;

import com.example.bankaccount.model.User_LoginModel;

public interface User_LoginDAO {
  void createTableIfNotExists();

  int insert(User_LoginModel user_loginModel);
  boolean login(User_LoginModel user_loginModel);
}
