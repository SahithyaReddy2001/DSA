package recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

    /*
    * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

    Example 1:

    Input: n = 3
    Output: ["((()))","(()())","(())()","()(())","()()()"]
    Example 2:

    Input: n = 1
    Output: ["()"]


    Constraints:

    1 <= n <= 8
    * */

    public static List<String> generateParenthesis(int n) {
        return rec(n, new ArrayList<String>(), "", 0, 0);
    }

    public static List<String> rec(int n, List<String> ans, String s, int openCount, int closeCount){
        if(openCount < closeCount) return ans;
        if(s.length() == 2*n){
            if(openCount > n) return ans;
            if(openCount == n && closeCount == n){
                ans.add(s);
                return ans;
            }
        }
        rec(n, ans, s+"(", openCount+1, closeCount);
        rec(n, ans, s+")", openCount, closeCount+1);
        return ans;
    }
}
