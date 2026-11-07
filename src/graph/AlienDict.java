package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class AlienDict {

    public static String findOrderTest(String[] words) {

        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            list.add(new ArrayList<>());
        }

        int[] inDegree = new int[26];
        boolean[] present = new boolean[26];
        boolean[][] edgeExists = new boolean[26][26];

        // Step 1: Mark characters present in the input
        int uniqueChars = 0;

        for (String word : words) {
            for (char ch : word.toCharArray()) {
                if (!present[ch - 'a']) {
                    present[ch - 'a'] = true;
                    uniqueChars++;
                }
            }
        }

        // Step 2: Build the graph
        for (int i = 0; i < words.length - 1; i++) {

            String s1 = words[i];
            String s2 = words[i + 1];

            int minLength = Math.min(s1.length(), s2.length());
            int j = 0;

            while (j < minLength &&
                    s1.charAt(j) == s2.charAt(j)) {
                j++;
            }

            if (j < minLength) {

                int u = s1.charAt(j) - 'a';
                int v = s2.charAt(j) - 'a';

                if (!edgeExists[u][v]) {
                    list.get(u).add(v);
                    inDegree[v]++;
                    edgeExists[u][v] = true;
                }

            } else if (s1.length() > s2.length()) {
                // Invalid prefix ordering
                return "";
            }
        }

        // Step 3: Add all present characters with in-degree 0
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < 26; i++) {
            if (present[i] && inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Step 4: Perform Kahn's algorithm
        StringBuilder ans = new StringBuilder();

        while (!queue.isEmpty()) {

            int node = queue.poll();

            ans.append((char) ('a' + node));

            for (int neighbor : list.get(node)) {
                inDegree[neighbor]--;

                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Step 5: Detect a cycle
        if (ans.length() != uniqueChars) {
            return "";
        }

        return ans.toString();
    }

}
