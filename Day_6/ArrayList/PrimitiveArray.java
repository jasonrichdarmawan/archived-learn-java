public class PrimitiveArray {
  public static void main(String[] args) {
    Student s1 = new Student(1);
    Student s2 = new Student(1);

    Student[] sArray = new Student[2];
    sArray[0] = s1;
    sArray[1] = s2;
    System.out.println(sArray[0].id);
  }
}

class Student {
  int id;

  Student(int id) {
    this.id = id;
  }
}