package com.example.springwebh2mybatis.model;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class GetKaryawanDto {
  @Pattern(regexp = "[0-9]{1,}")
  private String id;
  private String nama;
  private String alamat;
  private String rt;
  private String rw;
  private String kecamatan;
  private String kelurahan;
  private String telepon;
  private LocalDate input_date;
  private String input_by;
  private LocalDate approve_date;
  private String approve_by;

  public String getId() {
    return id;
  }

  public String getNama() {
    return nama;
  }

  public String getAlamat() {
    return alamat;
  }

  public String getRt() {
    return rt;
  }

  public String getRw() {
    return rw;
  }

  public String getKecamatan() {
    return kecamatan;
  }

  public String getKelurahan() {
    return kelurahan;
  }

  public String getTelepon() {
    return telepon;
  }

  public LocalDate getInput_date() {
    return input_date;
  }

  public String getInput_by() {
    return input_by;
  }

  public LocalDate getApprove_date() {
    return approve_date;
  }

  public String getApprove_by() {
    return approve_by;
  }
}
