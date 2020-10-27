package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class StaffWorkerModel extends WorkerModel implements Serializable {
  private ArrayList<String> Email;

  public StaffWorkerModel(@JsonProperty("IDKaryawan") Integer idKaryawan, @JsonProperty("Nama") String nama, @JsonProperty("TunjanganPulsa") BigDecimal tunjanganPulsa, @JsonProperty("GajiPokok") BigDecimal gajiPokok, @JsonProperty("AbsensiHari") Integer absensiHari, @JsonProperty("Email") ArrayList<String> email) {
    super(idKaryawan, nama, tunjanganPulsa, gajiPokok, absensiHari);
    this.Email = email;
  }

  @Override
  protected void AddAbsensi() {
    this.AbsensiHari += 1;
  }

  public BigDecimal HitungTunjanganMakan() {
    BigDecimal tunjanganMakan = new BigDecimal(200000);
    // in SDR izin is not defined, so :) the formula is tunjanganMakan / 20 * AbsensiHari
    if (this.AbsensiHari >= 0) {
      return tunjanganMakan
              .divide(new BigDecimal(20))
              .multiply(new BigDecimal(this.AbsensiHari));
    } else {
      return null;
    }
  }

  public ArrayList<String> getEmail() {
    return Email;
  }

  public void setEmail(ArrayList<String> email) {
    this.Email = email;
  }
}
