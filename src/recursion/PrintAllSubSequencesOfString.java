package recursion;

import java.util.ArrayList;
import java.util.List;

public class PrintAllSubSequencesOfString {
    /*
    * Problem Description: Given a string, find all the possible subsequences of the string.
    * Input: str = "abc"
    Output: [a, ab, abc, ac, b, bc, c]
    Explanation: Given string has 7 subsequences.
    Input: str = "aa"
    Output: [a, a, aa]
    Explanation: Given string has 3 subsequences.
     * */

    public static int count = 1;

    public static List<String> print(String s){
        return printAllSubSequence(s, new ArrayList<>(), "");
    }

    public static List<String> printAllSubSequence(String s, List<String> finList, String ans){
        System.out.println(count++);
        if(s.isEmpty()){
            finList.add(ans);
            return finList;
        }
        printAllSubSequence(s.substring(1), finList, ans + s.charAt(0));
        printAllSubSequence(s.substring(1), finList, ans);
        return finList;
    }
}
