package com.example.bankaccount.repository;

import com.example.bankaccount.dao.User_DetailDAO;
import com.example.bankaccount.model.User_DetailModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;

@Repository
public class User_DetailImpl implements User_DetailDAO {
  private SqlSession sqlSession;

  public User_DetailImpl() {
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
    sqlSession.update("User_Detail.createTableIfNotExists");
    sqlSession.commit();
  }

  @Override
  public int insert(User_DetailModel user_detailModel) {
    int rowsAffected = sqlSession.insert("User_Detail.insert", user_detailModel);
    sqlSession.commit();
    return rowsAffected;
  }

  @Override
  public User_DetailModel select(String Account_Number) {
    return sqlSession.selectOne("User_Detail.selectByAccountNumber", Account_Number);
  }
}
