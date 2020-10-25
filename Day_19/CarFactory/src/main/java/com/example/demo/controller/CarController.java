package com.example.demo.controller;

import com.example.demo.model.CarModel;
import com.example.demo.service.CarService;
import com.example.demo.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarController {

  @Autowired
  CarService carService = new CarServiceImpl();

  // sesuai SDR /AddCar/{brand}/{identifier}
  // implementasi: identifier sebagai key.
  @PostMapping("/AddCar/{brand}/{identifier}")
  public ResponseEntity<?> addCar(@PathVariable String brand, @PathVariable String identifier) {
    CarModel car = carService.addCar(identifier, brand);
    return new ResponseEntity<>(car, HttpStatus.CREATED);
  }

  @PutMapping("/EditCar/{identifier}/{feature}")
  public ResponseEntity<?> editCar(@PathVariable String identifier, @PathVariable String feature) {
    CarModel car = carService.editCar(identifier, feature);
    return new ResponseEntity<>(car, HttpStatus.OK);
  }
}
