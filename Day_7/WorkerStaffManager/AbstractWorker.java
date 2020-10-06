// iIDKaryawan,sNama,sJabatan,fTunjanganPulsa,fGajiPokok,iAbsensi
// abstract AbsensiMethod()

public abstract class AbstractWorker {
  protected int iIDKaryawan;
  protected String sNama;
  protected String sJabatan;
  protected float fTunjanganPulsa;
  protected float fGajiPokok;
  protected int iAbsensi = 20; // defaultValue

  // call with super() in the subclass.
  // Worker(int iIDKaryawan, String sNama, int iAbsensi) {
  // this.iIDKaryawan = iIDKaryawan;
  // this.sNama = sNama;
  // this.iAbsensi = iAbsensi;
  // }

  public int getiIDKaryawan() {
    return iIDKaryawan;
  }

  public void setiIDKaryawan(int iIDKaryawan) {
    this.iIDKaryawan = iIDKaryawan;
  }

  public String getsNama() {
    return sNama;
  }

  public void setsNama(String sNama) {
    this.sNama = sNama;
  }

  public String getsJabatan() {
    return sJabatan;
  }

  public void setsJabatan(String sJabatan) {
    this.sJabatan = sJabatan;
  }

  public int getiAbsensi() {
    return iAbsensi;
  }

  // abstract, similar to Interface in TypeScript.
  // AbsensiMethod will be used as this.iAbsensi += 1
  public abstract void AbsensiMethod();

  public void setiAbsensi(int iAbsensi) {
    this.iAbsensi = iAbsensi;
  }

  public float getfTunjanganPulsa() {
    return fTunjanganPulsa;
  }

  public void setfTunjanganPulsa(float fTunjanganPulsa) {
  this.fTunjanganPulsa = fTunjanganPulsa;
  }

  public float getfGajiPokok() {
    return fGajiPokok;
  }

  public void setfGajiPokok(float fGajiPokok) {
  this.fGajiPokok = fGajiPokok;
  }

}