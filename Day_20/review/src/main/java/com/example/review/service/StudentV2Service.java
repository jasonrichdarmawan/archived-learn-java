package com.example.review.service;

import com.example.review.builder.StudentBuilder;
import com.example.review.model.StudentModel;
import com.example.review.repository.StudentRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentV2Service {
  private final StudentRepository studentRepository;

  @Autowired
  public StudentV2Service(@Qualifier("studentRepository") StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  // TODO: json.get().asText() if null did not throw NullPointerException
  public int postStudent(JsonNode json) {
    return this.studentRepository.postStudentById(new StudentBuilder()
            .setID(json.get("ID").asText())
            .setFullName(json.get("Nama").asText())
            .setAddress(json.get("Address").asText())
            .setStatus(json.get("Status").asText())
            .setGrades(json.get("Grades"))
            .setAbsensi(json.get("Absensi").asText())
            .build()
    );
  }

  public int putStudent(JsonNode json) {
    return this.studentRepository.putStudent(new StudentBuilder()
            .setID(json.get("ID").asText())
            .setFullName(json.get("Nama").asText())
            .setAddress(json.get("Address").asText())
            .setStatus(json.get("Status").asText())
            .setGrades(json.get("Grades"))
            .setAbsensi(json.get("Absensi").asText())
            .build()
    );
  }

  public int putStudentAddAbsensi(int id) {
    return this.studentRepository.putStudentAddAbsensi(id);
  }

  public int putStudentUpdateGrades(JsonNode grades, int id) {
    return this.studentRepository.putStudentUpdateGrades(grades, id);
  }

  public int deleteStudent(int id) {
    return this.studentRepository.deleteStudent(id);
  }

  public List<StudentModel> getStudentsWithoutGrades() {
    List<StudentModel> students = this.studentRepository.getStudents();
    // hide the student's grades.
    for (StudentModel student : students) {
      student.setGrades(null);
    }
    return students;
  }

  public String getAverage(Map<String, Integer> grades) {
    int average = 0;
    for (Map.Entry<String, Integer> entry : grades.entrySet()) {
      average += entry.getValue();
    }
    average = average / 3;
    if (average > 8) {
      return "A";
    } else if (average >= 7 && average <= 8) {
      return "B";
    } else if (average >= 6 && average <= 7) {
      return "C";
    } else {
      return "D";
    }
  }

  public String getStudentsWithGradesAverage() {
    List<StudentModel> students = this.studentRepository.getStudents();
    String reportGrades = "";
    for (StudentModel student : students) {
      reportGrades += "ID : " + student.getID() + '\n' +
              "NAMA : " + student.getFullName() + '\n' +
              "AVERAGE : " + getAverage(student.getGrades()) + '\n' + '\n';
    }
    return reportGrades;
  }

  public String getStudentByIdOrName(JsonNode parameter) {
    List<StudentModel> students = this.studentRepository.getStudentsByIdOrName(parameter);
    String reportGrades = "";
    for (StudentModel student : students) {
      reportGrades += "ID : " + student.getID() + '\n' +
              "NAMA : " + student.getFullName() + '\n' +
              "FISIKA : " + student.getGrades().get("physics") + '\n' +
              "KALKULUS : " + student.getGrades().get("calculus") + '\n' +
              "BIOLOGI : " + student.getGrades().get("biologi") + '\n' + '\n';
    }

    return reportGrades;
  }

  public StudentModel getStudentById() {
    return null;
  }

  public List<StudentModel> getStudentByName() {
    return null;
  }
}
