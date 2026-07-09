package slidingWindowTwoPointer;

public class LongestRepeatingCharacterReplacement {
    public static int characterReplacement(String s, int k) {
        int maxCount = 0;
        for(int i =0; i<s.length(); i++){
            int[] hash = new int[26];
            int maxHash = 0;
            for(int j =i; j<s.length(); j++){
                int index = s.charAt(j) - 'A';
                hash[index] += 1;
                maxHash = Math.max(maxHash, hash[index]);
                int changes = (j-i+1) - maxHash;
                if(changes <= k){
                    maxCount = Math.max(j-i+1, maxCount);
                }else{
                    break;
                }
            }
        }
        return maxCount;
    }

    public static int characterReplacementOptimal(String s, int k) {
        int maxCount = 0;
        int l =0;
        int r = 0;
        int maxHash = 0;
        int[] hash = new int[26];
        while(r<s.length()){
            int index = s.charAt(r) - 'A';
            hash[index] += 1;
            maxHash = Math.max(maxHash, hash[index]);
            int length = r-l+1;
            if(length - maxHash <= k){
                maxCount = Math.max(maxCount, r-l+1);
            }else{
                hash[s.charAt(l) - 'A']--;
                maxHash = 0;
                l++;
            }
            r++;
        }
        return maxCount;
    }
}
