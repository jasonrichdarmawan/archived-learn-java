package com.example.websocket3.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendMessageModel {
  private final String audio;

  public SendMessageModel(@JsonProperty(value = "audio") String audio) {
    this.audio = audio;
  }

  public String getAudio() {
    return audio;
  }
}
