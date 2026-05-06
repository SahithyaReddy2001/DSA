package binarySearch;

public class KokoEatingBananas {
    public static int minEatingSpeed(int[] piles, int h) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i: piles){
            if( i < min) min = i;
            if(i>max) max = i;
        }
        while(min <= max){
            int mid = (min+max)/2;
            int hours = getHours(piles, mid);
            if(hours <= h){
                max = mid-1;
            }else{
                min = mid+1;
            }
        }
        return min;

    }

    //TODO: Check for Overflow
    public static int getHours(int[] piles, int b){
        int hours = 0;
        for(int i: piles){
            if(i<=b) hours++;
            else if(i%b != 0) hours += (i/b)+1;
            else if(i%b == 0) hours += i/b;
        }
        return hours;
    }
}
