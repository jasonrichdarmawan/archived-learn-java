package com.company.client.model;

import static com.company.client.Main.thisMonthTotalWorkDays;

public abstract class Worker {
  private final int id;
  private String nama;
  private final float gajiPokok;
  private int absensi;
  private float jumlahCuti; // 1 bulan maksimal 1 hari.

  protected Worker(int id, String nama, float gajiPokok, int absensi, float jumlahCuti) {
    this.id = id;
    this.nama = nama;
    this.gajiPokok = gajiPokok;
    this.absensi = absensi;
    this.jumlahCuti = jumlahCuti;
  }

  // in SDR == Absensi()
  public void TambahAbsensi() {
    this.absensi += 1;
  }

  public int HitungAbsensi() {
    return this.absensi;
  }

  abstract float HitungGajiPokok();

  public float getGajiPokok() {
    return this.gajiPokok;
  }

  public float getJumlahCuti() {
    return this.jumlahCuti;
  }

  public void TambahCuti() {
    this.jumlahCuti += 1;
  }

  public float getJumlahTanpaKabar() {
    return thisMonthTotalWorkDays - (this.absensi + this.jumlahCuti);
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public int getId() {
    return id;
  }
}
