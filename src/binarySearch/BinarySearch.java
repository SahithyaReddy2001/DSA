package binarySearch;

public class BinarySearch {
    public static int binarySearchIterative(int[] arr, int target){
        int low = 0;
        int high = arr.length-1;
        while (low <= high){
            int mid = low + ((high-low)/2);
            if(arr[mid] == target) return mid;
            else if (arr[mid] < target) {
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return -1;
    }


    public static int binarySearchRecursive(int[] arr, int target, int low, int high){
        int index = -1;
        if(low > high) return index;
        int mid = (high + low) / 2;
        if (arr[mid] == target) return mid;
        else if (arr[mid] < target) {
           index = binarySearchRecursive(arr, target, mid+1, high);
        } else {
           index = binarySearchRecursive(arr,target, low, mid-1);
        }
        return index;
    }
}
