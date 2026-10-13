package graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class DetectCycle {
    //Detect Cycle In graph using BFS
    public static boolean detectCycleUsingBFS(List<List<Integer>> list){
        Queue<List<Integer>> queue = new ArrayDeque<>();
        int[] visited = new int[list.size()+1];
        for(int i=1; i<visited.length; i++){
            if(visited[i] == 0 && isCycleByBFS(queue,i, visited, list)){
                return true;
            }
        }
        return false;
    }

    public static boolean isCycleByBFS(Queue<List<Integer>> queue, int start, int[] visited, List<List<Integer>> list){
        queue.add(Arrays.asList(start, -1));
        visited[start] = 1;
        int count = 0;
        while(!queue.isEmpty()){
            count++;
            System.out.println(count);
            List<Integer> l = queue.poll();
            for(int i: list.get(l.getFirst()-1)){
                if(i == l.getLast()) continue;
                if(visited[i] == 1) return true;
                visited[i] = 1;
                queue.add(Arrays.asList(i, l.getFirst()));
            }
        }
        return false;
    }


    public static boolean detectCycleUsingDFS(List<List<Integer>> list){
        int[] visited = new int[list.size()+1];
        for(int i=1; i<visited.length; i++){
            if(visited[i] == 0 && isCycleByDFS(i, visited, list, -1)){
                return true;
            }
        }
        return false;
    }

    public static boolean isCycleByDFS(int start, int[] visited, List<List<Integer>> list, int parent){
        visited[start] = 1;
        for(int i: list.get(start-1)){
            if(visited[i] == 1 && parent != i){
                return true;
            }
            if(visited[i] == 0) {
                isCycleByDFS(i, visited, list, start);
            }
        }
        return false;
    }
}
