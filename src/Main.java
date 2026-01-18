import arrays.*;
import sorting.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{2,3,-2,4,-1,-2,6};
        System.out.println(MaximumProductSubArray.maximumProductSubArrayBrute(arr));
        System.out.println(MaximumProductSubArray.maximumProductSubArrayOptimal(arr));
    }
}
