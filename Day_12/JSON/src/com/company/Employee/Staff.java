package com.company.Employee;

import java.util.ArrayList;

import static com.company.MainUtil.maxTunjanganMakan;
import static com.company.MainUtil.thisMonthTotalWorkDays;

public class Staff extends Worker {
  private float TunjanganMakan;
  private ArrayList<String> Emails = new ArrayList<>();

  public Staff(int idKaryawan, String nama, float tunjanganPulsa, float gajiPokok, int absensiHari, String[] emails) {
    super(idKaryawan, nama, tunjanganPulsa, gajiPokok, absensiHari);
    for (String email : emails) {
      this.Emails.add(email);
    }
  }

  @Override
  public int Absensi() {
    return AbsensiHari;
  }

  public float getTunjanganMakan() {
    this.TunjanganMakan = maxTunjanganMakan / thisMonthTotalWorkDays * AbsensiHari;
    return this.TunjanganMakan;
  }

  public float HitungTunjanganMakan() {
    this.TunjanganMakan = maxTunjanganMakan / thisMonthTotalWorkDays * AbsensiHari;
    return this.TunjanganMakan;
  }

  public ArrayList<String> getEmails() {
    return this.Emails;
  }
}
