package com.codeflex.springboot.service;

import com.codeflex.springboot.datasource.StaffDataSource;
import com.codeflex.springboot.model.Staff;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("staffService")
public class StaffServiceImpl implements StaffService {
  public static StaffDataSource staffDataSource = new StaffDataSource("jdbc", "mysql", "localhost", 3306, "day18", "root", "password");

  @Override
  public Staff findById(int id) {
    Staff staff = null;
    String sql = String.format("SELECT * FROM staff WHERE IDKaryawan=%d LIMIT 1", id);
    ResultSet resultSet = staffDataSource.executeQuery(sql);
    while (true) {
      try {
        if (!resultSet.next()) {
          resultSet.close();
          break;
        }
        int IDKaryawan = resultSet.getInt("IDKaryawan");
        String Nama = resultSet.getString("Nama");
        float TunjanganPulsa = resultSet.getFloat("TunjanganPulsa");
        float GajiPokok = resultSet.getFloat("GajiPokok");
        int AbsensiHari = resultSet.getInt("AbsensiHari");

        ArrayList<String> Email = new ArrayList<>();
        Object object = new JSONParser().parse(resultSet.getString("Email"));
        JSONObject jsonObject = (JSONObject) object;
        JSONArray jsonArray = (JSONArray) jsonObject.get("email");
        Iterator<String> email = jsonArray.iterator();
        while (email.hasNext()) {
          Email.add(email.next());
        }

        staff = new Staff(IDKaryawan, Nama, TunjanganPulsa, GajiPokok, AbsensiHari, Email);
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    return staff;
  }

  @Override
  public void saveStaff(Staff staff) {
    int IDKaryawan = staff.getIDKaryawan();
    String Nama = staff.getNama();
    float TunjanganPulsa = staff.getTunjanganPulsa();
    float GajiPokok = staff.getGajiPokok();
    int AbsensiHari = staff.getAbsensiHari();

    ArrayList<String> Email = staff.getEmail();
    JSONArray jsonArray = new JSONArray();
    for (String email : Email) {
      jsonArray.add(email);
    }
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("email", jsonArray);

    String sql = String.format("INSERT INTO staff (IDKaryawan, Nama, TunjanganPulsa, GajiPokok, AbsensiHari, Email) VALUES (%d, '%s', %.3f, %.3f, %d, '%s')", IDKaryawan, Nama, TunjanganPulsa, GajiPokok, AbsensiHari, jsonObject.toString());
    staffDataSource.executeUpdate(sql);
  }

  @Override
  public void updateStaff(Staff staff) {
    int IDKaryawan = staff.getIDKaryawan();
    String Nama = staff.getNama();
    float TunjanganPulsa = staff.getTunjanganPulsa();
    float GajiPokok = staff.getGajiPokok();
    int AbsensiHari = staff.getAbsensiHari();
    List<String> Email = staff.getEmail();
    JSONArray jsonArray = new JSONArray();
    for (String email : Email) {
      jsonArray.add(email);
    }
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("email", jsonArray);
    String sql = String.format("UPDATE staff SET Nama='%s', TunjanganPulsa=%.3f, GajiPokok=%.3f, AbsensiHari=%d, Email='%s' WHERE IDKaryawan=%d", Nama, TunjanganPulsa, GajiPokok, AbsensiHari, jsonObject.toJSONString(), IDKaryawan);
    staffDataSource.executeUpdate(sql);
  }

  @Override
  public void deleteStaffById(int id) {
    String sql = String.format("DELETE FROM staff WHERE IDKaryawan=%d", id);
    staffDataSource.executeUpdate(sql);
  }

  @Override
  public List<Staff> findAllStaffs() {
    List<Staff> staffs = new ArrayList<>();

    ResultSet resultSet = staffDataSource.executeQuery("SELECT * FROM staff");
    while (true) {
      try {
        if (!resultSet.next()) {
          resultSet.close();
          break;
        }
        ;
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }

      try {
        int IDKaryawan = resultSet.getInt("IDKaryawan");
        String Nama = resultSet.getString("Nama");
        float TunjanganPulsa = resultSet.getFloat("TunjanganPulsa");
        float GajiPokok = resultSet.getFloat("GajiPokok");
        int AbsensiHari = resultSet.getInt("AbsensiHari");

        ArrayList<String> Email = new ArrayList<>();
        Object object = new JSONParser().parse(resultSet.getString("Email"));
        JSONObject jsonObject = (JSONObject) object;
        JSONArray jsonArray = (JSONArray) jsonObject.get("email");
        Iterator<String> email = jsonArray.iterator();
        while (email.hasNext()) {
          Email.add(email.next());
        }

        staffs.add(new Staff(IDKaryawan, Nama, TunjanganPulsa, GajiPokok, AbsensiHari, Email));

      } catch (SQLException throwables) {
        throwables.printStackTrace();
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }

    return staffs;
  }

  @Override
  public void deleteAllStaffs() {
    staffDataSource.executeUpdate("DELETE FROM staff");
  }
}
