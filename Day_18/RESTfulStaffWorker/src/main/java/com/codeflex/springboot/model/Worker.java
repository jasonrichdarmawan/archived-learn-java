package com.codeflex.springboot.model;

import com.codeflex.springboot.service.StaffServiceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Worker {
  private int IDKaryawan;
  private String Nama;
  private float TunjanganPulsa;
  private float GajiPokok;
  private int AbsensiHari;

  protected Worker(int idKaryawan, String nama, float tunjanganPulsa, float gajiPokok, int absensiHari) {
    if (idKaryawan == 0) {
      ResultSet resultSet = null;
      try {
        resultSet = StaffServiceImpl.staffDataSource.executeQuery("SELECT IDKaryawan FROM staff ORDER BY IDKaryawan DESC LIMIT 1");
        resultSet.next();
        this.IDKaryawan = resultSet.getInt("IDKaryawan") + 1;
      } catch (SQLException throwables) {
        throwables.printStackTrace();
        this.IDKaryawan = 1;
      } finally {
        try {
          resultSet.close();
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }
      }
    } else {
      this.IDKaryawan = idKaryawan;
    }
    this.Nama = nama;
    this.TunjanganPulsa = tunjanganPulsa;
    this.GajiPokok = gajiPokok;
    this.AbsensiHari = absensiHari;
  }

  protected abstract void Absensi();

  public int getIDKaryawan() {
    return IDKaryawan;
  }

  public String getNama() {
    return Nama;
  }

  public void setNama(String nama) {
    Nama = nama;
  }

  public float getTunjanganPulsa() {
    return TunjanganPulsa;
  }

  public void setTunjanganPulsa(float tunjanganPulsa) {
    TunjanganPulsa = tunjanganPulsa;
  }

  public float getGajiPokok() {
    return GajiPokok;
  }

  public void setGajiPokok(float gajiPokok) {
    GajiPokok = gajiPokok;
  }

  public int getAbsensiHari() {
    return AbsensiHari;
  }

  public void setAbsensiHari(int absensiHari) {
    AbsensiHari = absensiHari;
  }
}
