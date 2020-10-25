package com.example.demo.service;

import com.example.demo.datasource.CarDataSource;
import com.example.demo.factory.CarFactory;
import com.example.demo.model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
  @Autowired
  CarFactory carFactory = new CarFactory();

  @Override
  public CarModel addCar(String identifier, String brand) {
    CarModel car = carFactory.createCar(identifier, brand);
    return CarDataSource.addCar(car);
  }

  @Override
  public CarModel editCar(String identifier, String feature) {
    CarModel car = CarDataSource.getCar(identifier);
    car.setFeature(feature);
    return CarDataSource.editCar(car);
  }
}
