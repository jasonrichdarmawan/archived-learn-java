package com.example.bankaccount.repository;

import com.example.bankaccount.dao.TransactionsDAO;
import com.example.bankaccount.model.TransactionsModel;
import com.example.bankaccount.service.CurrentBalanceService;
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
import java.util.List;
import java.util.Map;

@Repository
public class TransactionsImpl implements TransactionsDAO {
  private SqlSession sqlSession;

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
  public int insert(TransactionsModel transactionsModel, BigDecimal current_balance, String account_number) {
    if (current_balance.compareTo(transactionsModel.getTransaction_Value()) >= 0 && transactionsModel.getTransaction_Value().compareTo(BigDecimal.ZERO) > 0) {
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
