package com.example.bankaccount.repository;

import com.example.bankaccount.dao.User_InfoDAO;
import com.example.bankaccount.model.User_InfoModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Repository
public class User_InfoImpl implements User_InfoDAO {
  private SqlSession sqlSession;

  public User_InfoImpl() {
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
    sqlSession.update("User_Info.createTableIfNotExists");
    sqlSession.commit();
  }

  @Override
  public User_InfoModel insert(User_InfoModel user_infoModel) {
    sqlSession.insert("User_Info.insertByUser_ID", user_infoModel);
    sqlSession.commit();
    return user_infoModel;
  }

  @Override
  public User_InfoModel selectByUser_ID(String User_ID) {
    User_InfoModel user_infoModel = sqlSession.selectOne("User_Info.selectByUser_ID", User_ID);
    sqlSession.commit();
    return user_infoModel;
  }

  @Override
  public User_InfoModel selectByAccount_Number(String Account_Number) {
    User_InfoModel user_infoModel = sqlSession.selectOne("User_Info.selectByAccount_Number", Account_Number);
    sqlSession.commit();
    return user_infoModel;
  }

  @Override
  public List<String> getAccountNumbers() {
    List<String> Account_Numbers = sqlSession.selectList("User_Info.getAccountNumbers");
    sqlSession.commit();
    return Account_Numbers;
  }
}
