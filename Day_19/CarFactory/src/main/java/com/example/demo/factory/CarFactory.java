package com.example.demo.factory;

import com.example.demo.model.CarModelImpl;
import org.springframework.stereotype.Component;

@Component
public class CarFactory {
  public CarModelImpl createCar(String identifier, String brand) {
    switch (brand) {
      case "Lamborghini":
      case "BMW":
        return new CarModelImpl().setIdentifier(identifier).setBrand(brand);
      default:
        throw new UnsupportedOperationException("Unsupported car brand!");
    }
  }
}
