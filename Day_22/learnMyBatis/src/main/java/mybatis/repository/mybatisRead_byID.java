package main.java.mybatis.repository;

import java.io.IOException;
import java.io.Reader;

import main.java.mybatis.model.Student;
import main.java.mybatis.util.SoutStudent;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class mybatisRead_byID { 

   public static void Read_byID(Integer id) {
      
      Reader reader = null;
      try {
         reader = Resources.getResourceAsReader("SqlMapConfig.xml");
      } catch (IOException e) {
         e.printStackTrace();
      }
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);		
      SqlSession session = sqlSessionFactory.openSession();      
	  
      //select a particular student  by  id	
      Student student = (Student) session.selectOne("Student.getById", id);
	  
      //Print the student details
      SoutStudent.print(student);
		
      session.commit();
      session.close();
   }

   public static void main(String[] args) {
      Read_byID(1);
   }
   
}