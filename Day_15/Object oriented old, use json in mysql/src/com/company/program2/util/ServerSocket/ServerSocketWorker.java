package com.company.program2.util.ServerSocket;

import com.company.program2.datasource.StudentDataSource;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;

public class ServerSocketWorker implements Runnable {
  private Socket socket;
  private DataInputStream dataInputStream;
  private StudentDataSource studentDataSource;

  public ServerSocketWorker(Socket socket, String DATABASE_API, String DATABASE, String DATABASE_HOSTNAME, int DATABASE_PORT, String DATABASE_SCHEMA, String DATABASE_USER, String DATABASE_PASSWORD) {
    this.socket = socket;
    try {
      this.dataInputStream = new DataInputStream(this.socket.getInputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.studentDataSource = new StudentDataSource(DATABASE_API, DATABASE, DATABASE_HOSTNAME, DATABASE_PORT, DATABASE_SCHEMA, DATABASE_USER, DATABASE_PASSWORD);
  }

  @Override
  public void run() {
    while (!socket.isClosed()) {
      String line = "";
      try {
        line = dataInputStream.readUTF();
        System.out.println("Receiving from socket port: " + socket.getLocalPort() + " payload: " + line);
      } catch (IOException e) {
        e.printStackTrace();
        try {
          socket.close();
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
      }

      Object object = null;
      try {
        object = new JSONParser().parse(line);
      } catch (ParseException e) {
        e.printStackTrace();
      }

      JSONObject jsonObject = (JSONObject) object;

      jsonObject.keySet().forEach(key -> {
        Object value = jsonObject.get(key);
        String sql = String.format("INSERT INTO students (name, payload) VALUES ('%s', '%s')", key, value);
        this.studentDataSource.insertStudent(sql);
      });

    }

  }
}
