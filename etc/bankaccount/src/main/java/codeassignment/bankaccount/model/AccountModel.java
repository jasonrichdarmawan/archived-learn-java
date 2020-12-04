package codeassignment.bankaccount.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class AccountModel {
  private String account_number;
  private String customer_number;
  private BigDecimal balance;

  public String getAccount_number() {
    return account_number;
  }

  public String getCustomer_number() {
    return customer_number;
  }

  public BigDecimal getBalance() {
    return balance;
  }
}
