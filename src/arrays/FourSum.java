package arrays;

import java.util.*;

public class FourSum {
    //Q: Find four unique elements from array whose sum = 0

    /*
     * BruteForce:
     * Iterate over each element and find its sum if sum =0  sort it and add it into a set
     * And return the set
     * TC: O(N^4)
     * SC: O(N.of unique triplets)
     */
    public static Set<List<Integer>> fourSumBrute(int[] arr){
        Set<List<Integer>> set = new HashSet<>();
        for(int i=0; i<arr.length; i++){
            for(int j=i+1; j<arr.length; j++){
                for(int k=j+1; k<arr.length; k++){
                    for(int l= k+1; l<arr.length; l++){
                        if(arr[i] + arr[j] + arr[k] + arr[l] == 0){
                            List<Integer> list =new ArrayList<>();
                            list.add(arr[i]);
                            list.add(arr[j]);
                            list.add(arr[k]);
                            list.add(arr[l]);
                            Collections.sort(list);
                            set.add(list);
                        }
                    }
                }
            }
        }
        return set;
    }




    /*
     * Better:
     * Take 3 variables i, j, k
     * start iterating i from every element and j from i+1 and k from j+1 to n
     * whatever the middle elements in i and j store them in hashset
     * check for is there element in set = -(arr[i] + arr[j] + arr[k])
     * if yes that is the quadraple
     * sort it and add it in set
     * TC: O(N^3)
     * SC: O(N.of Unique triplets) + O(N)
     */
    public static Set<List<Integer>> fourSumBetter(int[] arr){
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> l = new ArrayList<>();
        for(int i=0; i<arr.length; i++){
            for(int j=i+1; j<arr.length; j++){
                Set<Integer> s = new HashSet<>();
                for(int k = j+1; k<arr.length; k++){
                    if(s.contains(-1*(arr[i]+arr[j]+arr[k]))){
                        l= new ArrayList<>();
                        l.add(arr[i]);
                        l.add(arr[j]);
                        l.add(arr[k]);
                        l.add(-1*(arr[i]+arr[j]+arr[k]));
                        Collections.sort(l);
                        set.add(l);
                    }
                    s.add(arr[k]);
                }
            }
        }
        return set;
    }




    /*
     * Optimal:
     * We will sort the array
     * then we will use 4 variable i,j, k and l
     * i will iterate for every element j =i+1, k = j+1 and l = arr.length-1
     * if(i+j+k+l) < 0 k++
     * if(i+j+k+l) > 0 l--;
     * else it's a triplet we will move l and k until its not current element
     * Base case i<j
     * TC: O(N log N) + O(N^3)
     * SC: O(1) We are using extra space to return the answer not to solve the problem
     */
    public static Set<List<Integer>> fourSumOptimal(int[] arr){
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(arr);
        for(int i=0; i<arr.length; i++){
            if(i>0 && arr[i] == arr[i-1]) continue;
            for(int j=i+1; j<arr.length; j++){
                if(j>i+1 && arr[j] == arr[j-1]) continue;
                int k =j+1;
                int l =arr.length-1;
                while(k<l){
                    int sum = arr[i] + arr[j] + arr[k] + arr[l];
                    if(sum<0) k++;
                    else if(sum>0) l--;
                    else{
                        list = new ArrayList<>();
                        list.add(arr[i]);
                        list.add(arr[j]);
                        list.add(arr[k]);
                        list.add(arr[l]);
                        set.add(list);
                        k++;l--;
                        while(k<l && arr[k] == arr[k-1]) k++;
                        while(k<l && arr[l] == arr[l+1]) l--;
                    }
                }
            }
        }
        return set;
    }
}
