package com.example.review.builder;

import com.example.review.model.StudentModel;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.LinkedHashMap;
import java.util.Map;

// TODO: in production do not use Builder pattern. It's slow and prone to error.
public class StudentBuilder {
  private int id = 0;
  private String fullName = "";
  private String address = "";
  private String status = "";
  private Map<String, Integer> grades = new LinkedHashMap<String, Integer>();
  private int absensi = 0;

  public StudentBuilder setID(String id) {
    if (id != null) {
      this.id = Integer.parseInt(id);
    }
    return this;
  }

  public StudentBuilder setFullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  public StudentBuilder setAddress(String address) {
    this.address = address;
    return this;
  }

  public StudentBuilder setStatus(String status) {
    this.status = status;
    return this;
  }

  public StudentBuilder setGrades(JsonNode grades) {
    if (grades.get("physics").asText() != null) {
      this.grades.put("physics", Integer.parseInt(grades.get("physics").asText()));
    }
    if (grades.get("calculus").asText() != null) {
      this.grades.put("calculus", Integer.parseInt(grades.get("calculus").asText()));
    }
    if (grades.get("biologi").asText() != null) {
      this.grades.put("biologi", Integer.parseInt(grades.get("biologi").asText()));
    }
    return this;
  }

  public StudentBuilder setGrades(String physics, String calculus, String biologi) {
    if (physics != null) {
      this.grades.put("physics", Integer.parseInt(physics));
    }
    if (calculus != null) {
      this.grades.put("calculus", Integer.parseInt(calculus));
    }
    if (biologi != null) {
      this.grades.put("biologi", Integer.parseInt(biologi));
    }
    return this;
  }

  public StudentBuilder setAbsensi(String absensi) {
    if (absensi != null) {
      this.absensi = Integer.parseInt(absensi);
    }
    return this;
  }

  public StudentModel build() {
    return new StudentModel(id, fullName, address, status, grades, absensi);
  }
}
