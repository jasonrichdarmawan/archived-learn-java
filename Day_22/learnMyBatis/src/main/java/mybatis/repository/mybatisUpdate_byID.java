package main.java.mybatis.repository;

import java.io.IOException;
import java.io.Reader;

import main.java.mybatis.model.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class mybatisUpdate_byID {

  public static void updateById(Student newStudent) {

    Reader reader = null;
    try {
      reader = Resources.getResourceAsReader("SqlMapConfig.xml");
    } catch (IOException e) {
      e.printStackTrace();
    }
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    SqlSession session = sqlSessionFactory.openSession();

    //select a particular student using id
    Student oldStudent = (Student) session.selectOne("Student.getById", newStudent.getId());
    System.out.println("Current details of the student are");
    System.out.println("EMAIL: " + oldStudent.getEmail());

    //Set new values to the mail and phone number of the student

    //Update the student record
    session.update("Student.update", newStudent);
    System.out.println("Record updated successfully");
    session.commit();

    //verifying the record
    Student studentFromDatabase = (Student) session.selectOne("Student.getById", newStudent.getId());
    System.out.println("Details of the student after update operation");
    System.out.println("EMAIL: " + studentFromDatabase.getEmail());
    session.commit();
    session.close();

  }

  public static void main(String[] args) {
    updateById(new Student(1, "a", "a", 1, 1, "d"));
  }
}