import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DaftarKaryawanUtils {
  private String sNama;
  private float fTunjanganPulsa;
  private float fGajiPokok;

  public void setWorkerAttribute() throws Exception {
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    System.out.print("Enter the staff's name: ");
    this.sNama = br.readLine();

    System.out.print("Enter the staff's tunjangan pulsa: ");
    this.fTunjanganPulsa = Float.parseFloat(br.readLine());

    System.out.print("Enter the staff's gaji pokok: ");
    this.fGajiPokok = Float.parseFloat(br.readLine());
  }

  public String getsNama() {
    return sNama;
  }

  public float getfTunjanganPulsa() {
    return fTunjanganPulsa;
  }

  public float getfGajiPokok() {
    return fGajiPokok;
  }
}
