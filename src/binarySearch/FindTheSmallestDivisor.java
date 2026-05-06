package binarySearch;

public class FindTheSmallestDivisor {
    public static int smallestDivisor(int[] nums, int threshold) {
        int low = 1;
        int high = Integer.MIN_VALUE;
        for(int i: nums){
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
            //sum +=  Math.ceil((double) i / num);
            sum += (i + num - 1) / num;

        }
        return sum;
    }
}
