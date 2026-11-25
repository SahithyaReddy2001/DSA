package graph;

public class FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance {

    /*
    * There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.
    Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.
    Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.


    Example 1:
    Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
    Output: 3
    Explanation: The figure above describes the graph.
    The neighboring cities at a distanceThreshold = 4 for each city are:
    City 0 -> [City 1, City 2]
    City 1 -> [City 0, City 2, City 3]
    City 2 -> [City 0, City 1, City 3]
    City 3 -> [City 1, City 2]
    Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.

    Example 2:
    Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
    Output: 0
    Explanation: The figure above describes the graph.
    The neighboring cities at a distanceThreshold = 2 for each city are:
    City 0 -> [City 1]
    City 1 -> [City 0, City 4]
    City 2 -> [City 3, City 4]
    City 3 -> [City 2, City 4]
    City 4 -> [City 1, City 2, City 3]
    The city 0 has 1 neighboring city at a distanceThreshold = 2.


    Constraints:
    2 <= n <= 100
    1 <= edges.length <= n * (n - 1) / 2
    edges[i].length == 3
    0 <= fromi < toi < n
    1 <= weighti, distanceThreshold <= 10^4
    All pairs (fromi, toi) are distinct.
    * */

    /*
     * Intuition:
     *     1. Initialize an adjacency matrix:
     *        - arr[i][j] = 1e9 (infinity) for unreachable cities.
     *        - arr[i][i] = 0 (distance to itself).
     *
     *     2. Store the given edges in the matrix.
     *        - Since the graph is undirected, update both:
     *          arr[u][v] = weight and arr[v][u] = weight.
     *
     *     3. Apply Floyd-Warshall:
     *        - Consider every vertex k as an intermediate vertex.
     *        - For every source i and destination j:
     *          - Check whether i -> k and k -> j are reachable.
     *          - Calculate the distance through k.
     *          - Update arr[i][j] if a shorter path is found.
     *
     *     4. Count reachable cities for each city:
     *        - Count cities whose shortest distance <= distanceThreshold.
     *        - Exclude the current city itself (i != j).
     *
     *     5. Find the city with the minimum number of reachable cities.
     *        - If multiple cities have the same count,
     *          return the city with the greatest index.
     *        - Using count <= minCities ensures the latest city
     *          is selected when there is a tie.
     *
     *
     *     Why check arr[i][k] != INF && arr[k][j] != INF?
     *        - INF represents an unreachable city.
     *        - We should not calculate a path through unreachable cities.
     *        - Otherwise, adding distances involving infinity
     *          can produce incorrect results.
     *
     *
     *     1. Time Complexity (TC)
     *
     *        - O(N^2): Initialize the adjacency matrix.
     *        - O(E): Store all edges in the matrix.
     *        - O(N^3): Floyd-Warshall to find all-pairs
     *          shortest distances.
     *        - O(N^2): Count reachable cities for each city.
     *
     *        Final TC: O(N^2) + O(E) + O(N^3) + O(N^2)
     *                  = O(N^3)
     *
     *
     *     2. Space Complexity (SC)
     *
     *        - O(N^2): Adjacency/distance matrix.
     *        - O(1): Extra variables.
     *
     *        Final SC: O(N^2)
     *
     *
     *     Note:
     *        - Floyd-Warshall is used to find shortest distances
     *          between every pair of cities.
     *        - The graph is undirected, so both directions
     *          must be updated for every edge.
     *        - Do not count the current city itself as a neighbor.
     *        - Use count <= minCities to handle ties by selecting
     *          the city with the greatest index.
     * */
    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] arr = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i!=j) {
                    arr[i][j] = (int) 1e9;
                }
            }
        }


        for (int[] edge : edges) {
            int k = edge[0];
            int l = edge[1];
            int dis = edge[2];
            arr[k][l] = dis;
            arr[l][k] = dis;
        }

        for(int k=0; k<n; k++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(arr[i][k] != (int)1e9 && arr[k][j] != (int)(1e9)){
                        arr[i][j] = Math.min(arr[i][j], arr[i][k]+arr[k][j]);
                    }
                }
            }
        }

        int ans = -1;
        int minCities = n;

        for(int i=0; i<n; i++){
            int count = 0;
            for(int j=0; j<n; j++){
                if(arr[i][j]<=distanceThreshold){
                    count++;
                }
            }
            if(count<=minCities){
                minCities = count;
                ans = i;
            }
        }

        return ans;
    }
}
