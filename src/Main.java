import recursion.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(SumOfAllSubsets.printSumOfSubsets(new int[]{5,2,1}));
    }

    public static int countGoodNumbers(long n) {
        long MOD = 1_000_000_007;
        boolean isOdd = n%2 != 0;
        long odd = n/2;
        long even = isOdd?odd+1 : odd;
        return (int) ((rec(4, odd, 1, MOD) * rec(5, even, 1, MOD)) % MOD);
    }

    public static long rec(long x, long n, long val, long mod){
        if(n <= 0) return val;
        if(n % 2 != 0){
            val = (val*x)%mod;
        }
        x = (x*x)%mod;
        n /=2;
        return rec(x, n, val, mod);
    }
}
