package binarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedianARowWiseSortedMatrix {
    /*Problem statement
    You are given a row-wise sorted matrix 'mat' of size m x n where 'm' and 'n' are the numbers of rows and columns of the matrix, respectively.
    Your task is to find and return the median of the matrix.
    Note:
    'm' and 'n' will always be odd.

    Example:
    Input: 'n' = 5, 'm' = 5
            'mat' =
            [     [ 1, 5, 7, 9, 11 ],
            [ 2, 3, 4, 8, 9 ],
            [ 4, 11, 14, 19, 20 ],
            [ 6, 10, 22, 99, 100 ],
            [ 7, 15, 17, 24, 28 ]   ]

    Output: 10
    Explanation: If we arrange the elements of the matrix in the sorted order in an array, they will be like this-
    1 2 3 4 4 5 6 7 7 8 9 9 10 11 11 14 15 17 19 20 22 24 28 99 100
    So the median is 10, which is at index 12, which is midway as the total elements are 25, so the 12th index is exactly midway. Therefore, the answer will be 10.
    Detailed explanation ( Input/output format, Notes, Images )
    Sample Input 1 :
    5 5
    1 5 7 9 11
    2 3 4 8 9
    4 11 14 19 20
    6 10 22 99 100
    7 15 17 24 28

    Sample Output 1 :
    10

    Explanation For Sample Input 1:
    If we arrange the elements of the matrix in the sorted order in an array, they will be like this-
    1 2 3 4 4 5 6 7 7 8 9 9 10 11 11 14 15 17 19 20 22 24 28 99 100
    So the median is 10, which is at index 12, which is midway as the total elements are 25, so the 12th index is exactly midway. Therefore, the answer will be 10.
    Sample Input 2 :
    3 5
    1 2 3 4 5
    8 9 11 12 13
    21 23 25 27 29
    Sample Output 2 :
    11
    Explanation For Sample Input 2:
    If we arrange the elements of the matrix in the sorted order in an array, they will be like this-
    1 2 3 4 5 8 9 11 12 13 21 23 25 27 29

    So the median is 11, which is at index 7, which is midway as the total elements are 15, so the 7th index is exactly midway. Therefore, the answer will be 11.

    Expected Time Complexity:
    Try to solve this in O(32 * m * log(n)).

    Constraints:
    1 <= m < 100
    1 <= n < 100
    1 <= mat[i][j] <=10^9

    Time Limit: 1 sec*/


    /*
    * BruteForce solution
    * Iterate over 2D array and make it 1D array
    * Sort the array
    * and return middle element
    * SC: O(m*n)
    * TC: m*n + (m*n)log(m*n)
    * */
    public static int medianBrute(int[][] arr, int m, int n){
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                list.add(arr[i][j]);
            }
        }
        Collections.sort(list);
        return list.get((m*n)/2);
    }



    /*
    * Better Solution
    * we will be doing binary search on small to large number in array
    * once low crosses high we will be returning low
    * we will be checking for  n.of occurrences less than or equal to the number and greater than (m*n/2);
    * TC: log 10^9 + n log m
    * */
    public static int medianBetter(int[][] arr, int m, int n){
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                low = Math.min(arr[i][j], low);
                high = Math.max(arr[i][j], high);
            }
        }

        while(low<=high){
            int mid = (low+high)/2;
            int count = upperBound(arr, mid);
            if(count <= (m*n)/2) low = mid+1;
            else high = mid-1;
        }
        return low;
    }


    public static int upperBound(int[][] arr, int target){
        int count = 0;
        for(int[] temp : arr){
            int low = 0;
            int high = temp.length-1;
            while(low<=high){
                int mid= (low+high)/2;
                if(temp[mid] <= target){
                    low = mid+1;
                }else{
                    high = mid-1;
                }
            }
            count += high+1;
        }
        return count;
    }
}
