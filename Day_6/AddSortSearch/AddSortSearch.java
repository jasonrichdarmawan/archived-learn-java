import java.lang.reflect.Array;
import java.util.Arrays;

public class AddSortSearch {
  private int[] intArray;

  AddSortSearch(int size) {
    intArray = new int[size];
  }

  void intAddToArray(int value) {
    for (int index = 0; index < this.intArray.length; index++) {
      // System.out.println(this.array[index]);
      if (this.intArray[index] == 0) {
        this.intArray[index] = value;
        // System.out.println(this.intArray[index]);
        break;
      }
    }
  }

  void intSortArray(int[] intAray) {
    for (int i = 0; i < intArray.length - 1; i++) {
      for (int j = 0; j < intArray.length - 1; j++) {
        // [3, 1, 4] => [1, 3, 4]
        if (intArray[j] > intArray[j + 1]) {
          int temp = intArray[j];
          intArray[j] = intArray[j + 1];
          intArray[j + 1] = temp; 
        }
      }
    }
  }

  // condition: intArray is sorted and no duplicate.
  void intSearchValue(int[] intArray, int value) {
    int left = 0;
    int middle;
    int right = intArray.length - 1;
    while (left <= right) {
      middle = (left + right) / 2;
      if (intArray[middle] == value) {
        System.out.println("Value found at index: " + middle);
        break;
      }

      // if true, make the search smaller from left.
      else if (intArray[middle] < value) {
        left = middle + 1;
        continue;
      }
      
      // if true, make the search smaller from right.
      else if (intArray[middle] > value) {
        right = middle - 1;
      }
    }
  }

  int[] getState() {
    return intArray;
  }

  void printState() {
    System.out.println(Arrays.toString(this.intArray));
  }

  public static void main(String[] args) {
    AddSortSearch array1 = new AddSortSearch(5);

    // 1. add to array.
    array1.intAddToArray(12);
    array1.intAddToArray(1);
    array1.intAddToArray(8);
    array1.intAddToArray(2);
    array1.intAddToArray(7);

    // 2. sort the array.
    array1.intSortArray(array1.getState());

    // 3. search data from array.
    array1.intSearchValue(array1.getState(), 2);

    array1.printState();
  }
}
