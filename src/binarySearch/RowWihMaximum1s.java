package binarySearch;

import java.util.List;

public class RowWihMaximum1s {
   /* You have been given a non-empty grid ‘mat’ with 'n' rows and 'm' columns consisting of only 0s and 1s. All the rows are sorted in ascending order.
    Your task is to find the index of the row with the maximum number of ones.
    Note: If two rows have the same number of ones, consider the one with a smaller index. If there's no row with at least 1 zero, return -1.
    Example:
    Input: 'n' = 3, 'm' = 3, 'mat' = [[1, 1, 1], [0, 0, 1], [0, 0, 0]]
    Output: 0
    Explanation: The row with the maximum number of ones is 0 (0 - indexed).*/


    public static int rowMaxOnes(List<List<Integer>> mat, int n, int m) {
        int index = -1;
        int maxCount = 0;
        int i =0;
        for(List<Integer> list: mat){
            int count = binarySearch(list);
            if(count > maxCount){
                maxCount = count;
                index = i;
            }
            i++;
        }
        return index;
    }

    public static int binarySearch(List<Integer> arr){
        int low = 0;
        int high = arr.size()-1;
        while(low<=high){
            int mid = (low+high)/2;
            if(arr.get(mid) == 1){
                high =mid-1;
            }else{
                low = mid+1;
            }
        }
        return arr.size() - low;
    }
}
