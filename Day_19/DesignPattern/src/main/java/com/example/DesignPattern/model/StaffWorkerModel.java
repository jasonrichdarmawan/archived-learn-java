package com.example.designpattern.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class StaffWorkerModel extends WorkerModel {
  public StaffWorkerModel(@JsonProperty("id") Integer id, @JsonProperty("nama") String nama, @JsonProperty("gajiPokok") BigDecimal gajiPokok, @JsonProperty("absensi") Integer absensi, @JsonProperty("izin") Integer izin) {
    super(id, nama, gajiPokok, absensi, izin);
  }

  public Integer getID() {
    return this.id;
  }

  public void setID(int id) {
    this.id = id;
  }

  public String getNama() {
    return this.nama;
  }

  public BigDecimal getGajiPokok() {
    return this.gajiPokok;
  }

  public Integer getIzin() {
    return this.izin;
  }

  @Override
  public Integer getAbsensi() {
    return this.absensi;
  }

  @Override
  public void addAbsensi() {
    this.absensi += 1;
  }

  @Override
  protected BigDecimal HitungGajiPokok() {
    if (this.gajiPokok != null && this.absensi != null) {
      return this.gajiPokok
              .divide(new BigDecimal(20))
              .multiply(new BigDecimal(this.absensi));
    } else {
      return null;
    }
  }

  protected BigDecimal HitungTunjanganMakan() {
    BigDecimal tunjanganMakan = new BigDecimal(200000);
    if (this.absensi >= 0 && this.izin != null) {
      return tunjanganMakan
              .divide(new BigDecimal(20))
              .multiply(new BigDecimal(this.absensi - Math.max(0, this.izin - 1))
              );
    } else {
      return null;
    }
  }

  protected BigDecimal HitungTunjanganTransport() {
    BigDecimal tunjanganTransport = new BigDecimal(400000);
    if (this.absensi >= 0 && this.izin >= 0) {
      return tunjanganTransport
              .divide(new BigDecimal(20))
              .multiply(new BigDecimal(this.absensi - Math.max(0, this.izin - 1))
              );
    } else {
      return null;
    }
  }

  public BigDecimal HitungTotalGaji() {
    try {
      return HitungGajiPokok().add(HitungTunjanganMakan()).add(HitungTunjanganTransport());
    } catch (NullPointerException e) {
      return null;
    }
  }
}
