package stack;

import java.util.Stack;

public class SumOfSubarrayMinimums {
    //LeetCode:907
    /*Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr.
    Since the answer may be large, return the answer modulo 109 + 7.

    Example 1:
    Input: arr = [3,1,2,4]
    Output: 17
    Explanation:
    Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
    Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
    Sum is 17.

    Example 2:
    Input: arr = [11,81,94,43,3]
    Output: 444

    Constraints:
    1 <= arr.length <= 3 * 104
    1 <= arr[i] <= 3 * 104*/

    public static int sumSubarrayMins(int[] arr) {
        int sum = 0;
        for(int i=0; i<arr.length; i++){
            int min = Integer.MAX_VALUE;
            for(int j=i; j<arr.length; j++){
                min = Math.min(min, arr[j]);
                sum += min;
                sum %= (int) (1e9+7);
            }
        }
        return sum;
    }


    public static int sumSubarrayMinsOptimal(int[] arr) {
        long sum = 0;
        int mod = (int) 1e9+7;
        int[] nse = nextSmallerElement(arr);
        int[] pse = prevSmallerElementOrEqual(arr);
        for(int i=0; i<arr.length; i++){
            int left = i-pse[i];
            int right = nse[i] - i;
            long freq = (long) left * right;
            int val = (int)((freq * arr[i]) % mod);
            sum = (sum + val) % mod;
        }
        return (int)sum;
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
}
