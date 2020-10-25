package com.example.designpattern.model;

import java.math.BigDecimal;

public class StaffUnpaidLeaveResponseEntity extends StaffTotalSalaryResponseEntity {
  private Integer tanpaKabar;

  public StaffUnpaidLeaveResponseEntity(StaffWorkerModel staff) {
    super(staff);
    if (this.izin != null) {
      this.tanpaKabar = Math.max(0, this.izin - 1);
    }
  }

  public Integer getTanpaKabar() {
    return this.tanpaKabar;
  }
}
