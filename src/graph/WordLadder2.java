package graph;

import java.util.*;

public class WordLadder2 {
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ansList = new ArrayList<>();
        Set<String> set = new HashSet<>(wordList);
        Queue<List<String>> queue = new ArrayDeque<>();
        queue.add(Collections.singletonList(beginWord));
        set.remove(beginWord);
        int level = 0;
        List<String> used = new ArrayList<>();
        used.add(beginWord);

        while(!queue.isEmpty()){
            List<String> list = queue.poll();
            if(list.size() > level){
                level++;
                for(String s: used){
                    set.remove(s);
                }
                used = new ArrayList<>();
            }
            if(list.getLast().equals(endWord)){
                if(ansList.isEmpty() || ansList.getFirst().size() == list.size())ansList.add(list);
            }else{
                String word = list.getLast();
                for(int i=0; i<beginWord.length(); i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        List<String> l = new ArrayList<>(list);
                        char[] arr = word.toCharArray();
                        arr[i] = c;
                        String temp = new String(arr);
                        if(set.contains(temp)){
                            l.add(temp);
                            queue.add(l);
                            used.add(temp);
                        }
                    }
                }
            }
        }

        return ansList;
    }



    public static List<List<String>> findLaddersOptimised(String beginWord, String endWord, List<String> wordList) {
        Map<String, Integer> map = new HashMap<>();
        Queue<String> queue = new ArrayDeque<>();
        Set<String> set = new HashSet<>(wordList);

        map.put(beginWord, 0);
        set.remove(beginWord);
        queue.add(beginWord);

        while(!queue.isEmpty()){
            String s = queue.poll();
            if(s.equals(endWord)) break;
            for(int i=0; i<s.length(); i++){
                for (char ch = 'a'; ch<= 'z'; ch++){
                    char[] arr = s.toCharArray();
                    arr[i] = ch;
                    String replaced = new String(arr);
                    if(set.contains(replaced)){
                        queue.add(replaced);
                        set.remove(replaced);
                        map.put(replaced, map.get(s)+1);
                    }
                }
            }
        }

        if(!map.containsKey(endWord)) return new ArrayList<>();

        List<List<String>> ansList = new ArrayList<>();

        backTrack(ansList, map, wordList, new ArrayList<>(Collections.singleton(endWord)), endWord);

        return ansList;


    }

    public static void backTrack(List<List<String>> ansList, Map<String, Integer> map, List<String> wordList, List<String> temp, String word){
        if(map.get(word) == 0){
            List<String> ans = new ArrayList<>(temp);
            Collections.reverse(ans);
            ansList.add(ans);
            return;
        }

        for(int i=0; i<word.length(); i++){
            for (char ch = 'a'; ch<= 'z'; ch++){
                char[] arr = word.toCharArray();
                arr[i] = ch;
                String replaced = new String(arr);
                if(map.containsKey(replaced) && map.get(replaced) == map.get(word)-1){
                    temp.add(replaced);
                    backTrack(ansList, map, wordList, temp, replaced);
                    temp.remove(replaced);
                }
            }
        }
    }

}
