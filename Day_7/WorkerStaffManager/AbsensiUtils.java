import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AbsensiUtils {
  public static void printMenuStaff(ArrayList<Staff> workers) throws Exception {
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    System.out.print("Enter the staff's ID: ");

    // question: cara await.
    int iIDKaryawan = Integer.parseInt(br.readLine());

    for (int index = 0; index < workers.size(); index++) {
      Staff staff = workers.get(index);
      if (iIDKaryawan == workers.get(index).getiIDKaryawan()) {
        MainUtils.printWorker(workers.get(index));

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
            workers.get(index).AbsensiMethod();
            break;
          case "n":
          case "no":
            break;
        }

        break;
      } else if (index == workers.size() - 1) {
        System.out.println("ID karyawan tidak ditemukan.");
      }
    }

    System.out.print("Press enter to continue: ");
    br.readLine();
  }

  public static void printMenuManager(ArrayList<Manager> workers) throws Exception {
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    System.out.print("Enter the manager's ID: ");

    // question: cara await.
    int iIDKaryawan = Integer.parseInt(br.readLine());

    for (int index = 0; index < workers.size(); index++) {
      // Manager worker = workers.get(index);
      if (iIDKaryawan == workers.get(index).getiIDKaryawan()) {
        MainUtils.printWorker(workers.get(index));

        // similar to case "1":
        System.out.print("Daftarkan absensi? Yes / No: ");
        String decision = br.readLine();
        switch (decision.toLowerCase()) {
          case "y":
          case "yes":
            workers.get(index).AbsensiMethod();
            break;
          case "n":
          case "no":
            break;
        }

        break;
      } else if (index == workers.size() - 1) {
        System.out.println("ID karyawan tidak ditemukan.");
      }
    }

    System.out.print("Press enter to continue: ");
    br.readLine();
  }
}
