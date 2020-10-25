package com.example.designpattern.builder;

import com.example.designpattern.model.StaffWorkerModel;

import java.math.BigDecimal;

public class StaffBuilder {
  private Integer id;
  private String nama;
  private BigDecimal gajiPokok;
  private Integer absensi;
  private Integer izin;

  public StaffBuilder setID(String id) {
    if (id != null) {
      this.id = Integer.parseInt(id);
    }
    return this;
  }

  public StaffBuilder setNama(String nama) {
    this.nama = nama;
    return this;
  }

  public StaffBuilder setGajiPokok(String gajiPokok) {
    if (gajiPokok != null) {
      this.gajiPokok = new BigDecimal(gajiPokok);
    }
    return this;
  }

  public StaffBuilder setAbsensi(String absensi) {
    if (absensi != null) {
      this.absensi = Integer.parseInt(absensi);
    }
    return this;
  }

  public StaffBuilder setIzin(String izin) {
    if (izin != null) {
      this.izin = Integer.parseInt(izin);
    }
    return this;
  }

  public StaffWorkerModel build() {
    return new StaffWorkerModel(id, nama, gajiPokok, absensi, izin);
  }
}
