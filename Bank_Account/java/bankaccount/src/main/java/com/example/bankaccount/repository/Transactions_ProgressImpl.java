package com.example.bankaccount.repository;

import com.example.bankaccount.model.Transactions_ProgressModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

@Repository
public class Transactions_ProgressImpl {
  private SqlSession sqlSession;

  public Transactions_ProgressImpl() {
    Reader reader = null;
    try {
      reader = Resources.getResourceAsReader("mybatis-config.xml");
    } catch (IOException e) {
      e.printStackTrace();
    }
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    sqlSession = sqlSessionFactory.openSession();
  }

  public void createTableIfNotExists() {
    sqlSession.update("Transactions.createTableIfNotExists");
    sqlSession.commit();
  }

  public Transactions_ProgressModel insert(Transactions_ProgressModel transactions_progressModel) {
    sqlSession.insert("Transactions_Progress.insert", transactions_progressModel);
    sqlSession.commit();
    return transactions_progressModel;
  }

  public Transactions_ProgressModel selectByProgress_IDAndAccount_Number(String progress_id, String account_number) {
    Map<String, Object> params = new HashMap<>();
    params.put("Progress_ID", progress_id);
    params.put("Account_Number", account_number);

    Transactions_ProgressModel transactions_progressModel = sqlSession.selectOne("Transactions_Progress.selectByProgress_IDAndAccount_Number", params);
    sqlSession.commit();
    return transactions_progressModel;
  }

  public int updateByProgress_ID(Transactions_ProgressModel transactions_progressModel) {
    int rowsAffected = sqlSession.update("Transactions_Progress.updateByProgress_ID", transactions_progressModel);
    sqlSession.commit();
    return rowsAffected;
  }
}
