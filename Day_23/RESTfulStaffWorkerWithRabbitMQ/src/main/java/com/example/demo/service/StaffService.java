package com.example.demo.service;

import com.example.demo.model.StaffWorkerModel;
import com.example.demo.repository.StaffDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @deprecated use StaffRabbitMQ
 */
@Service
public class StaffService {
  @Autowired
  private StaffDao staffDao;

  public List<StaffWorkerModel> getStaffs() {
    return this.staffDao.getStaffs();
  }

  public StaffWorkerModel getStaffById(int id) {
    return this.staffDao.getStaffById(id);
  }

  public StaffWorkerModel postStaff(StaffWorkerModel staff) {
    return this.staffDao.postStaff(staff);
  }

  public int putStaffById(StaffWorkerModel staff) {
    return this.staffDao.putStaffById(staff);
  }

  public int deleteStaffById(int id) {
    return this.staffDao.deleteStaffById(id);
  }

  public int deleteStaffs() {
    return this.staffDao.deleteStaffs();
  }
}
