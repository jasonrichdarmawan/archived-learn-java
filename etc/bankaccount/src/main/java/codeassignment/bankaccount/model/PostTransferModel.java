package codeassignment.bankaccount.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class PostTransferModel {
  private String to_account_number;
  private BigDecimal amount;

  @NotNull(message = "to_account_number must not be null")
  @Pattern(regexp = "[0-9]+", message = "to_account_number must only contain number")
  public String getTo_account_number() {
    return to_account_number;
  }

  @NotNull(message = "amount must not be null")
  @DecimalMin(value = "0.0", inclusive = false, message = "amount must be greater than 0.0")
  @Digits(integer = 11, fraction = 0, message = "amount maximum digits is 11 and no decimal")
  public BigDecimal getAmount() {
    return amount;
  }
}
