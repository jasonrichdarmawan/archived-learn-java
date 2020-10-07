import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) {
    SystemInReader sysin = new SystemInReader(); // unnecessary.

    try {
      String input = sysin.getBr().readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }

    MenuUtils.read(sysin);
    FeatureUtils.read(sysin);

    sysin.close();

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    try {
      String input = br.readLine();
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

class SystemInReader {
  private InputStreamReader r = new InputStreamReader(System.in);
  private BufferedReader br = new BufferedReader(r);

  protected void close() {
    try {
      this.r.close();
      this.br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public InputStreamReader getR() {
    return r;
  }

  public BufferedReader getBr() {
    return br;
  }

  // @Override
  // protected void finalize() throws Throwable {
  // try {
  // this.r.close();
  // this.br.close();
  // System.out.println("Closing BufferedReader..");
  // } catch (IOException e) {
  // e.printStackTrace();
  // }
  // }
}

class MenuUtils {
  public static void read(SystemInReader sysin) {
    try {
      String input = sysin.getBr().readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

class FeatureUtils {
  public static void read(SystemInReader sysin) {
    try {
      String input = sysin.getBr().readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}