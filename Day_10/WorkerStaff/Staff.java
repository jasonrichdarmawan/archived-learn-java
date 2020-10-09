public class Staff extends AbstractWorker {
  private float TunjanganMakan;
  private float TunjanganTransport;
  private float TotalGaji;

  Staff(int id, String nama, float gajipokok, int absensi, int izin) {
    super(id, nama, gajipokok, absensi, izin);
  }

  // in SDR == abstract method Hitung Gaji Pokok from the AbstractWorker class.
  public float HitungGajiPokok() {
    return (getGajiPokok() / 22) * getAbsensi();
  }

  // in SDR == Hitung Tunjangan Makan
  public void setTunjanganMakan() {
    float max = 220000;
    // a staff have a quota of 1 permit leave per month.
    // more than 1 leave per month == pay cuts.
    if (getIzin() > 1) {
      int unpermittedLeave = getIzin() - 1;
      this.TunjanganMakan = max - (max / 22 * unpermittedLeave);
    } else {
      this.TunjanganMakan = max;
    }
  }

  public float getTunjanganMakan() {
    setTunjanganMakan();
    return TunjanganMakan;
  }

  // in SDR == Hitung Tunjangan Transport
  public void setTunjanganTransport() {
    float max = 440000;
    // a staff have a quota of 1 permit leave per month.
    // more than 1 leave per month == pay cuts.
    if (getIzin() > 1) {
      int unpermittedLeave = getIzin() - 1;
      this.TunjanganTransport = max - (max / 22 * unpermittedLeave);
    } else {
      this.TunjanganTransport = max;
    }
  }

  public float getTunjanganTransport() {
    setTunjanganTransport();
    return TunjanganTransport;
  }

  public float getTotalGaji() {
    setTotalGaji();
    return this.TotalGaji;
  }

  // in SDR == Hitung Total Gaji
  public void setTotalGaji() {
    this.TotalGaji = getGajiPokok() + getTunjanganMakan() + getTunjanganTransport();
  }
}
