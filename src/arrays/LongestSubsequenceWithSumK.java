package arrays;

import java.util.HashMap;
import java.util.Map;

public class LongestSubsequenceWithSumK {

    /*
     * BruteForce Approach
     * In this approach we will find all the subsequence and their sums
     * if the sum == ans we will check for length and if length > finLength we will make finLength = length
     * TC = O(N^2)
     * SC = O(1)
     * */

    public static int longestSubSequenceBrute(int[] arr, int ans) {
        int sum = 0;
        int length = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum == ans && length < j - i + 1) {
                    length = j - i + 1;
                }
            }
        }
        return length;
    }


    /*
     * Better Solution
     * In this solution we will do reverse mathematics using HashMap
     * We will run a loop from 0 to length
     * if previousLength+currentLength == ans maxLength will be max(maxLength, index+1)
     * else we will check if previousLength+currentLength-ans exists in hashMap
     * if yes finaLength will be max(maxLength, index- (previousLength+currentLength-ans index in HashMap))
     * for every step we will insert previousLength+currentLength and index
     * and insertion will happen into hashmap only if the key doesnot exist to cover edgecase of (0)
     * TC : 0(N)
     * SC : 0(N)
     * This is the optimal solution if we have positive, negatives and zeros in array
     * But for array which have only positives we can optimize it further
     */

    public static int longestSubSequenceBetter(int[] arr, int ans) {
        Map<Long, Integer> map = new HashMap<>();
        long sum = 0;
        int maxLength = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum == ans) {
                maxLength = Math.max(maxLength, i + 1);
            }
            long key = sum - ans;
            if (map.containsKey(key)) {
                maxLength = Math.max(maxLength, i - map.get(key));
            }

            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return maxLength;
    }


    /*
    *  Optimal Approach
    *  This will work only for Positive Array
    *  we will start 2 pointer i=0 and j=0;
    *  for every step we will add sum += arr[i]
    *  if sum < ans we will move right pointer by 1
    *  if sum > ans we will move left pointer till that index where sum will become <= ans
    *  if sum == ans we will check maxLength ans Sum
    *  TC: O(N)
    *  SC: O(1)
    * */

    public static int longestSubSequenceOptimal(int[] arr, int ans) {
        int maxLength = 0;
        int i = 0;
        int j = 0;
        int sum = 0;
        while (i < arr.length && j < arr.length) {
            sum += arr[j];
            while (sum > ans) {
                sum -= arr[i];
                i++;
            }
            if (sum == ans) {
                maxLength = Math.max(maxLength, j - i + 1);
                System.out.println(maxLength + "i:" + i + "j:" + j);
            }
            j++;
        }
        return maxLength;
    }

}
