package codeassignment.bankaccount.controller;

import codeassignment.bankaccount.mapper.AccountMapper;
import codeassignment.bankaccount.mapper.CustomerMapper;
import codeassignment.bankaccount.model.AccountModel;
import codeassignment.bankaccount.model.GetCurrentBalanceModel;
import codeassignment.bankaccount.model.PostTransferModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@RestController
@Validated
@RequestMapping("/account")
public class AccountController {

  @Autowired
  AccountMapper accountMapper;

  @Autowired
  CustomerMapper customerMapper;

  @GetMapping("/{account_number}")
  public ResponseEntity<Object> getCurrentBalance(@PathVariable("account_number") @Pattern(regexp = "[0-9]+", message = "uri /account/{account_number}, {account_number} must only contain number") String accountNumber) {
    AccountModel model = accountMapper.getCustomerNumberAndBalance(accountNumber);

    if (model == null) {
      return ResponseEntity.notFound().build();
    } else {
      String name = customerMapper.getName(model.getCustomer_number());

      if (name == null) {
        return ResponseEntity.notFound().build();
      } else {
        GetCurrentBalanceModel responseBody = new GetCurrentBalanceModel(accountNumber, name, model.getBalance());
        return ResponseEntity.ok().body(responseBody);
      }
    }
  }

  @PostMapping("/{from_account_number}/transfer")
  public ResponseEntity<Object> postTransfer(@PathVariable("from_account_number") @Pattern(regexp = "[0-9]+", message = "uri /account/{from_account_number}/transfer, {from_account_number} must only contain number") String fromAccountNumber, @Valid @RequestBody PostTransferModel requestBody) {
    if (fromAccountNumber.equals(requestBody.getTo_account_number())) {
      return ResponseEntity.badRequest().body("from_account_number must not be equal to to_account_number");
    } else {
      if (!accountMapper.exists(requestBody.getTo_account_number())) {
        return ResponseEntity.notFound().build();
      } else {
        BigDecimal currentBalance = accountMapper.getBalance(fromAccountNumber);

        if (currentBalance == null) {
          return ResponseEntity.notFound().build();
        } else if (currentBalance.compareTo(requestBody.getAmount()) < 0) {
          return ResponseEntity.status(402).body("Insufficient balance");
        } else {

          if (accountMapper.updateBalance(currentBalance.subtract(requestBody.getAmount()), fromAccountNumber) != 1) {
            return ResponseEntity.status(500).body("Update failed");
          } else {
            if (accountMapper.addBalance(requestBody) != 1) {
              return ResponseEntity.status(500).body("Update failed");
            } else {
              return new ResponseEntity<>("", HttpStatus.CREATED);
            }
          }
        }
      }
    }
  }
}
