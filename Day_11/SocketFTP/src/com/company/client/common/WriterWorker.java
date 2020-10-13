package com.company.client.common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriterWorker implements Runnable {
  private String filepath;
  private String[] messages;
  private boolean newLine;

  public WriterWorker(String filepath, String[] messages, boolean newLine) {
    this.filepath = filepath;
    this.messages = messages;
    this.newLine = newLine;

    Thread thread = new Thread(this);
    thread.start();
    try {
      thread.join(); // waits for this thread to die => guarantee the worker is executed in order.
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {

    FileWriter fileWriter = null;
    try {
      fileWriter = new FileWriter(this.filepath, true);
    } catch (IOException e) {
      e.printStackTrace();
    }
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

    try {
      for (int i = 0; i < this.messages.length; i++) {
        String message = this.messages[i];
        bufferedWriter.write(message);
//        System.out.println(message);
        bufferedWriter.newLine();
      }
      if (this.newLine) {
        bufferedWriter.newLine();
      }
      bufferedWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
