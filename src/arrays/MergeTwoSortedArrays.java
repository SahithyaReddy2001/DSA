package arrays;

import java.util.Arrays;

public class MergeTwoSortedArrays {

    /*
    * Optimal 1:
    * Start i from arr1 ending
    * Start j from arr2 starting
    * if i> j swap
    * if i<j break
    * sort arr1 and arr2
    * SC : O(1)
    * TC: O(N) + O(2N log N)
    * */
    public static void mergeTwoSortedArrays(int[] arr1, int[] arr2){
        int i= arr1.length-1;
        int j = 0;
        while(i>=0 && j<arr2.length){
            if(arr1[i] > arr2[j]){
                int temp = arr1[i];
                arr1[i] = arr2[j];
                arr2[j] = temp;
            }else {
                break;
            }
            i--;
            j++;
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
    }



    /*
    * Optimal 2: Algorithm: Shell Sort
    * Gap = arr1.length + arr2.length / 2  + arr1.length + arr2.length % 2
    * We will place i = 0 and j = gap
    * we will compare and swap the elements until i < arr1.length and j < arr2.length
    * SC: O(1)
    * TC: log(m+n) + O(m+n)
    * */
    public static void mergeTwoSortedArraysOptimal2(int[] arr1, int[] arr2){
        int gap = (arr1.length + arr2.length) / 2  + (arr1.length + arr2.length) % 2;
        while(gap > 0){
            int i=0;
            int j=i+gap;
            while(j < arr2.length + arr1.length) {
                if (i < arr1.length && j >= arr1.length) {
                    swapIfGreater(arr1, arr2, i, j - arr1.length);
                } else if (i >= arr1.length) {
                    swapIfGreater(arr2, arr2, i - arr1.length, j - arr1.length);
                } else {
                    swapIfGreater(arr1, arr1, i, j);
                }
                i++;
                j++;
            }
            if(gap == 1) break;
            gap = gap / 2  + gap % 2;
        }
    }

    public static void swapIfGreater(int arr1[], int arr2[], int i1, int i2){
        if(arr1[i1]>arr2[i2]){
            int temp = arr1[i1];
            arr1[i1] = arr2[i2];
            arr2[i2] = temp;
        }
    }

}
