import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SimpleThread {
  public static void print(int workersSize, String[] messages) {
    int i = 0;
    for (int j = 0; j <= workersSize; j++) {
      for (; i < messages.length; i++) {
        String message = messages[i];
        // max worker == workersSize
        while (Thread.activeCount() == workersSize) {
        }
        PrintWorker worker = new PrintWorker(message);
      }
    }

  }

  public static void asc(int workersSize, String[] messages) {
    String file = "./fileDataAsc.txt";
    cleanup(file);
    int i = 0;
    for (int j = 0; j <= workersSize; j++) {
      // max worker == workersSize
      for (; i < messages.length; i++) {
        String message = messages[i];
        while (Thread.activeCount() == workersSize + 1) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        WriterWorker worker = new WriterWorker(message, file);
      }
    }
  }

  public static void dsc(int workersSize, String[] messages) {
    String file = "./fileDataDsc.txt";
    cleanup(file);
    int i = messages.length - 1;
    for (int j = 0; j <= workersSize; j++) {
      // max worker == workersSize
      for (; i >= 0; i--) {
        String message = messages[i];
        while (Thread.activeCount() == workersSize + 1) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        WriterWorker worker = new WriterWorker(message, file);
      }
    }
  }

  // if does not exist => create new file
  // if exist => emptied it.
  public static void cleanup(String file) {
    FileWriter fw = null;
    try {
      fw = new FileWriter(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
    BufferedWriter br = new BufferedWriter(fw);

    try {
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
