import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
  public static void print(int workersSize, String[] messages) {
    ExecutorService executor = Executors.newFixedThreadPool(workersSize);

    for (int i = 0; i < messages.length; i++) {
      String message = messages[i];
      Runnable runnable = new PrintWorker(message, true);
      executor.execute(runnable); // max workers = workersSize;
    }
    executor.shutdown();
  }
}
