package com.company.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
  public static Properties load(String filepath) {
    Properties properties = new Properties();
    InputStream input = null;
    try {
      input = new FileInputStream(filepath);
      // load a properties file
      properties.load(input);
      // get the property value and print it out
    } catch (IOException e) {
      e.printStackTrace();
    }

    return properties;
  }
}
