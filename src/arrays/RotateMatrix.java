package arrays;

public class RotateMatrix {
    /*
    * Q :  1   2  3  4
    *      5   6  7  8
    *      9  10 11 12
    *      13 14 15 16
    *
    * O/P: 13 9  5 1
    *      14 10 6 2
    *      15 11 7 3
    *      16 12 8 4
    * */


    /*
    * BruteForce
    * Maintain a new array
    * check the index difference store the element in new index
    * i.e arr[i][j] will move to arr[j][n-1-i]
    * TC: O(N^2)
    * SC: O(N^2)
    * */
    public static int[][] rotateMatrixBrute(int[][] arr){
        int[][] ansArr = new int[arr.length][arr[0].length];
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[0].length; j++){
                ansArr[j][arr.length-1-i] = arr[i][j];
            }
        }
        return ansArr;
    }


    /*
    * Optimal
    * Step 1: Traverse the matrix
    * Step 2 : Reverse each Row
    * TC: O(N/2) * O(N/2) + O(N) * O(N/2)
    * SC: O(1)
    * */
    public static void rotateMatrixOptimal(int[][] arr){
        for(int i=0; i<arr.length; i++){
            for(int j=i; j<arr[0].length; j++){
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }

        for (int i=0; i<arr.length; i++) {
            reverseArray(arr, i);
        }
    }

    public static void reverseArray(int[][] arr, int row){
        int i=0, j= arr[row].length-1;
        while (i < j){
            int temp = arr[row][i];
            arr[row][i] = arr[row][j];
            arr[row][j] = temp;
            i++;
            j--;
        }
    }
}
