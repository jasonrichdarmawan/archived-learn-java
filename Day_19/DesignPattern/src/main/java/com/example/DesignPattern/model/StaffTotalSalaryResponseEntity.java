package com.example.designpattern.model;

import java.math.BigDecimal;

public class StaffTotalSalaryResponseEntity extends StaffWorkerModel {
  private BigDecimal totalGaji;

  public StaffTotalSalaryResponseEntity(StaffWorkerModel staff) {
    super(staff.getID(), staff.getNama(), staff.getGajiPokok(), staff.getAbsensi(), staff.getIzin());
    this.totalGaji = this.HitungTotalGaji();
  }

  public BigDecimal getTotalGaji() {
    return this.totalGaji;
  }
}
