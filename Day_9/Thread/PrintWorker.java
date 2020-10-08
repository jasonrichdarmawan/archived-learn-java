import java.io.BufferedReader;
import java.io.FileReader;

public class PrintWorker implements Runnable {
  private String message;

  PrintWorker(String message) {
    this.message = message;
    Thread thread = new Thread(this);
    thread.start();
    System.out.println(
        thread.getName() + " Active Threads = " + Thread.activeCount() + " Thread's state: " + thread.getState());

    if ((thread.getState() == Thread.State.TERMINATED)) {
      System.out.println(thread.getName() + " (End)");
    }
  }

  public void run() {
    System.out.println(Thread.currentThread().getName() + " (Start) message = " + this.message);
  }
}
