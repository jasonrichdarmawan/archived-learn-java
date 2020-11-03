package com.example.bankaccount.model;

import java.math.BigDecimal;

public class StatementsModel {
  private String Account_Number;
  private int Month;
  private int Year;
  private BigDecimal Ending_Balance;

  public StatementsModel(String account_number, int month, int year, BigDecimal ending_balance) {
    Account_Number = account_number;
    Month = month;
    Year = year;
    Ending_Balance = ending_balance;
  }

  public String getAccount_Number() {
    return Account_Number;
  }

  public int getMonth() {
    return Month;
  }

  public int getYear() {
    return Year;
  }

  public BigDecimal getEnding_Balance() {
    return Ending_Balance;
  }
}
