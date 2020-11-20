package com.example.bankaccount.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionsModel implements Serializable {
  private LocalDate Date;
  private String Source;
  @Pattern(regexp = "[0-9]{17}", message = "Destination length must be 17")
  private String Destination;
  @Range(min = 1, max = 10, message = "Destination_Type is unknown")
  private int Destination_Type;
  @NotNull(message = "Transaction_Value must not be null")
  @DecimalMin(value = "1.0", message = "Transaction_Value must not be null")
  private BigDecimal Transaction_Value;
  private String Progress_ID; // this attribute is for TransactionsControllerv2, this is not stored in transactions table.

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

  public String getProgress_ID() {
    return Progress_ID;
  }

  public void setProgress_ID(String progress_ID) {
    Progress_ID = progress_ID;
  }
}
