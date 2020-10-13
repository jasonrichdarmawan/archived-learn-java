package com.company.client.common;

public class PrintWorker implements Runnable {
  private String[] messages;

  public PrintWorker(String[] messages) {
    this.messages = messages;

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
    for (int i = 0; i < messages.length; i++) {
      String message = messages[i];
      System.out.println(message);
    }
    System.out.println("");
  }
}
