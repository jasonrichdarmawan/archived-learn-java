class Vehicle {
  protected String brand = "Ford";

  public void honk() {
    System.out.println("Tuut, tuut!");
  }
}

public class Car extends Vehicle {
  private String modelName = "Mustang";

  public static void main(String[] args) {

    // Create a myCar object
    // <object_type> <object_name> = new <call to a constructor of a class>;
    Car myCar = new Car();

    myCar.honk();

    // If <object_type> Vehicle were used instead of the current example "Car"
    // myCar cannot access attribute modelName.
    System.out.println(myCar.brand + " " + myCar.modelName);
    
  }
}
