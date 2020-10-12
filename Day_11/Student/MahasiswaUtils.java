import java.util.ArrayList;

public class MahasiswaUtils {
  public static void printMahasiswaInfo(int id, String nama, ArrayList<Double> nilai, double nilaiMedian) {
    System.out.println("ID: " + id);
    System.out.println("Nama: " + nama);
    System.out.println("Bahasa Inggris: " + String.format("%.2f", nilai.get(0)));
    System.out.println("Fisika: " + String.format("%.2f", nilai.get(1)));
    System.out.println("Algoritma: " + String.format("%.2f", nilai.get(2)));
    System.out.println("Nilai Rata2: " + String.format("%.2f", nilaiMedian));
    System.out.println("");
  }

  public static void printMahasiswaInfo(MahasiswaBlueprint mahasiswa) {
    int id = mahasiswa.getID();
    String nama = mahasiswa.getNama();
    ArrayList<Double> nilai = mahasiswa.getNilai();
    double nilaiMedian = mahasiswa.HitungNilaiMedian();

    printMahasiswaInfo(id, nama, nilai, nilaiMedian);
  }

  public static void printMahasiswasMedianScore(ArrayList<MahasiswaBlueprint> mahasiswas) {
    String format = "%-10s  |  %-3.2f  %n";
    
    System.out.println("Nama        |  Nilai Rata2");
    for (MahasiswaBlueprint mahasiswa : mahasiswas) {
      System.out.format(format, mahasiswa.getNama(), mahasiswa.HitungNilaiMedian());
    }
  }
}
