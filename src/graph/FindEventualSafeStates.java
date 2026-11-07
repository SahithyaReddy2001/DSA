package graph;

import java.util.*;

public class FindEventualSafeStates {
    /*
        There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].
        A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).
        Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.

        Example 1:
        Illustration of graph
        Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
        Output: [2,4,5,6]
        Explanation: The given graph is shown above.
        Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
        Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.

        Example 2:
        Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
        Output: [4]
        Explanation:
        Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.

        Constraints:
        n == graph.length
        1 <= n <= 104
        0 <= graph[i].length <= n
        0 <= graph[i][j] <= n - 1
        graph[i] is sorted in a strictly increasing order.
        The graph may contain self-loops.
        The number of edges in the graph will be in the range [1, 4 * 104].
    * */

    public static
    List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] visited = new int[n];
        int[] pathVisited = new int[n];
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<n; i++){
            if(visited[i] == 0){
                dfs(visited, pathVisited, graph, i, list);
            }
        }
        Collections.sort(list);
        return list;
    }

    public static boolean dfs(int[] visited, int[] pathVisited, int[][] graph, int ele, List<Integer> list){
        visited[ele] = 1;
        pathVisited[ele] = 1;
        for(int i=0; i<graph[ele].length; i++){
            if(visited[graph[ele][i]] == 0 && dfs(visited, pathVisited, graph, graph[ele][i], list)){
                return true;
            } else if(pathVisited[graph[ele][i]] == 1){
                return true;
            }
        }
        list.add(ele);
        pathVisited[ele] = 0;
        return false;
    }



    /*
    *
        1. Build the reverse graph: For every original edge u -> v, add the reversed edge v -> u.
        2. Calculate in-degree: In the reverse graph, the in-degree of a node equals its original graph's out-degree.
        3. Initialize the queue: Add all nodes whose reverse in-degree is 0. These are the terminal nodes in the original graph (nodes with no outgoing edges).
        4. Process the queue:
           - Remove a node and add it to the safe-node list.
           - Visit its neighbors in the reverse graph.
           - Decrease their in-degrees.
           - If a neighbor's in-degree becomes 0, add it to the queue.
        5. Sort the answer: Sort the safe nodes in ascending order, as required by the problem.
        Why does this work?
        A safe node is one from which every possible path eventually reaches a terminal node. Nodes in cycles never become fully removable through this process, so they are excluded from the answer.

    * 1. Time Complexity (TC)
        Your calculation:

        O(V) + O(V + E) + O(V) + O(V) + O(log V)

        The main steps are:
        - O(V): Initialize the reverse adjacency list.
        - O(V + E): Build the reverse graph and calculate in-degrees.
        - O(V): Add all terminal nodes (in-degree 0) to the queue.
        - O(V + E): Process the queue and update in-degrees of neighboring nodes.
        - O(V log V): Sort the resulting safe nodes using Collections.sort(ans).
        Final TC: O(V + E + V log V), which simplifies to O(E + V log V).

        Your original calculation missed the + E part in the queue processing and underestimated the sorting cost. Sorting takes O(S log S), where S is the number of safe nodes, and in the worst case S = V.
        2. Space Complexity (SC)
        Your calculation:

        O(V + E) + O(V) + O(V)
        - O(V + E): Reverse adjacency list.
        - O(V): In-degree array.
        - O(V): Queue and answer list.
        Final SC: O(V + E), excluding the input graph
    * * */
    public static List<Integer> eventualSafeNodesBfs(int[][] graph) {
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0; i< graph.length; i++){
            list.add(new ArrayList<>());
        }
        int[] inDegree = new int[graph.length];

        for(int i=0; i<graph.length; i++){
            for(int j=0; j<graph[i].length; j++){
                list.get(graph[i][j]).add(i);
                inDegree[i]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> ans = new ArrayList<>();

        for(int i=0; i<inDegree.length; i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }
        while(!queue.isEmpty()){
            int ele = queue.poll();
            ans.add(ele);
            for(int i : list.get(ele)){
                inDegree[i] = inDegree[i]-1;
                if(inDegree[i] == 0){
                    queue.add(i);
                }
            }
        }
        Collections.sort(ans);
        return ans;
    }
}
