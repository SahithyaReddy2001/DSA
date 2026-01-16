package arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    //Q: In the array 102, 4,100,1,101,3,2,1,1 O/P: 4 because there are 2 consecutive sequences (1,2,3,4)
    // and 100,101,102 in both of them 1,2,3,4 is long whose length is 4 so output is 4

    /*
    * BruteForce Solution
    * Take every element check for its next element in array if found increase count
    * else go to next element
    * Return the max count at last
    *
    * TC: O(N^2)
    * SC: O(1)
    */
    public static int longestConsecutiveSequenceBrute(int[] arr){
        int ans = 1;
        int finAns = 1;
        for (int i=0; i< arr.length; i++){
            int var = arr[i];
            while (linearSearch(arr, var+1)){
                var = var+1;
                ans++;
            }
            finAns = Math.max(ans, finAns);
            ans = 1;
        }
        return  finAns;
    }

    public static boolean linearSearch(int[] arr, int num){
        for (int j : arr) {
            if (j == num) {
                return true;
            }
        }
        return false;
    }


    /*
    * Better Solution
    * Step 1 : sort the array
    * Step 2: iterate over the array and keep not of last minimum
    *         if current = lastminimun + 1 count++;
    *         else count =1;
    * step 3: return the finalCount;
    *
    * TC: O(N log N) + O(N)
    * SC: O(1)
    */
    public static int longestConsecutiveSequenceBetter(int[] arr){
        Arrays.sort(arr);
        int ans = 1;
        int finAns = 1;
        int lastMin = Integer.MIN_VALUE;
        for (int i=0; i< arr.length; i++){
            if(arr[i] == lastMin) continue;
            if(arr[i] == lastMin+1){
                ans++;
            }else{
                ans = 1;
            }
            lastMin = arr[i];
            finAns = Math.max(ans, finAns);
        }
        return  finAns;
    }



    /*
    * Optimal Solution
    * Step 1: Insert the array elements in set
    * Step 2: For every element check if its previous element is present
    *         If present Go to next element
    *         If not present check how its consecutive next elements are present
    *         Store the count
    * step 3: return the finalCount;
    *
    * TC: O(3N)
    * SC: O(N)
    */
    public static int longestConsecutiveSequenceOptimal(int[] arr){
        Set<Integer> set = new HashSet<>();
        for (Integer i: arr){
            set.add(i);
        }
        int count = 1;
        int finCount = 1;
        for(Integer i: set){
            if(!set.contains(i-1)){
                count = 1;
                int temp = i;
                while(set.contains(temp+1)){
                    temp++;
                    count++;
                }
                finCount = Math.max(count, finCount);
            }
        }
        return finCount;
    }


}
