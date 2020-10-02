public class Mobil {
  // attribute Mobil
  private int tahun;
  private String merek;
  private int kecepatan;

  // constructor untuk membuat Object Mobil
  Mobil(int tahun, String merek, int kecepatan) {
    this.tahun = tahun;
    this.merek = merek;
    this.kecepatan = kecepatan;
  }

  // method untuk menambah kecepatan
  void Tambah_Kecepatan(int kecepatan) {
    this.kecepatan += kecepatan;
    System.out.println("Menambah kecepatan. Kecepatan sekarang: " + this.kecepatan);
  }

  // method untuk mengurangi kecepatan
  void Kurangi_Kecepatan(int kecepatan) {
    this.kecepatan -= kecepatan;
    System.out.println("Mengurangi kecepatan. Kecepatan sekarang: " + this.kecepatan);
  }

  // method untuk sysout() attribute yang dimiliki oleh Object.
  void printStates() {
    System.out.println(
      "Tahun: " + this.tahun + '\n' +
      "Merek: " + this.merek + '\n' +
      "Kecepatan: " + this.kecepatan
    );
  }
  public static void main(String[] args) {
    Mobil mobil1 = new Mobil(2020, "Tesla", 0);
    mobil1.printStates(); // sysout() attribute yang dimiliki Object mobil1
    mobil1.Tambah_Kecepatan(10); // Setters untuk mengubah attribute Object mobil1
    mobil1.Kurangi_Kecepatan(5); // Setters.
  }
}
