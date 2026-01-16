package arrays;

import java.util.Arrays;

public class SetMatrixZeros {
    /*
    * Eg :  1,1,1
    *       0,1,0
    *       0,1,0
    *
    * OP:   0,1,0
    *       0,0,0
    *       0,0,0
    * */

    /*
    * BruteForce:
    * Step 1 : Iterate over the matrix and if you find 0 make its respective row and column to -1
    * Step 2 :  Iterate over the array and make 0 where it is 1
    * TC : O(N*M) + O(N) + O(M) + 0(N*M)
    * SC : O(1)
    * */
    public static void setMatrixZerosBrute(int[][] arr){
        for(int i=0; i<arr.length; i++){
            for (int j=0; j<arr[i].length; j++){
                if(arr[i][j] == 0){
                    markRowMinus1(arr, i);
                    markColumnMinus1(arr, j);
                }
            }
        }

        for(int i=0; i<arr.length; i++){
            for (int j=0; j<arr[i].length; j++){
                if(arr[i][j] == -1){
                    arr[i][j] = 0;
                }
            }
        }


    }
    public static void markRowMinus1(int[][] arr, int row){
        for (int i=0; i<arr[0].length; i++){
            if(arr[row][i] == 1)
                arr[row][i] = -1;
        }
    }
    public static void markColumnMinus1(int[][] arr, int column){
        for (int i=0; i<arr.length; i++){
            if(arr[i][column] == 1)
                arr[i][column] = -1;
        }
    }


    /*
     * Better:
     * Step 1 : we will maintain two arrays to store row 0's and column 0's
     * Step 2 : Iterate over the array again and we will check if there is 0 in row or column index and mark it accordingly
     * TC : O(N*M) + 0(N*M)
     * SC : O(M) + O(N)
     * */
    public static void setMatrixZerosBetter(int[][] arr){
        int[] rowIndex = new int[arr.length];
        int[] columnIndex = new int[arr[0].length];
        for(int i=0; i<arr.length; i++){
            for (int j=0; j<arr[i].length; j++){
                if(arr[i][j] == 0){
                    rowIndex[i] = 1;
                    columnIndex[j] = 1;
                }
            }
        }

        for(int i=0; i<arr.length; i++){
            for (int j=0; j<arr[i].length; j++){
                if(rowIndex[i] == 1 || columnIndex[j] == 1){
                    arr[i][j] = 0;
                }
            }
        }
    }


    /*
     * Optimal:
     * Step 1 : we will maintain two arrays to store row 0's and column 0's in the array oth row and oth column
     *          For collision point we will maintain a variable
     * Step 2 : Iterate over the array again and we will check if there is 0 in row or column index and mark it accordingly
     * TC : O(N*M) + 0(N*M)
     * SC : O(1)
     * */
    public static void setMatrixZerosOptimal(int[][] arr){
        int col0 = 1;
        for(int i=0; i<arr.length; i++){
            for (int j=0; j<arr[i].length; j++){
                if(arr[i][j] == 0) {
                    if (j == 0) {
                        col0 = 0;
                    }else{
                        arr[0][j] = 0;
                    }
                    arr[i][0] = 0;
                }
            }
        }

        for(int i=arr.length-1; i>0; i--){
            for (int j=arr[i].length-1; j>0; j--){
                if(arr[i][0] == 0 || arr[0][j] == 0){
                    arr[i][j] = 0;
                }
            }
        }

        if(arr[0][0] == 0){
            Arrays.fill(arr[0], 0);
        }
        if(col0 == 0){
            for (int i=0; i< arr.length; i++){
                arr[i][0] = 0;
            }
        }
    }
}
