package com.company.JSON;

import com.company.Employee.Manager;
import com.company.Employee.Staff;
import com.company.common.WriterWorker;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class JSONWriter {
  public static void writeStaffs(ArrayList<Staff> staffs) {
    WriterWorker.cleanup("Staff.txt");
    for (Staff staff : staffs) {
      JSONObject jo = new JSONObject();
      jo.put("IDKaryawan", staff.getIDKaryawan());
      jo.put("Nama", staff.getNama());
      jo.put("Tunjangan Pulsa", staff.getTunjanganPulsa());
      jo.put("Gaji Pokok", staff.getGajiPokok());
      jo.put("Absensi Hari", staff.Absensi());

      jo.put("Tunjangan Makan", staff.getTunjanganMakan());
      jo.put("Emails", staff.getEmails());

      WriterWorker.write(jo.toJSONString(), "Staff.txt", true);
    }
  }

  public static void writeManagers(ArrayList<Manager> managers) {
    WriterWorker.cleanup("Manager.txt");
    for (Manager manager : managers) {
      JSONObject jo = new JSONObject();

      jo.put("IDKaryawan", manager.getIDKaryawan());
      jo.put("Nama", manager.getNama());
      jo.put("Tunjangan Pulsa", manager.getTunjanganPulsa());
      jo.put("Gaji Pokok", manager.getGajiPokok());
      jo.put("Absensi Hari", manager.Absensi());

      jo.put("Tunjangan Transport", manager.getTunjanganTransport());
      jo.put("Tunjangan Entertaint", manager.getTunjanganEntertaint());
      jo.put("Telepons", manager.getTelepons());

      WriterWorker.write(jo.toJSONString(), "Manager.txt", true);
    }
  }
}
