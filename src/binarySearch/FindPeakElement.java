package binarySearch;

public class FindPeakElement {
    //LeetCode: 162
    /*A peak element is an element that is strictly greater than its neighbors.
    Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
    You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
    You must write an algorithm that runs in O(log n) time.

    Example 1:
    Input: nums = [1,2,3,1]
    Output: 2
    Explanation: 3 is a peak element and your function should return the index number 2.
    Example 2:

    Input: nums = [1,2,1,3,5,6,4]
    Output: 5
    Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.

    Constraints:
    1 <= nums.length <= 1000
    -231 <= nums[i] <= 231 - 1
    nums[i] != nums[i + 1] for all valid i.*/


    //Brute
    //Linear search over the array and check if the element is greater than left and right element
    //TC: O(N)
    public static int findPeakElementBrute(int[] arr) {
        if(arr.length == 1) return 0;
        if(arr[0] > arr[1]) return  0;
        if(arr[arr.length-1]> arr[arr.length-2]) return arr.length-1;
        for (int i=1; i< arr.length-1; i++){
            if(arr[i-1] < arr[i] && arr[i] > arr[i+1]) return i;
        }
        return -1;
    }

    //Optimal
    //We can perform binary search
    // consider the below 3 cases
    // case 1: if mid-1<mid>mid+1 return mid.
    // case 2: if mid -1 < mid < mid+1 then  it is in increasing side so low = mid+1;
    // case 3: if mid -1 > mid > mid+1 then  it is in decreasing side so high = mid-1;
    // but we have to be carefull about case 3 consider the example 1,2,1,5,1
    // now if the mid is 1 then neither condition 2 nor 3 will get satisfied then it will become infinite loop
    // so make sure case 3 else part should be conditionless that is just else{} not else if{}
    //TC: O(log N)
    public static int findPeakElementOptimal(int[] arr) {
        if(arr.length == 1) return 0;
        if(arr[0] > arr[1]) return  0;
        if(arr[arr.length-1]> arr[arr.length-2]) return arr.length-1;
        int low = 1;
        int high = arr.length-2;
        while(low <= high){
            int mid = (low + high)/2;
            if(arr[mid] > arr[mid-1] && arr[mid] > arr[mid+1])
                return mid;
            else if(arr[mid-1] < arr[mid] && arr[mid] < arr[mid+1])
                low = mid+1;
            else
                high = mid-1;

        }
        return -1;
    }

}
