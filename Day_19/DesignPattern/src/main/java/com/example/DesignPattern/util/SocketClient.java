package com.example.designpattern.util;

import com.example.designpattern.model.StaffUnpaidLeaveResponseEntity;
import com.example.designpattern.model.StaffWorkerModel;
import org.springframework.boot.CommandLineRunner;

import java.io.File;
import java.util.List;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

  public void send(List<StaffWorkerModel> staffs) {
    try {
      Socket socket = new Socket("localhost", 8082);

      DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
      StringBuilder textInMemory = new StringBuilder();
      textInMemory.append("ID,Nama,JmlAbsensi,JmlCutiTerpakai,JmlTanpaKabar,TotalGaji\n");
      for (StaffWorkerModel staff : staffs) {
        textInMemory.append(String.format(
                "%d,%s,%d,%d,%d,%s\n",
                staff.getID(),
                staff.getNama(),
                staff.getAbsensi(),
                staff.getIzin() == null ? 0 : 1,
                staff.getIzin() == null ? 0 : Math.max(0, staff.getIzin() - 1),
                staff.HitungTotalGaji() == null ? 0 : staff.HitungTotalGaji().toPlainString()
                )
        );
      }
      dataOutputStream.write(textInMemory.toString().getBytes());
      dataOutputStream.flush();

      textInMemory = null;
      dataOutputStream.close();
      socket.close();

    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
