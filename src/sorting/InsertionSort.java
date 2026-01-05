package sorting;

import java.util.Arrays;

public class InsertionSort {
    /* InsertionSort is type of Sorting technique Where we will select a particular element, and we will move
     it into the correct place For example Consider  4,8,2,3,5,6
     We will start from second element
     STEP 1: 8 is 8 greater than previous index? no, move to next element
     STEP 2: 2 is 2 greater than 8 no so move 2 to 8 place and vice versa
             4,2,8,3,5,6
             2 is 2 greater than 4 no so move 2 to 4 place and vice versa
             2,4,8,3,5,6
     STEP 3: 3 is 3 greater than 8 no so move 3 to 8 place and vice versa
             2,4,3,8,5,6
             3 is 3 greater than 4 no so move 3 to 4 place and vice versa
             2,3,4,8,5,6
             3 is 3 greater than 2  yes, move to next element
             2,3,4,8,5,6
     STEP 4: 5 is 5 greater than 8 no so move 5 to 8 place and vice versa
             2,4,3,5,8,6
             5 is 5 greater than 3 yes, move to next element
     STEP 5: 6 is 6 greater than 8 no so move 6 to 8 place and vice versa
             2,4,3,5,6,8
     STEP 6: 8 is 8 greater than 6 yes end of array

     Time Complexity :  BestCase: O(N), Worst and Avg : O(N^2)
     Space Complexity : O(1)
     */
    public static void insertionSort(int[] arr){
        int count = 0;
        //This code will take O(N^2) in BC, WC, AC
        for(int i=1; i< arr.length; i++){
            for(int j=i; j>0; j--){
                count++;
                if(arr[j] < arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }else{
                    break;
                }
            }
        }

//        for(int i=1; i<arr.length; i++){
//            int j = i;
//            while(j>0 && arr[j] < arr[j-1]){
//                int temp = arr[j];
//                arr[j] = arr[j-1];
//                arr[j-1] = temp;
//                j--;
//                count++;
//            }
//        }
        System.out.println(Arrays.toString(arr));
        System.out.println(count);
    }


    public static void baseRecursiveInsertionSort(int[] arr){
        for (int i=0; i< arr.length; i++){
            recursiveInsertionSort(arr, i);
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void recursiveInsertionSort(int[] arr, int index){
        if(index <= 0) return;
        if(arr[index] < arr[index-1]){
            int temp = arr[index];
            arr[index] = arr[index-1];
            arr[index-1] = temp;
        }else {
            return;
        }
        recursiveInsertionSort(arr, index-1);
    }

}
