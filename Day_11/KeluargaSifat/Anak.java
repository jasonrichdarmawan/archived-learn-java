import java.util.Properties;

public class Anak implements Ayah, Ibu {
  Properties prop;

  Anak(Properties prop) {
    this.prop = prop;
  }

  @Override
  public void NamaIbu() {
    System.out.println(prop.getProperty("NamaIbu"));
  }

  @Override
  public void SifatIbu() {
    System.out.println(prop.getProperty("SifatIbu"));
  }

  @Override
  public void NamaAyah() {
    System.out.println(prop.getProperty("NamaAyah"));
  }

  @Override
  public void SifatAyah() {
    System.out.println(prop.getProperty("SifatAyah"));
  }

  @Override
  public void NamaKakek() {
    System.out.println(prop.getProperty("NamaKakek"));
  }

  @Override
  public void SifatKakek() {
    System.out.println(prop.getProperty("SifatKakek"));
  }

  public void NamaAnak() {
    System.out.println(prop.getProperty("NamaAnak"));
  }

  public void SifatAnak() {
    System.out.println(prop.getProperty("SifatAnak"));
  }
}
