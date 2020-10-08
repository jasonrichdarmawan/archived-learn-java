import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriterWorker implements Runnable {
  private String file;
  private String message;
  
  WriterWorker(String message, String file) {
    this.file = file;
    this.message = message;
    Thread thread = new Thread(this);
    thread.start();
    try {
      // this is guarantee "Threads are executed in order."
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    // System.out.println(
    //     thread.getName() + " Active Threads = " + Thread.activeCount() + " Thread's state: " + thread.getState());

    // if ((thread.getState() == Thread.State.TERMINATED)) {
    //   System.out.println(thread.getName() + " (End)");
    // }
  }

  public void run() {
    // System.out.println(Thread.currentThread().getName() + " (Start) message = " + this.message);
    
    FileWriter fw = null;
    try {
      fw = new FileWriter(this.file, true);
    } catch (IOException e) {
      e.printStackTrace();
    }
    BufferedWriter br = new BufferedWriter(fw);

    try {
      br.write(message + ",");
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
