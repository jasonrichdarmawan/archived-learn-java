package com.example.bankaccount.repository;

import com.example.bankaccount.dao.Transactions_DAO;
import com.example.bankaccount.model.TransactionsModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TransactionsImpl implements Transactions_DAO {
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
  public int insert(TransactionsModel transactionsModel) {
    return 0;
  }

  @Override
  public List<TransactionsModel> selectByStartEndDate(String source, LocalDate start, LocalDate end) {
    Map<String, Object> params = new HashMap<>();
    params.put("Source", source);
    params.put("Start", start);
    params.put("End", end);

    List<TransactionsModel> transactions = sqlSession.selectList("Transactions.selectByStartAndEndDate", params);
    sqlSession.commit();
    return transactions;
  }
}
