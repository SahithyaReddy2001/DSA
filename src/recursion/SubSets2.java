package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSets2 {
    //LeetCode: 90
    /*
        Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
        The solution set must not contain duplicate subsets. Return the solution in any order.

        Example 1:
        Input: nums = [1,2,2]
        Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]

        Example 2:
        Input: nums = [0]
        Output: [[],[0]]

        Constraints:
        1 <= nums.length <= 1
        -10 <= nums[i] <= 10
    */


    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        return rec(nums, 0, new ArrayList<>(), new ArrayList<>());
    }

    public static List<List<Integer>> rec(int[] arr, int index, List<Integer> ansList, List<List<Integer>> finList){
        finList.add(new ArrayList<>(ansList));
        if(index >= arr.length) return finList;
        for(int i= index; i< arr.length; i++){
            if(i != index && arr[i] == arr[i-1]) continue;
            ansList.add(arr[i]);
            rec(arr, i+1, ansList, finList);
            ansList.removeLast();
        }
        return finList;
    }
}
