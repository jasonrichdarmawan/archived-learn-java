public abstract class AbstractWorker {
  private int ID;
  private String Nama;
  private float GajiPokok;
  private int Absensi;
  private int Izin; // in SDR == Jumlah Cuti (max 1 hari/bulan)

  AbstractWorker(int id, String nama, float gajipokok, int absensi, int izin) {
    this.ID = id;
    this.Nama = nama;
    this.GajiPokok = gajipokok;
    this.Absensi = absensi;
    this.Izin = izin;
  }

  public int getID() {
    return this.ID;
  }

  public String getNama() {
    return this.Nama;
  }

  // in SDR == +Hitung Absensi
  public int getAbsensi() {
    return this.Absensi;
  }

  // in SDR == +Absensi
  public void addAbsensi(int absensi) {
    this.Absensi = this.Absensi + absensi;
  }

  public int getIzin() {
    return this.Izin;
  }

  // in SDR == +Izin
  public void addIzin(int izin) {
    this.Izin = this.Izin + izin;
  }

  // out of SDR
  public float getGajiPokok() {
    return GajiPokok;
  }

  // in SDR == Hitung Gaji Pokok.
  abstract float HitungGajiPokok();
}
