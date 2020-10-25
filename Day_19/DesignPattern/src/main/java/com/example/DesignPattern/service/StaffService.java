package com.example.designpattern.service;

import com.example.designpattern.builder.StaffBuilder;
import com.example.designpattern.model.StaffTotalSalaryResponseEntity;
import com.example.designpattern.model.StaffUnpaidLeaveResponseEntity;
import com.example.designpattern.repository.StaffDao;
import com.example.designpattern.model.StaffWorkerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class StaffService {
  private final StaffDao staffDao;

  @Autowired
  public StaffService(StaffDao staffDao) {
    this.staffDao = staffDao;
  }

  public List<StaffWorkerModel> postStaffsWithStaffBuilder(List<Map<String, String>> requestStaffs) {
    List<StaffWorkerModel> responseStaffs = new ArrayList<>();
    for (Iterator iterator = requestStaffs.iterator(); iterator.hasNext(); ) {
      Map<String, String> staff = (Map<String, String>) iterator.next();
      StaffWorkerModel requestStaff = new StaffBuilder()
              .setID(staff.get("id"))
              .setNama(staff.get("nama"))
              .setGajiPokok(staff.get("gajiPokok"))
              .setAbsensi(staff.get("absensi"))
              .setIzin(staff.get("izin"))
              .build();
      int id = staffDao.postStaff(requestStaff);
      if (id != 0) {
        requestStaff.setID(id);
        responseStaffs.add(requestStaff);
      }
    }
    return responseStaffs;
  }

  public boolean putStaffs(List<Map<String, String>> requestStaffs) {
    int changed = 0;
    for (Iterator iterator = requestStaffs.iterator(); iterator.hasNext(); ) {
      Map<String, String> staff = (Map<String, String>) iterator.next();
      StaffWorkerModel requestStaff = new StaffBuilder()
              .setID(staff.get("id"))
              .setNama(staff.get("nama"))
              .setGajiPokok(staff.get("gajiPokok"))
              .setAbsensi(staff.get("absensi"))
              .setIzin(staff.get("izin"))
              .build();
      if (staff.get("id") != null) {
        changed = this.staffDao.putStaffById(requestStaff);
      } else if (staff.get("nama") != null) {
        changed = this.staffDao.putStaffByName(requestStaff);
      }
    }

    return changed > 0;
  }

  public List<StaffTotalSalaryResponseEntity> getStaffsWithTotalSalary() {
    List<StaffTotalSalaryResponseEntity> staffs = new ArrayList<>();

    for (Iterator iterator = this.staffDao.getStaffs().iterator(); iterator.hasNext(); ) {
      StaffWorkerModel staff = (StaffWorkerModel) iterator.next();
      staffs.add(new StaffTotalSalaryResponseEntity(staff));
    }

    return staffs;
  }

  public List<StaffWorkerModel> getStaffs() {
    return this.staffDao.getStaffs();
  }

  public List<StaffUnpaidLeaveResponseEntity> getStaffsByIdOrName(Integer id, String nama) {
    List<StaffUnpaidLeaveResponseEntity> staffs = new ArrayList<>();

    if (id != null) {
      System.out.println("c");
      staffs.add(new StaffUnpaidLeaveResponseEntity(this.staffDao.getStaffById(id)));
    } else if (nama != null) {
      System.out.println("b");
      for (Iterator iterator1 = this.staffDao.getStaffsByName(nama).iterator(); iterator1.hasNext(); ) {
        System.out.println("a");
        StaffUnpaidLeaveResponseEntity staff1 = new StaffUnpaidLeaveResponseEntity((StaffWorkerModel) iterator1.next());
        staffs.add(staff1);
      }
    }

    return staffs;
  }
}