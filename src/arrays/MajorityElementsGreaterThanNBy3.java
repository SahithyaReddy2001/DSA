package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorityElementsGreaterThanNBy3 {
    //The answer will always contain 2 elements probably

    /*
    * BruteForce:
    * Iterate over the array and check if each element occurred n/3 times If as add it to return list
    * SC: O(1)
    * TC: O(N^2)
    */
    public static List<Integer> majorityElementsGreaterThanNBy3Brute(int[] arr){
        List<Integer> l = new ArrayList<>();
        for (int i=0; i< arr.length; i++){
            int count = 0;
            if(l.isEmpty() || !l.getFirst().equals(arr[i])) {
                for (int j = 0; j < arr.length; j++) {
                    if(arr[i] == arr[j]){
                        count++;
                    }
                }
                if(count > Math.ceil((double) arr.length /3)) l.add(arr[i]);
            }
            if(l.size() == 2) break;
        }
        return l;
    }



    /*
    * Better:
    * HashMap
    * SC: O(N)
    * TC: O(N)
    */
    public static List<Integer> majorityElementsGreaterThanNBy3Better(int[] arr){
        List<Integer> l = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int val = (int) (Math.ceil((double)arr.length/3) +1);
        for (int i=0; i< arr.length; i++){
            map.put(arr[i], map.getOrDefault(arr[i],0) + 1);
            if(map.get(arr[i]) == val){
                l.add(arr[i]);
            }
        }
        return l;
    }


    /*
    * Optimal:
    * We will be using Moore's voting algorithm with slight change
    * SC: O(1)
    * TC: O(2N)
    */
    public static List<Integer> majorityElementsGreaterThanNBy3Optimal(int[] arr){
        List<Integer> l = new ArrayList<>();
        int element1 = 0;
        int count1 = 0;
        int element2 = 0;
        int count2 = 0;
        for(int i=0; i<arr.length; i++){
            if(count1 == 0 && arr[i] != element2){
                element1=arr[i];
                count1++;
            }if(count2 == 0 && arr[i] != element1){
                element2=arr[i];
                count2++;
            }else if(element1 == arr[i]){
                count1++;
            }else if(element2 == arr[i]){
                count2++;
            }else{
                count1--; count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for(int i=0; i<arr.length; i++){
            if(arr[i] == element1)
                count1++;
            if(arr[i] == element2)
                count2++;
        }
        if(count1 >=  (arr.length/3 + 1)){
            l.add(element1);
        }
        if(count2 >= (arr.length/3 + 1)){
            l.add(element2);
        }
        return l;
    }
}
