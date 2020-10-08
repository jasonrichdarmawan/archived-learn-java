import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteDatabaseThread extends Thread {
  static final String file = "./database.txt";

  private ArrayList<Mahasiswa> mahasiswas = null;

  WriteDatabaseThread(ArrayList<Mahasiswa> mahasiswas) {
    this.mahasiswas = mahasiswas;
  }

  public void run() {
    writeDatabase(this.mahasiswas);
  }

  // this essentialy did one thing:
  // 1. get Object mahasiswas as argument
  // 2. destructure the object into one String => iID,sName,dNilai1,dNilai2,dNilai3
  // 3. write it to the database.
  // 4. close the buffer.
  public static void writeDatabase(ArrayList<Mahasiswa> mahasiswas) {
    FileWriter writer = null;
    try {
      writer = new FileWriter(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
    BufferedWriter buffer = new BufferedWriter(writer);

    Main.clearConsole();
    for (int index = 0; index < mahasiswas.size(); index++) {
      Mahasiswa mahasiswa = mahasiswas.get(index);

      String sLine = "";
      sLine += mahasiswa.getiID() + ",";
      sLine += mahasiswa.getsName() + ",";
      ArrayList<Double> arrDNilai = mahasiswa.getarrDNilai();
      for (int i = 0; i < arrDNilai.size(); i++) {
        double dNilai = arrDNilai.get(i);
        if (!(i == arrDNilai.size() - 1)) {
          sLine += dNilai + ",";
        } else {
          sLine += dNilai;
        }
      }

      try {
        buffer.write(sLine);
        if (index < mahasiswas.size() - 1) {
          buffer.newLine();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

    }

    try {
      buffer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
