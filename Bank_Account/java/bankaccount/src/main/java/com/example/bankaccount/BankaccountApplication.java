package com.example.bankaccount;

import com.example.bankaccount.repository.*;
import com.example.bankaccount.repository.admin.Admin_LoginImpl;
import com.example.bankaccount.repository.admin.Admin_TransactionsImpl;
import com.example.bankaccount.service.CreateStatementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

  @Autowired
  Admin_LoginImpl admin_login;

  @Autowired
  Admin_TransactionsImpl admin_transactions;

  @Override
  public void run(String... args) throws Exception {
    this.user_login.createTableIfNotExists();
    this.user_info.createTableIfNotExists();
    this.user_detail.createTableIfNotExists();
    this.transactions.createTableIfNotExists();
    this.statements.createTableIfNotExists();

    this.admin_login.createTableIfNotExists();
    this.admin_transactions.createTableIfNotExists();

    this.createStatementsService.createPreviousMonthStatements();
  }
}
