package binarySearch;

public class KthMissingPositiveInteger {

    //Leetcode 1539
    /*Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
    Return the kth positive integer that is missing from this array.

    Example 1:
    Input: arr = [2,3,4,7,11], k = 5
    Output: 9
    Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.

    Example 2:
    Input: arr = [1,2,3,4], k = 2
    Output: 6
    Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.

    Constraints:
    1 <= arr.length <= 1000
    1 <= arr[i] <= 1000
    1 <= k <= 1000
    arr[i] < arr[j] for 1 <= i < j <= arr.length

    Follow up:
    Could you solve this problem in less than O(n) complexity?*/



    //Brute Force
    // iterate over each element from 2nd index and check how many elements are missing
    //case 1: If target < min number in array then target is the answer
    //case 2: If target > max number in array then max number + (target - total n.of missing numbers till max number) will be the answer
    //case 3: if missing elements >= target the return n.of missing elements till previous elements and target
    // TC : O(n)
    public static int findKthPositiveBrute(int[] arr, int k) {
        if(k<arr[0]) return k;

        for(int i=1; i< arr.length; i++){
            int temp = arr[i]-1-i;
            if(temp >= k){
                int previous = arr[i-1]-1-(i-1);
                return arr[i-1] + (k-previous);
            }
        }
        int temp = arr[arr.length-1]-1-(arr.length-1);
        return arr[arr.length-1]+(k-temp);
    }

    //Optimal Solution
    //case 1: If target < min number in array then target is the answer
    //case 2: If target > max number in array then max number + (target - total n.of missing numbers till max number) will be the answer
    //case 3: using binary search check for every mid how many numbers are missing
    //        if (missing numbers are >= target) high = mid-1;
    //        else low = mid + 1;
    //        once low crosses high, high will be pointing to the element after which target is present
    // return high + (target - total n.of missing numbers till high number)
    //TC : O(log n)
    public static int findKthPositiveOptimal(int[] arr, int k) {
        if(k<arr[0]) return k;
        if(k>arr[arr.length-1]) {
            int temp = arr[arr.length-1]-1-(arr.length-1);
            return arr[arr.length-1]+(k-temp);
        }
        int low = 0;
        int high = arr.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            int temp = arr[mid]-1-mid;
            if(temp>=k) high=mid-1;
            else low = mid+1;
        }

        int temp = arr[high] - 1- high;
        return arr[high] + (k-temp);
    }

}
