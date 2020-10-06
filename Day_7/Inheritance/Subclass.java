public class Subclass extends Superclass {
  int id;

  Subclass(int id, int age) {
    super(age);
    this.id = id;
  }

  public static void main(String argd[]) {
    Subclass s = new Subclass(1, 24);
    s.getAge();
  }
}

class Superclass {
  int age; // Protected

  Superclass(int age) {
    this.age = age;
  }

  public void getAge() {
    System.out.println("The value of the variable named age in super class is: " + age);
  }
}