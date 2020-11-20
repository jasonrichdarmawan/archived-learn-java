package com.example.bankaccount.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Admin_TransactionsModel {
  private LocalDate Date;
  private String User_ID;
  @Pattern(regexp = "[0-9]{17}", message = "Destination length must be 17")
  private String Destination;
  @Pattern(regexp = "[0-9]{1,}", message = "Transaction_Value must not be null")
  private BigDecimal Transaction_Value;

  public Admin_TransactionsModel(@JsonProperty("Destination") String destination, @JsonProperty("Transaction_Value") BigDecimal transaction_value) {
    Destination = destination;
    Transaction_Value = transaction_value;
  }

  public LocalDate getDate() {
    return Date;
  }

  public void setDate() {
    Date = LocalDate.now();
  }

  public String getUser_ID() {
    return User_ID;
  }

  public void setUser_ID(String source) {
    User_ID = source;
  }

  public String getDestination() {
    return Destination;
  }

  public BigDecimal getTransaction_Value() {
    return Transaction_Value;
  }
}
