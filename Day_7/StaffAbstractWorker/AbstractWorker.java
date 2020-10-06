public abstract class AbstractWorker {
  protected int iIDKaryawan;
  protected String sNama;
  protected int iAbsensi;

  // call with super() in the subclass.
  // Worker(int iIDKaryawan, String sNama, int iAbsensi) {
  // this.iIDKaryawan = iIDKaryawan;
  // this.sNama = sNama;
  // this.iAbsensi = iAbsensi;
  // }

  public int getiIDKaryawan() {
    return iIDKaryawan;
  }

  public String getsNama() {
    return sNama;
  }

  public int getiAbsensi() {
    return iAbsensi;
  }

  public void tambahAbsensi() {
    this.iAbsensi += 1;
  }

  public void setiIDKaryawan(int iIDKaryawan) {
    this.iIDKaryawan = iIDKaryawan;
  }

  public void setsNama(String sNama) {
    this.sNama = sNama;
  }

  public void setiAbsensi(int iAbsensi) {
    this.iAbsensi = iAbsensi;
  }

}