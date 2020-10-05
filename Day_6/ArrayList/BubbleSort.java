import java.util.Arrays;

public class BubbleSort {
  public static void main(String[] args) {
    int[] a = { 4, 85, 7, 1, 0, 36, -5, 48 };
    for (int i = 0; i < a.length - 1; i++) {
      for (int j = 0; j < a.length - 1 - i; j++) {
        System.out.println("a[j + 1] = " + a[j + 1]);
        System.out.println("a[j] = " + a[j]);
        if (a[j] > a[j + 1]) {
          int temp = a[j];
          a[j] = a[j + 1];
          a[j + 1] = temp;
        }
      }
    }
    System.out.println(Arrays.toString(a));
  }
}
