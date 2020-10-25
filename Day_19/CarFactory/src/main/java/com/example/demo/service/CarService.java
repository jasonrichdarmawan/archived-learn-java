package com.example.demo.service;

import com.example.demo.model.CarModel;

import java.util.Optional;

public interface CarService {
  CarModel addCar(String identifier, String brand);
  CarModel editCar(String identifier, String feature);
}
