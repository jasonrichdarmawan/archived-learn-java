package com.example.bankaccount.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionsModel {
  private LocalDate Date;
  private String Source;
  private String Destination;
  private int Destination_Type;
  private BigDecimal Transaction_Value;

  public TransactionsModel(String destination, int destination_Type, BigDecimal transaction_Value) {
    Destination = destination;
    Destination_Type = destination_Type;
    Transaction_Value = transaction_Value;
  }

  public TransactionsModel(@JsonProperty("Date") LocalDate date, @JsonProperty("Source") String source, @JsonProperty("Destination") String destination, @JsonProperty("Destination_Type") int destination_type, @JsonProperty("Transaction_Value") BigDecimal transaction_value) {
    Date = date;
    Source = source;
    Destination = destination;
    Destination_Type = destination_type;
    Transaction_Value = transaction_value;
  }

  public LocalDate getDate() {
    return Date;
  }

  public void setDate() {
    Date = LocalDate.now();
  }

  public String getSource() {
    return Source;
  }

  public void setSource(String source) {
    Source = source;
  }

  public String getDestination() {
    return Destination;
  }

  public int getDestination_Type() {
    return Destination_Type;
  }

  public BigDecimal getTransaction_Value() {
    return Transaction_Value;
  }
}
