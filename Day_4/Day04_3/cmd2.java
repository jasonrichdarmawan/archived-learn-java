class Calc {
  private float result;

  // Kubus & Lingkaran
  Calc(int feature, float a) {
    switch (feature) {
      // Kubus
      case 1:
        result = a * a * a;
        printState();
        break;
      // Bola
      case 2:
        result = 4/3F * 22/7F * a * a * a;
        printState();
        break;
      case 3:
        printLine(a * 3);
        break;
      default:
        System.out.println("Invalid feature.");
    }
  }

  void printLine(float a) {
    // convert float a with casting
    int length = (int) a;

    // for loop if (1) then sysout("Satu"); and so on.
    for (int index = 1; index <= length; index++) {
      switch (index) {
        case 1:
          System.out.print("Satu");
          break;
        case 2:
          System.out.print("Dua");
          break;
        case 3:
          System.out.print("Tiga");
          break;
        case 4:
          System.out.print("Empat");
          break;
        case 5:
          System.out.print("Lima");
          break;
        case 6:
          System.out.print("Enam");
          break;
        case 7:
          System.out.print("Tujuh");
          break;
        case 8:
          System.out.print("Delapan");
          break;
        case 9:
          System.out.print("Sembilan");
          break;
        default:
          System.out.print("Undefined");
      }
      
      // skip sysout(", ") if index == results.length
      if (index == length) {
        continue;
      }
      System.out.print(", ");
    }
  }

  void printState() {
    System.out.println(result);
  }
}

public class cmd2 {
  public static void main(String[] args) {
    int feature = Integer.parseInt(args[0]);

    Calc calc = new Calc(
      feature,
      Float.parseFloat(args[1])
    );
  }
}
