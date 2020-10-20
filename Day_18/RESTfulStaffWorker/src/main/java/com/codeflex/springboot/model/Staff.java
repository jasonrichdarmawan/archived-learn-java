package com.codeflex.springboot.model;

import java.util.ArrayList;

public class Staff extends Worker {
  private float TunjanganMakan;
  private ArrayList<String> Email;

  public Staff(int idKaryawan, String nama, float tunjanganPulsa, float gajiPokok, int absensiHari, ArrayList<String> email) {
    super(idKaryawan, nama, tunjanganPulsa, gajiPokok, absensiHari);
    this.Email = email;
  }

  public void HitungTunjanganMakan() {

  }

  @Override
  protected void Absensi() {

  }

  public ArrayList<String> getEmail() {
    return Email;
  }

  public void setEmail(ArrayList<String> email) {
    this.Email = email;
  }
}
