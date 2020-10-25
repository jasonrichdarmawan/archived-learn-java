package com.example.review.repository;

import com.example.review.builder.StudentBuilder;
import com.example.review.controller.StudentRESTInterface;
import com.example.review.model.StudentModel;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository implements StudentRESTInterface {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public int postStudent(StudentModel student) {
    return jdbcTemplate.update(
            "INSERT INTO student (fullname, address, status, absensi, physics, calculus, biologi) VALUES (?, ?, ?, ?, ?, ?, ?)",
            student.getFullName(), student.getAddress(), student.getStatus(), student.getAbsensi(), student.getGrades().get("physics"), student.getGrades().get("calculus"), student.getGrades().get("biologi")
    );
  }

  // TODO: WHERE NOT EXISTS
  public int postStudentById(StudentModel student) {
    return jdbcTemplate.update(
            "INSERT INTO student (id, fullname, address, status, absensi, physics, calculus, biologi) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
            student.getID(), student.getFullName(), student.getAddress(), student.getStatus(), student.getAbsensi(), student.getGrades().get("physics"), student.getGrades().get("calculus"), student.getGrades().get("biologi")
    );
  }

  @Override
  public int putStudent(StudentModel student) {
    return jdbcTemplate.update(
            "UPDATE student SET fullname=?, address=?, status=?, absensi=?, physics=?, calculus=?, biologi=? WHERE id=?",
            student.getFullName(), student.getAddress(), student.getStatus(), student.getAbsensi(), student.getGrades().get("physics"), student.getGrades().get("calculus"), student.getGrades().get("biologi"), student.getID()
    );
  }

  public int putStudentAddAbsensi(int id) {
    return jdbcTemplate.update(
            "UPDATE student SET absensi = absensi + 1 WHERE id=?",
            id
    );
  }

  public int putStudentUpdateGrades(JsonNode grades, int id) {
    return jdbcTemplate.update(
            "UPDATE student SET physics=?, calculus=?, biologi=? WHERE id=?",
            grades.get("physics").asInt(), grades.get("calculus").asInt(), grades.get("biologi").asInt(), id
    );
  }

  @Override
  public int deleteStudent(int id) {
    return jdbcTemplate.update(
            "DELETE FROM student WHERE id=?",
            id
    );
  }

  @Override
  public List<StudentModel> getStudents() {
    return jdbcTemplate.query(
            "SELECT * FROM student",
            (rs, rowNum) -> new StudentBuilder()
                    .setID(rs.getString("id"))
                    .setFullName(rs.getString("fullname"))
                    .setAddress(rs.getString("address"))
                    .setStatus(rs.getString("status"))
                    .setGrades(rs.getString("physics"), rs.getString("calculus"), rs.getString("biologi"))
                    .setAbsensi(rs.getString("absensi"))
                    .build()
    );
  }

  public List<StudentModel> getStudentsByIdOrName(JsonNode parameter) {
    if (parameter.isInt()) {
      System.out.println("hi");
      return jdbcTemplate.query(
              "SELECT id, fullname, physics, calculus, biologi FROM student WHERE id = ?",
              new Object[]{parameter.asInt()},
              (rs, rowNum) -> new StudentBuilder()
                      .setID(rs.getString("id"))
                      .setFullName(rs.getString("fullname"))
                      .setGrades(rs.getString("physics"), rs.getString("calculus"), rs.getString("biologi"))
                      .build()
      );
    } else {
      return jdbcTemplate.query(
              "SELECT id, fullname, physics, calculus, biologi FROM student WHERE fullname LIKE ?",
              new Object[]{"%" + parameter.asText() + "%"},
              (rs, rowNum) -> new StudentBuilder()
                      .setID(rs.getString("id"))
                      .setFullName(rs.getString("fullname"))
                      .setGrades(rs.getString("physics"), rs.getString("calculus"), rs.getString("biologi"))
                      .build()

      );
    }
  }

  @Override
  public StudentModel getStudentById() {
    return null;
  }

  @Override
  public List<StudentModel> getStudentByName() {
    return null;
  }
}
