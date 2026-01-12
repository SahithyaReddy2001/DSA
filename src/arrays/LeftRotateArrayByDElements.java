package arrays;

public class LeftRotateArrayByDElements {
    /*
    * BruteForce
    * we will save 1st D elements in temp Array
    * we will shift elements from d+1 index to d-i index
    * we will store temp array elements in last indexes
    * TC : O(N+D)
    * SC : O(D)
    */
    public static int[] leftRotateArrayByDElementsBrute(int[] arr, int d){
        d = d % arr.length;
        int[] temp = new int[d];
        for(int i=0; i<d; i++){
            temp[i] = arr[i];
        }
        for(int i=d; i<arr.length; i++){
            arr[i-d] = arr[i];
        }
        for(int i=arr.length-d; i<arr.length; i++){
            arr[i] = temp[i- (arr.length - d)];
        }
        return arr;
    }


    /*
    * Better
    * eg : [1,2,3,4,5] d = 3
    * step 1: reverse elements from 0 to d-1 [3,2,1,4,5]
    * step 2: reverse elements from d to length-1 [3,2,1,5,4]
    * step 3: reverse entire array [4,5,1,2,3]
    */
    public static int[] leftRotateArrayByDElementsBetter(int[] arr, int d){
        d = d % arr.length;
        reverseArr(arr, 0, d-1);
        reverseArr(arr, d, arr.length-1);
        reverseArr(arr, 0, arr.length-1);
        return arr;
    }

    public static void reverseArr(int[] arr, int start, int end){
        while(start < end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
