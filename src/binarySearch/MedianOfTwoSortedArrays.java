package binarySearch;

public class MedianOfTwoSortedArrays {
    //LeetCode: 4
    /*Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
    The overall run time complexity should be O(log (m+n)).

    Example 1:
    Input: nums1 = [1,3], nums2 = [2]
    Output: 2.00000
    Explanation: merged array = [1,2,3] and median is 2.

    Example 2:
    Input: nums1 = [1,2], nums2 = [3,4]
    Output: 2.50000
    Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

    Constraints:
    nums1.length == m
    nums2.length == n
    0 <= m <= 1000
    0 <= n <= 1000
    1 <= m + n <= 2000
    -106 <= nums1[i], nums2[i] <= 106*/


    //Brute Force
    //Iterate over each element from two arrays and make it one sorted array
    //Then find the median
    //TC: O(M+N)
    //SC: O(M+N)
    public static double findMedianOfTwoSortedArrays(int[] arr1, int[] arr2){
        int i = 0;
        int j = 0;
        int[] arr3 = new int[arr1.length + arr2.length];
        int k = 0;
        while(i<arr1.length && j < arr2.length){
            if(arr1[i] <= arr2[j]){
                arr3[k] = arr1[i];
                i++;
            }else{
                arr3[k] = arr2[j];
                j++;
            }
            k++;
        }

        if(i<arr1.length){
            while(i< arr1.length){
                arr3[k] = arr1[i];
                i++; k++;
            }
        }

        if(j<arr2.length){
            while(j< arr2.length){
                arr3[k] = arr2[j];
                j++; k++;
            }
        }

        if(arr3.length%2 == 0){
            int mid = arr3.length/2;
            return (double) (arr3[mid] + arr3[mid-1]) /2;
        }else{
            return arr3[arr3.length/2];
        }
    }




    //Better Solution
    //Iterate over each element from two arrays and take the required elements
    //Then find the median
    //TC: O(M+N)
    //SC: O(1)
    public static double findMedianOfTwoSortedArraysBetter(int[] arr1, int[] arr2){
        int n = arr1.length, m = arr2.length;
        int total = n + m;
        int mid = total / 2;

        int i = 0, j = 0;
        int count = 0;

        int prev = 0, curr = 0;

        while (count <= mid) {
            prev = curr;

            if (i < n && (j >= m || arr1[i] <= arr2[j])) {
                curr = arr1[i++];
            } else {
                curr = arr2[j++];
            }

            count++;
        }

        if (total % 2 == 0) {
            return (prev + curr) / 2.0;
        }

        return curr;
    }




    //Optimal Solution
    //through binary search
    //1  7  8  10
    //5  9  11  12  15
    //if I cut above elements like
    //1  7  8        10
    //   5  9        11  12  15
    // 8<11 && 9<10
    //so answer will be be min(l1,l2)+min(r1,r2)/2
    // for odd case we need to figure out cut2 like n+m+1/2
    //and answer will be min(l1,l2)
    //TC: O(log(min(l1,l2))
    //SC: O(1)
    public static double findMedianOfTwoSortedArraysOptimal(int[] arr1, int[] arr2){
        int n = arr1.length, m = arr2.length;
        if(n > m) return findMedianOfTwoSortedArraysOptimal(arr2, arr1);
        int total = n + m;
        int low =0;
        int high = n;
        while(low <= high){
            int cut1 = (low+high)/2;
            int cut2 = (total+1)/2 - cut1;

            int l1 = cut1 == 0? Integer.MIN_VALUE: arr1[cut1-1];
            int l2 = cut2 == 0? Integer.MIN_VALUE: arr2[cut2-1];

            int r1 = cut1 == n? Integer.MAX_VALUE: arr1[cut1];
            int r2 = cut2 == m? Integer.MAX_VALUE: arr2[cut2];

            if(l1<=r2 && l2<=r1){
                if(total%2==0) {
                    return (double) (Math.max(l1, l2) + Math.min(r1, r2)) / 2;
                }else{
                    return Math.max(l1,l2);
                }
            }else if(l1 > r2){
                high = cut1-1;
            }else{
                low = cut1+1;
            }
        }
        return 0.0;
    }
}
