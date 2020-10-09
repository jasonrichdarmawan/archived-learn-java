import java.io.BufferedReader;
import java.io.FileReader;

public class PrintWorker implements Runnable {
  private Staff staff;
  private int currentWorkDays;

  PrintWorker(Staff staff, int currentWorkDays) {
    this.staff = staff;
    this.currentWorkDays = currentWorkDays;
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
    StaffUtils.printStaffReport(this.staff, currentWorkDays);
    
    // StaffUtils.printStaffInfo(this.staff); // deprecated.

    System.out.println("");
  }
}
