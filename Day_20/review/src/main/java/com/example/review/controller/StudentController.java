package com.example.review.controller;

import com.example.review.model.StudentModel;
import com.example.review.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
  private final StudentService studentService;

  @Autowired
  public StudentController(@Qualifier("studentService") StudentService studentService) {
    this.studentService = studentService;
  }

  @PostMapping
  public ResponseEntity<String> postStudent(@RequestBody Map<String, StudentModel> students) {
    int postToStudent = 0;

    for (Map.Entry<String, StudentModel> entry : students.entrySet()) {
      postToStudent = this.studentService.postStudent(entry.getValue());
    }

    if (postToStudent == 1
    ) {
      return new ResponseEntity<String>("CREATED", HttpStatus.CREATED);
    } else {
      return new ResponseEntity<String>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
