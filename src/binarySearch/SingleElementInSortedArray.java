package binarySearch;

public class SingleElementInSortedArray {
    //LeetCode: 540
    /*You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
    Return the single element that appears only once.
    Your solution must run in O(log n) time and O(1) space.

    Example 1:
    Input: nums = [1,1,2,3,3,4,4,8,8]
    Output: 2

    Example 2:
    Input: nums = [3,3,7,7,10,11,11]
    Output: 10

    Constraints:
    1 <= nums.length <= 105
    0 <= nums[i] <= 105*/


    //Brute Force Solution
    //Iterate over each element and check if its left and right elements are equal
    //If not equal return the element
    //TC: O(N)
    public int singleNonDuplicateBrute(int[] arr) {
        if(arr.length == 1 || arr[0] != arr[1]) return arr[0];
        if(arr[arr.length-2] != arr[arr.length-1]) return arr[arr.length-1];
        for(int i=1; i<arr.length-1; i++){
            if(arr[i] != arr[i-1] && arr[i] != arr[i+1]){
                return arr[i];
            }
        }
        return -1;
    }



    //Optimal Solution
    // 1  1  2  2  3  3  4  5  5  6  6  7   7
    // o  e  o  e  o  e  o  e  o  e  o  e  o
    // If we observe care fully if we observe all the elements before single element they are (odd, even)
    // after single element they are (even, odd)
    //TC: O(log n)
    public int singleNonDuplicateOptimal(int[] arr) {
        if(arr.length == 1 || arr[0] != arr[1]) return arr[0];
        if(arr[arr.length-2] != arr[arr.length-1]) return arr[arr.length-1];
        int low = 1;
        int high = arr.length-2;
        while(low <= high){
            int mid = (low+high)/2;
            if(arr[mid] != arr[mid-1] && arr[mid] != arr[mid+1]){
                return arr[mid];
            }else if(mid%2 != 0 && arr[mid-1] == arr[mid] ||
                    mid%2==0 && arr[mid] == arr[mid+1]){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return -1;
    }
}
