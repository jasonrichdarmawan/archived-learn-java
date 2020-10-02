import java.lang.reflect.Array;
import java.util.Arrays;

public class ResizeCopyArray {

  // This will return new reference instead of resizing.
  static String[] copyResizeArray(String[] strArray, int newLength) {
    String[] newArray = new String[newLength];
    for (int i = 0; i < strArray.length; i++) {
      // prevent updating array if it's null.
      if (strArray[i] != null) {
        newArray[i] = strArray[i];
      }
    }
    return newArray;
  }

  public static void main(String[] args) {
    String[] strArray = new String[2];

    strArray[1] = "1";
    System.out.println("old reference: " + strArray + " data: " + Arrays.toString(strArray));

    // You can't resize Array.
    // You can use ArrayList to resize.
    // copyResizeArray() will only copy and return new reference.
    strArray = copyResizeArray(strArray, 5);
    System.out.println("new reference: " + strArray + " data: " + Arrays.toString(strArray));
  }
}
