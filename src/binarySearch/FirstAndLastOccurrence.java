package binarySearch;

public class FirstAndLastOccurrence {

    /*
         Given an array of integers nums sorted in non-decreasing order,
            find the starting and ending position of a given target value.
         If target is not found in the array, return [-1, -1].
         You must write an algorithm with O(log n) runtime complexity.
     */
    //TODO: Check recursion solution
    public static int[] searchRange(int[] arr, int target, int[] ansArr, int start, int end){
        if(start > end) return ansArr;
        int mid = start + ((end-start)/2);
        if(arr[mid] == target){
            ansArr[0] = ansArr[0] == -1 ? mid : Math.min(ansArr[0], mid);
            ansArr[1] = Math.max(ansArr[1],mid);
        }
        searchRange(arr, target, ansArr, start, mid-1);
        searchRange(arr, target, ansArr, mid+1, end);
        return ansArr;
    }

    public static int countOccurrences(int[] arr, int target, int count, int start, int end){
        if(start > end) return count;
        int mid = start + ((end-start)/2);
        if(arr[mid] == target){
            count = count+1;
        }
        count = countOccurrences(arr, target, count, start, mid-1);
        count = countOccurrences(arr, target, count, mid+1, end);
        return count;
    }
}
