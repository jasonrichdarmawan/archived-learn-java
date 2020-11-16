package com.example.mssqlserver.model;

public class NameModel {
  private Integer id;
  private String name;

  public NameModel(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public boolean requestIsValid() {
    if (this.name.isEmpty()) return false;

    return true;
  }
}
