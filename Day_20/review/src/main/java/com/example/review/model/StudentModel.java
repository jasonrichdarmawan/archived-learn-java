package com.example.review.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class StudentModel {
  private int id;
  private String fullName;
  private String address;
  private String status;
  private Map<String, Integer> grades;
  private int absensi;

  public StudentModel(@JsonProperty("id") int id, @JsonProperty("fullname") String fullName, @JsonProperty("address") String address, @JsonProperty("status") String status, @JsonProperty("grades") Map<String, Integer> grades, @JsonProperty("absensi") int absensi) {
    this.id = id;
    this.fullName = fullName;
    this.address = address;
    this.status = status;
    this.grades = grades;
    this.absensi = absensi;
  }

  public int getID() {
    return this.id;
  }

  public String getFullName() {
    return this.fullName;
  }

  public String getAddress() {
    return this.address;
  }

  public String getStatus() {
    return this.status;
  }

  public Map<String, Integer> getGrades() {
    return this.grades;
  }

  public int getAbsensi() {
    return this.absensi;
  }

  public void setGrades(Map<String, Integer> grades) {
    this.grades = grades;
  }
}
