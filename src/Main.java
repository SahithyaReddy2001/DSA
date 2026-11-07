import graph.FindEventualSafeStates;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
//        list.add(Arrays.asList(1,3));
//        list.add(Arrays.asList(0,2,3));
//        list.add(Arrays.asList(1,6));
//        list.add(Arrays.asList(0,4));
//        list.add(Arrays.asList(3,5));
//        list.add(Arrays.asList(4,6));
//        list.add(Arrays.asList(2,5,7,8));
//        list.add(Arrays.asList(6,8));
//        list.add(Arrays.asList(6,7));
//        list.add(Arrays.asList());


        list.add(Arrays.asList(1,2));
        list.add(Arrays.asList(3,2));
        list.add(Arrays.asList(5));
        list.add(Arrays.asList(0));
        list.add(Arrays.asList(5));
        list.add(Arrays.asList());
        list.add(Arrays.asList());
        System.out.println(FindEventualSafeStates.eventualSafeNodes(list.stream()
                .map(subList -> subList.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new)));
//
//        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>(List.of(
//                new ArrayList<>(List.of(
//                        new ArrayList<>(List.of(1, 1)),
//                        new ArrayList<>(List.of(2, 6))
//                )),
//                new ArrayList<>(List.of(
//                        new ArrayList<>(List.of(0, 1)),
//                        new ArrayList<>(List.of(2, 3))
//                )),
//                new ArrayList<>(List.of(
//                        new ArrayList<>(List.of(0, 6)),
//                        new ArrayList<>(List.of(1, 3))
//                ))
//        ));
//        //System.out.println(AlienDict.findOrderTest(new String[]{"baa", "abc", "abcd", "abca", "cab"}));
//        System.out.println(DijkstraAlgo.dijkstra(adj, 2));
    }

    public static boolean isCyclic(List<List<Integer>> adj){
        int n = adj.size();
        int[] visited = new int[n];
        int[] pathVisited = new int[n];
        for(int i=0; i<n; i++){
            if(visited[i] == 0 && dfs(adj, visited, pathVisited, i)){
                return true;
            }
        }
        return false;
    }

    public static boolean dfs(List<List<Integer>> adj, int[] visited, int[] pathVisited, int ele){
        visited[ele] = 1;
        pathVisited[ele] = 1;
        for(int i: adj.get(ele)){
            if(visited[i] == 1 && pathVisited[i] == 1) return true;
            if(visited[i] == 0 && dfs(adj, visited, pathVisited, i)) return true;
        }
        pathVisited[ele] = 0;
        return false;
    }

}
