package main.java.mybatis.util;

import main.java.mybatis.model.Student;

public class SoutStudent {
  public static void print(Student student) {
    System.out.println("ID: " + student.getId() + '\n' +
            "NAME: " + student.getName() + '\n' +
            "BRANCH: " + student.getBranch() + '\n' +
            "PERCENTAGE: " + student.getPercentage() + '\n' +
            "EMAIL: " + student.getEmail() + '\n' +
            "PHONE: " + student.getPhone() + '\n'
    );
  }
}
