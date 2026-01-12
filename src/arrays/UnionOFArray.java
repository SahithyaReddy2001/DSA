package arrays;

import java.util.ArrayList;
import java.util.List;

public class UnionOFArray {
    /*
    * Better Using set
    * TC: 0(N)
    * SC: 0(N)
    * */


    /*
    * Optimal
    * TC: O(N)
    * SC: O(1)
    * */

    public static void unionOfArray(int[] arr1, int[] arr2){
        int i=0;
        int j=0;
        List<Integer> list = new ArrayList<>();
        while(i < arr1.length && j< arr2.length){
            if(arr1[i] <= arr2[j]){
                if((list.isEmpty() || list.getLast() != arr1[i])) list.add(arr1[i]);
                i++;
            }else if(arr1[i] > arr2[j]){
                if((list.isEmpty() || list.getLast() != arr2[j])) list.add(arr2[j]);
                j++;
            }
        }

        for(int k = i; k< arr1.length; k++){
            if((list.isEmpty() || list.getLast() != arr1[i]))  list.add(arr1[k]);
        }
        for(int k = j; k< arr2.length; k++){
            if((list.isEmpty() || list.getLast() != arr2[k])) list.add(arr2[k]);
        }
        System.out.println(list);
    }
}
