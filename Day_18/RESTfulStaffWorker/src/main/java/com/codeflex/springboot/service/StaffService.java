package com.codeflex.springboot.service;

import com.codeflex.springboot.model.Staff;

import java.util.List;

public interface StaffService {
  Staff findById(int id);

  void saveStaff(Staff staff);

  void updateStaff(Staff staff);

  void deleteStaffById(int id);

  List<Staff> findAllStaffs();

  void deleteAllStaffs();
}
