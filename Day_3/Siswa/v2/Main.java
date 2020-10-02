import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class Siswa {
  private String sNama = "Siapa";
  private int iNilai = 80;
  private String sGender = "Laki-Laki";
  private String sTglLahir = "02/08/1997";

  // getDateTimeToString helper
  String getDateToString(int i) {
    // String concat "0" if parameter i < 10
    return i < 10 ? "0" + Integer.toString(i) : Integer.toString(i);
  }

  // receive parameter d, m, yyyy in integer and then String concat
  // to "dd/MM/yyyy" and then return the value.
  String getDateTimeToString(int iDay, int iMonth, int iYear) {
    String sDay = getDateToString(iDay);
    String sMonth = getDateToString(iMonth);
    String sYear = getDateToString(iYear);

    return sDay + "/" + sMonth + "/" + sYear;
  }

  // getUmur receive parameter of date "today" in int
  // return the YEARS between birthDate and "today"
  int getUmur(int iDay, int iMonth, int iYear) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    LocalDate birthDate = LocalDate.parse(this.sTglLahir, dateTimeFormatter);
    LocalDate today = LocalDate.parse(getDateTimeToString(iDay, iMonth, iYear),dateTimeFormatter);

    long lYearsBetween = ChronoUnit.YEARS.between(birthDate, today);

    return (int) lYearsBetween;
  }

  String getNilai() {
    String sNilai;

    // check if instance attr iNilai >= x ? if true then set sNilai
    // with a specified grade.
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
    LocalDate today = LocalDate.now();
    int iDay = today.getDayOfMonth();
    int iMonth = today.getMonthValue();
    int iYear = today.getYear();

    String sKelas = "";

    // if Umur >= 23 change the sKelas with the specified data.
    // else change the sKelas with the specified data.
    if (getUmur(iDay,iMonth,iYear) >= 23) sKelas = "Sudah Lulus, Sedang Bekerja";
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
    String[] aTglLahir = this.sTglLahir.split("/");

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
      "Umur: " + siswa1.getUmur(30,9,2020) + '\n' +
      "Nilai: " + siswa1.getNilai() + '\n' +
      "Kelas: " + siswa1.getKelas() + '\n' +
      "Tanggal Lahir: " + siswa1.getTglLahir() + '\n'
    );

    // baby was born in August 1997
    // expected result is 0.
    System.out.println("Umur di Tahun 1997: " + siswa1.getUmur(1,1,1997));
  }
}