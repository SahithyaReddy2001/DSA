package recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SumOfAllSubsets {
    //TODO: Need to do it in Bit Manipulation way
    /*
    * Problem Statement: Given an array print all the sum of the subset generated from it, in the increasing order.
        Examples:
        Input: N = 3, arr[] = {5,2,1}
        Output: 0,1,2,3,5,6,7,8
        Explanation: We have to find all the subset’s sum and print them.in this case the generated subsets are [ [], [1], [2], [2,1], [5], [5,1], [5,2]. [5,2,1],so the sums we get will be  0,1,2,3,5,6,7,8

        Input: N=3,arr[]= {3,1,2}
        Output: 0,1,2,3,3,4,5,6
        Explanation: We have to find all the subset’s sum and print them.in this case the generated subsets are [ [], [1], [2], [2,1], [3], [3,1], [3,2]. [3,2,1],so the sums we get will be  0,1,2,3,3,4,5,6
    * */

    public static List<Integer> printSumOfSubsets(int[] arr){
        List<Integer> list = printSumOfSubsetsRec(arr, 0, 0, new ArrayList<>());
        Collections.sort(list);
        return list;
    }

    public static List<Integer> printSumOfSubsetsRec(int[] arr, int index, int sum, List<Integer> ansList){
        if(index >= arr.length) return ansList;
        sum += arr[index];
        ansList.add(sum);
        printSumOfSubsetsRec(arr, index+1, sum, ansList);
        sum -= arr[index];
        printSumOfSubsetsRec(arr, index+1, sum, ansList);
        return ansList;
    }

}
