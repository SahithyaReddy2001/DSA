package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class NumberOfWaysToArriveAtDestination {
    /*
    You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.
    You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.
    Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.

    Example 1:
    Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
    Output: 4
    Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
    The four ways to get there in 7 minutes are:
    - 0 ➝ 6
    - 0 ➝ 4 ➝ 6
    - 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
    - 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6

    Example 2:
    Input: n = 2, roads = [[1,0,10]]
    Output: 1
    Explanation: There is only one way to go from intersection 0 to intersection 1, and it takes 10 minutes.


    Constraints:
    1 <= n <= 200
    n - 1 <= roads.length <= n * (n - 1) / 2
    roads[i].length == 3
    0 <= ui, vi <= n - 1
    1 <= timei <= 109
    ui != vi
    There is at most one road connecting any two intersections.
    You can reach any intersection from any other intersection.
    */

    public int countPaths(int n, int[][] roads) {
        List<List<List<Integer>>> edgeList = new ArrayList<>();
        int m = roads.length;
        long[] dis = new long[n];
        int[] ways = new int[n];

        for (int i = 0; i < n; i++) {
            edgeList.add(new ArrayList<>());
            dis[i] = Long.MAX_VALUE;
        }
        for (int i = 0; i < m; i++) {
            edgeList.get(roads[i][0]).add(Arrays.asList(roads[i][1], roads[i][2]));

            edgeList.get(roads[i][1]).add(Arrays.asList(roads[i][0], roads[i][2]));
        }
        PriorityQueue<long[]> queue = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        queue.add(new long[]{0, 0});
        ways[0] = 1;
        dis[0] = 0;

        int mod = (int) (1e9 + 7);

        while (!queue.isEmpty()) {
            long[] temp = queue.poll();
            long d = temp[0];
            int node = (int) temp[1];
            if (d > dis[node]) continue;
            for (List<Integer> l : edgeList.get(node)) {
                int cd = l.getLast();
                int cNode = l.getFirst();
                long distance = d + cd;
                if (distance < dis[cNode]) {
                    queue.add(new long[]{distance, cNode});
                    dis[cNode] = distance;
                    ways[cNode] = ways[node];
                } else if (distance == dis[cNode]) {
                    ways[cNode] = (ways[cNode] + ways[node]) % mod;
                }
            }
        }
        return ways[n - 1] % mod;
    }
}
