package com.example.designpattern.model;

import java.math.BigDecimal;

public abstract class WorkerModel {
  protected Integer id;
  protected String nama;
  protected BigDecimal gajiPokok;
  protected Integer absensi; // not defined in SDR
  protected Integer izin; // not defined in SDR

  protected WorkerModel(Integer id, String nama, BigDecimal gajiPokok, Integer absensi, Integer izin) {
    this.id = id;
    this.nama = nama;
    this.gajiPokok = gajiPokok;
    this.absensi = absensi;
    this.izin = izin;
  }

  protected abstract void addAbsensi(); // in SDR == Absensi()
  protected abstract Integer getAbsensi();
  protected abstract BigDecimal HitungGajiPokok();
}
