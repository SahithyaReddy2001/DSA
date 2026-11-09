package graph;

import java.util.*;

public class AlienDict {

    public static String findOrderTest(String[] words) {

        //Step 1: Initialize a List to store graph
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0; i<26; i++){
            list.add(new ArrayList<>());
        }

        int[] present = new int[26];
        int[] inDegree = new int[26];
        int count = 0;

        for(String str: words){
            for(Character c: str.toCharArray()){
                if(present[c - 'a'] == 0){
                    present[c - 'a'] = 1;
                    count++;
                }
            }
        }

        for(int i=0; i<words.length-1; i++){
            String s1 = words[i];
            String s2 = words[i+1];
            int minLength = Math.min(s1.length(), s2.length());

            int j =0;
            while(j< minLength && s1.charAt(j) == s2.charAt(j)){
                j++;
            }

            if(j<minLength){
                list.get(s1.charAt(j) - 'a').add(s2.charAt(j) - 'a');
                inDegree[s2.charAt(j) - 'a']++;
            }
            //Case when abcd appears before abc
            else if (s1.length() > s2.length()) {
                return "";
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();

        for(int i=0; i<inDegree.length; i++){
            if(inDegree[i] == 0 && present[i] == 1){
                queue.add(i);
            }
        }

        StringBuilder ans = new StringBuilder();

        while(!queue.isEmpty()){
            int ele = queue.poll();
            ans.append((char) (ele + 'a'));
            for(int i: list.get(ele)) {
                inDegree[i]--;
                if (inDegree[i] == 0) {
                    queue.add(i);
                }
            }
        }

        if (ans.length() != count) return "";

        return ans.toString();
    }


    public static String findOrder(String[] words) {

        // Step 1: Initialize graph
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add(new ArrayList<>());
        }

        int[] present = new int[26];

        // Step 2: Identify all unique characters
        for (String str : words) {
            for (char c : str.toCharArray()) {
                if (present[c - 'a'] == 0) {
                    present[c - 'a'] = 1;
                }
            }
        }

        // Step 3: Build the directed graph
        for (int i = 0; i < words.length - 1; i++) {

            String s1 = words[i];
            String s2 = words[i + 1];

            int minLength = Math.min(s1.length(), s2.length());

            int j = 0;

            while (j < minLength && s1.charAt(j) == s2.charAt(j)) {
                j++;
            }

            if (j < minLength) {
                list.get(s1.charAt(j) - 'a')
                        .add(s2.charAt(j) - 'a');
            }
            else if (s1.length() > s2.length()) {
                return "";
            }
        }

        // Step 4: DFS Topological Sort
        int[] visited = new int[26];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < 26; i++) {
            if (present[i] == 1 && visited[i] == 0) {
                if (!dfs(i, list, visited, stack)) {
                    return "";
                }
            }
        }

        // Step 5: Build the answer
        StringBuilder ans = new StringBuilder();

        while (!stack.isEmpty()) {
            ans.append((char) (stack.pop() + 'a'));
        }

        return ans.toString();
    }

    private static boolean dfs(int node, List<List<Integer>> list,
                               int[] visited, Stack<Integer> stack) {

        // 1 = currently visiting, 2 = completely visited
        visited[node] = 1;

        for (int neighbor : list.get(node)) {

            if (visited[neighbor] == 1) {
                return false; // Cycle detected
            }

            if (visited[neighbor] == 0) {
                if (!dfs(neighbor, list, visited, stack)) {
                    return false;
                }
            }
        }

        visited[node] = 2;
        stack.push(node);

        return true;
    }
}
