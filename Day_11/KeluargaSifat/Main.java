import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
  public static void main(String[] args) {
    Properties prop = new Properties();
    InputStream input = null;

    try {
      input = new FileInputStream("./Properties/" + args[0]);

      // load a properties file
      prop.load(input);

      // System.out.println(prop.getProperty("NamaAnak"));
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    Anak anak1 = new Anak(prop);
    anak1.NamaKakek();
    anak1.SifatKakek();

    anak1.NamaAyah();
    anak1.SifatAyah();

    anak1.NamaIbu();
    anak1.SifatIbu();

    anak1.NamaAnak();
    anak1.SifatAnak();
  }
}
