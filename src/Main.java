import binarySearch.Search2DMatrix;
import binarySearch.Search2DMatrix2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        System.out.println(Search2DMatrix2.searchMatrixBetter(matrix, 18));
        System.out.println(Search2DMatrix2.searchMatrixBrute(matrix, 18));
        System.out.println(Search2DMatrix2.searchMatrixOptimised(matrix, 18));

        System.out.println(Search2DMatrix2.searchMatrixOptimised(matrix, 20));
        System.out.println(Search2DMatrix2.searchMatrixBetter(matrix, 20));
        System.out.println(Search2DMatrix2.searchMatrixBrute(matrix, 20));


        System.out.println(Search2DMatrix2.searchMatrixOptimised(matrix, 5));
        System.out.println(Search2DMatrix2.searchMatrixBetter(matrix, 5));
        System.out.println(Search2DMatrix2.searchMatrixBrute(matrix, 5));

    }
}
