class ObjectConstructor {
  // atribute
  private String name;
  private int age;

  // constructor to create a new Instance of Object.
  ObjectConstructor(String name, int age) {
    this.setName(name);
    this.setAge(age);
  }

  // method
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}

public class ClassObject {
  public static void main(String[] args) {
    ObjectConstructor objectExample = new ObjectConstructor("Jason", 20); // This create new object with attributes.
    System.out.println("Name: " + objectExample.getName() + '\n' + "Age: " + objectExample.getAge()); // we can get the attributes using the method provided by the Object.
    System.out.println("Reference: " + objectExample); // Object stores Reference to where the attribute and method is stored in a memory heap.
  }
}