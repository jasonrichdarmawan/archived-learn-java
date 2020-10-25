package main.java.mybatis.repository;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import main.java.mybatis.model.Student;
import main.java.mybatis.util.SoutStudent;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class mybatisRead_All {

  public static void readAll() {
    Reader reader = null;
    try {
      reader = Resources.getResourceAsReader("SqlMapConfig.xml");
    } catch (IOException e) {
      e.printStackTrace();
    }
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    SqlSession session = sqlSessionFactory.openSession();

    //select contact all contacts
    List<Student> student = session.selectList("Student.getAll");

    for (Student st : student) {
      SoutStudent.print(st);
    }

    System.out.println("Records Read Successfully ");
    session.commit();
    session.close();
  }

  public static void main(String[] args) {
    readAll();
  }
} 