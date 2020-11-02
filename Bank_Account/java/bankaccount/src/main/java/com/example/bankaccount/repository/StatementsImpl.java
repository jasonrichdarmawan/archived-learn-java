package com.example.bankaccount.repository;

import com.example.bankaccount.dao.StatementsDAO;
import com.example.bankaccount.model.StatementsModel;
import com.example.bankaccount.model.TransactionsModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
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
    return 0;
  }

  @Override
  public BigDecimal selectByMonthAndYear(String account_number, int previousmonth, int year) {
    Map<String, Object> params = new HashMap<>();
    params.put("Account_Number", account_number);
    params.put("Month", previousmonth);
    params.put("Year", year);

    BigDecimal Ending_Balance = sqlSession.selectOne("Statements.selectByMonthAndYear", params);
    sqlSession.commit();
    return Ending_Balance;
  }
}