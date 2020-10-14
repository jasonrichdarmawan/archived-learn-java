package com.company.JSON;

import com.company.MainUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class JSONReader {
  public static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

  public static void readCommon(JSONObject jo) {
    int IDKaryawan = (int) (long) jo.get("IDKaryawan");
    System.out.println("IDKaryawan: " + IDKaryawan);

    String Nama = (String) jo.get("Nama");
    System.out.println("Nama: " + Nama);

    float TunjanganPulsa = (float) (double) jo.get("Tunjangan Pulsa");
    System.out.println("Tunjangan Pulsa: " + currencyFormatter.format(TunjanganPulsa));

    float GajiPokok = (float) (double) jo.get("Gaji Pokok");
    System.out.println("Gaji Pokok: " + currencyFormatter.format(GajiPokok));

    int AbsensiHari = (int) (long) jo.get("Absensi Hari");
    System.out.println("Absensi Hari: " + AbsensiHari);
  }

  public static void readStaffs(String fileName) {
    MainUtil.clearTerminal();

    BufferedReader bufferedReader = null;
    try {
      bufferedReader = new BufferedReader(new FileReader(fileName));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      String line = "";
      while ((line = bufferedReader.readLine()) != null) {
        Object obj = null;
        try {
          obj = new JSONParser().parse(line);
        } catch (ParseException e) {
          e.printStackTrace();
        }

        JSONObject jo = (JSONObject) obj;

        readCommon(jo);

        // Staff variable.

        float TunjanganMakan = (float) (double) jo.get("Tunjangan Makan");
        System.out.println("Tunjangan Makan: " + currencyFormatter.format(TunjanganMakan));

        JSONArray Emails = (JSONArray) jo.get("Emails");
        Iterator<String> iterator = Emails.iterator();
        while (iterator.hasNext()) {
          System.out.println("Email: " + iterator.next());
        }

        System.out.println("");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void readManagers(String fileName) {
    MainUtil.clearTerminal();

    BufferedReader bufferedReader = null;
    try {
      bufferedReader = new BufferedReader(new FileReader(fileName));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      String line = "";
      while ((line = bufferedReader.readLine()) != null) {
        Object obj = null;
        try {
          obj = new JSONParser().parse(line);
        } catch (ParseException e) {
          e.printStackTrace();
        }

        JSONObject jo = (JSONObject) obj;

        readCommon(jo);

        // Manager Variable

        float TunjanganTransport = (float) (double) jo.get("Tunjangan Transport");
        System.out.println("Tunjangan Makan: " + currencyFormatter.format(TunjanganTransport));

        float TunjanganEntertaint = (float) (double) jo.get("Tunjangan Entertaint");
        System.out.println("Tunjangan Entertaint: " + currencyFormatter.format(TunjanganEntertaint));

        JSONArray Telepons = (JSONArray) jo.get("Telepons");
        Iterator<String> iterator = Telepons.iterator();
        while (iterator.hasNext()) {
          System.out.println("Telepons: " + iterator.next());
        }

        System.out.println("");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
