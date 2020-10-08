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

    while (!(thread.getState() == Thread.State.TERMINATED)) {
    }

    if ((thread.getState() == Thread.State.TERMINATED)) {
      System.out.println(thread.getName() + " (End)");
    }
  }

  // Runnable only.
  PrintWorker(String message, boolean runnable) {
    this.message = message;
    System.out.println(Thread.currentThread().getName() + " Active Threads = " + Thread.activeCount()
        + " Thread's state: " + Thread.currentThread().getState());

    // unnecessary line of code, Thread's ThreadPool never get to terminated.
    if ((Thread.currentThread().getState() == Thread.State.TERMINATED)) {
      System.out.println(Thread.currentThread().getName() + " (End)");
    }
  }

  public void run() {
    System.out.println(Thread.currentThread().getName() + " (Start) message = " + this.message);
  }
}
