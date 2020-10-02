public class ArrayMultiDimensi {
  /**
   * Schema: [ [Nama, UTS, UAS, TUGAS], [Nama, UTS, UAS, TUGAS] ]
   */
  String[][] mahasiswa;

  // Constructor untuk membuat object.
  ArrayMultiDimensi(int size) {
    this.mahasiswa = new String[size][];
  }

  // method untuk tambah mahasiswa ke Object.
  void addMahasiswa(String nama, String uts, String uas, String tugas) {
    for (int index = 0; index < this.mahasiswa.length; index++) {
      if (this.mahasiswa[index] == null) {
        this.mahasiswa[index] = new String[] { nama, uts, uas, tugas };
        break;
      }
    }
  }

  // method to print data per row.
  void printState() {
    for (int index = 0; index < this.mahasiswa.length; index++) {
      String[] data = this.mahasiswa[index];
      float result = 0.35F * Float.parseFloat(data[1]) + 0.45F * Float.parseFloat(data[2]) + 0.2F * Float.parseFloat(data[3]);
      System.out.println(
        "|  " + this.mahasiswa[index][0] + "  "+ 
        "|   " + this.mahasiswa[index][1] + "  " +
        "|   " + this.mahasiswa[index][2] + "  " +
        "|    " + this.mahasiswa[index][3] + "   " +
        "|      " + result + "     |"
      );
    }
  }

  // method to print the summary.
  void printSummary() {
    System.out.println("+--------+-------+-------+---------+---------------+");
    System.out.println("|  Nama  |  UTS  |  UAS  |  TUGAS  |  NILAI AKHIR  |");
    System.out.println("+--------+-------+-------+---------+---------------+");
    this.printState();
    System.out.println("+--------+-------+-------+---------+---------------+");
  }

  public static void main(String[] args) {
    ArrayMultiDimensi mahasiswa = new ArrayMultiDimensi(3);
    // add data "mahasiswa" to the attribute.
    mahasiswa.addMahasiswa("Ardi", "60", "70", "90");
    mahasiswa.addMahasiswa("Pian", "80", "70", "90");
    mahasiswa.addMahasiswa("Robi", "70", "60", "90");

    // print the summary
    mahasiswa.printSummary();
  }
}
