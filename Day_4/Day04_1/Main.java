class Luas {
  private float result;
  // Segitiga & Lingkaran
  Luas(float a, float b, float c) {
    result = a * b * c;
    printState();
  }
  // Persegi
  Luas(float a, float b) {
    result = a * b;
    printState();
  }
  void printState() {
    System.out.println(result);
  }
}

public class Main {
  public static void main(String[] args) {
    Luas luasSegitiga = new Luas(1/2F, 2, 3);
    Luas luasPersegi = new Luas(2,2);
    Luas luasLingkaran = new Luas(22/7F, 3, 3);
  }
}
