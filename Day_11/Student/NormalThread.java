import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class NormalThread {
  public static void print(int workersSize, ArrayList<MahasiswaBlueprint> mahasiswas) {
    int i = 0;
    for (int j = 0; j <= workersSize; j++) {
      for (; i < mahasiswas.size(); i++) {
        MahasiswaBlueprint mahasiswa = mahasiswas.get(i);
        // max worker == workersSize
        while (Thread.activeCount() == workersSize) {
        }
        PrintWorker worker = new PrintWorker(mahasiswa);
      }
    }
  }

  public static void write(int workersSize, ArrayList<MahasiswaBlueprint> mahasiswas) {
    String file = "./LaporanNilaiMahasiswa.txt";
    String firstline = "ID,Nama,Nilai Rata2";
    cleanup(file, firstline);
    int i = 0;
    for (int j = 0; j <= workersSize; j++) {
      // max worker == workersSize
      for (; i < mahasiswas.size(); i++) {
        MahasiswaBlueprint mahasiswa = mahasiswas.get(i);
        while (Thread.activeCount() == workersSize + 1) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        WriterWorker worker = new WriterWorker(mahasiswa, file);
      }
    }
  }

  // if does not exist => create new file
  // if exist => emptied it.
  public static void cleanup(String file, String firstline) {
    FileWriter fw = null;
    try {
      fw = new FileWriter(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
    BufferedWriter br = new BufferedWriter(fw);

    try {
      br.write(firstline);
      br.newLine();
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
