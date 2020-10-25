package com.example.demo.datasource;

import com.example.demo.model.CarModel;

import java.util.LinkedHashMap;
import java.util.Map;

public class CarDataSource {
  private static Map<String, CarModel> cars = new LinkedHashMap<>();

  public static CarModel addCar(CarModel car) {
    cars.put(car.getIdentifier(), car);
    return car;
  }

  public static CarModel editCar(CarModel car) {
    cars.replace(car.getIdentifier(), car);
    return car;
  }

  public static CarModel getCar(String identifier) {
    return cars.get(identifier);
  }
}
