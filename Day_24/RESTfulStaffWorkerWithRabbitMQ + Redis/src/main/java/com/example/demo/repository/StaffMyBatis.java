package com.example.demo.repository;

import com.example.demo.model.StaffWorkerModel;
import com.example.demo.util.StringToArrayList;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class StaffMyBatis {
  private SqlSession session;

  public StaffMyBatis() {
    try {
      Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
      session = sqlSessionFactory.openSession();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<StaffWorkerModel> getStaffs() {
    List<StaffWorkerModel> staffs = this.session.selectList("Staff.getStaffs");
    this.session.commit();
    return staffs;
  }

  public StaffWorkerModel getStaffById(int id) {
    return this.session.selectOne("Staff.getStaffById", id);
  }

  public StaffWorkerModel postStaff(StaffWorkerModel staff) {
    this.session.insert("Staff.postStaff", staff);
    this.session.commit();
    return staff;
  }

  public int putStaffById(StaffWorkerModel staff) {
    int response = this.session.update("Staff.putStaffById", staff);
    this.session.commit();
    return response;
  }

  public int deleteStaffById(int id) {
    int response = this.session.delete("Staff.deleteStaffById", id);
    this.session.commit();
    return response;
  }

  public int deleteStaffs() {
    int response = this.session.delete("Staff.deleteStaffs");
    this.session.commit();
    return response;
  }

}
