package slidingWindowTwoPointer;

public class BinarySubArrayWithSum {
    //Leetcode: 930 Binary Subarrays With Sum

    /*Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
    A subarray is a contiguous part of the array.

    Example 1:
    Input: nums = [1,0,1,0,1], goal = 2
    Output: 4
    Explanation: The 4 subarrays are bolded and underlined below:
            [1,0,1,0,1]
            [1,0,1,0,1]
            [1,0,1,0,1]
            [1,0,1,0,1]

    Example 2:
    Input: nums = [0,0,0,0,0], goal = 0
    Output: 15

    Constraints:
    1 <= nums.length <= 3 * 104
    nums[i] is either 0 or 1.
    0 <= goal <= nums.length*/


    //Brute Force
    //Iterate over all the subarrays and count all subarrays whose sum == goal
    //TC: O(N^2)
    //SC: O(1)
    public static int numSubarraysWithSumBrute(int[] nums, int goal) {
        int count = 0;
        for(int i=0; i<nums.length; i++){
            int sum = 0;
            for(int j=i; j<nums.length; j++){
                sum+= nums[j];
                if(sum == goal) count++;
                if(sum > goal) break;
            }
        }
        return count;
    }


    public static int numSubarraysWithSumBetter(int[] nums, int goal) {
        int count = 0;
        for(int i=0; i<nums.length; i++){
            int sum = 0;
            for(int j=i; j<nums.length; j++){
                sum+= nums[j];
                if(sum == goal) count++;
                if(sum > goal) break;
            }
        }
        return count;
    }

}
