package com.example.bankaccount.repository;

import com.example.bankaccount.model.Transactions_ProgressModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

@Repository
public class Transactions_ProgressImpl {
  @Autowired
  private MyBatis myBatis;

  private SqlSession sqlSession;

  public void createTableIfNotExists() {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    sqlSession.update("Transactions_Progress.createTableIfNotExists");
    sqlSession.commit();

    sqlSession.close();
  }

  public Transactions_ProgressModel insert(Transactions_ProgressModel transactions_progressModel) {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    sqlSession.insert("Transactions_Progress.insert", transactions_progressModel);
    sqlSession.commit();

    sqlSession.close();

    return transactions_progressModel;
  }

  public Transactions_ProgressModel selectByProgress_IDAndAccount_Number(String progress_id, String account_number) {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    Map<String, Object> params = new HashMap<>();
    params.put("Progress_ID", progress_id);
    params.put("Account_Number", account_number);

    Transactions_ProgressModel transactions_progressModel = sqlSession.selectOne("Transactions_Progress.selectByProgress_IDAndAccount_Number", params);

    sqlSession.close();

    return transactions_progressModel;
  }

  public int updateByProgress_ID(Transactions_ProgressModel transactions_progressModel) {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    int rowsAffected = sqlSession.update("Transactions_Progress.updateByProgress_ID", transactions_progressModel);
    sqlSession.commit();

    sqlSession.close();

    return rowsAffected;
  }
}
