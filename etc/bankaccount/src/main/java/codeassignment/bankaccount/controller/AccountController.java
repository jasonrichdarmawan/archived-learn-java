package codeassignment.bankaccount.controller;

import codeassignment.bankaccount.mapper.AccountMapper;
import codeassignment.bankaccount.mapper.CustomerMapper;
import codeassignment.bankaccount.model.AccountModel;
import codeassignment.bankaccount.model.GetCurrentBalanceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;

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
    AccountModel model = accountMapper.getCurrentBalance(accountNumber);

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
  public ResponseEntity<Object> postTransfer(@PathVariable("from_account_number") @Pattern(regexp = "[0-9]+", message = "uri /account/{from_account_number}/transfer, {from_account_number} must only contain number") String fromAccountNumber) {
    return new ResponseEntity<>(fromAccountNumber, HttpStatus.CREATED);
  }
}
