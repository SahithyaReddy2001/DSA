package sorting;

public class MergeSort {
    /*
    Merge Sort follows Divide and conquer technique
    Eg:                         20,40,10,30
                         ____________|_____________
                         |                        |
                      20, 40                    10,30
                   ______|______              _____|_____
                   |           |             |          |
                  20          40            10          30
                   |___________|             |___________|
                         |                        |
                      20, 40                    10,30
                         |________________________|
                                10,20,30,40

       Time Complexity : O(n log n)
       Space Complexity : O(n)
    */

    public static void mergeSort(int[] arr, int start, int end){
        if(start >= end) return;
        int mid = (start+end)/2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid+1, end);
        sortArrays(arr, start, mid, end);

    }

    public static void sortArrays(int[] arr, int start, int mid, int last){
        int[] arr3 = new int[last+1];
        int i = start;
        int j = mid+1;
        int k = i;
        while(i <= mid && j <= last){
            if(arr[i] <= arr[j]){
                arr3[k] = arr[i];
                i++;
            }else{
                arr3[k] = arr[j];
                j++;
            }
            k++;
        }
        if(i <= mid){
            for (int l = i; l<= mid; l++){
                arr3[k] = arr[l];
                k++;
            }
        }

        if(j <= last){
            for (int l = j; l<= last; l++){
                arr3[k] = arr[l];
                k++;
            }
        }
        for(int x=start; x<arr3.length; x++){
            arr[x] = arr3[x];
        }
    }
}
