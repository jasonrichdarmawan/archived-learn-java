package com.company.client.Student;

public class Student {
  private final String name;
  private final int physicsScore;
  private final int chemistryScore;
  private final int biologyScore;

  public Student(String name, int physicsScore, int chemistryScore, int biologyScore) {
    this.name = name;
    this.physicsScore = physicsScore;
    this.chemistryScore = chemistryScore;
    this.biologyScore = biologyScore;
  }

  public String getName() {
    return name;
  }

  public int getPhysicsScore() {
    return physicsScore;
  }

  public int getChemistryScore() {
    return chemistryScore;
  }

  public int getBiologyScore() {
    return biologyScore;
  }
}
