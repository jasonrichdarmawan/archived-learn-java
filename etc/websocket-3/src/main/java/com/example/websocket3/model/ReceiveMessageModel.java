package com.example.websocket3.model;

public class ReceiveMessageModel {
  private final String from;
  private final String to; // dev only.
  private final int[] audio;

  public ReceiveMessageModel(String from, String to, int[] audio) {
    this.from = from;
    this.audio = audio;
    this.to = to;
  }

  public String getFrom() {
    return from;
  }

  public int[] getAudio() {
    return audio;
  }

  public String getTo() {
    return to;
  }
}
