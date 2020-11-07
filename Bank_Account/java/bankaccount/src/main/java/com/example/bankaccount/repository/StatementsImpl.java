package com.example.bankaccount.repository;

import com.example.bankaccount.dao.StatementsDAO;
import com.example.bankaccount.model.StatementsModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Repository
public class StatementsImpl implements StatementsDAO {
  private SqlSession sqlSession;

  public StatementsImpl() {
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
    sqlSession.update("Statements.createTableIfNotExists");
    sqlSession.commit();
  }

  @Override
  public int insert(StatementsModel statementsModel) {
    int rowsAffected = 0;
    // the table is hard coded to be unique, so in case of a failure, it will throws an error.
    try {
      rowsAffected = sqlSession.insert("Statements.insert", statementsModel);
    } catch (Exception e) {
      rowsAffected = 0;
    }
    sqlSession.commit();
    return rowsAffected;
  }

  @Override
  public BigDecimal selectOpeningBalanceByAccountNumber(String account_number) {
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
    sqlSession.commit();
    return Ending_Balance == null ? new BigDecimal(0) : Ending_Balance;
  }
}
