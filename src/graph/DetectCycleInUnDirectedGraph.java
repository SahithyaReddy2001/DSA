package graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class DetectCycleInUnDirectedGraph {
    //Detect Cycle In Undirected graph (Nodes will be from 1 to N)

    /*
    * BFS + Parent
    *  Tracking
    1. Create a visited array of size V + 1.
    2. Iterate through all vertices from 1 to V to handle disconnected components.
    3. For each unvisited vertex:
       - Mark it visited.
       - Add {vertex, parent} to the queue, using -1 as the initial parent.
    4. While the queue is not empty:
       - Remove the current vertex and its parent.
       - Traverse all its neighbors.
       - If a neighbor is already visited and is not the parent of the current vertex, a cycle exists.
       - Otherwise, if the neighbor is unvisited, mark it visited and enqueue it with the current vertex as its parent.
    5. If BFS finishes for every component without finding a cycle, return false.
    Why do we check the parent?

    In an undirected graph, every edge appears in both directions in the adjacency list.
    * When traversing an edge back to the parent, that parent is already visited, but this does not indicate a cycle.
    * A visited neighbor other than the parent does indicate a cycle.
    * TC: O(V) + O(V + E) = O(V + E)
    * SC: O(V) + O(V) = O(V)
    * */
    public static boolean detectCycleBfs(List<List<Integer>> list){
        int[] visited = new int[list.size()+1];
        Queue<int[]> queue = new ArrayDeque<>();

        for(int i=1; i<visited.length; i++){
            if(visited[i] == 0){
                queue.add(new int[]{i,-1});
                visited[i] = 1;
                if(bfs(queue, visited, list)) return true;
            }
        }
        return false;
    }

    public static boolean bfs(Queue<int[]> queue, int[] visited, List<List<Integer>> list){
        while(!queue.isEmpty()){
            int[] arr = queue.poll();
            for(int i: list.get(arr[0]-1)){
                if(i != arr[1] && visited[i] == 1) return true;
                if(visited[i] == 0) {
                    visited[i] = 1;
                    queue.add(new int[]{i, arr[0]});
                }
            }
        }
        return false;
    }


    /*
    * Approach: DFS + Parent Tracking
        1. Create a visited array of size V + 1.
        2. Iterate through all vertices from 1 to V to handle disconnected components.
        3. For each unvisited vertex, call DFS with parent -1.
        4. In DFS:
           - Mark the current vertex as visited.
           - Traverse all its adjacent vertices.
           - If a neighbor is visited and is not the parent of the current vertex, a cycle exists; return true.
           - If a neighbor is unvisited, recursively call DFS with the current vertex as its parent.
        5. If any DFS call detects a cycle, return true. If all components are explored without finding one, return false.
        Why check the parent?

        In an undirected graph, each edge appears in both directions.
        * When DFS encounters the parent of the current vertex, that parent is already visited, but this is not a cycle.
        * A visited neighbor other than the parent indicates a cycle.
        4. Final complexity comments
        TC: O(V) + O(V + E) = O(V + E)

        SC: O(V) + O(V) = O(V)
    * */
    public static boolean detectCycleDfs(List<List<Integer>> list){
        int[] visited = new int[list.size()+1];

        for (int i=1; i<visited.length; i++){
            if(visited[i] == 0 && dfs(list, visited, -1, i)) return true;
        }
        return false;
    }

    public static boolean dfs(List<List<Integer>> list, int[] visited, int parent, int current){
        visited[current] = 1;
        for(int i: list.get(current-1)){
            if(i != parent && visited[i] == 1) return true;
            if(visited[i] == 0 && dfs(list, visited, current, i)) return true;
        }
        return false;
    }

}
