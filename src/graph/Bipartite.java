package graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class Bipartite {
    /*
    * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v.
        The graph has the following properties:
        There are no self-edges (graph[u] does not contain u).
        There are no parallel edges (graph[u] does not contain duplicate values).
        If v is in graph[u], then u is in graph[v] (the graph is undirected).
        The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
        A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.
        Return true if and only if it is bipartite.


        Example 1:
        Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
        Output: false
        Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.

        Example 2:
        Input: graph = [[1,3],[0,2],[1,3],[0,2]]
        Output: true
        Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.
    * */

    //TC: O(N)+O(N)+O(2E)
    //SC: O(N)+O(N)
    public static boolean isBipartite(int[][] graph) {
        int[] visited = new int[graph.length];
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=0; i<graph.length; i++){
            if(visited[i] == 0){
                queue.add(i);
                visited[i] = -1;
                if(!bfs(queue, visited, graph)) return false;
            }
        }
        return true;
    }

    public static boolean bfs(Queue<Integer> queue, int[] visited, int[][] graph){
        while(!queue.isEmpty()){
            int ele = queue.poll();
            int[] edgeList = graph[ele];
            for(int i=0; i<edgeList.length; i++){
                if(visited[edgeList[i]] != 0 && visited[edgeList[i]] == visited[ele]){
                    return false;
                }
                if(visited[edgeList[i]] == 0) {
                    int col = visited[ele] == 1 ? -1 : 1;
                    queue.add(edgeList[i]);
                    visited[edgeList[i]] = col;
                }

            }
        }
        return true;
    }



    //SC: O(N)+O(N)
    //TC: O(N) + O(2E)
    public static boolean isBipartiteDFS(int[][] graph){
        int[] visited = new int[graph.length];
        for(int i=0; i<graph.length; i++){
            if(visited[i] == 0){
                visited[i] = -1;
                if(!dfs(graph, visited, i)) return false;
            }
        }
        return true;
    }

    public static boolean dfs(int[][] graph, int[] visited, int current){
        int[] arr = graph[current];
        for(int i=0; i<arr.length; i++){
            int col = visited[current] == -1 ? 1 : -1;
            if(visited[arr[i]] == 0){
                visited[arr[i]] = col;
                if(!dfs(graph,visited,arr[i])) return false;
            }else if(visited[current] == visited[arr[i]]){
                return false;
            }
        }
        return true;
    }

}
