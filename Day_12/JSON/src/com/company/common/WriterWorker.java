package com.company.common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriterWorker {
  public static void cleanup(String fileName) {
    try {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
      bufferedWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void write(String line, String fileName, boolean append) {
    BufferedWriter bufferedWriter = null;
    try {
      bufferedWriter = new BufferedWriter(new FileWriter(fileName, true));
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      bufferedWriter.write(line);
      if (append) {
        bufferedWriter.newLine();
      }
      bufferedWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
