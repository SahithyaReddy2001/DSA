package arrays;

public class SortArrayWith0s1sAnd2s {
    //BruteForce MergeSort

    /*OptimalSolution
    * [0,2,1,1,1,2,2,0]
    * maintain three variables for n.of zeros, ones and twos
    * in first iteration count all the 0's, 1's and 2's
    * and in next iterate all the three variable and save them according to order in array
    * TC: 0(2N)
    * SC: 0(1)
    * But this can still be optimised from 0(2N) to 0(N)
    */

    public static int[]  sortArrayWith0s1sAnd2sOptimal(int[] arr){
        int zeroCount =0;
        int oneCount = 0;
        int twoCount = 0;
        for (int j : arr) {
            if (j == 0) zeroCount++;
            if (j == 1) oneCount++;
            if (j == 2) twoCount++;
        }

        for (int i=0; i<zeroCount; i++){
            arr[i] = 0;
        }

        for(int i=zeroCount; i<oneCount+zeroCount; i++){
            arr[i] = 1;
        }

        for (int i=oneCount+zeroCount; i<arr.length; i++){
            arr[i] = 2;
        }
        return arr;
    }





    /*Better Solution
    * [0,2,1,1,1,2,2,0]
    * Algorithm: Dutch NationalFlag algorithm
    * we will be maintaining 3 flags here low, mid, high
    * if mid ==0 swap(low, mid) low++, mid++
    * if(mid==1) mid++;
    * if(mid==2) swap(high,mid) high--
    * TC: O(N)
    * SC: O(1)
    */

    public static int[]  sortArrayWith0s1sAnd2sBetter(int[] arr){
        int low =0;
        int mid = 0;
        int high = arr.length-1;
        while(mid<=high){
            if(arr[mid] == 0){
                int temp = arr[mid];
                arr[mid] = arr[low];
                arr[low] = temp;
                low++;
                mid++;
            }else if(arr[mid] == 1){
                mid++;
            }else if(arr[mid] == 2){
                int temp = arr[mid];
                arr[mid] = arr[high];
                arr[high] = temp;
                high--;
            }
        }
        return arr;
    }

}
