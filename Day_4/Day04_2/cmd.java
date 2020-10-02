import java.time.LocalDate;

class Calc {
  private float result;

  // Persegi & Lingkaran
  Calc(int feature, float a) {
    switch (feature) {
      case 1:
        result = a * a;
        break;
      case 2:
        result = 22 / 7F * a * a;
        break;
      default:
        System.out.println("Invalid feature.");
    }

    printState();
  }

  // Segitiga
  Calc(int feature, float a, float b) {
    switch (feature) {
      case 3:
        result = 1 / 2F * a * b;
        break;
      default:
        System.out.println("Invalid feature.");
    }

    printState();
  }

  Calc(int feature, int day, int month, int year) {
    switch (feature) {
      case 4:
        if (month >= LocalDate.now().getMonthValue()) {
          result = 2020 - year - 1;
        } else {
          result = 2020 - year;
        }
        break;
      default:
        System.out.println("Invalid feature.");
    }

    printState();
  }

  void printState() {
    System.out.println(result);
  }
}

public class cmd {
  public static void main(String[] args) {
    int feature = Integer.parseInt(args[0]);

    switch (feature) {
      case 1:
        Calc persegi = new Calc(
          1,
          Float.parseFloat(args[1])
        );
        break;
      case 2:
        Calc lingkaran = new Calc(
          2,
          Float.parseFloat(args[1])
        );
        break;
      case 3:
        Calc segitiga = new Calc(
          3,
          Float.parseFloat(args[1]),
          Float.parseFloat(args[2])
        );
        break;
      case 4:
        Calc umur = new Calc(
          4, 
          Integer.parseInt(args[1]),
          Integer.parseInt(args[2]),
          Integer.parseInt(args[3])
        );
        break;
      default:
        System.out.println("Invalid feature.");
    }

  }
}
