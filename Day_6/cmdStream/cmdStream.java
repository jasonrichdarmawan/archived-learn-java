import java.io.FileInputStream;
import java.io.FileOutputStream;

public class cmdStream {

  static void read() {
    try {
      FileInputStream fin = new FileInputStream(args[1]);
      int i = 0;
      while ((i = fin.read()) != -1) {
        System.out.print((char) i);
      }
      fin.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void main(String[] args) {
    // java cmdStream.java read test2.txt
    // java cmdStream.java write test2.txt "abcd efgh"
    switch (args[0]) {
      case "read":
        try {
          // FileInputStream read the byte one by one / char by char.
          // Alternatively, we can use BufferedRead to read the file in one go
          // instead of one by one.
          FileInputStream fin = new FileInputStream(args[1]);
          int i = 0;
          while ((i = fin.read()) != -1) {
            System.out.print((char) i);
          }
          fin.close();
        } catch (Exception e) {
          System.out.println(e);
        }
        break;
      case "write":
        try {
          // FileOutputStream write to the file one by one / char by char.
          // Alternatively, we can use BufferedWrite to write to the file in one go
          // instead of one by one.
          FileOutputStream fout = new FileOutputStream(args[1]);
          String s = args[2];
          byte b[] = s.getBytes();// converting string into byte array
          fout.write(b);
          fout.close();
          System.out.println("success...");
        } catch (Exception e) {
          System.out.println(e);
        }
        break;
      default:
        System.out.println("Invalid");
    }
  }
}
