package com.example.demo.model;

public class CarModelImpl implements CarModel {
  String identifier;

  String brand;
  String feature;

  @Override
  public CarModelImpl setIdentifier(String type) {
    this.identifier = type;
    return this;
  }

  @Override
  public String getIdentifier() {
    return this.identifier;
  }

  @Override
  public CarModelImpl setBrand(String brand) {
    this.brand = brand;
    return this;
  }

  @Override
  public String getBrand() {
    return this.brand;
  }

  @Override
  public CarModelImpl setFeature(String feature) {
    this.feature = feature;
    return this;
  }

  @Override
  public String getFeature() {
    return this.feature;
  }
}
