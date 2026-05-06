package binarySearch;

public class SplitArray {
    public static int splitArray(int[] nums, int k) {
        int low = Integer.MIN_VALUE;
        int high = 0;
        for(int i: nums){
            low = Math.max(low, i);
            high += i;
        }

        while(low <= high){
            int mid = (low+high)/2;
            if(isSumPossible(nums, k, 15)){
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return low;
    }

    public static boolean isSumPossible(int[] arr, int k, int reqSum){
        int sum = 0;
        int count = 1;
        for(int i : arr){
            sum += i;
            if(sum > reqSum){
                sum = i;
                count++;
            }
        }
        return count <= k;
    }
}
