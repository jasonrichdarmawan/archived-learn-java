package com.example.bankaccount.repository.admin;

import com.example.bankaccount.model.admin.Admin_TransactionsModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;

@Repository
public class Admin_TransactionsImpl {
  private SqlSession sqlSession;

  public Admin_TransactionsImpl() {
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
    sqlSession.update("Admin_Transactions.createTableIfNotExists");
    sqlSession.commit();
  }

  public int insert(Admin_TransactionsModel admin_transactionsModel, String user_id) {
    admin_transactionsModel.setDate();
    admin_transactionsModel.setUser_ID(user_id);
    int rowsAffected = sqlSession.insert("Admin_Transactions.insert", admin_transactionsModel);
    sqlSession.commit();
    return rowsAffected;
  }
}
