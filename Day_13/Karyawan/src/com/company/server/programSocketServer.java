package com.company.server;

import com.company.common.PropertiesUtil;
import com.company.server.ServerSocket.ServerSocketUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

public class programSocketServer {
  public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

  public static Properties properties = null;

  public static void main(String[] args) {
    properties = PropertiesUtil.load(args[0]);

    int SOCKET_PORT = Integer.parseInt(properties.getProperty("SOCKET_PORT"));
    ServerSocketUtil.run(SOCKET_PORT);
  }
}
