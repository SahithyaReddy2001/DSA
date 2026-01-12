package arrays;

import java.util.ArrayList;
import java.util.List;

public class RearrangeTheArray {
    //Q1: Rearrange the array in alternating positive and negative items n.of positives = n.of negatives

    /*
    *Optimal
    * We will me maintaining 2 arrays to store positives and negatives
    * we will iterate over those 2 arrays and store them in correct order in original Array
    * TC : O(N) + O(N/2)
    * SC : O(N)
    * */
    public static int[] rearrangeTheArrayOptimal(int[] arr){
        int[] arr1 = new int[arr.length/2];
        int[] arr2 = new int[arr.length/2];
        int x=0,y=0;
        for(int i=0; i<arr.length; i++){
            if(arr[i] > 0){
                arr1[x] = arr[i];
                x++;
            }else{
                arr2[y] = arr[i];
                y++;
            }
        }

        for(int i=0; i<arr.length/2; i++){
            arr[i*2] = arr1[i];
            arr[i*2 + 1] = arr2[i];
        }
        return arr;
    }


    /*
    *Optimal
    * We will me maintaining 2 arrays to store positives and negatives
    * we will iterate over those 2 arrays and store them in correct order in original Array
    * TC : O(N)
    * SC : O(N)
    * */
    public static int[] rearrangeTheArrayBetter(int[] arr){
        int x=0,y=1;
        int[] finAns = new int[arr.length];
        for(int i=0; i<arr.length; i++){
            if(arr[i] > 0){
                finAns[x] = arr[i];
                x += 2;
            }else{
                finAns[y] = arr[i];
                y += 2;
            }
        }
        return finAns;
    }



    //Q2: Rearrange the array in alternating positive and negative items n.of positives != n.of negatives
    /*
     *For this Q1 Optimal solution is best solution
     * We will me maintaining 2 arrays to store positives and negatives
     * we will iterate over those 2 arrays and store them in correct order in original Array
     * TC : O(N) + O(N)
     * SC : O(N)
     * */
    public static int[] rearrangeTheArrayOptimalQ2(int[] arr){
        List<Integer> positives = new ArrayList<>();
        List<Integer> negatives = new ArrayList<>();
        for(int i=0; i<arr.length; i++){
            if(arr[i] > 0){
                positives.add(arr[i]);
            }else{
                negatives.add(arr[i]);
            }
        }

        if(positives.size() < negatives.size()){
            for (int i=0; i< positives.size(); i++){
                arr[i*2] = positives.get(i);
                arr[i*2 + 1] = negatives.get(i);
            }
            int index = (positives.size() * 2);
            for(int i= positives.size(); i<negatives.size();i++){
                arr[index] = negatives.get(i);
                index++;
            }
        }else{
            for (int i=0; i< negatives.size(); i++){
                arr[i*2] = positives.get(i);
                arr[i*2 + 1] = negatives.get(i);
            }
            int index = (negatives.size() * 2);
            for(int i= negatives.size(); i<positives.size();i++){
                arr[index] = positives.get(i);
                index++;
            }
        }
        return arr;
    }
}
