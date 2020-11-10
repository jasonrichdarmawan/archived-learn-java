package com.example.bankaccount.repository.admin;

import com.example.bankaccount.model.admin.Admin_TransactionsModel;
import com.example.bankaccount.repository.MyBatis;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Admin_TransactionsImpl {

  @Autowired
  private MyBatis myBatis;

  private SqlSession sqlSession;

  public void createTableIfNotExists() {
    sqlSession = myBatis.getSqlSessionFactory().openSession();

    sqlSession.update("Admin_Transactions.createTableIfNotExists");
    sqlSession.commit();

    sqlSession.close();
  }

  public int insert(Admin_TransactionsModel admin_transactionsModel, String user_id) {
    admin_transactionsModel.setDate();
    admin_transactionsModel.setUser_ID(user_id);

    int rowsAffected = sqlSession.insert("Admin_Transactions.insert", admin_transactionsModel);
    sqlSession.commit();

    sqlSession.close();

    return rowsAffected;
  }
}
