import sorting.BubbleSort;
import sorting.InsertionSort;
import sorting.MergeSort;
import sorting.SelectionSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{4,5,1,3,2,4,6,7};
        MergeSort.mergeSort(arr,0, arr.length-1);
        System.out.println(Arrays.toString(arr));


    }
}
