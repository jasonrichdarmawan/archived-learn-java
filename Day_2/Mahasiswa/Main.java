class Mahasiswa {
  private String sNama;
  private String sJenisKelamin;
  private int iUmur;

  void sSetNama(String newValue) {
    sNama = newValue;
  }

  void getNama() {
    System.out.println(sNama);
  }

  void iSetJK(int newValue) {
    if (newValue == 0) { sJenisKelamin = "Laki - Laki"; }
    else if (newValue == 1) { sJenisKelamin = "Perempuan"; }
  }

  void getJK() {
    System.out.println(sJenisKelamin);
  }

  void iSetUmur(int newValue) {
    iUmur = newValue;
  }

  void getUmur() {
    System.out.println(iUmur);
  }

  void printMahasiswa() {
    System.out.println("Nama: " + sNama + " Jenis Kelamin: " + sJenisKelamin + " Umur: " + iUmur);
  }
}

public class Main {
  public static void main(String[] args) {
    Mahasiswa mahasiswa1 = new Mahasiswa();

    mahasiswa1.sSetNama("Siapa");
    mahasiswa1.iSetJK(0);
    mahasiswa1.iSetUmur(20);
    mahasiswa1.printMahasiswa();
  }
}
