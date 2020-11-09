package com.example.bankaccount.model;

public class Transactions_ProgressModel {
  private String Progress_ID;
  private String Account_Number;
  private String Message_Code;

  public String getProgress_ID() {
    return Progress_ID;
  }

  public void setProgress_ID(String progress_ID) {
    Progress_ID = progress_ID;
  }

  public String getAccount_Number() {
    return Account_Number;
  }

  public void setAccount_Number(String account_Number) {
    Account_Number = account_Number;
  }

  public String getMessage_Code() {
    return Message_Code;
  }

  public void setMessage_Code(String message_Code) {
    Message_Code = message_Code;
  }
}
