class Mobil {
  String merek = "";
  private String warna = "";
  private int gigi = 0;
  private int kecepatan = 0;

  void tambahGigi(int increment) {
    gigi = gigi + increment;
    System.out.println("Mobil tambah gigi: " + gigi + " kecepatan bertambah.");
  }

  void kurangGigi(int decrement) {
    gigi = gigi - decrement;
    System.out.println("Mobil turun gigi: " + gigi + " kecepatan berkurang.");
  }

  void mengerem() {
    kecepatan = 0;
  }
}