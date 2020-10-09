import java.io.BufferedReader;
import java.io.FileReader;

public class PrintWorker implements Runnable {
  private Staff staff;

  PrintWorker(Staff staff) {
    this.staff = staff;
    Thread thread = new Thread(this);
    thread.start();
    try {
      // this is guarantee "Threads are executed in order."
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void run() {
    StaffUtils.printStaffInfo(this.staff);
    System.out.println("");
  }
}
