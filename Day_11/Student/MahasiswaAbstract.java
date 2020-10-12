import java.util.ArrayList;

public abstract class MahasiswaAbstract {
  private int ID;
  private String Nama;
  private ArrayList<Double> Nilai;

  public MahasiswaAbstract(int iD, String nama, ArrayList<Double> nilai) {
    this.ID = iD;
    this.Nama = nama;
    this.Nilai = nilai;
  }

  public int getID() {
    return ID;
  }

  public String getNama() {
    return Nama;
  }

  public ArrayList<Double> getNilai() {
    return this.Nilai;
  }

  public void setNilai(ArrayList<Double> nilai) {
    this.Nilai = nilai;
  }
  
  // abstract
  abstract void MengisiNilai(double BahasaInggris, double Fisika, double Algoritma);
}
