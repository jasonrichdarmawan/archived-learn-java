package com.example.bankaccount.dao;

import com.example.bankaccount.model.User_InfoModel;
import java.util.List;

public interface User_InfoDAO {
  void createTableIfNotExists();

  User_InfoModel insert(User_InfoModel user_infoModel);
  User_InfoModel selectByUser_ID(String User_ID);
  User_InfoModel selectByAccount_Number(String Account_Number);
  List<String> getAccountNumbers();
}
