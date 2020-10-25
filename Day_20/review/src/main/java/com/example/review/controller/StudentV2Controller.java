package com.example.review.controller;

import com.example.review.model.StudentModel;
import com.example.review.service.StudentV2Service;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/student")
public class StudentV2Controller {
  private final StudentV2Service studentService;

  @Autowired
  public StudentV2Controller(@Qualifier("studentV2Service") StudentV2Service studentService) {
    this.studentService = studentService;
  }

  @PostMapping
  public ResponseEntity<?> postStudent(@RequestBody JsonNode jsonNode) {
    List<StudentModel> students = new ArrayList<>();
    Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
    while (fields.hasNext()) {
      Map.Entry<String, JsonNode> entry = fields.next();
      switch (entry.getKey()) {
        case "1":
          Iterator<Map.Entry<String, JsonNode>> fields1 = entry.getValue().fields();
          while (fields1.hasNext()) {
            Map.Entry<String, JsonNode> entry1 = fields1.next();
            switch (entry1.getKey()) {
              case "1":
                this.studentService.postStudent(entry1.getValue());
                break;
              case "2":
                this.studentService.putStudent(entry1.getValue());
                break;
              case "3":
                this.studentService.deleteStudent(entry1.getValue().get("ID").asInt());
                break;
            }
          }
          if (entry.getValue().asInt() == 4) {
            return new ResponseEntity<>(this.studentService.getStudentsWithoutGrades(), HttpStatus.OK);
          }
          break;
        case "2":
          this.studentService.putStudentAddAbsensi(entry.getValue().asInt());
          break;
        case "3":
          Iterator<Map.Entry<String, JsonNode>> fields3 = entry.getValue().fields();
          while (fields3.hasNext()) {
            Map.Entry<String, JsonNode> entry3 = fields3.next();
            this.studentService.putStudentUpdateGrades(entry3.getValue().get("grades"), Integer.parseInt(entry3.getKey()));
          }
          break;
        case "4":
          return new ResponseEntity(this.studentService.getStudentsWithGradesAverage(), HttpStatus.OK);
        case "5":
          return new ResponseEntity(this.studentService.getStudentByIdOrName(entry.getValue()), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>("{\"Return\": \"Success\"}",HttpStatus.CREATED);
  }
}
