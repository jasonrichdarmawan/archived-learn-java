public class MobilDemo {
  public static void main(String[] args) {
    Mobil mobil1 = new Mobil();
    Mobil mobil2 = new Mobil();
  
    mobil1.tambahGigi(1);
    
    mobil2.tambahGigi(1);
    mobil2.kurangGigi(1);

    System.out.println("merek: " + mobil1.merek);
    // System.out.println("gagal" + mobil1.gigi);
  }
}
