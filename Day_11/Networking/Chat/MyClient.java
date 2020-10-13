import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Properties;

public class MyClient {
  static InputStreamReader isr = new InputStreamReader(System.in);
  static BufferedReader br = new BufferedReader(isr);

  // run: java MyClient.java config.properties
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
          String IP = prop.getProperty("IP");
          int PORT = Integer.parseInt(prop.getProperty("PORT"));
          Socket s = new Socket(IP, PORT);
          DataInputStream dis = new DataInputStream(s.getInputStream());
          DataOutputStream dout = new DataOutputStream(s.getOutputStream());

          boolean isExit = false;
          while (!isExit) {
            String message = br.readLine(); // send message
            switch (message.toLowerCase()) {
              case "exit":
                isExit = true;
                br.close();
                dout.close();
                s.close();
                input.close();
                break;
              default:
                dout.writeUTF(message);
                dout.flush();

                // receive message
                message = dis.readUTF();
                System.out.println("from server: " + message);
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}
