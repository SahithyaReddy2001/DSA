import recursion.PowXN;
import recursion.ReverseAStack;
import recursion.SortStackUsingRecursion;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(4);
        st.push(1);
        st.push(3);
        st.push(2);

        // Reverse the stack
        SortStackUsingRecursion.sortStack(st);
    }

    public static int countGoodNumbers(long n) {
        long MOD = 1_000_000_007;
        boolean isOdd = n%2 != 0;
        long odd = n/2;
        long even = isOdd ? odd+1 : odd;
        odd = myPowOptimal(4, odd, MOD);
        even = myPowOptimal(5, even, MOD);
        return (int) ((odd * even) % MOD);

    }

    public static long myPowOptimal(long x, long n, long mod) {
        long val = 1;
        x = x%mod;
        long tempN = n;
        while(tempN > 0){
            if(tempN % 2 != 0){
                val = (val*x)%mod;
            }
            x = (x*x)%mod;
            tempN /=2;
        }
        return val;
    }
    public static int mergeSort(int[] nums, int start, int end, int count){
        if(start == end) return count;
        int mid = (start+end)/2;
        mergeSort(nums, start, mid, count);
        mergeSort(nums, mid+1, end, count);
        count += mergeTwoSortedArrays(nums, start, mid, end, count);
        return count;
    }

    public static int mergeTwoSortedArrays(int[] nums, int start, int mid, int end, int count){
        int i = start;
        int j = mid+1;
        int temp[] = new int[end-start+1];
        int k =0;
        while(i<=mid && j<=end){
            if(nums[i] <= nums[j]){
                temp[k] = nums[i];
                i++;
            }else{
                temp[k] = nums[j];
                if(nums[i] > 2*nums[j]){
                    count++;
                }
                j++;
            }
            k++;
        }
        if(i<=mid){
            while(i<=mid){
                temp[k] = nums[i];
                i++;
                k++;
            }
        }
        if(j<=end){
            while(j<=end){
                temp[k] = nums[j];
                j++;
                k++;
            }
        }

        for(int z=0; z< temp.length; z++){
            nums[start+z] = temp[z];
        }
        return count;
    }

    public static int lengthOfLongestConsecutiveSequence(int[] arr, int N) {
        Arrays.sort(arr);
        int ans = 1;
        int count = 1;
        for(int i=1; i<arr.length; i++){
            if(arr[i] == arr[i-1]) continue;
            if(arr[i] != arr[i-1]+1){
                ans = Math.max(ans, count);
                count = 0;
            }
            count++;
        }

        return ans;
    }
}
