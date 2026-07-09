package slidingWindowTwoPointer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubStringWithoutRepeatingCharacters {
    //LeetCode: 3
   /*Given a string s, find the length of the longest substring without duplicate characters.

    Example 1:
    Input: s = "abcabcbb"
    Output: 3
    Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.

    Example 2:
    Input: s = "bbbbb"
    Output: 1
    Explanation: The answer is "b", with the length of 1.
    Example 3:

    Input: s = "pwwkew"
    Output: 3
    Explanation: The answer is "wke", with the length of 3.
    Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

    Constraints:
    0 <= s.length <= 5 * 104
    s consists of English letters, digits, symbols and spaces.*/



    /*
    * Brute force:
    * Iterate over all the substrings and check for unique characters
    * SC: O(N)
    * TC: O(N^2)
    * */
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set;
        int count = 0;
        int maxCount = 0;
        for(int i=0; i<s.length(); i++){
            int j = i;
            set = new HashSet<>();
            count = 0;
            while(j < s.length() && !set.contains(s.charAt(j))){
                set.add(s.charAt(j));
                count++;
                j++;
            }
            maxCount = Math.max(count, maxCount);
        }
        return maxCount;
    }




    /*
    * Optimal Solution:
    * Iterate over each element
    * if the element contains in map and left pointer is less than or equal to element index then move left pointer 1 index ahead of previous positon of element
    * for every step insert element into map
    * count the distance between left and right pointer
    * update max Index
    * return max Index
    * SC: O(N)
    * TC: O(N)
    * */
    public static int lengthOfLongestSubstringOptimal(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int count = 0;
        int maxCount = 0;
        int l = 0;
        int r = 0;
        while(l<s.length() && r<s.length()){
            char key = s.charAt(r);
            if(map.containsKey(key) && l <= map.get(key)){
                l = map.get(key)+1;
            }
            count = r-l+1;
            map.put(key, r);
            maxCount = Math.max(count, maxCount);
            r++;
        }
        return maxCount;
    }
}
