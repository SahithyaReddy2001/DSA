package stack;

import java.util.Arrays;
import java.util.Stack;

public class NextSmallerElement {
    /*
    * Problem Statement: Given an array of integers arr, your task is to find the Next Smaller Element (NSE) for every element in the array.
      The Next Smaller Element for an element x is defined as the first element to the right of x that is smaller than x.
       If there is no smaller element to the right, then the NSE is -1.*/


    /*
    * TC: O(N^2)
    * SC: O(N) for storing answer
    * */
    public static int[] nextSmallerElement(int[] arr){
        int[] ansArr = new int[arr.length];
        Arrays.fill(ansArr, -1);
        for(int i=0; i<arr.length; i++){
            for(int j=i+1; j<arr.length; j++){
                if(arr[j] < arr[i]){
                    ansArr[i] = arr[j];
                    break;
                }
            }
        }
        return ansArr;
    }

    /*
     * TC: O(N)
     * SC: O(2N)
     * */
    public static int[] nextSmallerElementOptimal(int[] arr){
        int[] ansArr = new int[arr.length];
        Arrays.fill(ansArr, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) ansArr[i] = stack.peek();
            stack.push(arr[i]);
        }
        return ansArr;
    }
}
