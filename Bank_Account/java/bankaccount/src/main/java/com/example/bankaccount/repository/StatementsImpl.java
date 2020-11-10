package com.example.bankaccount.repository;

import com.example.bankaccount.dao.StatementsDAO;
import com.example.bankaccount.model.StatementsModel;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Repository
public class StatementsImpl implements StatementsDAO {
  @Autowired
  private MyBatis myBatis;

  private SqlSession sqlSession;

  @Override
  public void createTableIfNotExists() {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    sqlSession.update("Statements.createTableIfNotExists");
    sqlSession.commit();

    sqlSession.close();
  }

  @Override
  public int insert(StatementsModel statementsModel) {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    int rowsAffected = 0;
    // the table is hard coded to be unique, so in case of a failure, it will throws an error.
    try {
      rowsAffected = sqlSession.insert("Statements.insert", statementsModel);
      sqlSession.commit();
    } catch (Exception e) {
      rowsAffected = 0;
    }

    sqlSession.close();

    return rowsAffected;
  }

  @Override
  public BigDecimal selectOpeningBalanceByAccountNumber(String account_number) {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    LocalDate now = LocalDate.now();
    int previousMonth = now.getMonthValue() - 1;
    int year = now.getYear();
    if (previousMonth == 0) {
      previousMonth = 12;
      year -= 1;
    }

    Map<String, Object> params = new HashMap<>();
    params.put("Account_Number", account_number);
    params.put("Month", previousMonth);
    params.put("Year", year);

    BigDecimal Ending_Balance = sqlSession.selectOne("Statements.selectByMonthAndYear", params);

    sqlSession.close();

    return Ending_Balance == null ? new BigDecimal(0) : Ending_Balance;
  }
}
