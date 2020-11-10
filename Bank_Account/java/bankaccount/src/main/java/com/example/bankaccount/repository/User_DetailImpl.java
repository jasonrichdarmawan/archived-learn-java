package com.example.bankaccount.repository;

import com.example.bankaccount.dao.User_DetailDAO;
import com.example.bankaccount.model.User_DetailModel;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class User_DetailImpl implements User_DetailDAO {
  @Autowired
  private MyBatis myBatis;

  private SqlSession sqlSession;

  @Override
  public void createTableIfNotExists() {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    sqlSession.update("User_Detail.createTableIfNotExists");
    sqlSession.commit();

    sqlSession.close();
  }

  @Override
  public int insert(User_DetailModel user_detailModel) {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    int rowsAffected = sqlSession.insert("User_Detail.insert", user_detailModel);
    sqlSession.commit();

    sqlSession.close();
    return rowsAffected;
  }

  @Override
  public User_DetailModel select(String Account_Number) {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    User_DetailModel user_detailModel = sqlSession.selectOne("User_Detail.selectByAccountNumber", Account_Number);

    sqlSession.close();

    return user_detailModel;
  }
}
