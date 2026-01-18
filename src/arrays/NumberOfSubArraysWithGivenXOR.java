package arrays;

import java.util.HashMap;
import java.util.Map;

public class NumberOfSubArraysWithGivenXOR {
    /*
     * BruteForce:
     * Find all subarrays and check if their XOR  = target
     * And return the scount
     * SC: O(1), TC: O(N^2)
     */
    public static int longestSubArrayWithGivenXORBrute(int[] arr, int target){
        int count = 0;
        for(int i=0; i< arr.length; i++){
            int xor = 0;
            for(int j=i; j<arr.length; j++){
                xor ^= arr[j];
                if(xor == target){
                    count++;
                }
            }
        }
        return count;
    }



    /*
     * Optimal:
     * We will maintain the hashmap which stores current xor and its index
     * For every xor^arr[i] we will check in map if xor^target exists if yes we will do count + how many times its there
     * else we will add xor and count in map
     * SC: O(N), TC: O(N)
     */
    public static int longestSubArrayWithGivenXOROptimal(int[] arr, int target){
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int xor = 0;
        map.put(xor, 1);
        for(int i=0; i< arr.length; i++){
            xor ^= arr[i];
            int tempXor = xor ^ target;
            if(map.containsKey(tempXor)){
                count += map.get(tempXor);
            }
            map.put(xor, map.getOrDefault(xor, 0)+1);
        }
        return count;
    }
}
