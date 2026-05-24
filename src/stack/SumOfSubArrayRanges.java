package stack;

import java.util.Stack;

public class SumOfSubArrayRanges {
    //LeetCode: 2104

    /*You are given an integer array nums. The range of a subarray of nums is the difference between the largest and smallest element in the subarray.
    Return the sum of all subarray ranges of nums.
    A subarray is a contiguous non-empty sequence of elements within an array.

    Example 1:
    Input: nums = [1,2,3]
    Output: 4
    Explanation: The 6 subarrays of nums are the following:
            [1], range = largest - smallest = 1 - 1 = 0
            [2], range = 2 - 2 = 0
            [3], range = 3 - 3 = 0
            [1,2], range = 2 - 1 = 1
            [2,3], range = 3 - 2 = 1
            [1,2,3], range = 3 - 1 = 2
    So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.

    Example 2:
    Input: nums = [1,3,3]
    Output: 4
    Explanation: The 6 subarrays of nums are the following:
            [1], range = largest - smallest = 1 - 1 = 0
            [3], range = 3 - 3 = 0
            [3], range = 3 - 3 = 0
            [1,3], range = 3 - 1 = 2
            [3,3], range = 3 - 3 = 0
            [1,3,3], range = 3 - 1 = 2
    So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.

    Example 3:
    Input: nums = [4,-2,-3,4,1]
    Output: 59
    Explanation: The sum of all subarray ranges of nums is 59.

    Constraints:
    1 <= nums.length <= 1000
    -109 <= nums[i] <= 109
    Follow-up: Could you find a solution with O(n) time complexity?*/


    //Iterate over all subarrays compute max - min in subarray and add it to the final sum
    //TC: O(N^2)
    //SC: O(1)
    public static long subArrayRanges(int[] nums) {
        long sum = 0;
        for(int i=0; i<nums.length; i++){
            int min = nums[i];
            int max = nums[i];
            for(int j=i+1; j<nums.length; j++){
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                sum+= (max-min);
            }
        }
        return sum;
    }

    public static int[] nextSmallerElement(int[] arr){
        int[] ansArr = new int[arr.length];
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        for(int i=arr.length-1; i>=0; i--){
            while(!stack.isEmpty() && arr[stack.peek()] >arr[i]){
                stack.pop();
            }
            if(!stack.isEmpty()) ansArr[i] = stack.peek();
            else ansArr[i] = arr.length;
            stack.push(i);
        }
        return ansArr;
    }

    public static int[] prevSmallerElementOrEqual(int[] arr){
        int[] ansArr = new int[arr.length];
        java.util.Stack<Integer> stack = new Stack<>();
        for(int i=0; i<arr.length; i++){
            while(!stack.isEmpty() && arr[stack.peek()] >=arr[i]){
                stack.pop();
            }
            if(!stack.isEmpty()) ansArr[i] = stack.peek();
            else ansArr[i] = -1;
            stack.push(i);
        }
        return ansArr;
    }


    public static int[] nextGreaterElement(int[] arr){
        int[] ansArr = new int[arr.length];
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        for(int i=arr.length-1; i>=0; i--){
            while(!stack.isEmpty() && arr[stack.peek()] <arr[i]){
                stack.pop();
            }
            if(!stack.isEmpty()) ansArr[i] = stack.peek();
            else ansArr[i] = arr.length;
            stack.push(i);
        }
        return ansArr;
    }

    public static int[] prevGreaterElementOrEqual(int[] arr){
        int[] ansArr = new int[arr.length];
        java.util.Stack<Integer> stack = new Stack<>();
        for(int i=0; i<arr.length; i++){
            while(!stack.isEmpty() && arr[stack.peek()] <=arr[i]){
                stack.pop();
            }
            if(!stack.isEmpty()) ansArr[i] = stack.peek();
            else ansArr[i] = -1;
            stack.push(i);
        }
        return ansArr;
    }


    public static long sumSubarrayRangesOptimal(int[] arr) {
        long sum = 0;
        long sumOfSubArrayMaximum = 0;
        long sumOfSubArrayMinimum = 0;
        int[] nse = nextSmallerElement(arr);
        int[] pse = prevSmallerElementOrEqual(arr);
        int[] nge = nextGreaterElement(arr);
        int[] pge = prevGreaterElementOrEqual(arr);
        for(int i=0; i<arr.length; i++){
            int left = i-pse[i];
            int right = nse[i] - i;
            long freq = (long) left * right;
            int val = (int)((freq * arr[i]));
            sumOfSubArrayMinimum = (sumOfSubArrayMinimum + val);
        }

        for(int i=0; i<arr.length; i++){
            int left = i-pge[i];
            int right = nge[i] - i;
            long freq = (long) left * right;
            int val = (int)((freq * arr[i]));
            sumOfSubArrayMaximum = (sumOfSubArrayMaximum + val);
        }
        return sumOfSubArrayMaximum - sumOfSubArrayMinimum;
    }
}
