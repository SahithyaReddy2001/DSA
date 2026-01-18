package arrays;

import sorting.MergeSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReversePairs {
    //Q: We have to return all the elements which has i<j and arr[i] > 2*arr[j]
    //Eg: [40,25,19,12,9,6,2]
    //Op : (40,2)(40,6)(40,9)(40,12)(40,19)(25,2)(25,6)(25,9)(25,12)(19,2)(9,2)(6,2)

    /*
     * Brute:
     * Iterate over each element and check if its left element is greater than 2*num
     * TC: O(N^2)
     * SC: O(1)
     * */
    public static List<List<Integer>> reversePairsBrute(int[] arr){
        List<List<Integer>> ans = new ArrayList<>();
        for(int i=0; i<arr.length; i++){
            for(int j=i+1; j<arr.length; j++){
                if(arr[i] > arr[j]*2) {
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
     * consider 2 and 1 is 2 > 1*2 false
     * consider 3 yes 1*2<3 go to 7
     * consider 5 and 7
     * consider 6 and 7
     * consider 8 and 7
     * TC: O(N log N)
     * SC: O(1)
     * */

    public static List<List<Integer>> mergeSort(int[] arr, int start, int end, List<List<Integer>> list){
        if(start >= end) return list;
        int mid = (start+end)/2;
        mergeSort(arr, start, mid, list);
        mergeSort(arr, mid+1, end, list);
        reversePairs(arr, start, mid, end, list);
        sortArrays(arr, start, mid, end, list);
        return list;
    }

    public static void reversePairs(int[] arr, int start, int mid, int end, List<List<Integer>> list){
        int right = mid+1;
        for(int i=start; i<=mid; i++){
            right = mid+1;
            while(right <= end && arr[i] > arr[right] *2){
                List<Integer> list1= Arrays.asList(arr[i], arr[right]);
                list.add(list1);
                right++;
            }
        }
    }

    public static void sortArrays(int[] arr, int start, int mid, int last, List<List<Integer>> list){
        MergeSort.sortArrays(arr, start, mid, last);
    }
}
