package arrays;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class MajorityElementsGreaterThanNBy2 {
    //Q: We will be given and array find te element in the array which occurred more than arr.length/2 time


    /*
    * Brute Force: Take each element and store its count and return the element whose count is greater than n/2
    * TC: O(N^2)
    * SC: O(1)
    * */

    public static int majorityElementsGreaterThanNBy2Brute(int[] arr){
        int element = -1;
        int count;
        for (int k : arr) {
            count = 0;
            for (int i : arr) {
                if (i == k) {
                    count++;
                }
            }
            if (count > arr.length / 2) {
                element = k;
                break;
            }
        }
        return element;
    }


    /*
    * Optimal Solution
    * We will be using hasMap to maintain the count of each element
    * And we will iterate over hashMap to find the element which occurred > n/2 times
    * */
    public static int majorityElementsGreaterThanNBy2Optimal(int[] arr){
        Map<Integer, Integer> map = new HashMap<>();
        for (int k : arr) {
            map.put(k, map.getOrDefault(k,0) + 1);
        }
        for (Map.Entry<Integer, Integer> m : map.entrySet()){
            if(m.getValue() > arr.length/2){
                return m.getKey();
            }
        }
        return -1;
    }


    /*
    * Algorithm: Moore's voting algorithm
    * Eg: 1,2,2,3,2,2
    * we will be maintaining two variables element and count
    * i= 1: element = 1 count = 1;
    * i= 2 : element = 1 count = 0; as soon as the currentElement != element decrease count by 1
    * i= 3: element = 2 count = 1; as the count moved to 0 we will change the element to the next index element
    * i= 4: element = 3 count = 0;
    * i=5: element = 2 count =1;
    * i=6: element = 2 count =2;
    * In final whatever the value left in element place  iterate over the array and count number of times it occured
    * if it occurred greater than n/2 times return the element else -1;
    * TC: O(2N)
    * SC: O(1)
    * */
    public static int majorityElementsGreaterThanNBy2Better(int[] arr){
        int element = -1;
        int count = 0;
        for(int i=0; i< arr.length; i++){
            if(count == 0){
                element = arr[i];
                count++;
            }else if(arr[i] == element){
                count++;
            }else{
                count--;
            }
        }
        int tempCount = 0;
        for(int i=0; i<arr.length; i++){
            if(arr[i] == element){
                tempCount++;
            }
        }
        if(tempCount > arr.length/2) return element;
        return -1;
    }


}
