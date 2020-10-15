package com.company.client.model;

import static com.company.client.Main.maxCutiPerBulan;
import static com.company.client.Main.thisMonthTotalWorkDays;

public class Staff extends Worker {
  private final float tunjanganMakan;
  private final float tunjanganTransport;
  private int statusKaryawan; // 0 = Probation, 1 = Kontrak, 2 = Tetap, 3 = Keluar

  protected Staff(int id, String nama, float gajiPokok, int absensi, float jumlahCuti, float tunjanganMakan, float tunjanganTransport, int statusKaryawan) {
    super(id, nama, gajiPokok, absensi, jumlahCuti);
    this.tunjanganMakan = tunjanganMakan;
    this.tunjanganTransport = tunjanganTransport;
    this.statusKaryawan = statusKaryawan;
  }

  @Override
  float HitungGajiPokok() {
    return (getGajiPokok() / thisMonthTotalWorkDays) / HitungAbsensi();
  }

  float HitungTunjanganMakan() {
    float tunjanganMakan = this.tunjanganMakan - (this.tunjanganMakan / thisMonthTotalWorkDays) * HitungAbsensi();

    if (getJumlahCuti() > maxCutiPerBulan) {
      tunjanganMakan = this.tunjanganMakan - (this.tunjanganMakan / thisMonthTotalWorkDays) * (getJumlahCuti() - maxCutiPerBulan);
    }

    return tunjanganMakan;
  }

  float HitungTunjanganTransport() {
    float tunjanganTransport = this.tunjanganTransport - (this.tunjanganTransport / thisMonthTotalWorkDays) * HitungAbsensi();

    if (getJumlahCuti() > maxCutiPerBulan) {
      tunjanganTransport = this.tunjanganTransport - (this.tunjanganMakan / thisMonthTotalWorkDays) * (getJumlahCuti() - maxCutiPerBulan);
    }

    return tunjanganTransport;
  }

  float HitungTotalGaji() {
    float totalGaji = HitungGajiPokok() + HitungTunjanganMakan() + HitungTunjanganTransport();

    return totalGaji;
  }

  public int getStatusKaryawan() {
    return statusKaryawan;
  }

  public void setStatusKaryawan(int statusKaryawan) {
    this.statusKaryawan = statusKaryawan;
  }
}
