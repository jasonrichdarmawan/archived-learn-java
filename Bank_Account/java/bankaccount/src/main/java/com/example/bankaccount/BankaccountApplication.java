package com.example.bankaccount;

import com.example.bankaccount.repository.*;
import com.example.bankaccount.service.CreateStatementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class BankaccountApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(BankaccountApplication.class, args);
  }

  @Autowired
  User_LoginImpl user_login;

  @Autowired
  User_InfoImpl user_info;

  @Autowired
  User_DetailImpl user_detail;

  @Autowired
  TransactionsImpl transactions;

  @Autowired
  StatementsImpl statements;

  @Autowired
  CreateStatementsService createStatementsService;

  @Override
  public void run(String... args) throws Exception {
    this.user_login.createTableIfNotExists();
    this.user_info.createTableIfNotExists();
    this.user_detail.createTableIfNotExists();
    this.transactions.createTableIfNotExists();
    this.statements.createTableIfNotExists();

    this.createStatementsService.createPreviousMonthStatements();
  }
}
