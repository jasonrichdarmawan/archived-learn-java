package codeassignment.bankaccount.model;

import java.math.BigDecimal;

public class GetCurrentBalanceModel {
  private String account_number;
  private String customer_name;
  private BigDecimal balance;

  public GetCurrentBalanceModel(String account_number, String customer_name, BigDecimal balance) {
    this.account_number = account_number;
    this.customer_name = customer_name;
    this.balance = balance;
  }

  public String getAccount_number() {
    return account_number;
  }

  public String getCustomer_name() {
    return customer_name;
  }

  public BigDecimal getBalance() {
    return balance;
  }
}
