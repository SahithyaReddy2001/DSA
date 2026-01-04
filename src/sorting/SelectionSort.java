package sorting;

import java.util.Arrays;

public class SelectionSort {
   /* InsertionSort is type of Sorting technique Where we will select a minimum element,  and place it in correct
    position for example Consider  4,8,2,3,5,6
     STEP 1: Select minimum element from 0th index i.e 2 place in correct position
            2,4,8,3,5,6
     STEP 2: Select minimum element from 1st index i.e 3 place in correct position
            2,3,4,8,5,6
     STEP 3: Select minimum element from 2nd index i.e 4 place in correct position
            2,3,4,8,5,6
     STEP 4: Select minimum element from 3rd index i.e 5 place in correct position
            2,3,4,5,8,6
     STEP 5: Select minimum element from 4th index i.e 6 place in correct position
            2,3,4,5,6,8

      For loop runs for n, n-1, n-2, n-3,....., 1, 0 = n(n+1)/2 = n^2/2 + n/2 = O(N^2)
      Time Complexity : Best, Worst and Average : O(N^2)
      Space Complexity: O(1)
    */
    public static void selectionSort(int[] arr){
        for(int i=0; i<arr.length; i++){
            int minIndex = i;
            for (int j=i+1; j<arr.length; j++){
                //pick the min index
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            //swap minIndex with i th index
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }



}
