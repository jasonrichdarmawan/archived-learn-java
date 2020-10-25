package main.java.mybatis.repository;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class mybatisDelete_byID {
  public static void deleteById(Integer id) {
    Reader reader = null;
    try {
      reader = Resources.getResourceAsReader("SqlMapConfig.xml");
    } catch (IOException e) {
      e.printStackTrace();
    }
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    SqlSession session = sqlSessionFactory.openSession();

    int changed = session.delete("Student.delete", id);
    System.out.println(changed == 1 ? "success" : "bad request");
    session.commit();
    session.close();
  }
  public static void main(String[] args) {
    deleteById(1);
  }
}
