### The folder structure:

```
|-- src
|   |-- server
|   |-- common (util used both by server and client)
|   |-- client
|       |-- Menu
|           |-- ..Util.java (static method) => create thread from ../common/(..Worker.java)
|       |-- Socket
|           |-- ..Worker.java (runnable)
|           |-- ..Util.java (static method)
|       |-- common
|           |-- ...Worker.java (runnable)
|           |-- ..Util.java (static method)
|-- config.properties (defining the hostname, port, user, passwrod for socket and FTP)
```

### Connection

The server support multiple clients with this logic:
```
while (true) {
  Socket socket = serverSocket.accept(); // waiting for connection => accept a connection
  ServerSocketWorker serverSocketWorker = new ServerSocketWorker(socket); // handle the new established connection
}
```

The client sends a request with a body, for example:
```
get: /api/v1/file.txt
```

The server will close the connection if the request is undefined.
```
boolean isExit = false;
while (!isExit) {
  switch (request) {
    case "get: /api/v1/file.txt":
      System.out.println("Receiving request 'get: /api/v1/file.txt'");
      try {
        FileReader fr = new FileReader("./file.txt");
        BufferedReader br = new BufferedReader(fr);
        String response = "";
        while ((response = br.readLine()) != null) {
          dout.writeUTF(response);
          dout.flush();
          System.out.println("Sending response: " + response);
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      break;
    default:
      socket.close();
      isExit = true;
  }
}
```

### The client behavior
The client will not close the connection unless asked by the user.