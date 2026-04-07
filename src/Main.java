import arrays.LeftRotateArrayByDElements;
import arrays.RemoveDuplicatesFromSortedArray;
import binarySearch.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7};
        System.out.println(Arrays.toString(LeftRotateArrayByDElements.rotate(arr,3)));
    }
}
