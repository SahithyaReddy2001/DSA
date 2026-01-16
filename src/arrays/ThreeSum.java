package arrays;

import java.util.*;

public class ThreeSum {
    //Q: Find three unique elements from array whose sum = 0

    /*
    * BruteForce:
    * Iterate over each element and find its sum if sum =0  sort it and add it into a set
    * And return the set
    * TC: O(N^3)
    * SC: O(N.of unique triplets)
    */
    public static Set<List<Integer>> threeSumBrute(int[] arr){
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> l = new ArrayList<>();
        for(int i=0; i<arr.length; i++){
            for(int j=i+1; j<arr.length; j++){
                for(int k=j+1; k<arr.length; k++){
                    if(arr[i] + arr[j] + arr[k] == 0){
                        l=new ArrayList<>();
                        l.add(arr[i]);
                        l.add(arr[j]);
                        l.add(arr[k]);
                        Collections.sort(l);
                        set.add(l);
                    }
                }
            }
        }
        return set;
    }


    /*
    * Better:
    * Take 2 variables i, j
    * start iterating i from every element and j from i+1 to n
    * whatever the middle elements in i and j store them in hashset
    * check for is there element in set = -(arr[i] + arr[j])
    * if yes that is the triplet
    * sort it and add it in set
    * TC: O(N^2)
    * SC: O(N.of Unique triplets) + O(N)
    */
    public static Set<List<Integer>> threeSumBetter(int[] arr){
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> l = new ArrayList<>();
        for(int i=0; i<arr.length; i++){
            Set<Integer> s = new HashSet<>();
            for(int j=i+1; j<arr.length; j++){
                if(s.contains(-1*(arr[i]+arr[j]))){
                    l= new ArrayList<>();
                    l.add(arr[i]);
                    l.add(arr[j]);
                    l.add(-1*(arr[i]+arr[j]));
                    Collections.sort(l);
                    set.add(l);
                }
                s.add(arr[j]);
            }
        }
        return set;
    }



    /*
    * Optimal:
    * We will sort the array
    * then we will use 3 variable i,j and k
    * i will iterate for every element j =i+1 and k = arr.length-1
    * if(i+j+k) < 0 j++
    * if(i+j+k) > 0 k--;
    * else it's a triplet we will move j and k until its not current element
    * Base case i<j
    * TC: O(N log N) + O(N^2)
    * SC: O(1) We are using extra space to return the answer not t solve the problem
    */
    public static Set<List<Integer>> threeSumOptimal(int[] arr){
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> l = new ArrayList<>();
        Arrays.sort(arr);
        for(int i=0; i<arr.length; i++){
            if(i>0 && arr[i] == arr[i-1]) continue;
            int j=i+1;
            int k=arr.length-1;
            while(j<k){
                int sum = arr[i] + arr[j] + arr[k];
                if(sum<0) j++;
                else if(sum>0) k--;
                else{
                    l = new ArrayList<>();
                    l.add(arr[i]);
                    l.add(arr[j]);
                    l.add(arr[k]);
                    set.add(l);
                    j++;k--;
                    while(j<k && arr[j] == arr[j-1]) j++;
                    while(j<k && arr[k] == arr[k+1]) k--;
                }
            }
        }
        return set;
    }


}
