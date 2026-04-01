package binarySearch;

public class FindPeakElement2 {

    /*A peak element in a 2D grid is an element that is strictly greater than all of its adjacent neighbors to the left, right, top, and bottom.
    Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find any peak element mat[i][j] and return the length 2 array [i,j].
    You may assume that the entire matrix is surrounded by an outer perimeter with the value -1 in each cell.
    You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.

    Example 1:
    Input: mat = [[1,4],[3,2]]
    Output: [0,1]
    Explanation: Both 3 and 4 are peak elements so [1,0] and [0,1] are both acceptable answers.

    Example 2:
    Input: mat = [[10,20,15],[21,30,14],[7,16,32]]
    Output: [1,1]
    Explanation: Both 30 and 32 are peak elements so [1,1] and [2,2] are both acceptable answers.

    Constraints:

    m == mat.length
    n == mat[i].length
    1 <= m, n <= 500
    1 <= mat[i][j] <= 105
    No two adjacent cells are equal.*/

    //Brute Force Solution
    //Iterate over each element in 2D array and check if its peak
    //TC: 0(4*m*n)
    public static int[] findPeakGridBrute(int[][] arr) {
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[0].length; j++){
                int left = j>=1 ? arr[i][j-1] : -1;
                int right = j<arr[0].length-1 ? arr[i][j+1] : -1;
                int top = i>=1 ? arr[i-1][j] : -1;
                int bottom= i<arr.length-1 ? arr[i+1][j] : -1;
                int max = Math.max(Math.max(left, right), Math.max(top, bottom));
                if(arr[i][j] > max) return new int[]{i,j};

            }
        }
        return new int[]{-1,-1};
    }


    //Better Solution
    //Return the max element
    //TC: 0(m*n)
    public static int[] findPeakGridBetter(int[][] arr) {
        int max = -1;
        int[] ans = new int[]{-1,-1};
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[0].length; j++){
               if(arr[i][j] > max) {
                   max = arr[i][j];
                   ans[0] = i;
                   ans[1] = j;
               }

            }
        }
        return ans;
    }


    //Optimal Solution
    //Step1: Find the mid among the rows or columns
    //Step2: Find max in that row or column
    //Step3: check with its left right top and bottom
    //Step4: if peak found return
    //Step5: else continue with the process
    //TC: 0(m * log n)
    public static int[] findPeakGridOptimal(int[][] arr) {
        int low = 0;
        int high = arr.length-1;
        while(low <= high){
            int mid = (low+high)/2;
            int max = -1;
            int column = -1;
            for(int i=0; i< arr[0].length; i++){
                if(max < arr[mid][i]){
                    max = arr[mid][i];
                    column = i;
                }
            }
            int top = mid > 0 ? arr[mid-1][column] : -1;
            int bottom = mid < arr.length-1 ? arr[mid+1][column] : -1;
            if(max > top && max > bottom) return  new int[]{mid, column};
            else if(max > top) low = mid+1;
            else high = mid-1;
        }
        return new int[]{-1,-1};
    }
}
