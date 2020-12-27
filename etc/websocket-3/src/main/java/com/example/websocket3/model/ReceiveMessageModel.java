package com.example.websocket3.model;

public class ReceiveMessageModel {
  private final String from;
  private final int[] audio;

  public ReceiveMessageModel(String from, int[] audio) {
    this.from = from;
    this.audio = audio;
  }

  public String getFrom() {
    return from;
  }

  public int[] getAudio() {
    return audio;
  }
}
