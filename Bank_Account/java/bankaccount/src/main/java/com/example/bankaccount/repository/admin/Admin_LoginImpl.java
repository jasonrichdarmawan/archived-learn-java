package com.example.bankaccount.repository.admin;

import com.example.bankaccount.repository.MyBatis;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Admin_LoginImpl {

  @Autowired
  private MyBatis myBatis;

  private SqlSession sqlSession;

  public void createTableIfNotExists() {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    sqlSession.update("Admin_Login.createTableIfNotExists");
    sqlSession.commit();

    sqlSession.close();
  }

  public boolean exist(String User_ID) {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    boolean isExist = sqlSession.selectOne("Admin_Login.exist", User_ID);
    sqlSession.close();

    return isExist;
  }
}
