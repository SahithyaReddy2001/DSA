package binarySearch;

import java.util.Arrays;

public class PayPal {
    /*
    * Given 'n' distinct points on x-axis, select k points from them such that minimum distance between any 2 selected points
    * is as large as possible. Find maximum possible minimum distance.

        eg: n=5 , k=3 , x=[1,4,2,9,8]
        solution: 3

        explanation: selecting [1,4,8], the distance between each
        * selected point is (1-4) , (1-8) , (4-8) is 3,7,4 respectively, 3 is minimum among them, which is maximum possible.
    * */

    //BruteForce: Minimum Distance will definitely start with  1, so iterate linearly from 1 and check if selecting points possible
    //TC:O(N*D) where D = max-min
    //SC:O(1)
    public static int findMaximumPossibleMinimumDistance(int[] arr, int k){
        Arrays.sort(arr);
        int low = 1;
        int high = arr[arr.length-1]-arr[0];
        int min = 0;
        for(int i=low; i<= high; i++){
            if(!isSelectionPossible(arr,k,i)){
                return min;
            }
            min = i;
        }
        return min;
    }

    public static boolean isSelectionPossible(int[] arr, int points, int dist){
        int prev = arr[0];
        points--;
        for(int i=1; i<arr.length; i++){
            if(arr[i]-prev >= dist){
                prev = arr[i];
                points--;
            }
            if(points==0) return true;
        }
        return false;
    }

    //Binary Search on answers
    //TC:O(D * log N)
    //SC: O(1)
    public static int binarySearchOnAnswers(int[] arr, int k){
        Arrays.sort(arr);
        int low = 1;
        int high = arr[arr.length-1]-arr[0];
        while(low <= high){
            int mid = (low+high)/2;
            if(isSelectionPossible(arr, k, mid)){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return high;
    }

}
