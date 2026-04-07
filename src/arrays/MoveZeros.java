package arrays;

import java.util.ArrayList;
import java.util.List;

public class MoveZeros {
    //LeetCode: 283
    /*Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
    Note that you must do this in-place without making a copy of the array.

    Example 1:
    Input: nums = [0,1,0,3,12]
    Output: [1,3,12,0,0]

    Example 2:
    Input: nums = [0]
    Output: [0]

    Constraints:
    1 <= nums.length <= 104
    -231 <= nums[i] <= 231 - 1*/



    //Brute
    //copy all the non-zero numbers to list
    // copy them back to array and make remaining zero
    // TC: O(2N)
    // SC: O(Non zero numbers)
    public void moveZeroesBrute(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int j : arr) {
            if (j != 0) {
                list.add(j);
            }
        }
        int index = 0;
        for(Integer i: list){
            arr[index] = i;
            index++;
        }
        for(int i=index; i<arr.length; i++){
            arr[i] = 0;
        }
    }

    //Optimal
    //Step 1: Find the first occurrence of 0  if not found return
    //Step 2: start from next to the first occurrence of 0
    //        if arr[i] != 0 swap i and j and do j++
    //TC: O(N)
    public void moveZeroes(int[] arr) {
        int j=-1;
        for(int i=0; i<arr.length; i++){
            if(arr[i]==0){
                j=i;
                break;
            }
        }
        if(j==-1) return;
        for(int i=j+1; i<arr.length; i++){
            if(arr[i] != 0){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
        }
    }

}
