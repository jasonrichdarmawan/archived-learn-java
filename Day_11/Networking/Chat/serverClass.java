import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class serverClass {
  static InputStreamReader isr = new InputStreamReader(System.in);
  static BufferedReader br = new BufferedReader(isr);

  // run: java serverClass.java config.properties
  public static void main(String[] args) {
    Properties prop = new Properties();
    InputStream input = null;
    try {
      input = new FileInputStream(args[0]);
      // load a properties file
      prop.load(input);
      // get the property value and print it out
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (input != null) {
        try {
          int PORT = Integer.parseInt(prop.getProperty("PORT"));
          ServerSocket ss = new ServerSocket(PORT);
          Socket s = ss.accept(); // establishes connection
          DataInputStream dis = new DataInputStream(s.getInputStream());
          DataOutputStream dout = new DataOutputStream(s.getOutputStream());

          boolean isExit = false;
          while (!isExit) {
            String message = (String) dis.readUTF(); // receive message
            switch (message.toLowerCase()) {
              case "exit":
                isExit = true;
                ss.close();
                input.close();
                break;
              default:
                System.out.println("from client: " + message);
                
                // send message
                message = br.readLine();
                dout.writeUTF(message);
                dout.flush();
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}
