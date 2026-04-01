import binarySearch.FindPeakElement2;
import binarySearch.Search2DMatrix;
import binarySearch.Search2DMatrix2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {{70,50,40,30,20},{100,1,2,3,4}};
        System.out.println(Arrays.toString(FindPeakElement2.findPeakGridBrute(matrix)));
        System.out.println(Arrays.toString(FindPeakElement2.findPeakGridBetter(matrix)));
        System.out.println(Arrays.toString(FindPeakElement2.findPeakGridOptimal(matrix)));

    }
}
