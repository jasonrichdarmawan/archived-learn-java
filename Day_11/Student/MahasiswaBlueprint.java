import java.util.ArrayList;

public class MahasiswaBlueprint extends MahasiswaAbstract {

  public MahasiswaBlueprint(int iD, String nama, ArrayList<Double> nilai) {
    super(iD, nama, nilai);
  }

  @Override
  void MengisiNilai(double bahasaInggris, double fisika, double algoritma) {
    ArrayList<Double> nilai = new ArrayList<>();
    nilai.add(bahasaInggris);
    nilai.add(fisika);
    nilai.add(algoritma);
    setNilai(nilai);
  }

  public double HitungNilaiMedian() {
    double totalNilai = 0.0d;
    for (double nilai : getNilai()) {
      totalNilai += nilai;
    }

    return totalNilai / 3;
  }
  
}
