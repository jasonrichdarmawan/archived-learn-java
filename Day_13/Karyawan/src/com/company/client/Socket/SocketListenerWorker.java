package com.company.client.Socket;

import com.company.client.datasource.MySQLDataSource;
import com.company.client.model.Staff;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.IOException;

import static com.company.client.Socket.SocketUtil.socket;
import static com.company.client.util.MenuUtil.staffs;

public class SocketListenerWorker implements Runnable {
  @Override
  public void run() {
    while (!socket.isClosed()) {
      DataInputStream dis = null;
      try {
        dis = new DataInputStream(socket.getInputStream());
        String response = dis.readUTF();
        System.out.println("Receiving response: " + response);

        Object object = null;
        try {
          object = new JSONParser().parse(response);
        } catch (ParseException e) {
          e.printStackTrace();
        }

        JSONObject jsonObject = (JSONObject) object;
        int id = (int) (long) jsonObject.get("ID");
        String nama = (String) jsonObject.get("Nama");
        float gajiPokok = (float) (double) jsonObject.get("GajiPokok");
        int absensi = (int) (long) jsonObject.get("JmlAbsensi");
        int jumlahCuti = (int) (long) jsonObject.get("JmlCutiTerpakai");
        int statusKaryawan = (int) (long) jsonObject.get("StatusKaryawan");

        MySQLDataSource.updateKaryawanById(id, nama, gajiPokok, absensi, jumlahCuti, statusKaryawan);

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
