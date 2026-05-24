package stack;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement2 {
    //LeetCode: 503
    /*Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.
    The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
    Example 1:
    Input: nums = [1,2,1]
    Output: [2,-1,2]
    Explanation: The first 1's next greater number is 2;
    The number 2 can't find next greater number.
    The second 1's next greater number needs to search circularly, which is also 2.

    Example 2:
    Input: nums = [1,2,3,4,3]
    Output: [2,3,4,-1,4]
    Constraints:
    1 <= nums.length <= 104
    -109 <= nums[i] <= 109*/


    public static int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);
        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j<i+nums.length; j++){
                int index = j > nums.length-1 ? j%nums.length: j;
                if(nums[i] < nums[index]){
                    ans[i] = nums[index];
                    break;
                }
            }
        }
        return ans;
    }


    /*
    * Optimal Solution:
    *
    *
    * */
    public static int[] nextGreaterElementsOptimal(int[] nums) {
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();
        for(int i=nums.length*2 -1; i>=0; i--){
            int index = i%nums.length;
            while(!stack.isEmpty() && stack.peek() <= nums[index]){
                stack.pop();
            }
            if(i < nums.length){
                ans[i] = !stack.isEmpty() ? stack.peek() : -1;
            }
            stack.push(nums[index]);
        }
        return ans;
    }
}
