import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TXTReader {
  public static String[] ReturnStrings(String file) {
    FileReader fr = null;
    try {
      fr = new FileReader(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    BufferedReader br = new BufferedReader(fr);
    String strLine = "";
    // to avoid NullPointerException
    // always call strLineStrings with if (strLine != null) statement.
    String[] strLineStrings = null;
    try {
      strLine = br.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (strLine != null) {
        strLineStrings = strLine.split(",");
      }
    }

    return strLineStrings;
  }
}
