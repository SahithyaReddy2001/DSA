package graph;

import java.util.Arrays;

public class FloydWarshall {

    /*
    Given a weighted directed graph, represented by an adjacency matrix dist[][], where dist[i][j] represents the weight of the edge from node i to node j. If there is no direct edge, dist[i][j] is set to a large value (i.e., 108) to represent infinity.
    The graph may contain negative edge weights, but it does not contain any negative weight cycles.
    Find the shortest distance between every pair of nodes i and j in the graph.
    Note: Modify the distances for every pair in place.

    Examples :
    Input: dist[][] = [[0, 4, 108, 5, 108], [108, 0, 1, 108, 6], [2, 108, 0, 3, 108], [108, 108, 1, 0, 2], [1, 108, 108, 4, 0]]
    Output: [[0, 4, 5, 5, 7], [3, 0, 1, 4, 6], [2, 6, 0, 3, 5], [3, 7, 1, 0, 2], [1, 5, 5, 4, 0]]
    Explanation: Each cell dist[i][j] in the output shows the shortest distance from node i to node j, computed by considering all possible intermediate nodes.

    Input: dist[][] = [[0, -1, 2], [1, 0, 108], [3, 1, 0]]
    Output: [[0, -1, 2], [1, 0, 3], [2, 1, 0]]
    Explanation: Each cell dist[i][j] in the output shows the shortest distance from node i to node j, computed by considering all possible intermediate nodes.
    From 2 to 0 shortest distance should be 2 by following path 2 -> 1 -> 0
    From 1 to 2 shortest distance should be 3 by following path 1 -> 0 -> 2

    Constraints:
    1 ≤ dist.size() ≤ 100
    -103 ≤ dist[i][j] ≤ 108
    dist[i].size() = dist.size()
    */

    /*
     * Intuition:
     *     1. We are given a distance matrix where:
     *        - dist[i][j] represents the distance from vertex i to vertex j.
     *        - INF (1e8) represents an unreachable vertex.
     *
     *     2. Consider every vertex k as an intermediate vertex.
     *
     *     3. For every source vertex i and destination vertex j:
     *        - Check whether i -> k is reachable.
     *        - Check whether k -> j is reachable.
     *        - Calculate the new distance:
     *              newDist = dist[i][k] + dist[k][j]
     *        - If newDist < dist[i][j], update the distance.
     *
     *     4. Negative cycle detection:
     *        - After completing all iterations, check the diagonal.
     *        - If dist[i][i] < 0 for any vertex i,
     *          a negative weight cycle exists.
     *
     *     5. Otherwise, the distance matrix contains
     *        the shortest distances between all pairs of vertices.
     *
     *
     * Why check dist[i][k] != INF && dist[k][j] != INF?
     *     - INF represents an unreachable vertex.
     *     - We should not calculate a path through an unreachable vertex.
     *     - Otherwise, negative edge weights can produce
     *       incorrect distances.
     *
     *
     * Why is k the outermost loop?
     *     - k represents the intermediate vertex.
     *     - We process all source-destination pairs using k
     *       before moving to the next intermediate vertex.
     *     - This ensures that previously considered intermediate
     *       vertices are available when calculating new paths.
     *
     *
     * 1. Time Complexity (TC)
     *
     *     - O(N^3): Three nested loops to calculate shortest
     *       distances between all pairs of vertices.
     *     - O(N): Check the diagonal for negative cycles.
     *
     *     Final TC: O(N^3) + O(N) = O(N^3)
     *
     *
     * 2. Space Complexity (SC)
     *
     *     - O(1): Only extra variables are used.
     *     - The given distance matrix is modified in-place.
     *
     *     Final SC: O(1) auxiliary space.
     *
     *
     * Note:
     *     - Floyd-Warshall finds shortest paths between
     *       all pairs of vertices.
     *     - Unlike Dijkstra, it supports negative edge weights.
     *     - It cannot give meaningful shortest paths if a
     *       negative weight cycle exists.
     *     - Negative cycle detection is done by checking
     *       whether any diagonal element becomes negative.
     *     - Intermediate vertex (k) must be the outermost loop.
     *     - INF should match the value specified in the problem.
     */

    public static void floydWarshall(int[][] dist) {
        int m = dist.length;

        int INF = 100000000;
        for (int k = 0; k < m; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        int newDist = dist[i][k] + dist[k][j];

                        if (newDist < dist[i][j]) {
                            dist[i][j] = newDist;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (dist[i][i] < 0) {
                System.out.println("Negative Cycle");
                return;
            }
        }
        System.out.println(Arrays.deepToString(dist));

    }
}
