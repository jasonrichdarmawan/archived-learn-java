import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class SearchStaffUtils {
  private Staff focusStaff = null;
  private int index = 0;

  SearchStaffUtils(ArrayList<Staff> staffs, BufferedReader br) {
    setFocusStaff(staffs, br);
  }

  public Staff getFocusStaff() {
    return focusStaff;
  }

  public void setFocusStaff(Staff staff) {
    this.focusStaff = staff;
  }

  public void setFocusStaff(ArrayList<Staff> staffs, BufferedReader br) {
    ArrayList<Staff> tempStaffs = SearchStaffUtils.searchName(staffs, br);

    // if found only 1 staff with exact name.
    if (tempStaffs.size() == 1) {
      setFocusStaff(tempStaffs.get(0));
    }
    // if found 2 staffs with similar name.
    else if (tempStaffs.size() > 1) {
      Main.clearConsole();
      for (Staff staff : tempStaffs) {
        StaffUtils.printStaffInfo(staff);
        System.out.println("");
      }
      System.out.println("Found more than 2 staffs with similar name");
      System.out.print("Please enter the ID you wish to select: ");
      int id = 0;
      try {
        id = Integer.parseInt(br.readLine());
      } catch (IOException e) {
        e.printStackTrace();
      }

      for (int i = 0; i < staffs.size(); i++) {
        Staff staff = staffs.get(i);
        if (staff.getID() == id) {
          setIndex(i);
          setFocusStaff(staff);
          break;
        }
      }
    }
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  private static ArrayList<Staff> searchName(ArrayList<Staff> staffs, BufferedReader br) {
    Main.clearConsole();

    String nama = "";
    try {
      System.out.print("Enter the staff's name: ");
      nama = br.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }

    ArrayList<Staff> tempStaffs = new ArrayList<>();

    for (Staff staff : staffs) {
      if (!tempStaffs.contains(staff) && staff.getNama().contains(nama)) {
        tempStaffs.add(staff);
      }
    }

    return tempStaffs;
  }
}
