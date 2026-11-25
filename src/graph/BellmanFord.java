package graph;

import java.util.ArrayList;

public class BellmanFord {
    /*
    * Given a weighted graph with V vertices numbered from 0 to V-1 and edges, represented by a 2d array edges[][], where edges[i] = [u, v, w] represents a direct edge from node u to v having w edge weight. You are also given a source vertex src.
      Compute the shortest distances from the src to all other vertices. If a vertex is unreachable from the src, its distance should be marked as 108. Additionally, if the graph contains a negative weight cycle, return [-1] to indicate that shortest paths cannot be reliably computed.

        Examples:
        Input: V = 5, edges[][] = [[1, 3, 2], [4, 3, -1], [2, 4, 1], [1, 2, 1], [0, 1, 5]], src = 0
        Output: [0, 5, 6, 6, 7]
        Explanation: Shortest Paths:
        For 0 to 1 minimum distance will be 5. By following path 0 -> 1
        For 0 to 2 minimum distance will be 6. By following path 0 -> 1 -> 2
        For 0 to 3 minimum distance will be 6. By following path 0 -> 1 -> 2 -> 4 -> 3
        For 0 to 4 minimum distance will be 7. By following path 0 -> 1 -> 2 -> 4

        Input: V = 4, edges[][] = [[0, 1, 4], [1, 2, -6], [2, 3, 5], [3, 1, -2]], src = 0
        Output: [-1]
        Explanation: The graph contains a negative weight cycle formed by the path 1 -> 2 -> 3 -> 1, where the total weight of the cycle is negative.

        Constraints:
        1 ≤ V ≤ 100
        1 ≤ edges.size() ≤ 3*103
        0 ≤ src < V
        -103 ≤ w ≤ 103
    * */


    /*
    * Intuition:
        1. Initialize the distance array with 1e8 (infinity).
        2. Set the source distance to 0.
        3. Relax all edges V-1 times:
           - For every edge u -> v with weight w:
           - Check whether u is reachable from the source.
           - If dis[u] + w < dis[v], update dis[v].
        4. Negative cycle detection:
           - Iterate through all edges one more time.
           - If any distance can still be reduced,
             a reachable negative weight cycle exists.
           - Return [-1].
        5. Otherwise, return the distance array.

    Why check dis[u] != 1e8?
        - 1e8 represents infinity (unreachable).
        - We should not relax edges starting from unreachable nodes.
        - Otherwise, negative edge weights can produce
          incorrect distances.

    1. Time Complexity (TC)

        - O(V): Initialize distance array.
        - O((V-1) * E): Relax all edges V-1 times.
        - O(E): Negative cycle detection.

        Final TC: O(V * E)

    2. Space Complexity (SC)

        - O(V): Distance array (answer list).
        - O(1): Extra variables.

        Final SC: O(V)

    Note:
        - V-1 iterations are sufficient to find shortest distances
          if there is no reachable negative weight cycle.
        - The Vth iteration is used for negative cycle detection.
        - int is safe for the given constraints.
      */
    public ArrayList<Integer> bellmanFord(int v, int[][] edges, int src) {
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0; i<v; i++){
            ans.add((int)1e8);
        }

        ans.set(src,0);

        for(int i=0; i<v-1; i++){
            for(int[] edge: edges){
                int ue = edge[0];
                int ve = edge[1];
                int w = edge[2];
                if(ans.get(ue) != 1e8 && ans.get(ue) + w < ans.get(ve)){
                    ans.set(ve, ans.get(ue)+w);
                }
            }
        }

        for(int[] edge: edges){
            int ue = edge[0];
            int ve = edge[1];
            int w = edge[2];
            if(ans.get(ue) != 1e8 && ans.get(ue) + w < ans.get(ve)){
                ans = new ArrayList<>();
                ans.add(-1);
                break;
            }
        }
        return ans;

    }
}
