package com.example.bankaccount.repository;

import com.example.bankaccount.dao.User_LoginDAO;
import com.example.bankaccount.model.User_LoginModel;
import com.example.bankaccount.service.PinHashingService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;

@Repository
public class User_LoginImpl implements User_LoginDAO {
  private SqlSession sqlSession;
  private PinHashingService pinHashingService;

  public User_LoginImpl() {
    Reader reader = null;
    try {
      reader = Resources.getResourceAsReader("mybatis-config.xml");
    } catch (IOException e) {
      e.printStackTrace();
    }
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    sqlSession = sqlSessionFactory.openSession();

    pinHashingService = new PinHashingService();
  }

  @Override
  public void createTableIfNotExists() {
    sqlSession.update("User_Login.createTableIfNotExists");
    sqlSession.commit();
  }

  @Override
  public int insert(User_LoginModel user_loginModel) {
    int rowsAffected = sqlSession.insert("User_Login.insert", user_loginModel);
    sqlSession.commit();
    return rowsAffected;
  }

  @Override
  public boolean login(User_LoginModel user_loginModel) {
    String Hashed_PIN = sqlSession.selectOne("User_Login.login", user_loginModel);
    if (Hashed_PIN != null) {
      return pinHashingService.validate(user_loginModel.getPIN(), Hashed_PIN);
    } else {
      return false;
    }
  }

  public boolean exist(String User_ID) {
    return sqlSession.selectOne("User_Login.exist", User_ID);
  }
}
