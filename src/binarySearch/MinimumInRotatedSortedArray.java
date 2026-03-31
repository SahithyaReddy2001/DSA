package binarySearch;

public class MinimumInRotatedSortedArray {
    public static int minimumInRotatedSortedArray(int[] arr){
        int low=0;
        int high = arr.length-1;
        int min = Integer.MAX_VALUE;
        while(low<=high){
            int mid = (low+high)/2;
            if(arr[low] < arr[high]){
                min = Math.min(min, arr[low]);
                break;
            }
            if(arr[low] <= arr[mid]){
                min = Math.min(min, arr[low]);
                low = mid+1;
            }else{
                min = Math.min(min, arr[mid]);
                high = mid-1;            }
        }
        return min;
    }

    public static int howManyTimesArrayIsRotated(int[] arr){
        int low=0;
        int high = arr.length-1;
        int min = Integer.MAX_VALUE;
        int index = 0;
        while(low<=high){
            int mid = (low+high)/2;
            if(arr[low] < arr[high]){
                if(min > arr[low]) index = low;
                min = Math.min(min, arr[low]);
                break;
            }
            if(arr[low] <= arr[mid]){
                if(min > arr[low]) index = low;
                min = Math.min(min, arr[low]);
                low = mid+1;
            }else{
                if(min > arr[mid]) index = mid;
                min = Math.min(min, arr[mid]);
                high = mid-1;            }
        }
        return index;
    }
}
