package com.company.Employee;

import java.util.ArrayList;

import static com.company.MainUtil.*;

public class Manager extends Worker {
  private float TunjanganTransport;
  private float TunjanganEntertaint;
  private ArrayList<String> Telepons = new ArrayList<>();

  public Manager(int idKaryawan, String nama, float tunjanganPulsa, float gajiPokok, int absensiHari, String[] telepons) {
    super(idKaryawan, nama, tunjanganPulsa, gajiPokok, absensiHari);
    for (String telepon : telepons) {
      this.Telepons.add(telepon);
    }
  }

  @Override
  public int Absensi() {
    return AbsensiHari;
  }

  public float HitungTunjanganTransport() {
    this.TunjanganTransport = maxTunjanganTransport / thisMonthTotalWorkDays * AbsensiHari;
    return this.TunjanganTransport;
  }

  public float HitungTunjanganEntertaint() {
    this.TunjanganEntertaint = maxTunjanganEntertaint / thisMonthTotalWorkDays * AbsensiHari;
    return this.TunjanganEntertaint;
  }

  public float getTunjanganTransport() {
    return this.TunjanganTransport;
  }

  public float getTunjanganEntertaint() {
    return this.TunjanganEntertaint;
  }

  public ArrayList<String> getTelepons() {
    return this.Telepons;
  }

}
