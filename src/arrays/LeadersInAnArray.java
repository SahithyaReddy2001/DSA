package arrays;

import java.util.ArrayList;
import java.util.List;

public class LeadersInAnArray {

    //Q: 10,22,12,3,0,6 return the elements which has all the elements smaller to its right (ans : 22,12,6)

    /*
    * Brute
    * pick each element and check if all the elements to it right are smaller
    * if yes add to returning array
    * TC: ~O(N^2)
    * SC: O(1)
    */
    public static List<Integer> leadersInAnArray(int[] arr){
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<arr.length; i++){
            boolean isLeader = true;
            for(int j=i+1; j<arr.length; j++){
                if(arr[i] < arr[j]){
                    isLeader = false;
                    break;
                }
            }
            if(isLeader) list.add(arr[i]);
        }
        return list;
    }


    /*
    * Optimal
    * Iterate the array from last maintain max element
    * if there is an element found greater than max element in its left side index the add to finalList
    * TC: O(N)
    * SC: O(1)
    */
    public static List<Integer> leadersInAnArrayOptimal(int[] arr){
        List<Integer> list = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for(int i=arr.length-1; i>=0; i--){
            if(arr[i] > max){
                max = arr[i];
                list.add(max);
            }
        }
        return list;
    }

}
