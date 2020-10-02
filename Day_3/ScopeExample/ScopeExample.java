public class ScopeExample {
  private int i = 1; // 9 //15

  public void firstMethod() {
    int i = 4, j = 5; // 1
    this.i = i + j; // 9
    System.out.println("firstMethod() local i: " + i + " instance i: " + this.i + " local i: " + i);
    secondMethod(7);
  }

  public void secondMethod(int i) {
    int j = 8; // 9
    this.i = i + j; // 15
    System.out.println("secondMethod() j: " + j + " instance  i: " + this.i + " local i: " + i);
  }
}
