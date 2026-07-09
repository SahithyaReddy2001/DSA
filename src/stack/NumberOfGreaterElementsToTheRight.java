package stack;

import java.util.Arrays;
import java.util.Stack;

public class NumberOfGreaterElementsToTheRight {

    /*
    * You will be given and array find the total number of greater elements to the right of a particular element
    * eg: 4,8,5,2,25
    * o/p: 3,1,1,1,0
    * */

    /*
    * TC:O(N^2)
    * SC: O(N)
    * */
    public static int[] findTotalNumberOFGreaterElementsToTheRight(int[] arr){
        int[] ansArr = new int[arr.length];
        for(int i=0; i<arr.length; i++){
            int count = 0;
            for(int j=i+1; j<arr.length; j++){
                if(arr[j] > arr[i]){
                    count++;
                }
            }
            ansArr[i] = count;
        }
        return ansArr;
    }

}
