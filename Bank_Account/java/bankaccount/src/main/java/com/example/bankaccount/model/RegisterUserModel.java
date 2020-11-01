package com.example.bankaccount.model;

import com.example.bankaccount.service.PinHashingService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDate;

public class RegisterUserModel {
  private String User_ID;
  private String Hashed_PIN;
  private String Full_Name;
  private LocalDate Birth_Date;
  private int ISO_4217;
  private String Address_3;
  private String Address_4;
  private String Address_1;
  private String Address_2;
  private int Zip_Code;
  private int ISO_3166_1;

  private PinHashingService pinHashingService;

  public RegisterUserModel(@JsonProperty("User_ID") String user_id, @JsonProperty("PIN") String pin, @JsonProperty("Full_Name") String full_name, @JsonProperty("Birth_Date") LocalDate birth_date, @JsonProperty("ISO_4217") int iso_4217, @JsonProperty("Address_3") String address_3, @JsonProperty("Address_4") String address_4, @JsonProperty("Address_1") String address_1, @JsonProperty("Address_2") String address_2, @JsonProperty("Zip_Code") int zip_code, @JsonProperty("ISO_3166_1") int iso_3166_1) {
    pinHashingService = new PinHashingService();

    User_ID = user_id;
    Hashed_PIN = pinHashingService.hash(pin);
    Full_Name = full_name;
    Birth_Date = birth_date;
    ISO_4217 = iso_4217;
    Address_3 = address_3;
    Address_4 = address_4;
    Address_1 = address_1;
    Address_2 = address_2;
    Zip_Code = zip_code;
    ISO_3166_1 = iso_3166_1;
  }

  public String getUser_ID() {
    return User_ID;
  }

  public String getHashed_PIN() {
    return Hashed_PIN;
  }

  public String getFull_Name() {
    return Full_Name;
  }

  public LocalDate getBirth_Date() {
    return Birth_Date;
  }

  public int getISO_4217() {
    return ISO_4217;
  }

  public String getAddress_3() {
    return Address_3;
  }

  public String getAddress_4() {
    return Address_4;
  }

  public String getAddress_1() {
    return Address_1;
  }

  public String getAddress_2() {
    return Address_2;
  }

  public int getZip_Code() {
    return Zip_Code;
  }

  public int getISO_3166_1() {
    return ISO_3166_1;
  }
}
