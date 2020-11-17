package com.example.mssqlserver.model;

public class NameModel {
  private Integer id;
  private String name;
  private String newid;

  public NameModel(Integer id, String name, String newid) {
    this.id = id;
    this.name = name;
    this.newid = newid;
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

  public String getNewid() {
    return newid;
  }

  public void setNewid(String newid) {
    this.newid = newid;
  }

  public boolean requestIsValid() {
    if (this.name.isEmpty()) return false;

    return true;
  }
}
