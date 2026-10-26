package CompanyBased;

import java.util.Arrays;

public class Visa {
    /*
    *   Question : There are n points on a number line, numbered from 1 to n.
        There are q lamps, and each lamp illuminates all points in the inclusive range [l, r].
        For each lamp, you are given its starting point l and ending point r.
        Determine the number of lamps illuminating each point from 1 to n.
        Example:
        lamps = [[1, 7], [5, 11], [7, 9]]
        points = [7, 1, 5, 10, 9, 15]
        Output: [3, 1, 2, 1, 2, 0]
      */


    //TC: O(P*L) For every point P we check Lamp L
    //SC: O(P) For storing answer Arr of length P
    public static int[] findLamps(int[][] lamps, int[] points){
        int[] ansArr = new int[points.length];
        for(int i=0; i<points.length; i++){
            int count = 0;
            for(int j=0; j<lamps.length; j++){
                if(points[i] >= lamps[j][0] && points[i] <= lamps[j][1]){
                    count++;
                }
            }
            ansArr[i] = count;
        }
        return ansArr;
    }

    public static int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int n = flowers.length;
        int[] lowerBound = new int[n];
        int[] upperBound = new int[n];
        int[] ansArr = new int[people.length];

        for(int i= 0; i<n; i++){
            lowerBound[i] = flowers[i][0];
            upperBound[i] = flowers[i][1];
        }

        Arrays.sort(lowerBound);
        Arrays.sort(upperBound);

        for(int i=0; i< ansArr.length; i++){
            ansArr[i] = checkLowerBound(lowerBound, people[i]) - checkUpperBound(upperBound, people[i]);
        }

        return ansArr;


    }

    public static int checkLowerBound(int[] lowerBound, int n){
        int low = 0;
        int high = lowerBound.length-1;
        while(low <= high){
            int mid = low + ((high-low)/2);
            if(lowerBound[mid] > n){
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return low;
    }

    public static int checkUpperBound(int[] upperBound, int n){
        int low = 0;
        int high = upperBound.length-1;
        while(low <= high){
            int mid = low + ((high-low)/2);
            if(upperBound[mid] < n){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return low;
    }



}
