package com.example.websocket3.model;

public class ReceiveMessageModel {
  private final String from;
  private final String to; // dev only.
  private final String audio;

  public ReceiveMessageModel(String from, String to, String audio) {
    this.from = from;
    this.audio = audio;
    this.to = to;
  }

  public String getFrom() {
    return from;
  }

  public String getAudio() {
    return audio;
  }

  public String getTo() {
    return to;
  }
}
