package recursion;

import java.util.*;

public class CombinationSum2 {
    //Leetcode: 40
    /*
    * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

        Each number in candidates may only be used once in the combination.

        Note: The solution set must not contain duplicate combinations.



        Example 1:

        Input: candidates = [10,1,2,7,6,1,5], target = 8
        Output:
        [
        [1,1,6],
        [1,2,5],
        [1,7],
        [2,6]
        ]
        Example 2:

        Input: candidates = [2,5,2,1,2], target = 5
        Output:
        [
        [1,2,2],
        [5]
        ]


        Constraints:

        1 <= candidates.length <= 100
        1 <= candidates[i] <= 50
        1 <= target <= 30
    * */


    //BruteForce
    public List<List<Integer>> combinationSum2(int[] arr, int k) {
        Arrays.sort(arr);
        return new ArrayList<>(recPrint(0, arr, new ArrayList<>(), new HashSet<>(), 0, k));

    }

    public static Set<List<Integer>> recPrint(int index, int[] arr, List<Integer> currentList, Set<List<Integer>> finList, int sum, int k){
        if(sum == k){
            finList.add(new ArrayList<>(currentList));
            return finList;
        }else if(sum>k || index>= arr.length){
            return finList;
        }
        sum += arr[index];
        currentList.add(arr[index]);
        recPrint(index+1, arr, currentList, finList, sum, k);
        sum -= arr[index];
        currentList.removeLast();
        recPrint(index+1, arr, currentList, finList, sum, k);
        return finList;
    }


    //Optimal
    public List<List<Integer>> combinationSum2Optimal(int[] arr, int k) {
        Arrays.sort(arr);
        return recPrintOptimal(0, arr, new ArrayList<>(), new ArrayList<>(), k);

    }

    public static List<List<Integer>> recPrintOptimal(int index, int[] arr, List<Integer> currentList, List<List<Integer>> finList, int sum){
        if(sum == 0){
            finList.add(new ArrayList<>(currentList));
            return finList;
        }else if(index>= arr.length || arr[index] > sum){
            return finList;
        }
        for(int i=index; i<arr.length; i++){
            if(i>index && arr[i] == arr[i-1]) continue;
            if(arr[index] > sum) break;
            currentList.add(arr[i]);
            recPrintOptimal(i+1, arr, currentList, finList, sum-arr[i]);
            currentList.removeLast();
        }
        return finList;
    }
}
