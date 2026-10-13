package binarySearch;

public class Amazon {
    /*
    * Question: given an integer array "parcels" where parcel[i] tells the parcel that ith delivery agent already has.
    * Given an integer variable "extraParcels" telling the number of parcels that needed to be delivered too.
    * so we must distribute extraParcels among those agents, such that the maximum parcels that each agent need to be delivered is minimum.
    * All parcels must be delivered.

    eg: parcel: [1,2,3] , extraParcel: 3
    solution: 3
    explanation: we will distribute extra parcels as: [3,3,3] so that all parcels are delivered along with max. parcels each agent is getting is minimum.

    * parcel: [7,5,1,9,1], extraParcel: 25
    * solution: 10
    * */

    public static int amazonTest(int[] parcels, int extra){
        int n = parcels.length;
        int low = Integer.MIN_VALUE;
        int high =  Integer.MIN_VALUE;
        for(int i=0; i<n; i++){
            low = Math.max(low, parcels[i]);
            high = Math.max(high, parcels[i]+extra);
        }

        while(low<=high){
            int mid = (low+high)/2;
            if(isAssignmentPossible(parcels, extra, mid)){
                high = mid-1;
            }else{
                low = mid+1;
            }
        }

        return low;
    }

    public static boolean isAssignmentPossible(int[] parcels, int extra, int num){
        for(int i=0; i<parcels.length; i++){
            int req = num-parcels[i];
            if(req<=extra){
                extra -= req;
            }
            else  return true;
        }

        if(extra > 0) return false;
        return true;
    }
}
