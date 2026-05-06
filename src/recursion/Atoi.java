package recursion;

import java.util.Arrays;

public class Atoi {
    public static int myAtoi(String s) {
        if(s==null || s.isEmpty()){
            return 0;
        }
        int sign = 1;
        int start = 0;
        s = s.trim();
        if (s.charAt(0)== '-'){
            sign = -1;
            start = 1;
            if(s.length() == 1) return 0;
        }else if(s.charAt(0) == '+'){
            start = 1;
            if(s.length() == 1) return 0;
        }
        while(s.charAt(start) == '0'){
            start++;
        }
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        long val = rec(s.trim(), start, 0);
        if(sign*val <= min){
            return min;
        }else if(sign*val>=max){
            return max;
        }
        return sign * (int)rec(s.trim(), start, 0);
    }

    public static long rec(String s, int start, long sum){
        if(start >= s.length() || !Character.isDigit(s.charAt(start))){
            return sum;
        }
        sum = sum* 10 + (s.charAt(start)-'0');
        sum = rec(s, start+1, sum);
        return sum;
    }
}
