package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountInversions {
    //Q: [5,3,2,4,1] output should be left element should be greater tha right element
    //OP: (5,3), (5,2), (5,4), (5,1), (3,2), (3,1), (2,1), (4,1)

    /*
    * Brute:
    * Iterate over each element and check is there any element smaller than it on its right.
    * TC: O(N^2)
    * SC: O(1)
    * */
    public static List<List<Integer>> countInversionsBrute(int[] arr){
        List<List<Integer>> ans = new ArrayList<>();
        for(int i=0; i<arr.length; i++){
            for(int j=i+1; j<arr.length; j++){
                if(arr[i] > arr[j]) {
                    List<Integer> list = Arrays.asList(arr[i], arr[j]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }


    /*
    * Optimal:
    * In this we will use merge sort algorithm
    * eg arr1 = {2,3,5,6,8} arr2 = {1, 7}
    * consider 2 and 1 as 2> 1 all the elements in arr1 > 1 so the count can become 5
    * TC: O(N log N)
    * SC: O(1)
    * */

    public static List<List<Integer>> mergeSort(int[] arr, int start, int end, List<List<Integer>> list){
        if(start >= end) return list;
        int mid = (start+end)/2;
        mergeSort(arr, start, mid, list);
        mergeSort(arr, mid+1, end, list);
        sortArrays(arr, start, mid, end, list);
        return list;
    }

    public static void sortArrays(int[] arr, int start, int mid, int last, List<List<Integer>> list){
        int[] arr3 = new int[last+1];
        int i = start;
        int j = mid+1;
        int k = i;
        while(i <= mid && j <= last){
            if(arr[i] <= arr[j]){
                arr3[k] = arr[i];
                i++;
            }else{
                for(int temp= i; temp <=mid; temp++){
                    List<Integer> list1= Arrays.asList(arr[temp], arr[j]);
                    list.add(list1);
                }
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
