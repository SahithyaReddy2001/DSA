package binarySearch;

public class SearchElementsInRotatedArray1 {

    //Q : [7,8,9,1,2,3,4,5,6] target =2 O/P: 4

    /*
    * check the sorted part either left or right
    * if taget exist in sorted part bs in sorted part
    * else go to unsorted part
    * SC: O(1)
    * TC: O(N log N)
    * */
    public static int searchElementsInRotatedArray1(int[] arr, int target){
        int index = -1;
        int low = 0;
        int high = arr.length-1;
        while(high >= low){
            int mid = low + (high-low)/2;
            if(arr[mid] == target) return mid;
            if(arr[low] <= arr[mid]){
                if(arr[low] <= target && target <= arr[mid]){
                    high = mid-1;
                }else{
                    low = mid+1;
                }
            }else{
                if(arr[mid]<= target && target <= arr[high]){
                    low = mid+1;
                }else{
                    high = mid-1;
                }
            }
        }
        return index;
    }



    //DUPLICATES
    //Q: [4,4,1,2,3,4,4,4,4,4,4] target = 3

    /*
    * trim the array if low == mid == high
    * check the sorted part either left or right
    * if taget exist in sorted part bs in sorted part
    * else go to unsorted part
    * SC: O(1)
    * TC: O(N log N)
    * */
    public static int searchElementsInRotatedArray2(int[] arr, int target){
        int index = -1;
        int low = 0;
        int high = arr.length-1;
        while(high >= low){
            int mid = low + (high-low)/2;
            if(arr[mid] == target) return mid;
            if(arr[low] == arr[mid] && arr[mid] == arr[high]) {
                low = low + 1;
                high = high -1;
                continue;
            }
            if(arr[low] <= arr[mid]){
                if(arr[low] <= target && target <= arr[mid]){
                    high = mid-1;
                }else{
                    low = mid+1;
                }
            }else{
                if(arr[mid]<= target && target <= arr[high]){
                    low = mid+1;
                }else{
                    high = mid-1;
                }
            }
        }
        return index;
    }
}
