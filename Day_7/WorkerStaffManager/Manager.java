// ...AbstractWorker,iEntertaint

public class Manager extends AbstractWorker implements StaffManager {
  // fTunjanganTransport, fTunjanganEntertaint is not saved to database.txt
  // similar to Staff.
  private float fTunjanganTransport = 50000F; // defaultValue
  private int iEntertaint = 0; // outside the software requirements.
  private float fTunjanganEntertaint = 500000F;

  @Override
  public float HitungfTunjanganMakan() {
    return 0.0F;
  }

  @Override
  public float HitungfTunjanganTransport() {
    return fTunjanganTransport * getiAbsensi();
  }

  public void setiEntertaint(int iEntertaint) {
    this.iEntertaint += iEntertaint;
  }

  @Override
  public float HitungfTunjanganEntertaint() {
    return fTunjanganEntertaint * iEntertaint;
  }

  // abstract method of AbstractWorker
  public void AbsensiMethod() {
    this.iAbsensi += 1;
  }
}