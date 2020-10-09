import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class EditDataKaryawan {
  public static ArrayList<Staff> showMenu(BufferedReader br, ArrayList<Staff> staffs) {
    SearchStaffUtils search = new SearchStaffUtils(staffs, br);

    if (search.getFocusStaff() != null) {
      Main.clearConsole();
      StaffUtils.printStaffInfo(search.getFocusStaff());
      String decision = "";
      try {
        System.out.print("Proceed to Edit? Yes or No: ");
        decision = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      boolean isExit = false;
      while (isExit == false) {
        switch (decision.toLowerCase()) {
          case "y":
          case "yes":
            staffs = proceedEdit(br, staffs, search.getIndex());
            isExit = true;
            break;
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
    } else {
      System.out.println("No staff with the specified name is found.");
    }

    return staffs;
  }

  public static ArrayList<Staff> proceedEdit(BufferedReader br, ArrayList<Staff> staffs, int index) {
    return InputDataKaryawan.showMenu(br, staffs, true, index);
  }
}
