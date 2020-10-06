// interface for ArrayList<StaffManager> or combined ArrayList of 2 objects.
// because of how interface woroks, both Staff and Manager have each other's method
// but it returns 0.0F.

public interface StaffManager {
  // Worker
  public int getiIDKaryawan();
  public float getfTunjanganPulsa();
  public float getfGajiPokok();
  public String getsNama();
  public String getsJabatan();

  // Staff
  public float HitungfTunjanganMakan();

  // Manager
  public float HitungfTunjanganTransport();
  public float HitungfTunjanganEntertaint();
}
