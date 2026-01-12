package arrays;

import java.util.*;

public class TwoSum {
    /*
    * This problem has 2 sub questions as below
    * 1)Given an array and a target tell if there are 2 elements in different index in that array which
    * sums = target return yes or no
    * 2) return the indexes of those sums
    */

    // BruteForce TC: O(N^2) SC: O(2) extra space is used just to return the answer
    public static int[] twoSumBrute(int[] arr, int target){
        int[] ans = new int[]{-1,-1};
        for (int i=0; i<arr.length; i++){
            for (int j= i+1; j<arr.length; j++){
                if(arr[i] + arr[j] == target){
                    ans[0] = i;
                    ans[1] = j;
                    break;
                }
            }
        }
        return ans;
    }


    //Optimal TC: O(N)
    // SC: O(N log N) log n depends on type of map we use
    //This is the better solution if we have unordered array
    public static int[] twoSumOptimal(int[] arr, int target){
        int[] ans = new int[]{-1,-1};
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<arr.length; i++){
            if(map.containsKey(target-arr[i])){
                ans[0] = map.get(target-arr[i]);
                ans[1] = i;
                break;
            }
            map.put(arr[i], i);
        }
        return ans;
    }

    //Better TC: O(N)
    // SC: O(1)
    // This is the better solution if we have ordered array else space complexity will be 0(N) for storing indexes
    //This is also a better solution for 1st sub question
    public static List<List<Integer>> twoSumBetter(int[] arr, int target){
        int[] ans = new int[]{-1,-1};
        Arrays.sort(arr);
        List<List<Integer>> finList = new ArrayList<>();
        int i=0;
        int j=arr.length-1;
        while (i<j){
            List<Integer> list = new ArrayList<>();
            if(arr[i] + arr[j] == target){
                list.add(i);
                list.add(j);
                finList.add(list);
                i++;
            }
            if(arr[i] + arr[j] < target){
                i++;
            }else if(arr[i] + arr[j] > target){
                j--;
            }
        }
        return finList;
    }

}
