package com.company.server.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONReaderUtil {
  public static List<JSONObject> readKaryawan(String fileName) {
    List<JSONObject> list = new ArrayList<>();

    BufferedReader bufferedReader = null;

    try {
      bufferedReader = new BufferedReader(new FileReader(fileName));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    try {
      bufferedReader.readLine(); // skip first line.

      String line = "";
      while ((line = bufferedReader.readLine()) != null) {
        String[] lineStrings = line.split(",");

        int id = Integer.parseInt(lineStrings[0]);
        String nama = lineStrings[1];
        float gajiPokok = Float.parseFloat(lineStrings[2]);
        int jmlAbsensi = Integer.parseInt(lineStrings[3]);
        int jmlCutiTerpakai = Integer.parseInt(lineStrings[4]);
        int statusKaryawan = Integer.parseInt(lineStrings[5]);

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("ID", id);
        jsonObject.put("Nama", nama);
        jsonObject.put("GajiPokok", gajiPokok);
        jsonObject.put("JmlAbsensi", jmlAbsensi);
        jsonObject.put("JmlCutiTerpakai", jmlCutiTerpakai);
        jsonObject.put("StatusKaryawan", statusKaryawan);

        list.add(jsonObject);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return list;
  }
}
