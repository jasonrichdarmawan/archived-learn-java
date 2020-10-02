public class Main {

  static char[] createArray() {
    char[] s;
    s = new char[26];
    for (int i = 0; i < 26; i++) {
      s[i] = (char) ('A' + i);
    }

    return s;
  }

  public static void main(String[] args) {
    System.out.println(createArray());

    System.out.println('A' + 1); // 66 "this is decimal"
    System.out.println((char) ('A' + 1)); // (char) 66 => B
    System.out.println((char) 'A' + 1); // (char) 66 "Hex" => 66
    System.out.println((char) 66); // (char) 66 "Decimal"
  }

}