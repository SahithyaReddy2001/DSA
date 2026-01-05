import sorting.BubbleSort;
import sorting.InsertionSort;
import sorting.SelectionSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{4,8,2,3,5,6};
        BubbleSort.recursiveBubbleSort(arr, arr.length-1);
        System.out.println(Arrays.toString(arr));

    }
}
