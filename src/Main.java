import binarySearch.FindPeakElement2;
import binarySearch.MedianARowWiseSortedMatrix;
import binarySearch.Search2DMatrix;
import binarySearch.Search2DMatrix2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {{1,5,7,9,11},{2,3,4,8,9},{4,11,14,19,20},{6,10,22,99,100},{7,15,17,24,28}};
        System.out.println(MedianARowWiseSortedMatrix.medianBetter(matrix, 5,5));

    }
}
