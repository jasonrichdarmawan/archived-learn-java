import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class SearchMahasiswaUtils {
  private MahasiswaBlueprint focusMahasiswa = null;
  private int index = 0;

  SearchMahasiswaUtils(ArrayList<MahasiswaBlueprint> mahasiswas, BufferedReader br) {
    showMenu(mahasiswas, br);
  }
  
  public MahasiswaBlueprint getFocusMahasiswa() {
    return focusMahasiswa;
  }

  // private: no need for subclass to inherit this.
  private void setFocusMahasiswa(MahasiswaBlueprint mahasiswa) {
    this.focusMahasiswa = mahasiswa;
  }

  public int getIndex() {
    return index;
  }

  // private: no need for subclass to inherit this.
  private void setIndex(int index) {
    this.index = index;
  }

  // searchByName => return array list of mahasiswa with matching criteria
  public static ArrayList<MahasiswaBlueprint> searchByName(ArrayList<MahasiswaBlueprint> mahasiswas,
      BufferedReader br) {
    Main.clearConsole();

    String nama = "";
    try {
      System.out.print("Masukkan nama mahasiswa: ");
      nama = br.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }

    ArrayList<MahasiswaBlueprint> tempMahasiswas = new ArrayList<>();

    for (MahasiswaBlueprint mahasiswa : mahasiswas) {
      if (!tempMahasiswas.contains(mahasiswa) && mahasiswa.getNama().contains(nama)) {
        tempMahasiswas.add(mahasiswa);
      }
    }

    return tempMahasiswas;
  }

  // show menu => update instance variable. => pakai getters untuk get.
  public void showMenu(ArrayList<MahasiswaBlueprint> mahasiswas, BufferedReader br) {
    ArrayList<MahasiswaBlueprint> tempMahasiswas = SearchMahasiswaUtils.searchByName(mahasiswas, br);

    // if found only 1 mahasiswa with exact name.
    if (tempMahasiswas.size() == 1) {
      setFocusMahasiswa(tempMahasiswas.get(0));
    }
    // if found more than 1 mahasiswa with similar name.
    else if (tempMahasiswas.size() > 1) {
      Main.clearConsole();
      for (MahasiswaBlueprint mahasiswa : mahasiswas) {
        MahasiswaUtils.printMahasiswaInfo(mahasiswa);
      }
      System.out.println("Ditemukan lebih dari 1 mahasiswa dengan nama yang mirip");
      System.out.print("Masukkan ID mahasiswa yang ingin dipilih: ");
      int id = 0;
      try {
        id = Integer.parseInt(br.readLine());
      } catch (IOException e) {
        e.printStackTrace();
      }

      for (int i = 0; i < mahasiswas.size(); i++) {
        MahasiswaBlueprint mahasiswa = mahasiswas.get(i);
        if (mahasiswa.getID() == id) {
          setIndex(i);
          setFocusMahasiswa(mahasiswa);
          break;
        }
      }
    }
  }
}
