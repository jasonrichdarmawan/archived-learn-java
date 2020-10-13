package com.company.client.common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileCleanUpUtil {
  public static void cleanup(String filepath, String firstLine) {
    FileWriter fileWriter = null;
    try {
      fileWriter = new FileWriter(filepath);
    } catch (IOException e) {
      e.printStackTrace();
    }
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

    try {
      if (firstLine != "") {
        bufferedWriter.write(firstLine);
        bufferedWriter.newLine();
      }
      bufferedWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void cleanup(String filepath) {
    cleanup(filepath, "");
  }
}
