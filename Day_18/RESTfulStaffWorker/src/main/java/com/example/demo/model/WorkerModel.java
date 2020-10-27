package com.example.demo.model;

import java.math.BigDecimal;

public abstract class WorkerModel {
  protected Integer IDKaryawan;
  protected String Nama;
  protected BigDecimal TunjanganPulsa;
  protected BigDecimal GajiPokok;
  protected Integer AbsensiHari;

  public WorkerModel(Integer idKaryawan, String nama, BigDecimal tunjanganPulsa, BigDecimal gajiPokok, Integer absensiHari) {
    this.IDKaryawan = idKaryawan;
    this.Nama = nama;
    this.TunjanganPulsa = tunjanganPulsa;
    this.GajiPokok = gajiPokok;
    this.AbsensiHari = absensiHari;
  }

  protected abstract void AddAbsensi();

  public Integer getIDKaryawan() {
    return this.IDKaryawan;
  }

  public void setIDKaryawan(Integer IDKaryawan) {
      this.IDKaryawan = IDKaryawan;
  }

  public String getNama() {
    return this.Nama;
  }

  public void setNama(String nama) {
    this.Nama = nama;
  }

  public BigDecimal getTunjanganPulsa() {
    return this.TunjanganPulsa;
  }

  public void setTunjanganPulsa(BigDecimal tunjanganPulsa) {
    this.TunjanganPulsa = tunjanganPulsa;
  }

  public BigDecimal getGajiPokok() {
    return this.GajiPokok;
  }

  public void setGajiPokok(BigDecimal gajiPokok) {
    this.GajiPokok = gajiPokok;
  }

  public Integer getAbsensiHari() {
    return this.AbsensiHari;
  }
}
