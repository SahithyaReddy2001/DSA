package binarySearch;

public class Search2DMatrix2 {
    /*
    Leetcode- 240
    Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
    Integers in each row are sorted in ascending from left to right.
    Integers in each column are sorted in ascending from top to bottom.

    Example 1:
    Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
    Output: true

    Example 2:
    Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
    Output: false

    Constraints:
    m == matrix.length
    n == matrix[i].length
    1 <= n, m <= 300
    -10^9 <= matrix[i][j] <= -10^9
    All the integers in each row are sorted in ascending order.
    All the integers in each column are sorted in ascending order.
    --10^9 <= target <= -10^9*/

    //Brute force solution
    // Iterate over each element and check if its equal to target
    //Time complexity - O(N * M)
    public static boolean searchMatrixBrute(int[][] arr, int target) {
        for (int[] ints : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                if (ints[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }


    //Better solution
    //Iterate over each row and apply binary search to check if target is present
    //Time complexity - O(N * log M)
    public static boolean searchMatrixBetter(int[][] arr, int target) {
        for (int[] tempArr : arr) {
            int low = 0;
            int high = tempArr.length - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (tempArr[mid] == target) return true;
                else if (tempArr[mid] > target) high = mid - 1;
                else low = mid + 1;
            }
        }
        return false;
    }



    //Optimal solution
    // 1  4  7  11  15
    // 2  5  8  12  19
    // 3  6  9  16  22
    // 10 13 14 17  24
    // 18 21 23 26  30
    // If we observe right top corner all the elements to its left are small and all the elements down to it are increasing
    // so we will compare that element with target
    // if arr[i][j] < target eliminate the row
    // else eliminate the column
    // we can follow above algo from last rows very first element
    //Time complexity - O(N + M)
    public static boolean searchMatrixOptimised(int[][] arr, int target) {
        int i = 0;
        int j = arr[0].length-1;
        while(i<arr.length && j >=0){
            if(arr[i][j] == target){
                return true;
            }else if(arr[i][j] > target){
                j--;
            }else{
                i++;
            }
        }
        return false;
    }
}
