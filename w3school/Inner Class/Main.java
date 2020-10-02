class OuterClass {
  int x = 10;

  class InnerClass {
    public int iGetX() {
      return x;
    }
  }
}

public class Main {
  public static void main(String[] args) {
    OuterClass myOuter = new OuterClass();
    OuterClass.InnerClass myInner = myOuter.new InnerClass();
    System.out.println(myInner.iGetX());
  }
}

// static inner class can't reference outer class.