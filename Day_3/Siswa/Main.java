import java.util.Date;

class Siswa {
  private String sNama = "Siapa";
  private int iNilai = 80;
  private String sGender = "Laki-Laki";
  private String sTglLahir = "2-8-1997";

  // TODO: quick fix, otherwise use package to calculate the difference between 2 date in milliseconds and get the year.
  int getUmur(int tahun) {
    String[] aTglLahir = this.sTglLahir.split("-");

    String a = aTglLahir[2];
    int b = Integer.parseInt(a);
    return tahun - b;
  }

  String getNilai() {
    String sNilai;

    // TODO: nilai lain.
    // switch (this.iNilai) {
    //   case 80:
    //   sNilai = "A";
    //   break;
      
    //   default:
    //   sNilai = "Invalid";
    //   break;
    // }
    if (this.iNilai >= 80) {
      sNilai = "A";
    } else if (this.iNilai >= 70) {
      sNilai = "B";
    } else if (this.iNilai >= 60) {
      sNilai = "C";
    } else {
      sNilai = "D";
    }
    
    // and then return the local sNilai.
    return sNilai;
  }

  String getKelas() {
    String sKelas = "";

    // if Umur >= 23 change the sKelas with the specified data.
    // else change the sKelas with the specified data.
    if (getUmur(2020) >= 23) sKelas = "Sudah Lulus, Sedang Bekerja";
    else sKelas = "Belum Lulus, Sedang Bekerja";
    
    // and then this method return a string;
    return sKelas;
  }

  String iGetBulan(int value) {
    String sBulan;

    // TODO: bulan lain.
    // current requirement only bulan 8 -> Agustus.
    // switch check the local value;
    // if the value is match, change the local sBulan,
    // break the switch.
    switch (value) {
      case 8:
        sBulan = "Agustus";
        break;
      
      default:
        sBulan = "Invalid";
        break;
    }

    // and then return sBulan;
    return sBulan;
  }

  String getTglLahir() {
    // split the string 2-8-1997 into array of string ["2","8","1997"] 
    String[] aTglLahir = this.sTglLahir.split("-");

    // String concat into desireable form.
    // iGetBulan have int parameter.
    // It's intentional, it's easier to call getBulan(1)
    // then getBulan("1");
    String a = aTglLahir[0] + " " + iGetBulan(Integer.parseInt(aTglLahir[1])) + " " + aTglLahir[2];

    return a;
  }
}

public class Main {
  public static void main(String[] args) {
    Siswa siswa1 = new Siswa();

    System.out.println(
      "Umur: " + siswa1.getUmur(2020) + '\n' +
      "Nilai: " + siswa1.getNilai() + '\n' +
      "Kelas: " + siswa1.getKelas() + '\n' +
      "Tanggal Lahir: " + siswa1.getTglLahir() + '\n'
    );

    // baby was born in August 1997
    // expected result is 0.
    System.out.println("Umur di Tahun 1997: " + siswa1.getUmur(1997));
  }
}