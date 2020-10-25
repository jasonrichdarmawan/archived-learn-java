package com.example.review.service;

import com.example.review.controller.StudentRESTInterface;
import com.example.review.model.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements StudentRESTInterface {
  private final StudentRESTInterface studentRepository;

  @Autowired
  public StudentService(@Qualifier("studentRepository") StudentRESTInterface studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public int postStudent(StudentModel student) {
    return this.studentRepository.postStudent(student);
  }

  @Override
  public int putStudent(StudentModel student) {
    return 0;
  }

  @Override
  public int deleteStudent(int id) {
    return 0;
  }

  @Override
  public List<StudentModel> getStudents() {
    return null;
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
