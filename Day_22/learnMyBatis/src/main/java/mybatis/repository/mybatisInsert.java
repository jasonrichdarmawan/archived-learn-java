package main.java.mybatis.repository;

import main.java.mybatis.model.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class mybatisInsert {
  public static void insert(Student student) {
    Reader reader = null;
    try {
      reader = Resources.getResourceAsReader("SqlMapConfig.xml");
    } catch (IOException e) {
      e.printStackTrace();
    }
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    SqlSession session = sqlSessionFactory.openSession();

    session.insert("Student.insert", student);
    System.out.println("ID: " + student.getId());
    session.commit();
    session.close();
  }

  public static void main(String[] args) {
    insert(new Student("a", "b", 1, 1, "b"));
  }
}
