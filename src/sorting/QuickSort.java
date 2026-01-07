package sorting;

public class QuickSort {

    /*
       Quick Sort is an algorithm in which we select a PIVOT and place it in its correct place and repeat the same for array(0, PIVOT-1) and array(PIVOT+1, length)
        Eg: 4, 6, 2, 5, 7
        Step 1: PIVOT : 4
                2,4,6,5,7 (pivot index is 2 so there is only single element at left so go to right index)
        Step 2: PIVOT :6
                2,4,5,6,7 (pivot index is  3 at left there is only 1 element left and at right there is only one element left so array is sorted)

        TC: O(N^2)
        SC: O(1)
    */

    public static void quickSort(int[] arr, int start, int end){
        if(start >= end) return;
        int pivotIndex = sort(arr, start, end);
        quickSort(arr, start, pivotIndex-1);
        quickSort(arr, pivotIndex+1, end);

    }

    public static int sort(int[] arr, int startIndex, int endIndex){
        int pivot = arr[startIndex];
        int i = startIndex + 1;
        int j = endIndex;
        while(i <= j){
            while (i <= j && arr[i] <= pivot) i++;

            // move j until we find element < pivot
            while (i <= j && arr[j] > pivot) j--;

            // if valid, swap
            if (i < j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        int temp = arr[j];
        arr[j] = arr[startIndex];
        arr[startIndex] = temp;
        return  j;
    }
}
