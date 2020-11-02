package com.example.bankaccount.model;

import java.time.LocalDate;

public class User_DetailModel {
  private String Account_Number;
  private LocalDate Birth_Date;
  private String Address_3;
  private String Address_4;
  private String Address_1;
  private String Address_2;
  private int Zip_Code;
  private int ISO_3166_1;

  public String getAccount_Number() {
    return Account_Number;
  }

  public void setAccount_Number(String account_Number) {
    Account_Number = account_Number;
  }

  public LocalDate getBirth_Date() {
    return Birth_Date;
  }

  public void setBirth_Date(LocalDate birth_date) {
    Birth_Date = birth_date;
  }

  public String getAddress_3() {
    return Address_3;
  }

  public void setAddress_3(String address_3) {
    Address_3 = address_3;
  }

  public String getAddress_4() {
    return Address_4;
  }

  public void setAddress_4(String address_4) {
    Address_4 = address_4;
  }

  public String getAddress_1() {
    return Address_1;
  }

  public void setAddress_1(String address_1) {
    Address_1 = address_1;
  }

  public String getAddress_2() {
    return Address_2;
  }

  public void setAddress_2(String address_2) {
    Address_2 = address_2;
  }

  public int getZip_Code() {
    return Zip_Code;
  }

  public void setZip_Code(int zip_Code) {
    Zip_Code = zip_Code;
  }

  public int getISO_3166_1() {
    return ISO_3166_1;
  }

  public void setISO_3166_1(int ISO_3166_1) {
    this.ISO_3166_1 = ISO_3166_1;
  }
}
