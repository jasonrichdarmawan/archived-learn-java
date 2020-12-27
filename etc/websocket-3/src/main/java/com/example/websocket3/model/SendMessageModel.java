package com.example.websocket3.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendMessageModel {
  private final int[] audio;

  public SendMessageModel(@JsonProperty(value = "audio") int[] audio) {
    this.audio = audio;
  }

  public int[] getAudio() {
    return audio;
  }
}
