package com.example.bankaccount.repository;

import com.example.bankaccount.dao.User_InfoDAO;
import com.example.bankaccount.model.User_InfoModel;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class User_InfoImpl implements User_InfoDAO {
  @Autowired
  private MyBatis myBatis;

  private SqlSession sqlSession;

  @Override
  public void createTableIfNotExists() {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    sqlSession.update("User_Info.createTableIfNotExists");
    sqlSession.commit();

    sqlSession.close();
  }

  @Override
  public User_InfoModel insert(User_InfoModel user_infoModel) {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    sqlSession.insert("User_Info.insertByUser_ID", user_infoModel);
    sqlSession.commit();

    sqlSession.close();

    return user_infoModel;
  }

  @Override
  public User_InfoModel selectByUser_ID(String User_ID) {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    User_InfoModel user_infoModel = sqlSession.selectOne("User_Info.selectByUser_ID", User_ID);

    sqlSession.close();

    return user_infoModel;
  }

  @Override
  public User_InfoModel selectByAccount_Number(String Account_Number) {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    User_InfoModel user_infoModel = sqlSession.selectOne("User_Info.selectByAccount_Number", Account_Number);

    sqlSession.close();

    return user_infoModel;
  }

  @Override
  public boolean isAccount_NumberExists(String Account_Number) {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    boolean response = sqlSession.selectOne("User_Info.isAccount_NumberExists", Account_Number);

    sqlSession.close();

    return response;
  }

  @Override
  public List<String> getAccountNumbers() {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    List<String> Account_Numbers = sqlSession.selectList("User_Info.getAccountNumbers");

    sqlSession.close();
    
    return Account_Numbers;
  }
}
