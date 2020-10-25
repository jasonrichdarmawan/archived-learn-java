package com.example.demo.model;

public interface CarModel {
  CarModelImpl setIdentifier(String type);
  String getIdentifier();

  CarModelImpl setBrand(String brand);
  String getBrand();

  CarModelImpl setFeature(String feature);
  String getFeature();
}
