package graph;

import java.util.*;

public class WordLadder1 {
    static class Pair {
        String word;
        int len;

        Pair(String word, int len) {
            this.word = word;
            this.len = len;
        }


    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);

        Queue<Pair> queue = new ArrayDeque<>();

        queue.add(new Pair(beginWord, 1));

        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            if (p.word.equals(endWord)) {
                return p.len;
            }

            for (int i = 0; i < beginWord.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char[] replacedCharArray = p.word.toCharArray();
                    replacedCharArray[i] = c;
                    String replaced = new String(replacedCharArray);
                    if (set.contains(replaced)) {
                        queue.add(new Pair(replaced, p.len + 1));
                        set.remove(replaced);
                    }
                }
            }


        }

        return 0;
    }
}
