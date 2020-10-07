import java.util.ArrayList;

public class Mahasiswa {
  private int iID;
  private String sName;
  private ArrayList<Double> arrDNilai;

  Mahasiswa(int iID, String sName, ArrayList<Double> arrDNilai) {
    this.iID = iID;
    this.sName = sName;
    this.arrDNilai = arrDNilai;
  }

  public int getiID() {
    return iID;
  }

  public String getsName() {
    return sName;
  }

  public void setsName(String sName) {
    this.sName = sName;
  }

  public ArrayList<Double> getarrDNilai() {
    return arrDNilai;
  }

}
