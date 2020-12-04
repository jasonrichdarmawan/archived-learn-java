package codeassignment.bankaccount.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

  @GetMapping("/{account_number}")
  public ResponseEntity<Object> getCurrentBalance(@PathVariable("account_number") String accountNumber) {
    return ResponseEntity.ok().body(accountNumber);
  }
}
