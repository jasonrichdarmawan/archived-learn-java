package com.company.Employee;

public abstract class Worker {
  private final int IDKaryawan;
  private final String Nama;
  private final float TunjanganPulsa;
  private final float GajiPokok;
  protected int AbsensiHari;

  Worker(int idKaryawan, String nama, float tunjanganPulsa, float gajiPokok, int absensiHari) {
    this.IDKaryawan = idKaryawan;
    this.Nama = nama;
    this.TunjanganPulsa = tunjanganPulsa;
    this.GajiPokok = gajiPokok;
    this.AbsensiHari = absensiHari;
  }

  public abstract int Absensi();

  public int getIDKaryawan() {
    return this.IDKaryawan;
  }

  public String getNama() {
    return this.Nama;
  }

  public float getTunjanganPulsa() {
    return this.TunjanganPulsa;
  }

  public float getGajiPokok() {
    return this.GajiPokok;
  }
}
