package codeassignment.bankaccount.controller;

import codeassignment.bankaccount.mapper.AccountMapper;
import codeassignment.bankaccount.mapper.CustomerMapper;
import codeassignment.bankaccount.model.AccountModel;
import codeassignment.bankaccount.model.GetCurrentBalanceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

  @Autowired
  AccountMapper accountMapper;

  @Autowired
  CustomerMapper customerMapper;

  @GetMapping("/{account_number}")
  public ResponseEntity<Object> getCurrentBalance(@PathVariable("account_number") String accountNumber) {
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
}
