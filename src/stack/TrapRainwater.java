package stack;

public class TrapRainwater {


    /*
    * TC: O(3N)
    * SC: O(2N)
    * */
    public static int trap(int[] height) {
        int total = 0;
        int[] pre = prefixMax(height);
        int[] suf = suffixMax(height);
        for(int i=0; i< height.length; i++){
            if(height[i] < pre[i] && height[i] < suf[i]){
                total += Math.min(suf[i],pre[i]) - height[i];
            }

        }
        return total;
    }

    public static int[] prefixMax(int[] height){
        int[] prefixMax = new int[height.length];
        int max = 0;
        for(int i=0; i< height.length; i++){
            max = Math.max(max, height[i]);
            prefixMax[i] = max;
        }
        return prefixMax;
    }

    public static int[] suffixMax(int[] height){
        int[] suffixMax = new int[height.length];
        int max = 0;
        for(int i= height.length-1; i>=0; i--){
            max = Math.max(max, height[i]);
            suffixMax[i] = max;        }
        return suffixMax;
    }



    /*
    * TC: O(2N)
    * SC: O(N)
    * */
    public static int trapBetter(int[] height) {
        int total = 0;
        int[] suf = suffixMax(height);
        int max = 0;
        for(int i=0; i< height.length; i++){
            max = Math.max(max, height[i]);
            if(height[i] < max && height[i] < suf[i]){
                total += Math.min(suf[i],max) - height[i];
            }
        }
        return total;
    }


    /*
     * TC: O(N)
     * SC: O(1)
     * */
    public static int trapOptimal(int[] arr) {
        int total = 0, lmax = 0, rmax = 0;
        int left = 0;
        int right = arr.length-1;
        while(left < right){
            if(arr[left] <= arr[right]){
                if(lmax > arr[left]){
                    total += lmax-arr[left];
                }else{
                    lmax = arr[left];
                }
                left++;
            }else{
                if(rmax > arr[right]){
                    total += rmax-arr[right];
                }else{
                    rmax = arr[right];
                }
                right--;
            }
        }
        return total;
    }

}
