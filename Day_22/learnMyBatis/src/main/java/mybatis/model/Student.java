package main.java.mybatis.model;

public class Student {
  private Integer id;
  private final String name;
  private final String branch;
  private final int percentage;
  private Integer phone;
  private String email;

  public Student(Integer id, String name, String branch, Integer percentage, Integer phone, String email) {
    this(name,branch,percentage,phone,email);
    this.setId(id);
  }

  public Student(String name, String branch, Integer percentage, Integer phone, String email) {
    this.name = name;
    this.branch = branch;
    this.percentage = percentage;
    this.phone = phone;
    this.email = email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhone(int phone) {
    this.phone = phone;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public String getBranch() {
    return this.branch;
  }

  public int getPercentage() {
    return this.percentage;
  }

  public String getEmail() {
    return this.email;
  }

  public int getPhone() {
    return this.phone;
  }
}
