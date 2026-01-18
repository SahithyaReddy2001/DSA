package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeOverLappingIntervals {

    //Q: Given List arrays eg: (1,3) (2,6) (8,9) (9,11) (8,10) (2,4) (15,18) (16, 17)
    //OP: (1,6) (8,11) (15,18)


    /*
    * Optimal:
    * We will sort the Array
    * For each element we will check if if it comes in answers last interval
    * If yes we will merge it
    * Else we will add it as new interval in ans
    * SC : O(N) To return the answer
    * TC : O(N)
    */
    public static List<List<Integer>> mergeOverLappingIntervals(int[][] arr){
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
        for (int i=0; i< arr.length; i++){
            if(list.isEmpty() || list.getLast().get(1) < arr[i][0]){
                list.add(Arrays.asList(arr[i][0], arr[i][1]));
            }else{
                list.set(list.size()-1, Arrays.asList(list.getLast().get(0), Math.max(list.getLast().get(1), arr[i][1])));
            }
        }
        return list;
    }
}
