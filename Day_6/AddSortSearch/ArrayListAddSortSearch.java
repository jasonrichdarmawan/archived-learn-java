import java.util.ArrayList;
import java.util.Arrays;

// similar to AddSortSearch.java, the difference is this use ArrayList instead of
// primitive array.
public class ArrayListAddSortSearch {

  static ArrayList<Integer> IntegerSortArray(ArrayList<Integer> IntegerArrayList) {
    for (int i = 0; i < IntegerArrayList.size() - 1; i++) {
      for (int j = 0; j < IntegerArrayList.size() - 1; j++) {
        // [3, 1, 4] => [1, 3, 4]
        if (IntegerArrayList.get(j) > IntegerArrayList.get(j + 1)) {
          int temp = IntegerArrayList.get(j);
          IntegerArrayList.set(j, IntegerArrayList.get(j + 1));
          IntegerArrayList.set(j + 1, temp);
        }
      }
    }

    return IntegerArrayList;
  }

  static void IntegerSearchValue(ArrayList<Integer> IntegerArrayList, int value) {
    int left = 0;
    int middle;
    int right = IntegerArrayList.size() - 1;
    while (left <= right) {
      middle = (left + right) / 2;
      if (IntegerArrayList.get(middle) == value) {
        System.out.println("Value found at index: " + middle);
        break;
      }

      // if true, make the search smaller from left.
      else if (IntegerArrayList.get(middle) < value) {
        left = middle + 1;
        continue;
      }

      // if true, make the search smaller from right.
      else if (IntegerArrayList.get(middle) > value) {
        right = middle - 1;
      }
    }
  }

  public static void main(String[] args) {
    ArrayList<Integer> IntegerArrayList = new ArrayList<Integer>();

    // 1. add to array;
    IntegerArrayList.add(12);
    IntegerArrayList.add(1);
    IntegerArrayList.add(8);
    IntegerArrayList.add(2);
    IntegerArrayList.add(7);

    // unsorted
    System.out.println(IntegerArrayList.toString());

    // 2. sort the array
    IntegerArrayList = IntegerSortArray(IntegerArrayList);
    System.out.println(IntegerArrayList.toString());

    // 3. search data from array.
    IntegerSearchValue(IntegerArrayList, 2);

  }
}
