package binarySearch;

public class Search2DMatrix {

    public static boolean searchMatrix(int[][] matrix, int target) {
        int columnSize = matrix[0].length;
        for(int i=0; i<matrix.length; i++){
            if(matrix[i][columnSize-1] == target){
                return true;
            }else if(matrix[i][columnSize-1] > target){
                return binarySearch(matrix, target, i);
            }
        }
        return false;
    }

    public static boolean binarySearch(int[][] arr, int target, int row){
        int low = 0;
        int high = arr[0].length-1;
        while(low <= high){
            int mid = (low+high)/2;
            if(arr[row][mid] == target){
                return true;
            }else if(arr[row][mid] < target){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return false;
    }
}
