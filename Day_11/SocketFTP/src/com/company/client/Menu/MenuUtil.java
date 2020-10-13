package com.company.client.Menu;

import com.company.client.MainUtil;
import com.company.client.Socket.SocketUtil;
import com.company.client.Student.Student;
import com.company.client.common.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.company.client.Main.*;

public class MenuUtil {
  public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
  public static ArrayList<Student> students = new ArrayList<>();

  public static void printMenu() {
    MainUtil.clearTerminal();
    System.out.println("MENU");
    System.out.println("1. Connect Socket");
    System.out.println("2. Create FileProses.txt");
    System.out.println("3. Tampilkan dilayar, Tulis ke File, Connect FTP - Kirim FTP (Multi Threading)");
    System.out.println("4. Download dari FTP Server");
    System.out.println("5. Close All Connection");
    System.out.println("99. Exit");
  }

  public static void showMenu() {
    boolean isExit = false;
    while (!isExit) {
      MainUtil.pressAnyKeyToContinue(bufferedReader);

      String menu = "";
      printMenu();
      try {
        System.out.print("Masukkan nomor menu: ");
        menu = bufferedReader.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      switch (menu) {
        case "1":
          SocketUtil.listen(SOCKET_SERVER, SOCKET_PORT, "get: /api/v1/file.txt");
          break;
        case "2":
          FileCleanUpUtil.cleanup("./FileProses.txt");
          for (Student student : students) {
            String name = "Nama : " + student.getName();
            String physicsScore = "Nilai Fisika : " + student.getPhysicsScore();
            String chemistryScore = "Nilai Kimia : " + student.getChemistryScore();
            String biologyScore = "Nilai Biologi : " + student.getBiologyScore();
            String[] messages = {name, physicsScore, chemistryScore, biologyScore};

            WriterWorker writerWorker = new WriterWorker("./FileProses.txt", messages, true);
          }
          break;
        case "3":
          FileCleanUpUtil.cleanup("./FileRata2.txt", "Nama,NilaiRata2");
          for (Student student : students) {
            String name = student.getName();
            int physicsScore = student.getPhysicsScore();
            int chemistryScore = student.getChemistryScore();
            int biologyScore = student.getBiologyScore();

            String[] messages = {"Nama: " + name, "Nilai Fisika : " + physicsScore, "Nilai Kimia : " + chemistryScore, "Nilai Biologi : " + biologyScore};
            PrintWorker printWorker = new PrintWorker(messages);

            String message = name + "," + ((physicsScore + chemistryScore + biologyScore) / 3);
            messages = new String[]{message};
            WriterWorker writerWorker = new WriterWorker("./FileRata2.txt", messages, false);
          }
          FTPUploadWorker ftpUploadWorker = new FTPUploadWorker(FTP_SERVER, FTP_PORT, FTP_USER, FTP_PASSWORD, "./FileProses.txt"); // sesuai dengan SDR "kirim ftp file no. 2"
          break;
        case "4":
          FTPDownloadUtil.showMenu(FTP_SERVER, FTP_PORT, FTP_USER, FTP_PASSWORD);
          break;
        case "5":
          if (SocketUtil.socket != null) {
            if (!SocketUtil.socket.isClosed()) {
              try {
                SocketUtil.socket.close();
              } catch (IOException e) {
                e.printStackTrace();
              } finally {
                System.out.println("java.net.Socket isClosed(): " + SocketUtil.socket.isClosed());
              }
            }
          }
          FTPClientUtil.disconnect();
          break;
        case "99":
          if (SocketUtil.socket != null) {
            if (!SocketUtil.socket.isClosed()) {
              try {
                SocketUtil.socket.close();
              } catch (IOException e) {
                e.printStackTrace();
              } finally {
                System.out.println("java.net.Socket isClosed(): " + SocketUtil.socket.isClosed());
              }
            }
          }
          FTPClientUtil.disconnect();
          isExit = true;
          break;
        default:
          System.out.println("Invalid menu's number.");
      }
    }
  }
}
