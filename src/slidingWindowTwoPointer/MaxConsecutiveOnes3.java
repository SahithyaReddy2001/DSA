package slidingWindowTwoPointer;

public class MaxConsecutiveOnes3 {
    //LetCode: 1004 Max Consecutive Ones III

    /*Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
    Example 1:
    Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
    Output: 6
    Explanation: [1,1,1,0,0,1,1,1,1,1,1]
    Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

    Example 2:
    Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
    Output: 10
    Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
    Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.


    Constraints:
    1 <= nums.length <= 105
    nums[i] is either 0 or 1.
    0 <= k <= nums.length*/

    /*
    * BruteForce:
    * Iterative over each subarray and check for maximum subarray with 1s by flipping k zeros
    * SC: O(1)
    * TC: O(N^2)
    * */

    public static int longestOnes(int[] nums, int k) {
        int count = 0;
        int maxCount = 0;
        for(int i=0; i<nums.length; i++){
            int j = i;
            count = 0;
            int temp = k;
            while(j < nums.length){
                if(nums[j] == 0){
                    if(temp ==0)
                        break;
                    temp--;
                }
                count++;
                j++;
            }
            maxCount = Math.max(count, maxCount);
        }
        return maxCount;
    }


    /*
    * Better:
    * Keep moving right pointer until zeros > k
    * once zeros is greater than k keep moving left till zeros <=k
    * find the window length
    * update max length
    * TC: O(N) + O(N)
    * SC: O(1)
    * */
    public static int longestOnesBetter(int[] nums, int k) {
        int count = 0;
        int maxCount = 0;
        int l =0;
        int r = 0;
        int zero = 0;
        while(r < nums.length){
            if(nums[r] == 0) zero++;
            while(zero > k){
                if(nums[l] == 0)
                    zero--;
                l++;
            }
            count = r-l+1;
            maxCount = Math.max(count,maxCount);
            r++;
        }
        return maxCount;
    }


    /*
     * Better:
     * Keep moving right pointer until zeros > k
     * once zeros is greater than k move left pointer by 1 step and right by 1
     * if left is 0 decrease zero count
     * find the window length only when zero <=k
     * update max length
     * TC: O(N)
     * SC: O(1)
     * */
    public static int longestOnesOptimal(int[] nums, int k) {
        int count = 0;
        int maxCount = 0;
        int l =0;
        int r = 0;
        int zero = 0;
        while(r < nums.length){
            if(nums[r] == 0) zero++;
            if(zero <= k){
                count = r-l+1;
                maxCount = Math.max(count,maxCount);
            }else{
                if(nums[l] == 0) zero--;
                l++;
            }
            r++;
        }
        return maxCount;
    }
}
