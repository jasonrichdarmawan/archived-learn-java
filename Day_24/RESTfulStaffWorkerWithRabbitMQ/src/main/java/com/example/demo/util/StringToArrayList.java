package com.example.demo.util;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;

public class StringToArrayList {
  public static ArrayList<String> toArrayList(String array) {
    if (array != null) {
      ArrayList<String> arrayList = new ArrayList<>();
      JSONArray jsonArray = null;
      try {
        jsonArray = (JSONArray) new JSONParser().parse(array);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      for (Iterator<String> iterator = jsonArray.iterator(); iterator.hasNext(); ) {
        arrayList.add(iterator.next());
      }

      return arrayList;
    } else {
      return null;
    }
  }

  public static String parseArrayList(ArrayList<String> array) {
    if (array == null) {
      return null;
    }
    JSONArray jsonArray = new JSONArray();
    for (Iterator<String> iterator = array.iterator(); iterator.hasNext(); ) {
      String item = iterator.next();
      jsonArray.add(item);
    }

    return jsonArray.toString();
  }
}
