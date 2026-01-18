package arrays;

public class MaximumProductSubArray {
    //Q: (2,3,-2,4) OP: 6

    /*
    * Brute:
    * Iterate over all subarrays and find their products
    * Get the max product from them
    * TC: O(N^2)
    * SC: O(1)
    * */
    public static int maximumProductSubArrayBrute(int[] arr){
        int finProd = Integer.MIN_VALUE;
        for(int i=0; i<arr.length; i++){
            int maxProd = 1;
            for(int j=i; j<arr.length; j++){
                maxProd *= arr[j];
                finProd = Math.max(finProd, maxProd);
            }
        }
        return finProd;
    }


    /*
    *Optimal:
    * Find the product from start
    * find the product from start
    * If product becomes 0 make prod =1
    * TC: O(N)
    * SC: O(1)
    * */
    public static int maximumProductSubArrayOptimal(int[] arr){
        int finProd = Integer.MIN_VALUE;
        int fronSum = 1;
        int backSum = 1;
        for(int i=0; i<arr.length; i++){
            fronSum *= arr[i];
            backSum *= arr[arr.length-i-1];
            if(fronSum == 0) fronSum=1;
            if(backSum == 0) backSum=1;
            finProd = Math.max(finProd, Math.max(fronSum, backSum));
        }
        return finProd;
    }
}
