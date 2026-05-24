package binarySearch;

public class ShipWithInDays {
    public static int shipWithinDays(int[] arr, int days) {
        int low = 1;
        int high = 0;
        for(int i: arr){
            high+=i;
        }
        while(low<=high){
            int mid = low+(high-low)/2;
            int temp = isPossible(arr, mid);
            if(temp<= days) high = mid-1;
            else low = mid+1;
        }
        return low;
    }

    public static int isPossible(int[] arr, int weight){
        int count = 0;
        int temp = 0;
        for(int i: arr){
            temp += i;
            if(temp> weight){
                count++;
                temp =i;
            }if(temp == weight){
                count++;
                temp = 0;
            }
        }
        if(temp > 0) return count+1;
        return count;
    }
}
