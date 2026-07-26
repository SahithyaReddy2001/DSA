package recursion;

import java.util.ArrayList;
import java.util.List;

public class CountAllSubsequencesWithSumK {
    /*
    * Problem Statement: Given an array nums and an integer k.Return the number of
    *  non-empty subsequences of nums such that the sum of all elements in the
    * subsequence is equal to k.

        Examples
        Example 1:
        Input :
         nums = [4, 9, 2, 5, 1] , k = 10
        Output :
         2
        Explanation :
         The possible subsets with sum k are [9, 1] , [4, 5, 1].

        Example 2:
        Input :
         nums = [4, 2, 10, 5, 1, 3] , k = 5
        Output :
         3
        Explanation :
         The possible subsets with sum k are [4, 1] , [2, 3] , [5].
    * */

    public static int countSubSequencesWithSumK(List<Integer> arr, int k){
        return recCount(0, arr,0, k);
    }

    public static int recCount(int index, List<Integer> arr, int sum, int k){
        if(sum == k){
            return 1;
        }else if(sum>k || index>= arr.size()){
            return 0;
        }
        sum += arr.get(index);
        int l = recCount(index+1, arr,sum, k);
        sum -= arr.get(index);
        int r = recCount(index+1, arr, sum, k);
        return l+r;
    }





    public static List<List<Integer>> printSubSequencesWithSumK(List<Integer> arr, int k){
        return recPrint(0, arr, new ArrayList<>(), new ArrayList<>(), 0, k);
    }

    public static List<List<Integer>> recPrint(int index, List<Integer> arr, List<Integer> currentList, List<List<Integer>> finList, int sum, int k){
        if(sum == k){
            finList.add(new ArrayList<>(currentList));
            return finList;
        }else if(sum>k || index>= arr.size()){
            return finList;
        }
        sum += arr.get(index);
        currentList.add(arr.get(index));
        recPrint(index+1, arr, currentList, finList, sum, k);
        sum -= arr.get(index);
        currentList.removeLast();
        recPrint(index+1, arr, currentList, finList, sum, k);
        return finList;
    }

    /*
    * Problem Statement: Given an array nums and an integer k. Return true if there exist subsequences such that the sum of all elements in subsequences is equal to k else false.
    Examples
    Example 1:
    Input :
     nums = [1, 2, 3, 4, 5] , k = 8
    Output :
     Yes
    Explanation :
     The subsequences like [1, 2, 5] , [1, 3, 4] , [3, 5] sum up to 8.

    Example 2:
    Input :
     nums = [4, 3, 9, 2] , k = 10
    Output :
     No
    Explanation :
     No subsequence can sum up to 10.
    * */

    public static boolean isSubSequencesWithSumK(List<Integer> arr, int k){
        return recCheck(0, arr,0, k);
    }

    public static boolean recCheck(int index, List<Integer> arr, int sum, int k){
        if(sum == k){
            return true;
        }else if(sum>k || index>= arr.size()){
            return false;
        }
        sum += arr.get(index);
        boolean l = recCheck(index+1, arr,sum, k);
        if(l) return true;
        sum -= arr.get(index);
        return recCheck(index+1, arr, sum, k);
    }

}
