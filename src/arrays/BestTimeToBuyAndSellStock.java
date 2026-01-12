package arrays;

public class BestTimeToBuyAndSellStock {

    /*
    *7,1,5,3,6,4
    * Find the max profit we can get after buying the stock
    * we will be storing the minimum at the left for the current element
    * we will be finding the difference between current and min
    * and we will update the finMin
    *TC:O(N)
    *SC:O(1)
    */
    public static int bestTimeToBuyAndSellStock(int[] arr){
        int min = arr[0];
        int profit = 0;
        for(int i=1; i<arr.length; i++){
            if(arr[i] - min > profit) profit = arr[i] - min;
            min = Math.min(arr[i], min);
        }
        return profit;
    }

}
