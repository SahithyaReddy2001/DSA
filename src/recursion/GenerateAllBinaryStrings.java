package recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateAllBinaryStrings {
    /*
    * Given an integer n, return all binary strings of length n that do not contain consecutive 1s.
    *  Return the result in lexicographically increasing order.
    * A binary string is a string consisting only of characters '0' and '1'.
    * */

    public static List<String> generateRec(int n) {
        return generateRec(n, "", new ArrayList<>());
    }

    public static List<String> generateRec(int n, String s, List<String> ansList) {
        if (s.length() >1  && s.charAt(s.length() - 1) == '1' && s.charAt(s.length()-2) == '1') {
            return ansList;
        }
        if (s.length() >= n) {
            ansList.add(s);
            return ansList;
        }
        generateRec(n, s+"0", ansList);
        generateRec(n, s+"1", ansList);
        return ansList;
    }
}
