package arrays;

import java.util.ArrayList;
import java.util.List;

public class SpiralTraversal {

    /*
    * Q:    1   2  3  4
     *      5   6  7  8
     *      9  10 11 12
     *      13 14 15 16
     *
     * O/P: 1,2,3,4,8,12,16,15,14,12,9,5,6,7,11,10
    * */

    public static List<Integer> spiralTraversal(int[][] arr){
        int left = 0, top =0, bottom = arr.length-1, right = arr[0].length-1;
        List<Integer> list= new ArrayList<>();
        while(left <= right && top <= bottom){
            for(int i= left; i<=right; i++){
                list.add(arr[top][i]);
            }
            top++;
            for(int i= top; i<=bottom; i++){
                list.add(arr[i][right]);
            }
            right--;
            if(top<=bottom) {
                for (int i = right; i >= left; i--) {
                    list.add(arr[bottom][i]);
                }
                bottom--;
            }
            if(left<=right) {
                for (int i = bottom; i >= top; i--) {
                    list.add(arr[i][left]);
                }
                left++;
            }
        }
        return list;
    }
}
