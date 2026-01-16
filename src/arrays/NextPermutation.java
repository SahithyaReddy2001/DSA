package arrays;

public class NextPermutation {
    /*
    * Optimal Solution
    * Eg: 3,4,5,8,10,9,6,4
    * Step 1: find the break point where the peek goes on there will be a down
    *         In above example the breakpoint is 8,10
    * step 2: find the greatest element than 8 but which is near to it and swap them
    *          3,4,5,9,10,8,6,4
    * step 3: Reverse the elements from 10 to 6
    * So the next permutation will be 3,4,5,9,4,6,8,10
    *
    * TC: O(3N)
    * SC: O(1)
    * */
    public static void nextPermutation(int[] arr){
        int index = -1;
        for(int i= arr.length-1; i>0; i--){
            if(arr[i] > arr[i-1]){
                index = i;
                break;
            }
        }
        if(index == -1){
            reverseArray(arr, 0, arr.length-1);
            return;
        }

        for(int i=arr.length-1; i>=index; i--){
            if(arr[i] > arr[index-1]){
                int temp = arr[i];
                arr[i] = arr[index-1];
                arr[index-1] = temp;
                break;
            }
        }
        reverseArray(arr, index, arr.length-1);
    }

    public static void reverseArray(int[] arr, int start, int end){
        while(start < end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

}
