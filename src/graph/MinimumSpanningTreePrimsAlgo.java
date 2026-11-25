package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumSpanningTreePrimsAlgo {

    /*
    * Given a weighted, undirected, and connected graph represented by an adjacency list adj[][][], where adj[u] contains pairs [v, w] representing an edge from u to v with weight w.
        Find the total weight of its Minimum Spanning Tree (MST) using Prim's Algorithm.

        Examples:
        Input: adj[][][] = [[[1, 5], [2, 1]], [[0, 5], [2, 3]], [[0, 1], [1, 3]]]
        Output: 4
        Explanation:
        The MST contains edges (0, 2) with weight 1 and (1, 2) with weight 3. The total weight is 1 + 3 = 4.

        Input: adj[][][] = [[[1, 5]], [[0, 5]]]
        Output: 5
        Explanation: Only one Spanning Tree is possible which has a weight of 5.

        Constraints:
        2 ≤ adj.size() ≤ 103
        adj[u][i] = [v, w]
        0 ≤ v < adj.size()
        1 ≤ w ≤ 103
     */

    /*
     * Intuition:
     *     1. Use a Min-Heap (PriorityQueue) to always select
     *        the edge with the minimum weight.
     *
     *     2. Store the following in the PriorityQueue:
     *        - Weight of the edge.
     *        - Destination node.
     *        - Parent node.
     *
     *     3. Start from node 0 and insert it with weight 0.
     *
     *     4. While the PriorityQueue is not empty:
     *        - Remove the edge with minimum weight.
     *        - Check whether the destination node is already visited.
     *        - If visited, skip it to avoid forming a cycle.
     *        - Otherwise:
     *            - Mark the node as visited.
     *            - Add the edge weight to the MST sum.
     *            - Store the parent and node in the MST.
     *            - Traverse its adjacent nodes.
     *            - Add unvisited neighbors to the PriorityQueue.
     *
     *     5. Continue until the PriorityQueue becomes empty.
     *
     *     6. Return the total weight of the MST.
     *
     *     Why check visited[node] == 0?
     *        - Multiple edges connecting the same node can be
     *          present in the PriorityQueue.
     *        - We only select a node once.
     *        - Skipping already visited nodes prevents cycles
     *          from being included in the MST.
     *
     *
     *     1. Time Complexity (TC)
     *
     *        - O(E): Traversing all adjacency list entries.
     *        - O(E log E): PriorityQueue insert and poll operations.
     *        - O(V): Visiting and processing vertices.
     *
     *        Final TC: O(V + E log E)
     *
     *        For a simple connected graph, E <= V^2,
     *        so log E = O(log V).
     *
     *        Final TC: O(E log V)
     *
     *
     *     2. Space Complexity (SC)
     *
     *        - O(V): Visited array.
     *        - O(E): PriorityQueue in the worst case.
     *        - O(V): MST list.
     *
     *        Final SC: O(V + E)
     *
     *
     *     Note:
     *        - Prim's algorithm is used to find a Minimum
     *          Spanning Tree (MST) of a weighted undirected graph.
     *        - At each step, select the minimum-weight edge
     *          connecting the MST to an unvisited node.
     *        - Visited array prevents cycles.
     *        - MST contains V-1 edges for a connected graph.
     *        - This implementation starts from node 0.
     *        - For a disconnected graph, this code only processes
     *          the component containing node 0 (a spanning forest
     *          requires starting Prim from each unvisited node).
     * */


    //TODO: While revision try to insert other node instead of 0 in queue initially
    public int primMST(ArrayList<ArrayList<ArrayList<Integer>>> adj) {
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>((a, b) -> Integer.compare(a.get(0) , b.get(0)));
        int[] visited = new int[adj.size()];
        int sum = 0;
        List<List<Integer>> mst = new ArrayList<>();

        queue.add(Arrays.asList(0,0,-1));

        while(!queue.isEmpty()){
            List<Integer> list = queue.poll();
            int wt = list.getFirst();
            int node = list.get(1);
            int parent = list.getLast();
            if(visited[node] == 0){
                visited[node] = 1;
                sum += wt;
                mst.add(Arrays.asList(parent, node));
                for(ArrayList<Integer> temp: adj.get(node)){
                    if(visited[temp.getFirst()] == 0){
                        queue.add(Arrays.asList(temp.getLast(), temp.getFirst(), node));
                    }
                }
            }
        }
        System.out.println(mst);

        return sum;

    }
}
