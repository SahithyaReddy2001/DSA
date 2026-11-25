package graph;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class KruskalsAlgorithm {
    /*
        Given a weighted, undirected, and connected graph with V vertices and edges are represented as an edge list edges[][],
        where edges[i] = [u, v, w] denotes an undirected edge between u and v with weight w.
        Find the sum of the weights of the edges in the Minimum Spanning Tree (MST) of the graph using Kruskal's Algorithm.
        Input: V = 3, edges[][] = [[0, 1, 5], [1, 2, 3], [0, 2, 1]]
        Output: 4
        Explanation:
        The Spanning Tree resulting in a weight of 4 is shown above.

        Input: V = 2, edges = [[0, 1, 5]]
        Output: 5
        Explanation: Only one Spanning Tree is possible which has a weight of 5.

        Constraints:
        2 ≤ V ≤ 103
        V-1 ≤ edges.size() ≤ (V*(V-1))/2
        0 ≤ u, v < n
        1 ≤ w ≤ 103
    */

    /*
        1) Sorts edges by weight using PriorityQueue.
        2) Picks the minimum-weight edge first.
        3) Checks whether both vertices belong to different components.
        4) If they belong to different components, adds the edge weight to MST and performs union.
        5) Otherwise, discards the edge.
    */

//    Operation	                                            Time Complexity
//    Creating PriorityQueue and inserting E edges	        O(E log E)
//    Initializing parent and size arrays	                O(V)
//    Polling all E edges	                                O(E log E)
//    Finding ultimate parents for each edge	            O(E × α(V))
//    Union by Size for each edge	                        O(E × α(V))

    //TC: O(E log E) + O(V) + O(E log E + O(E * alpha) + O(E * alpha)
    //SC : O(V) + O(V) + O(E)
    public static int kruskalsMST(int V, int[][] edges) {
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>((a, b) -> a.get(0) - b.get(0));
        for(int i=0; i<edges.length; i++){
            queue.add(Arrays.asList(edges[i][2], edges[i][0], edges[i][1]));
        }

        int wt = 0;
        int[] parent = new int[V];
        int[] size = new int[V];
        for(int i=0; i<V; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        while(!queue.isEmpty()){
            List<Integer> temp = queue.poll();
            int n1 = temp.get(1);
            int n2 = temp.getLast();
            int cwt = temp.getFirst();

            if(findUltimateParent(n1, parent) != findUltimateParent(n2, parent)){
                wt += cwt;
                unionBySize(parent, size, temp);
            }
        }
        return wt;
    }

    public static void unionBySize(int[] parent, int[] size, List<Integer> temp){
        int n1 = temp.get(1);
        int n2 = temp.getLast();

        int upn1 = findUltimateParent(n1, parent);
        int upn2 = findUltimateParent(n2, parent);

        if(size[upn1] > size[upn2]){
            parent[upn2] = upn1;
            size[upn1] += size[upn2];
        }else{
            parent[upn1] = upn2;
            size[upn2] += size[upn1];
        }
    }

    public static int findUltimateParent(int ele, int[] arr){
        if(arr[ele] == ele) return ele;
        arr[ele] = findUltimateParent(arr[ele], arr);
        return arr[ele];
    }

}
