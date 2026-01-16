import arrays.*;
import sorting.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{-2,-2,-2,-1,-1,1,4,-1,0,0,0,2,2,2,2,-5};
        System.out.println(ThreeSum.threeSumBetter(arr));
        System.out.println(ThreeSum.threeSumOptimal(arr));
    }
}
