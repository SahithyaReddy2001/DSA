package arrays;

import java.util.ArrayList;

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

    public static int findPages(ArrayList<Integer> arr, int n, int m) {
        int low = Integer.MAX_VALUE;
        int high = 0;
        for(int i: arr){
            low = Math.min(i, low);
            high += i;
        }

        while(low <= high){
            int mid = (low + high)/2;
            if(isAllocationPossile(arr, n, m ,mid)){
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return low;
    }


    public static boolean isAllocationPossile(ArrayList<Integer> arr, int n, int m, int size){
        int students = 1;
        int pages = 0;

        for(int i : arr){
            if(pages + i > size){
                students++;
                pages = i;
            }else{
                pages += i;
            }
        }
        System.out.println(students);

        return students <= m;
    }

    public static int shipWithinDays(int[] arr, int days) {
        int low = Integer.MIN_VALUE;
        int high = 0;
        for(int i: arr){
            low = Math.max(low, i);
            high += i;
        }

        while(low <= high){
            int mid = (low+high)/2;
            int tempDays =isPossible(arr, days, mid);
            if(tempDays <= days)
                high = mid-1;
            else low = mid+1;
        }
        return low;

    }

    public static int isPossible(int[] arr, int days, int weight){
        int count = 0;
        int sum = 0;
        for(int i: arr){
            sum += i;
            if(sum > weight){
                sum= i;
                count++;
            }
        }

        return count+1;
    }

    public static int smallestDivisor(int[] nums, int threshold) {
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for(int i: nums){
            low = Math.min(i, low);
            high = Math.max(i, high);
        }

        while(low <= high){
            int mid = (low+high)/2;
            if(getAns(nums, mid) <= threshold){
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return low;

    }

    public static int getAns(int[] nums, int num){
        int sum =0;
        for(int i: nums){
            sum += (int) Math.ceil((double) i / num);
        }
        return sum;
    }

    public static int minDays(int[] arr, int m, int k) {
        if ((m * k) > arr.length) return -1;

        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            low = Math.min(arr[i], low);
            high = Math.max(arr[i], high);
        }
        while (low <= high) {
            int mid = (low + high) / 2;
            if (isPossible(arr, mid, m, k)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static boolean isPossible(int[] arr, int day, int m, int k) {
        int count = 0;
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= day) {
                count++;
            }else{
                count = 0;
            }
            if (count >= k) {
                temp++;
                count = 0;
            }
            if (temp >= m) {
                return true;
            }
        }
        return false;
    }
}
