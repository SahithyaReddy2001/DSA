package binarySearch;

public class LowerBound {
    //Q: [3,5,8,9,10,15] target = 7 O/P: 8

    public static int lowerBound(int[] arr, int target){
        int ans = arr.length-1;
        int low = 0;
        int high = arr.length-1;
        while (low<=high){
            int mid = (high + low)/2;
            if(arr[mid] < target) low = mid+1;
            else if(arr[mid] >= target){
                ans = mid;
                high = mid-1;
            }
        }

        return ans;
    }
}
