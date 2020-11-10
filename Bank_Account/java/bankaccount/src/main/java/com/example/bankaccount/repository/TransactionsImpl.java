package com.example.bankaccount.repository;

import com.example.bankaccount.dao.TransactionsDAO;
import com.example.bankaccount.model.TransactionsModel;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TransactionsImpl implements TransactionsDAO {
  @Autowired
  private MyBatis myBatis;

  private SqlSession sqlSession;

  @Override
  public void createTableIfNotExists() {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    sqlSession.update("Transactions.createTableIfNotExists");
    sqlSession.commit();

    sqlSession.close();
  }

  @Override
  public int insert(TransactionsModel transactionsModel, BigDecimal current_balance, String account_number) {
    if (current_balance.compareTo(transactionsModel.getTransaction_Value()) >= 0 && transactionsModel.getTransaction_Value().compareTo(BigDecimal.ZERO) > 0) {
      transactionsModel.setDate();
      transactionsModel.setSource(account_number);

      sqlSession = myBatis.getSqlSessionFactory().openSession();

      int rowsAffected = sqlSession.insert("Transactions.insert", transactionsModel);
      sqlSession.commit();

      return rowsAffected;
    } else {
      return 0;
    }
  }

  @Override
  public List<TransactionsModel> selectByStartAndEndDate(String account_number, LocalDate start, LocalDate end) {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    Map<String, Object> params = new HashMap<>();
    params.put("Account_Number", account_number);
    params.put("Start", start);
    params.put("End", end);

    List<TransactionsModel> transactions = sqlSession.selectList("Transactions.selectByStartAndEndDate", params);

    sqlSession.close();

    return transactions;
  }

  @Override
  public List<TransactionsModel> selectDebitByStartAndEndDate(String source, LocalDate start, LocalDate end) {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    Map<String, Object> params = new HashMap<>();
    params.put("Source", source);
    params.put("Start", start);
    params.put("End", end);

    List<TransactionsModel> transactions = sqlSession.selectList("Transactions.selectDebitByStartAndEndDate", params);

    sqlSession.close();

    return transactions;
  }

  @Override
  public List<TransactionsModel> selectCreditByStartAndEndDate(String destination, LocalDate start, LocalDate end) {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    Map<String, Object> params = new HashMap<>();
    params.put("Destination", destination);
    params.put("Start", start);
    params.put("End", end);

    List<TransactionsModel> transactions = sqlSession.selectList("Transactions.selectCreditByStartAndEndDate", params);

    sqlSession.close();

    return transactions;
  }
}
