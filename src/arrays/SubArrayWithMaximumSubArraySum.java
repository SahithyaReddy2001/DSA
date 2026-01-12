package arrays;

public class SubArrayWithMaximumSubArraySum {
    /*
    * Brute Solution
    * Find all subarrays and store the max sum from them
    * TC: O(N^3)
    * SC: O(1)
    */
    public static int[] subArrayWithMaximumSubArraySumBrute(int[] arr){
        int[] ans = new int[3];
        for(int i=0; i< arr.length; i++){
            for (int j=i; j<arr.length; j++){
                int sum =0;
                for (int k=i; k<=j; k++){
                    sum += arr[k];
                }
                if(sum > ans[2]){
                    ans[0] = i;
                    ans[1] = j;
                    ans[2] = sum;
                }
            }
        }
        return ans;
    }



    /*
    * Optimal Solution
    * Find all subarrays and store the max sum from them
    * TC: O(N^2)
    * SC: O(1)
    */
    public static int[] subArrayWithMaximumSubArraySumBetter(int[] arr){
        int[] ans = new int[3];
        for(int i=0; i< arr.length; i++){
            int sum =0;
            for (int j=i; j<arr.length; j++){
                sum += arr[j];
                if(sum > ans[2]){
                    ans[0] = i;
                    ans[1] = j;
                    ans[2] = sum;
                }
            }
        }
        return ans;
    }

    /*
    * Optimal Solution
    * Algorithm: KADANE'S Algorithm
    * Eg: -3,4,-1,5,6,-6,-2
    * We will be maintaining two variable sum = Math.Min and finSum = sum
    * for every iteration
    * if sum becomes <0 we will make sum = 0;
    * else sum += arr[i]
    * compare sum with maxSUm If it's greater than make maxSum=sum
    * TC: O(1)
    * SC: O(1)
    */
    public static int[] subArrayWithMaximumSubArraySumOptimal(int[] arr){
        int[] ans = new int[3];
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        int startIndex = 0;
        for(int i=0; i< arr.length; i++){
            if(sum < 0){
                startIndex = i;
                sum = 0;
            }
            sum += arr[i];
            if(sum > maxSum){
                maxSum = sum;
                ans[0] = startIndex;
                ans[1] = i;
                ans[2] = maxSum;
            }
        }
        return ans;
    }
}
