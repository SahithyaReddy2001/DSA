import sorting.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{4,6,2,5,7};
        QuickSort.quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));


    }
}
