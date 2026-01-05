package sorting;

import java.util.Arrays;

public class BubbleSort {
     /*
     BubbleSort is way to swap the adjacent elements and move the largest elements to last index
     Eg: 4,8,7,3,6,2
     -> loop will run from 0 to n-1
         STEP 1: compare index 0,1 check 4,8 they are correct move to next index
         STEP 2: compare index 1,2 check 8,7  8 > 7 swap
                 4,7,8,3,6,2
         STEP 3: compare index 2,3 check 8,3  8 > 3 swap
                 4,7,3,8,6,2
         STEP 4: compare index 3,4 check 8,6  8 > 6 swap
                 4,7,3,6,8,2
         STEP 5: compare index 4,5 check 8,2  8 > 2 swap
                 4,7,3,6,2,8
     -> loop will run from 0 to n-2
         STEP 1: compare index 0,1 check 4,7 they are correct move to next index
         STEP 2: compare index 1,2 check 7,3  7 > 3 swap
                 4,3,7,6,2,8
         STEP 3: compare index 2,3 check 7,6  7 > 6 swap
                 4,3,6,7,2,8
         STEP 4: compare index 3,4 check 7,2  7 > 2 swap
                 4,3,6,2,7,8
     -> loop will run from 0 to n-3
         STEP 1: compare index 0,1 check 4,3  4 > 3 swap
                 3,4,6,2,7,8
         STEP 2: compare index 1,2 check 4,6  they are correct move to next index
         STEP 3: compare index 2,3 check 6,2  6 > 2 swap
                 3,4,2,6,7,8
     -> loop will run from 0 to n-4
         STEP 1: compare index 0,1 check 4,3   they are correct move to next index
         STEP 2: compare index 1,2 check 2,4  4 > 2 swap
                 3,2,4,6,7,8
     -> loop will run from 0 to n-5
         STEP 1: compare index 0,1 check 3,2  3 > 2 swap
                2,3,4,6,7,8

        Time Complexity :  BestCase: O(N), Worst and Avg : O(N^2)
        Space Complexity : O(1)
     */
    public static void bubbleSort(int[] arr){
        for(int i=1; i<arr.length; i++){
            boolean isSwap = false;
            for(int j=0; j< arr.length - i - 1; j++){
                if(arr[j] > arr[j+1]){
                    isSwap = true;
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if(!isSwap) break;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void recursiveBubbleSort(int[] arr, int index){
        if(index <= 0) return;
        for(int i=0; i<index; i++){
            if(arr[i] > arr[i+1]){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        recursiveBubbleSort(arr, index-1);
    }

}
