package com.example.bankaccount.repository;

import com.example.bankaccount.dao.TransactionsDAO;
import com.example.bankaccount.model.TransactionsModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public class TransactionsImpl implements TransactionsDAO {
  private SqlSession sqlSession;

  @Autowired
  StatementsImpl statements;

  public TransactionsImpl() {
    Reader reader = null;
    try {
      reader = Resources.getResourceAsReader("mybatis-config.xml");
    } catch (IOException e) {
      e.printStackTrace();
    }
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    sqlSession = sqlSessionFactory.openSession();
  }

  @Override
  public void createTableIfNotExists() {
    sqlSession.update("Transactions.createTableIfNotExists");
    sqlSession.commit();
  }

  @Override
  public int insert(TransactionsModel transactionsModel, String account_number) {
    LocalDate now = LocalDate.now();
    int previousMonth = now.getMonthValue() - 1;
    int year = now.getYear();
    if (previousMonth == 0) {
      previousMonth = 12;
      year -= 1;
    }

    BigDecimal Opening_Balance = statements.selectByMonthAndYear(account_number, previousMonth, year);
    Opening_Balance = Opening_Balance == null ? new BigDecimal(0) : Opening_Balance;

    LocalDate Start = now.withDayOfMonth(1);
    LocalDate End = now.withDayOfMonth(now.lengthOfMonth());

    /**
     * TODO: refactor to one select sql statement, identify whether it is a debit / credit.
     * No shortcut, creating a redudant column e.g Transaction_Type.
     */
    List<TransactionsModel> credit = selectCreditByStartAndEndDate(account_number, Start, End);
    Iterator<TransactionsModel> iterator = credit.iterator();
    while (iterator.hasNext()) {
      TransactionsModel transaction = iterator.next();
      Opening_Balance = Opening_Balance.add(transaction.getTransaction_Value());
    }

    List<TransactionsModel> debit = selectDebitByStartAndEndDate(account_number, Start, End);
    iterator = debit.iterator();
    while (iterator.hasNext()) {
      TransactionsModel transaction = iterator.next();
      Opening_Balance = Opening_Balance.subtract(transaction.getTransaction_Value());
    }

    if (Opening_Balance.compareTo(transactionsModel.getTransaction_Value()) > 0) {
      transactionsModel.setDate();
      transactionsModel.setSource(account_number);
      int rowsAffected = sqlSession.insert("Transactions.insert", transactionsModel);
      sqlSession.commit();

      return rowsAffected;
    } else {
      return 0;
    }
  }

  @Override
  public List<TransactionsModel> selectByStartAndEndDate(String account_number, LocalDate start, LocalDate end) {
    Map<String, Object> params = new HashMap<>();
    params.put("Account_Number", account_number);
    params.put("Start", start);
    params.put("End", end);

    List<TransactionsModel> transactions = sqlSession.selectList("Transactions.selectByStartAndEndDate", params);
    sqlSession.commit();
    return transactions;
  }

  @Override
  public List<TransactionsModel> selectDebitByStartAndEndDate(String source, LocalDate start, LocalDate end) {
    Map<String, Object> params = new HashMap<>();
    params.put("Source", source);
    params.put("Start", start);
    params.put("End", end);

    List<TransactionsModel> transactions = sqlSession.selectList("Transactions.selectDebitByStartAndEndDate", params);
    sqlSession.commit();
    return transactions;
  }

  @Override
  public List<TransactionsModel> selectCreditByStartAndEndDate(String destination, LocalDate start, LocalDate end) {
    Map<String, Object> params = new HashMap<>();
    params.put("Destination", destination);
    params.put("Start", start);
    params.put("End", end);

    List<TransactionsModel> transactions = sqlSession.selectList("Transactions.selectCreditByStartAndEndDate", params);
    sqlSession.commit();
    return transactions;
  }
}
