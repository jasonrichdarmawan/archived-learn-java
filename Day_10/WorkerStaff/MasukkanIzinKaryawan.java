import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class MasukkanIzinKaryawan {
  public static ArrayList<Staff> showMenu(BufferedReader br, ArrayList<Staff> staffs) {
    SearchStaffUtils search = new SearchStaffUtils(staffs, br);

    if (search.getFocusStaff() != null) {
      Main.clearConsole();
      StaffUtils.printStaffInfo(search.getFocusStaff());
      String decision = "";
      try {
        System.out.print("Proceed? Yes or No: ");
        decision = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      switch (decision.toLowerCase()) {
        case "y":
        case "yes":
          Staff staff = search.getFocusStaff();
          staff.addIzin(1);
          staffs.set(search.getIndex(), staff);
          break;
        case "n":
        case "no":
          System.out.println("Canceling..");
          break;
        default:
          System.out.println("Canceling..");
      }
    }

    return staffs;
  }
}
