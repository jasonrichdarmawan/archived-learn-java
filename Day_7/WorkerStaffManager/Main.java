import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Main {
  public static void main(String[] args) throws Exception {
    ArrayList<Staff> staffs = MainUtils.createArrayListOfObjectStaffs();
    ArrayList<Manager> managers = MainUtils.createArrayListOfObjectManagers();

    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    Locale inLocale = new Locale("id", "ID");
    NumberFormat formatter = NumberFormat.getCurrencyInstance(inLocale);

    String menu = "";
    while (menu != "6") {
      MainUtils.printMenu();
      System.out.print("Enter the menu's number: ");
      menu = br.readLine();

      switch (menu) {
        case "1":

          MainUtils.printMenuPendaftaranKaryawan();

          while (menu != "3") {
            System.out.print("Enter the menu's number: ");
            menu = br.readLine();
            switch (menu) {
              case "1":
                System.out.print("Enter the staff's name: ");
                String sNama = br.readLine();

                System.out.print("Enter the staff's tunjangan pulsa: ");
                float fTunjanganPulsa = Float.parseFloat(br.readLine());

                System.out.print("Enter the staff's gaji pokok: ");
                float fGajiPokok = Float.parseFloat(br.readLine());

                // alternative 1: with constructor
                // staffs.add(new Staff(staffs.size() + 1, sNama, sJabatan, 0));

                // alternative 2: getters and settrs.
                Staff newStaff = new Staff();
                newStaff.setiIDKaryawan(staffs.size() + managers.size() + 1);
                newStaff.setsNama(sNama);
                newStaff.setfTunjanganPulsa(fTunjanganPulsa);
                newStaff.setfGajiPokok(fGajiPokok);
                newStaff.setsJabatan("Staff");
                staffs.add(newStaff);

                // get back to the previous page.
                menu = "3";
                break;
              case "2":
                System.out.print("Enter the manager's name: ");
                sNama = br.readLine();

                System.out.print("Enter the manager's tunjangan pulsa: ");
                fTunjanganPulsa = Float.parseFloat(br.readLine());

                System.out.print("Enter the manager's gaji pokok: ");
                fGajiPokok = Float.parseFloat(br.readLine());

                // alternative is similar to case "1"
                Manager newManager = new Manager();
                newManager.setiIDKaryawan(staffs.size() + managers.size() + 1);
                newManager.setsNama(sNama);
                newManager.setfTunjanganPulsa(fTunjanganPulsa);
                newManager.setfGajiPokok(fGajiPokok);
                newManager.setsJabatan("Manager");
                managers.add(newManager);

                // get back to the previous page.
                menu = "3";
                break;
              case "3":
                menu = "3";
                break;
              default:
                System.out.println("Invalid menu. Please enter the correct menu's number: ");
            }
          }

          break;
        case "2":

          MainUtils.printMenuAbsensi();

          while (menu != "3") {
            System.out.print("Enter the menu's number: ");
            menu = br.readLine();
            switch (menu) {
              case "1":
                System.out.print("Enter the staff's ID: ");

                // question: cara await.
                int iIDKaryawan = Integer.parseInt(br.readLine());

                for (int index = 0; index < staffs.size(); index++) {
                  Staff staff = staffs.get(index);
                  if (iIDKaryawan == staff.getiIDKaryawan()) {
                    MainUtils.printWorker(staff);

                    // opsi 1: tambahin bisa lebih dari 1.
                    // System.out.print("Masukkan jumlah absensi: ");
                    // int iAbsensi = Integer.parseInt(br.readLine());
                    // staffs.set(index, new Staff(staff.getiIDKaryawan(), staff.getsNama(),
                    // staff.getsJabatan(), iAbsensi));

                    // opsi 2: tambah absensi 1 per 1
                    System.out.print("Daftarkan absensi? Yes / No: ");
                    String decision = br.readLine();
                    switch (decision.toLowerCase()) {
                      case "y":
                      case "yes":
                        staff.AbsensiMethod();
                        break;
                      case "n":
                      case "no":
                        break;
                    }

                    break;
                  } else if (index == staffs.size() - 1) {
                    System.out.println("ID karyawan tidak ditemukan.");
                  }
                }

                System.out.print("Press enter to continue: ");
                br.readLine();

                // get back to the previous page.
                menu = "3";
                break;
              case "2":
                System.out.print("Enter the manager's ID: ");

                // question: cara await.
                iIDKaryawan = Integer.parseInt(br.readLine());

                for (int index = 0; index < managers.size(); index++) {
                  Manager manager = managers.get(index);
                  if (iIDKaryawan == manager.getiIDKaryawan()) {
                    MainUtils.printWorker(manager);

                    // similar to case "1":
                    System.out.print("Daftarkan absensi? Yes / No: ");
                    String decision = br.readLine();
                    switch (decision.toLowerCase()) {
                      case "y":
                      case "yes":
                        manager.AbsensiMethod();
                        break;
                      case "n":
                      case "no":
                        break;
                    }

                    break;
                  } else if (index == managers.size() - 1) {
                    System.out.println("ID karyawan tidak ditemukan.");
                  }
                }

                System.out.print("Press enter to continue: ");
                br.readLine();

                // get back to the previous page.
                menu = "3";
                break;
              case "3":
                menu = "3";
                break;
              default:
                System.out.println("Invalid menu. Please enter the correct menu's number: ");
            }
          }
          break;
        case "3":
          MainUtils.printMenuHitungTunjangan();

          while (menu != "3") {
            System.out.print("Enter the menu's number: ");
            menu = br.readLine();
            switch (menu) {
              case "1":
                System.out.print("Enter the staff's ID: ");

                // question: cara await.
                int iIDKaryawan = Integer.parseInt(br.readLine());

                for (int index = 0; index < staffs.size(); index++) {
                  Staff staff = staffs.get(index);
                  if (iIDKaryawan == staff.getiIDKaryawan()) {
                    MainUtils.printWorker(staff);
                    System.out.println("Tunjangan Pulsa: " + formatter.format(staff.getfTunjanganPulsa()));

                    float totalfTunjanganMakan = staff.HitungfTunjanganMakan();
                    System.out.println("Tunjangan Makan: " + formatter.format(totalfTunjanganMakan));

                    System.out.println("==============================");

                    System.out.println(
                        "Total Tunjangan: " + formatter.format(staff.getfTunjanganPulsa() + totalfTunjanganMakan));
                  } else if (index == staffs.size() - 1) {
                    System.out.println("ID karyawan tidak ditemukan.");
                  }
                }

                System.out.print("Press enter to continue: ");
                br.readLine();

                // get back to the previous page.
                menu = "3";
                break;
              case "2":
                MainUtils.printMenuHitungTunjanganManager();

                while (menu != "3") {
                  System.out.print("Enter the menu's number: ");
                  menu = br.readLine();

                  switch (menu) {
                    case "1":
                      System.out.print("Enter the manager's ID: ");
                      iIDKaryawan = Integer.parseInt(br.readLine());

                      for (int index = 0; index < managers.size(); index++) {
                        Manager manager = managers.get(index);
                        if (iIDKaryawan == manager.getiIDKaryawan()) {
                          MainUtils.printWorker(manager);
                          System.out
                              .println("Tunjangan Transport: " + formatter.format(manager.HitungfTunjanganTransport()));
                        } else if (index == managers.size() - 1) {
                          System.out.println("ID karyawan tidak ditemukan.");
                        }
                      }

                      System.out.print("Press enter to continue: ");
                      br.readLine();

                      menu = "3"; // go back to previous page
                      break;
                    case "2":
                      System.out.print("Enter the manager's ID: ");
                      iIDKaryawan = Integer.parseInt(br.readLine());

                      for (int index = 0; index < managers.size(); index++) {
                        Manager manager = managers.get(index);
                        if (iIDKaryawan == manager.getiIDKaryawan()) {
                          MainUtils.printWorker(manager);

                          System.out.print("Enter the unrecorded amount: ");
                          int iEntertaint = Integer.parseInt(br.readLine());

                          // for future upgrade: saved to database, currently the
                          // SDR does not require to save.
                          manager.setiEntertaint(iEntertaint);

                          System.out.println(
                              "Tunjangan Entertaint: " + formatter.format(manager.HitungfTunjanganEntertaint()));

                        } else if (index == managers.size() - 1) {
                          System.out.println("ID karyawan tidak ditemukan.");
                        }
                      }

                      System.out.print("Press enter to continue: ");
                      br.readLine();

                      menu = "3"; // go back to previous page.
                      break;
                    case "3":
                      menu = "3";
                      break;
                    default:
                      System.out.println("Invalid menu. Please enter the correct menu's number: ");
                  }
                }

                // get back to the previous page.
                menu = "3";
                break;
              case "3":
                menu = "3";
                break;
              default:
                System.out.println("Invalid menu. Please enter the correct menu's number: ");
            }
          }
          break;
        case "4":
          MainUtils.printMenuHitungTotalGaji();
          while (menu != "3") {
            System.out.print("Enter the menu's number: ");
            menu = br.readLine();
            switch (menu) {
              case "1":
                System.out.print("Enter the staff's ID: ");

                // question: cara await.
                int iIDKaryawan = Integer.parseInt(br.readLine());

                for (int index = 0; index < staffs.size(); index++) {
                  Staff staff = staffs.get(index);
                  if (iIDKaryawan == staff.getiIDKaryawan()) {
                    MainUtils.printWorker(staff);

                    // AbstractWorker
                    System.out.println("Gaji Pokok: " + formatter.format(staff.getfGajiPokok()));
                    System.out.println("Tunjangan Pulsa: " + formatter.format(staff.getfTunjanganPulsa()));

                    // Staff
                    // intentionally, so we don't have to call staff.HitungFTunjanganMakan() 2
                    // times.
                    float totalfTunjanganMakan = staff.HitungfTunjanganMakan();
                    System.out.println("Tunjangan Makan: " + formatter.format(totalfTunjanganMakan));

                    System.out.println("==============================");

                    System.out.println("Total Gaji: "
                        + formatter.format(staff.getfGajiPokok() + staff.getfTunjanganPulsa() + totalfTunjanganMakan));
                  } else if (index == staffs.size() - 1) {
                    System.out.println("ID karyawan tidak ditemukan.");
                  }
                }

                System.out.print("Press enter to continue: ");
                br.readLine();

                // get back to the previous page.
                menu = "3";
                break;
              case "2":
                System.out.print("Enter the staff's ID: ");

                // question: cara await.
                iIDKaryawan = Integer.parseInt(br.readLine());

                for (int index = 0; index < managers.size(); index++) {
                  Manager manager = managers.get(index);
                  if (iIDKaryawan == manager.getiIDKaryawan()) {
                    MainUtils.printWorker(manager);

                    // Worker
                    System.out.println("Gaji Pokok: " + formatter.format(manager.getfGajiPokok()));
                    System.out.println("Tunjangan Pulsa: " + formatter.format(manager.getfTunjanganPulsa()));

                    // Manager
                    System.out.println("Tunjangan Transport: " + formatter.format(manager.HitungfTunjanganTransport()));
                    System.out
                        .println("Tunjangan Entertaint: " + formatter.format(manager.HitungfTunjanganEntertaint()));

                    System.out.println("==============================");

                    System.out.println(
                        "Total Gaji: " + formatter.format(manager.getfGajiPokok() + manager.getfTunjanganPulsa()
                            + manager.HitungfTunjanganTransport() + manager.HitungfTunjanganEntertaint()));
                  } else if (index == managers.size() - 1) {
                    System.out.println("ID karyawan tidak ditemukan.");
                  }
                }

                System.out.print("Press enter to continue: ");
                br.readLine();

                // get back to the previous page.
                menu = "3";
                break;
              case "3":
                menu = "3";
                break;
              default:
                System.out.println("Invalid menu. Please enter the correct menu's number: ");
            }
          }

          break;
        case "5":
          System.out.println("Laporan Gaji Seluruh Karyawan:");
          System.out.println("ID  |     Nama     |     Jabatan     |  Total Gaji  ");

          String format = "%-2d  |  %-10s  |  %-13s  |  %-15s %n";

          // StaffManager is an interface.
          ArrayList<StaffManager> combinedArrayList = new ArrayList<>();

          // bubble sort algorithm
          combinedArrayList.addAll(staffs);
          combinedArrayList.addAll(managers);
          for (int i = 0; i < combinedArrayList.size() - 1; i++) {
            for (int j = 0; j < combinedArrayList.size() - 1; j++) {
              if (combinedArrayList.get(j).getiIDKaryawan() > combinedArrayList.get(j + 1).getiIDKaryawan()) {
                StaffManager worker = combinedArrayList.get(j);
                combinedArrayList.set(j, combinedArrayList.get(j + 1));
                combinedArrayList.set(j + 1, worker);
              }
            }
          }

          for (StaffManager worker : combinedArrayList) {
            float fTotalGaji = 0.0F;
            if (worker instanceof Staff) {
              fTotalGaji = worker.getfGajiPokok() + worker.getfTunjanganPulsa() + worker.HitungfTunjanganMakan();
            } else if (worker instanceof Manager) {
              fTotalGaji = worker.getfGajiPokok() + worker.getfTunjanganPulsa() + worker.HitungfTunjanganTransport()
                  + worker.HitungfTunjanganEntertaint();
            }
            System.out.format(format, worker.getiIDKaryawan(), worker.getsNama(), worker.getsJabatan(),
                formatter.format(fTotalGaji));
          }

          break;
        case "6":
          menu = "6";
          break;
        default:
          System.out.println("Invalid menu's number. Please enter the correct menu's number.");
      }

    }

  }
}