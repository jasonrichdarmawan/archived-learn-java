package com.example.demo.repository;

import com.example.demo.model.StaffWorkerModel;

import java.util.List;

public interface StaffDao {
  List<StaffWorkerModel> getStaffs();
  StaffWorkerModel getStaffById(int id);
  StaffWorkerModel postStaff(StaffWorkerModel staff);
  int putStaffById(StaffWorkerModel staff);
  int deleteStaffById(int id);
  int deleteStaffs();
}
