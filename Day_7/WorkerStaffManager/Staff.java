// ...AbstractWorker

public class Staff extends AbstractWorker implements StaffManager {
  // fTunjanganMakan is not saved to database.txt
  // because there is no requirement to have setter method.
  // float is used to accomodate future setter method, if asked.
  private float fTunjanganMakan = 20000F; // defaultValue

  @Override
  public float HitungfTunjanganMakan() {
    return fTunjanganMakan * getiAbsensi();
  }

  @Override
  public float HitungfTunjanganTransport() {
    return 0.0F;
  }

  @Override
  public float HitungfTunjanganEntertaint() {
    return 0.0F;
  }

  // abstract method of AbstractWorker
  public void AbsensiMethod() {
    this.iAbsensi += 1;
  }
}