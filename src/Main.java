import arrays.*;
import sorting.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,6,6,5};
        FindMissingAndRepeatingNumber.findMissingAndRepeatingNumberBrute(arr);
        FindMissingAndRepeatingNumber.findMissingAndRepeatingNumberBetter(arr);
        FindMissingAndRepeatingNumber.findMissingAndRepeatingNumberOptimal1(arr);
    }
}
