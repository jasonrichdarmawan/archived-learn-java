package com.example.bankaccount.repository.admin;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;

@Repository
public class Admin_LoginImpl {
  private SqlSession sqlSession;

  public Admin_LoginImpl() {
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
    sqlSession.update("Admin_Login.createTableIfNotExists");
    sqlSession.commit();
  }

  public boolean exist(String User_ID) {
    boolean isExist = sqlSession.selectOne("Admin_Login.exist", User_ID);
    sqlSession.commit();
    return isExist;
  }
}
