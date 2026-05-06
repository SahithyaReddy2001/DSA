package binarySearch;

public class MinimiseMaxDistanceBetweenGasStations {
    //TC: O((K+1)*N)
    //SC: O(arr.length-1)
    public static double minimiseMaxDistance(int []arr, int k){
        int[] temp = new int[arr.length-1];
        while(k > 0){
            int index = findMaxDistanceIndex(arr, temp);
            temp[index] = temp[index]+1;
            k--;
        }
        int index = findMaxDistanceIndex(arr, temp);
        return (double) (arr[index + 1] - arr[index]) /(temp[index]+1);
    }

    public static int findMaxDistanceIndex(int[] arr, int[] temp){
        int index = -1;
        double max = 0;
        for(int i=0; i<arr.length-1; i++){
            if((double)(arr[i+1] - arr[i])/(temp[i]+1) > max){
                max = (double)(arr[i+1] - arr[i])/(temp[i]+1);
                index = i;
            }
        }
        return index;
    }
}
