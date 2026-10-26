package graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class Bipartite {

    //A BIPARTITE GRAPH IS A GRAPH WHICH CAN BE COLORED WITH EXACTLY 2 COLORES SUCH THAT NO 2 ADJACENT NODE HAVE SAME COLOR
    //---------------------------------------------------------------------------------------------------------------------


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

    //TC: O(V)+O(V+E)
    //SC: O(N)+O(N)

    /*BFS + Two-Coloring
        A graph is bipartite if its vertices can be divided into two groups such that no two adjacent vertices belong to the same group.
        1. Create a visited array to store the color of each vertex:
           - 1 represents one color.
           - -1 represents the other color.
           - 0 means unvisited.
        2. Iterate through every vertex to handle disconnected components.
        3. If a vertex is unvisited:
           - Assign it color 1.
           - Add it to the queue.
        4. Perform BFS:
           - Remove a vertex from the queue.
           - Traverse all its neighbors.
           - If a neighbor is already colored with the same color as the current vertex, return false.
           - If a neighbor is unvisited, assign it the opposite color and add it to the queue.
        5. If all components are processed without a color conflict, return true.
        Why use two colors?

        In a bipartite graph, every edge must connect vertices of opposite colors. If two adjacent vertices have the same color, the graph is not bipartite.
        TC: O(V) + O(V + E) = O(V + E)

        SC: O(V) + O(V) = O(V)
        */
    public static boolean isBipartite(int[][] graph) {
        int[] visited = new int[graph.length];
        Queue<Integer> queue = new ArrayDeque<>();

        for(int i=0; i<visited.length; i++){
            if(visited[i] == 0){
                visited[i] = 1;
                queue.add(i);
                if(!bfs(graph, visited, queue)) return false;
            }
        }
        return true;
    }

    public static boolean bfs(int[][] graph, int[] visited, Queue<Integer> queue){
        while(!queue.isEmpty()){
            int ele = queue.poll();
            int[] arr = graph[ele];
            int color = visited[ele] == 1 ? -1 : 1;
            for(int i=0; i< arr.length; i++){
                if(visited[arr[i]] != 0 && visited[arr[i]] == visited[ele]) return false;
                if(visited[arr[i]] == 0){
                    visited[arr[i]] = color;
                    queue.add(arr[i]);
                }
            }
        }
        return true;
    }



    /*
    * DFS Bipartite Check
    1. Initialize: Create a visited array where 0 means unvisited, 1 means one color, and -1 means the other color.
    2. Handle disconnected components: Iterate through every vertex. If unvisited, start DFS with color 1.
    3. Color the current vertex: Assign the given color to visited[curr].
    4. Check neighbors:
       - If a neighbor has the same color as the current vertex, return false.
       - If a neighbor is unvisited, recursively visit it with the opposite color.
    5. Finish: If every component is colored without a conflict, return true.
    *
    *  Time and Space Complexity
        TC: O(V) + O(V + E) = O(V + E)
        - O(V) — outer loop visits every vertex to handle disconnected components.
        - O(V + E) — DFS visits each vertex and processes each adjacency-list edge at most once.
        Your O(V * E) is incorrect because DFS does not traverse all edges separately for every vertex.

        SC: O(V) + O(V) = O(V)
        - O(V) — visited color array.
        - O(V) — recursive DFS call stack in the worst case.
    * */
    public static boolean isBipartiteDfs(int[][] graph) {
        int[] visited = new int[graph.length];

        for(int i=0; i<visited.length; i++){
            if(visited[i] == 0){
                if(!dfs(graph, visited, i, 1)) return false;
            }
        }
        return true;
    }

    public static boolean dfs(int[][] graph, int[] visited, int curr, int color){
        visited[curr] = color;
        int[] arr = graph[curr];
        int nextCol = color == 1 ? -1 : 1;
        for(int i=0; i<arr.length; i++){
            int ele = arr[i];
            if(visited[ele] != 0 && visited[curr] == visited[ele]) return false;
            if(visited[ele] == 0 && !dfs(graph, visited, ele, nextCol)) return false;
        }
        return true;
    }



}
