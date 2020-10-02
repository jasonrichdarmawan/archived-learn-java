// package_name.sub_package_name.class
import java.util.Locale;
import java.text.NumberFormat;

class Karyawan {
  // private, atribut hanya dapat diakses dengan get dan set method.
  private String sNama;
  private String sJabatan;
  // tujuan menggunakan data type int: support gaji hingga 2 miliar / jarang ada karyawan dengan gaji 2 miliar per periode.
  private int iGaji;

  // constructor untuk mempersingkat line of code di class Main.
  Karyawan(String sNama, String sJabatan, int iGaji) {
    this.sNama = sNama; // this.sName adalah atribut milik class Karyawan;
    this.sJabatan = sJabatan; // sJabatan adalah atribut milik constructor Karyawan;
    this.iGaji = iGaji;
  }

  void sSetNama(String newValue) {
    sNama = newValue;
  }

  String getNama() {
    return sNama;
  }

  void sSetJabatan(String newValue) {
    sJabatan = newValue;
  }

  String getJabatan() {
    return sJabatan;
  }

  void iSetGaji(int newValue) {
    iGaji = newValue;
  }

  int getGaji() {
    return iGaji;
  }

  void printStates() {
    Locale locale = new Locale("en", "ID");
    // tampilkan mata uang berdasarkan new Locale("language", "region") dengan method getCurrencyInstance();
    // contoh output: IDR16,000,000.00
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
    System.out.println("Nama: " + sNama + " Jabatan: " + sJabatan + " Gaji: " + currencyFormatter.format(iGaji));
  }
}

public class Main {
  public static void main(String[] args) {
    Karyawan manager = new Karyawan("Si Manajer", "Manager", 20000000);
    manager.printStates();

    Karyawan supervisor = new Karyawan("Si Supervisor", "Supervisor", 16000000);
    supervisor.printStates();

    Karyawan staff = new Karyawan("Si Staff", "Staff", 10000000);
    // contoh tanpa method getCurrencyInstance();
    // expected output: Rp10000000
    System.out.println("Nama: " + staff.getNama() + " Jabatan: " + staff.getJabatan() + " Gaji: Rp" + staff.getGaji());
  }
}
