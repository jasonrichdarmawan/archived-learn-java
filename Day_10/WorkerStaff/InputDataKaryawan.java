import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputDataKaryawan {
  public static ArrayList<Staff> showMenu(BufferedReader br, ArrayList<Staff> staffs, int currentWorkDays, boolean set, int index) {
    int id = staffs.size() + 1;
    String nama = "";
    float gajipokok = 0.0F;
    int absensi = 0;
    int izin = 0;

    boolean isExit = false; // check if absensi + izin = current total workdays.
    while (isExit == false) {
      try {
        Main.pressEnterToContinue(br);
        Main.clearConsole();
        System.out.println("The current total work days is: " + currentWorkDays);
        System.out.println(
            "Please make sure the the total attendance + the total leaves is equals to the current total work days.");

        System.out.print("Enter the staff's name: ");
        nama = br.readLine();

        System.out.print("Enter the staff's base salary: ");
        gajipokok = Float.parseFloat(br.readLine());

        System.out.print("Enter the staff's total attendance: ");
        String tempAbsensi = br.readLine();
        if (tempAbsensi == "0") {
          absensi = 0;
        } else {
          absensi = Integer.parseInt(tempAbsensi);
        }

        System.out.print("Enter the staff's total leaves: ");
        String tempIzin = br.readLine();
        if (tempIzin == "0") {
          izin = 0;
        } else {
          izin = Integer.parseInt(tempIzin);
        }

        if (absensi + izin == currentWorkDays) {
          Main.clearConsole();
          Staff staff = null;
          if (set == true) {
            staff = new Staff(staffs.get(index).getID(), nama, gajipokok, absensi, izin);
          } else {
            staff = new Staff(id, nama, gajipokok, absensi, izin);
          }
          StaffUtils.printStaffInfo(staff);
          System.out.print("Is the information correct? Yes or No: ");
          String decision = br.readLine();
          switch (decision.toLowerCase()) {
            case "y":
            case "yes":
              if (set == true) {
                staffs.set(index, staff);
              } else {
                staffs.add(staff);
              }
              isExit = true;
              break;
            // for future development. not defined in SDR.
            case "n":
            case "no":
              isExit = true;
              System.out.println("Canceling..");
              break;
            default:
              isExit = true;
              System.out.println("Canceling..");
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return staffs;
  }

  public static ArrayList<Staff> showMenu(BufferedReader br, ArrayList<Staff> staffs, int currentWorkDays) {
    return showMenu(br, staffs, currentWorkDays, false, 0);
  }

}
