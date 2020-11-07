package com.example.bankaccount.service;

import com.example.bankaccount.repository.User_LoginImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class GenerateUser_IDService {
  @Autowired
  User_LoginImpl user_login;

  /**
   * Do final check before inserting new user. This method can return used User_ID.
   * @param Full_Name
   * @param Birth_Date
   * @return String User_ID
   */
  public String generate(String Full_Name, LocalDate Birth_Date) {
    Full_Name = Full_Name.toLowerCase().replaceAll("[\\s\\W]", "");

    // The first eight letters + yyMM from Birth Date
    StringBuilder User_ID = new StringBuilder(Full_Name.substring(0, 8) + DateTimeFormatter.ofPattern("ddMM").format(Birth_Date));

    // brute-force by iteration to find available User_ID (1. jasonric3103 -> ... -> 3. jasonriz3103)
    int index = 7;
    char ch = 'a';
    while (this.user_login.exist(User_ID.toString())) {
      if (ch > 'z') {
        ch = 'a';
        --index;
      }
      User_ID.setCharAt(index, ch);
      ++ch;
    }

    return User_ID.toString();
  }
}
