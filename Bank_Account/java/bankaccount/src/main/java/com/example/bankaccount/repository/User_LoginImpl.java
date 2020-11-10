package com.example.bankaccount.repository;

import com.example.bankaccount.dao.User_LoginDAO;
import com.example.bankaccount.model.User_LoginModel;
import com.example.bankaccount.service.PinHashingService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class User_LoginImpl implements User_LoginDAO {
  @Autowired
  private MyBatis myBatis;

  private SqlSession sqlSession;
  private PinHashingService pinHashingService;

  public User_LoginImpl() {
    pinHashingService = new PinHashingService();
  }

  @Override
  public void createTableIfNotExists() {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    sqlSession.update("User_Login.createTableIfNotExists");
    sqlSession.commit();

    sqlSession.close();
  }

  @Override
  public int insert(User_LoginModel user_loginModel) {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    int rowsAffected = sqlSession.insert("User_Login.insert", user_loginModel);
    sqlSession.commit();

    sqlSession.close();

    return rowsAffected;
  }

  @Override
  public boolean login(User_LoginModel user_loginModel) {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    String Hashed_PIN = sqlSession.selectOne("User_Login.login", user_loginModel);

    sqlSession.close();

    if (Hashed_PIN != null) {
      return pinHashingService.validate(user_loginModel.getPIN(), Hashed_PIN);
    } else {
      return false;
    }
  }

  public boolean exist(String User_ID) {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    boolean isExist = sqlSession.selectOne("User_Login.exist", User_ID);

    sqlSession.close();

    return isExist;
  }
}
