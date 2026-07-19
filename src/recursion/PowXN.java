package recursion;

public class PowXN {
    //LeetCode: 50
    /*
    *Implement pow(x, n), which calculates x raised to the power n (i.e., xn).

    Example 1:

    Input: x = 2.00000, n = 10
    Output: 1024.00000
    Example 2:

    Input: x = 2.10000, n = 3
    Output: 9.26100
    Example 3:

    Input: x = 2.00000, n = -2
    Output: 0.25000
    Explanation: 2-2 = 1/22 = 1/4 = 0.25


    Constraints:

    -100.0 < x < 100.0
    -231 <= n <= 231-1
    n is an integer.
    Either x is not zero or n > 0.
    -104 <= xn <= 104
    *  */

    //BruteForce Solution:
    //TC: O(N)
    //SC: O(1)
    public double myPow(double x, int n) {
        boolean isNegative = n < 0;
        double val = 1;
        long tempN = isNegative ? (long) -1 * n : n;
        for(int i=0; i<tempN; i++){
            val *= x;
        }

        if(isNegative){
            return 1/val;
        }
        return val;
    }


    /*
    * Optimal Solution:
    * Consider the following example:
    * 2^10
    * step 1: (2*2)^5
    * step 2: 4((4*4)^2)
    * step 3: 4((16*16)^1)
    * step 4: 4(256)
    *
    * SC: O(1)
    * TC: O(log N)
    * */
    public static double myPowOptimal(double x, int n) {
        boolean isNegative = n < 0;
        double val = 1;
        long tempN = isNegative ? (long) -1 * n : n;
        while(tempN > 0){
            if(tempN % 2 != 0){
                val *= x;
            }
            x *= x;
            tempN /=2;
        }

        if(isNegative){
            return 1/val;
        }
        return val;
    }


    //Recursive solution
    public static double myPowOptimalRec(double x, int n) {
        boolean isNegative = n < 0;
        double val = 1;
        long tempN = isNegative ? (long) -1 * n : n;
        val = rec(x, tempN, val);

        if(isNegative){
            return 1/val;
        }
        return val;
    }

    public static double rec(double x, long n, double val){
        if(n <= 0) return val;
        if(n % 2 != 0){
            val *= x;
        }
        x *= x;
        n /=2;
        return rec(x, n, val);
    }

}
