import java.util.Arrays;

public class cmd2 {

  static void Calc(int feature, float a) {
    // Kubus & Bola
    switch (feature) {
      // Volume Kubus
      case 1:
        System.out.println(a * a * a);
        break;
      // Volume Bola
      case 2:
        System.out.println(4/3F * 22/7F * a * a * a);
        break;
      case 3:
        createRow(a);
        break;
      default:
        System.out.println("Invalid feature.");
    }
  }

  static void createRow(float a) {
    // convert float a with casting
    int length = (int) a;
    // initialize an empty two-dimensional array;
    // suppose length is Row.
    String[][] twoDim = new String[length][];

    // create an array with initial values;
    if (length == 2) {
      // Before twoDim[0] && twoDim[1]
      // []
      // []
      // []

      // After twoDim[0] && twoDim[1]
      // [["Satu, Dua, Tiga, Empat,"]]
      // [["Lima, Enam,"]]
      
      twoDim[0] = new String[]{"Satu, Dua, Tiga, Empat,"};
      twoDim[1] = new String[]{"Lima, Enam,"};

    } else if (length == 3) {
      // similar to the first if statement.
      twoDim[0] = new String[]{"Satu, Dua, Tiga, Empat,"};
      twoDim[1] = new String[]{"Lima, Enam,"};
      twoDim[2] = new String[]{"Tujuh, Delapan, Sembilan,"};
    }

    for (int index = 0; index < length; index++) {
      String target = Arrays.toString(twoDim[index]);
      System.out.println(
        // remove the "[" && "]" from the sysout();
        target.substring(1, target.length() - 1)
      );
    }
  }

  public static void main(String[] args) {
    int feature = Integer.parseInt(args[0]);

    // call using static method
    // instead of creating new object with constructor.
    // it takes less memory heap.
    Calc(
      feature,
      Float.parseFloat(args[1])
    );
  }
}
