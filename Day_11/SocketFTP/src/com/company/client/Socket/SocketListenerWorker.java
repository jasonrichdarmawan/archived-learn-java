package com.company.client.Socket;

import com.company.client.Student.Student;

import java.io.DataInputStream;
import java.io.IOException;

import static com.company.client.Menu.MenuUtil.students;
import static com.company.client.Socket.SocketUtil.socket;

public class SocketListenerWorker implements Runnable {

  SocketListenerWorker() {
    Thread thread = new Thread(this);
    thread.start();
  }

  @Override
  public void run() {
    while (!socket.isClosed()) {
      DataInputStream dis = null;
      try {
        dis = new DataInputStream(socket.getInputStream());
        String response = dis.readUTF();
//        System.out.println("Receiving response: " + response);

        String[] resSplit = response.split(",");
        String name = resSplit[0];
        int physicsScore = Integer.parseInt(resSplit[1]);
        int chemistryScore = Integer.parseInt(resSplit[2]);
        int biologyScore = Integer.parseInt(resSplit[3]);
        Student student = new Student(name, physicsScore, chemistryScore, biologyScore);
        students.add(student);
      } catch (IOException e) {
        e.printStackTrace();
        try {
          dis.close();
          socket.close();
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
      }
    }
  }
}
