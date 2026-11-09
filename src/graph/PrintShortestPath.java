package graph;

import java.util.*;

public class PrintShortestPath {
    /*
        Given a weighted undirected graph with V vertices numbered from 1 to V and edges represented by 2D array edges[][], where edges[i] = [u, v, w] denotes an undirected edge between vertices u and v with weight w. We are also given two vertices src and dest, find the shortest path from src to dest.
        If multiple shortest paths exist with the same total weight, return the one whose sequence of vertices is lexicographically smallest.
        If no path exists between src and dest, return {-1}.
        Note: The graph contains no negative-weight edges.

        Examples :

        Input: V = 5, edges[][] = [[1, 2, 2], [2, 5, 5], [2, 3, 4], [1, 4, 1], [4, 3, 3], [3, 5, 1]], src = 1, dest = 5
        Output: [1, 4, 3, 5]
        Explanation: The shortest path from vertex 1 to vertex 5 is 1 -> 4 -> 3 -> 5 with a total weight of 5.

        Input: V = 6, edges[][] = [[1, 2, 1], [1, 3, 1], [2, 4, 1], [3, 4, 1], [4, 6, 1], [2, 5, 2], [5, 6, 1]], src = 1, dest = 6
        Output: [1, 2, 4, 6]
        Explanation: There are two shortest paths from 1 to 6 with total weight 3: 1 -> 2 -> 4 -> 6 and 1 -> 3 -> 4 -> 6. Since [1, 2, 4, 6] is lexicographically smaller, it is returned.

        Input: V = 5, edges[][] = [[1,2,4], [2,3,2], [4,5,1]], src = 1, dest = 5
        Output: [-1]
        Explanation: Since there are no edges, so no answer is possible.

        Constraints:

        2 ≤ V ≤ 105
        0 ≤ edges.size() ≤ 2* 105
        1 ≤ src, dest ≤ V
        edges[i].size() = 3
        1 ≤ edges[i][2] ≤ 104
    */

    //This dont work for lexicographically shortest
    public static ArrayList<Integer> shortestPath(int V, int[][] edges, int src, int dest) {
        List<List<List<Integer>>> list = new ArrayList<>();

        int[] parent = new int[V+1];
        int[] dis = new int[V+1];


        for(int i=0; i<V+1; i++){
            list.add(new ArrayList<>());
            parent[i] = i;
            dis[i] = Integer.MAX_VALUE;
        }

        for(int i=0; i<edges.length; i++){
            list.get(edges[i][0]).add(Arrays.asList(edges[i][1], edges[i][2]));
            list.get(edges[i][1]).add(Arrays.asList(edges[i][0], edges[i][2]));
        }

        PriorityQueue<List<Integer>> queue = new PriorityQueue<>((a, b) -> Integer.compare(a.get(0) , b.get(0)));

        queue.add(Arrays.asList(0, src));
        dis[src] = 0;

        while(!queue.isEmpty()){
            List<Integer> temp = queue.poll();
            int d = temp.getFirst();
            int ele = temp.getLast();
            if(d > dis[ele]) continue;
            for(List<Integer> i: list.get(ele)){
                int ud = d+i.getLast();
                if(ud<dis[i.getFirst()]){
                    dis[i.getFirst()] = ud;
                    parent[i.getFirst()] = ele;
                    queue.add(Arrays.asList(ud, i.getFirst()));
                }
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        if(dis[dest] == Integer.MAX_VALUE){
            ans.add(-1);
            return ans;
        }


        int index = dest;
        ans.add(dest);

        while(true){
            ans.add(parent[index]);
            index = parent[index];
            if(index == src) break;
        }

        Collections.reverse(ans);

        return ans;

    }


    //Lexicographically shortest
    public static ArrayList<Integer> shortestPathTest(int V, int[][] edges, int src, int dest) {
        List<List<List<Integer>>> list = new ArrayList<>();

        int[] dis = new int[V+1];


        for(int i=0; i<V+1; i++){
            list.add(new ArrayList<>());
            dis[i] = Integer.MAX_VALUE;
        }

        for(int i=0; i<edges.length; i++){
            list.get(edges[i][0]).add(Arrays.asList(edges[i][1], edges[i][2]));
            list.get(edges[i][1]).add(Arrays.asList(edges[i][0], edges[i][2]));
        }

        PriorityQueue<List<String>> queue = new PriorityQueue<>((a, b) -> Integer.compare(Integer.parseInt(a.get(0)) , Integer.parseInt(b.get(0))));

        queue.add(Arrays.asList(String.valueOf(0), String.valueOf(src), src+"."));
        dis[src] = 0;

        ArrayList<Integer> ans = new ArrayList<>();


        while(!queue.isEmpty()){
            List<String> temp = queue.poll();
            int d = Integer.parseInt(temp.getFirst());
            int ele = Integer.parseInt(temp.get(1));
            String s = temp.getLast();
            if(d > dis[ele]) continue;
            if(ele == dest){
                if(ans.isEmpty()){
                    String[] arr = s.split("\\.");
                    for(String str: arr){
                        ans.add(Integer.valueOf(str));
                    }
                }else{
                    ans =  getLex(ans, s);
                }
            }
            for(List<Integer> i: list.get(ele)){
                int ud = d+i.getLast();
                if(ud<dis[i.getFirst()]){
                    dis[i.getFirst()] = ud;
                    queue.add(Arrays.asList(String.valueOf(ud), String.valueOf(i.getFirst()), s+i.getFirst()+"."));
                }
            }
        }


        if(ans.isEmpty()){
            ans.add(-1);
            return ans;
        }

        return ans;
    }

    public static ArrayList<Integer> getLex(ArrayList<Integer> l1, String s){
        ArrayList<Integer> l2 = new ArrayList<>();
        String[] arr = s.split("\\.");
        for(String str: arr){
            l2.add(Integer.valueOf(str));
        }
        for (int i = 0; i < Math.min(l1.size(), l2.size()); i++) {
            if (!l1.get(i).equals(l2.get(i))) {
                return l1.get(i) < l2.get(i) ? l1 : l2;
            }
        }

        return l1.size() <= l2.size() ? l1 : l2;
    }

}
