package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ShortestPathDAG {
    public static List<Integer> shortestPathDag(int[][] arr, int v){
        List<List<List<Integer>>> list = new ArrayList<>();
        for(int i=0; i<v; i++){
            list.add(new ArrayList<>());
        }
        int[] visited = new int[v];
        for(int i=0; i<arr.length; i++){
            int l = arr[i][0];
            int m = arr[i][1];
            int n = arr[i][2];
            list.get(l).add(Arrays.asList(m,n));
        }

        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<v; i++){
            if(visited[i] == 0){
                topoSortDfs(list,stack, visited, i);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=0; i<v; i++){
            if(i == 0){
                ans.add(0);
            }
            else ans.add(Integer.MAX_VALUE);
        }

        while(!stack.isEmpty()){
            int ele = stack.pop();
            if(ans.get(ele) == Integer.MAX_VALUE){
                ans.set(ele, -1);
            }else{
                List<List<Integer>> l = list.get(ele);
                for(List<Integer> temp : l){
                    if(ans.get(ele) + temp.get(1) < ans.get(temp.get(0))){
                        ans.set(temp.get(0),ans.get(ele) + temp.get(1));
                    }
                }
            }
        }
        return ans;

    }

    public static void topoSortDfs(List<List<List<Integer>>> list, Stack<Integer> stack, int[] visited, int ele){
        visited[ele] = 1;
        List<List<Integer>> l = list.get(ele);
        for(List<Integer> temp : l){
            if(visited[temp.get(0)] == 0){
                topoSortDfs(list, stack, visited, temp.get(0));
            }
        }
        stack.add(ele);
    }
}
