package arrays;

import java.util.HashMap;
import java.util.Map;

public class LargestSubArrayWithZeroSum {
    /*
    * BruteForce:
    * Find all subarrays and check if their sum = 0
    * And return the subArray whose length is greater
    * SC: O(1), TC: O(N^2)
    */
    public static int longestSubArrayBrute(int[] arr){
        int maxLength = 0;
        for(int i=0; i< arr.length; i++){
            int sum = 0;
            for(int j=i; j<arr.length; j++){
                sum += arr[j];
                if(sum ==0){
                    maxLength = Math.max(maxLength, j-i+1);
                }
            }
        }
        return maxLength;
    }



    /*
    * Optimal:
    * We will maintain the hashmap which stores current sum and its index
    * And for every sum ==0 we will make maxLen = i+1
    * if sum !=0 we will check in map if sum exists if yes we will check for length
    * else we will add sum and index in map
    * SC: O(N), TC: O(N)
    */
    public static int longestSubArrayOptimal(int[] arr){
        int maxLength = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for(int i=0; i< arr.length; i++){
            sum += arr[i];
            if(sum ==0){
                maxLength = i+1;
            }else {
                if (map.containsKey(sum)) {
                    maxLength = Math.max(maxLength, i - map.get(sum));
                } else {
                    map.put(sum, i);
                }
            }
        }
        return maxLength;
    }
}
