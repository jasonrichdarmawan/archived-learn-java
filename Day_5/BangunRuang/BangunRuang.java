public class BangunRuang {
  int feature;
  float result;

  // Constructor Volume Balok
  BangunRuang(int feature, float panjang, float lebar, float tinggi) {
    if (feature == 1) {
      this.feature = feature;
      this.result = panjang * lebar * tinggi;
    } else {
      System.out.println("Invalid feature.");
    }
  }

  // Constructor Volume Bola, Volume Kubus
  BangunRuang(int feature, float value) {
    switch (feature) {
      // Volume Bola
      case 2:
        this.feature = feature;
        this.result = 22/7F * value * value * value * 4/3F;
        break;
      // Volume Kubus
      case 3:
        this.feature = feature;
        this.result = value * value * value;
        break;
      default:
        System.out.println("Invalid feature.");
    }
  }

  // method untuk menampilkan hasil perhitungan
  void printState() {
    switch (this.feature) {
      case 1:
        System.out.println("Volume Balok: " + this.result);
        break;
      case 2:
        System.out.println("Volume Bola: " + this.result);
        break;
      case 3:
        System.out.println("Volume Kubus: " + this.result);
        break;
      default:
        System.out.println("Invalid feature.");
    }
  }

  // method untuk menampilkan dari 3 nilai;
  static void printStates(float...array) {
    System.out.println(
      "Volume Balok: " + array[0] + '\n' +
      "Volume Bola: " + array[1] + '\n' +
      "Volume Kubus: " + array[2] + '\n'
    );
  }

  // method untuk menghitung average
  float getState() {
    return this.result;
  }

  static void calcAverage(float...array) {
    float result = 0;
    for (int index = 0; index < array.length; index++) {
      result += array[index];
    }
    result = result / array.length;
    System.out.println("Average: " + result);
  }

  public static void main(String[] args) {

    System.out.println("Method getters State." + '\n');

    BangunRuang balok = new BangunRuang(1, 3, 4, 5);
    balok.printState();
    BangunRuang bola = new BangunRuang(2, 7);
    bola.printState();
    BangunRuang kubus = new BangunRuang(3, 3);
    kubus.printState();

    System.out.println('\n' + "Method getters Average." + '\n');

    // Memanggil method untuk menghitung summary berdasarkan parameter
    // yang diberikan.
    calcAverage(balok.getState(), bola.getState(), kubus.getState());

    System.out.println('\n' + "Method getters Summary." + '\n');

    // Memanggil method untuk menampilkan summary berdasarkan parameter
    // yang diberikan
    printStates(balok.getState(), bola.getState(), kubus.getState());
  }
}
