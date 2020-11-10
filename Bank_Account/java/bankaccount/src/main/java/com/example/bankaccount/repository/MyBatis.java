package com.example.bankaccount.repository;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;

@Repository
public class MyBatis {
  private final SqlSessionFactory sqlSessionFactory;

  public MyBatis() {
    Reader reader = null;
    try {
      reader = Resources.getResourceAsReader("mybatis-config.xml");
    } catch (IOException e) {
      e.printStackTrace();
    }
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
  }

  public SqlSessionFactory getSqlSessionFactory() {
    return sqlSessionFactory;
  }
}
