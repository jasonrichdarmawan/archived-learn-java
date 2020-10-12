// // write
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.io.OutputStream;
// import java.util.Properties;

// public class App {
//   public static void main(String[] args) {
//     Properties prop = new Properties();
//     OutputStream output = null;
//     try {
//       output = new FileOutputStream("config.properties");
//       // set the properties value
//       prop.setProperty("server", "localhost");
//       prop.setProperty("port", "8080");
//       // save properties to project root folder
//       prop.store(output, null);
//     } catch (IOException io) {
//       io.printStackTrace();
//     } finally {
//       if (output != null) {
//         try {
//           output.close();
//         } catch (IOException e) {
//           e.printStackTrace();
//         }
//       }
//     }
//   }
// }

// read
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {
  public static void main(String[] args) {
    Properties prop = new Properties();
    InputStream input = null;
    try {
      input = new FileInputStream("config.properties");
      // load a properties file
      prop.load(input);
      // get the property value and print it out
      System.out.println(prop.getProperty("server"));
      System.out.println(prop.getProperty("port"));
    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}