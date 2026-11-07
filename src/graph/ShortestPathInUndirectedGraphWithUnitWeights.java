package graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class ShortestPathInUndirectedGraphWithUnitWeights {

    public static int[] shortestPath(List<List<Integer>> list, int v){
        int[] ans = new int[v];
        for(int i=0; i<v; i++){
            ans[i] = Integer.MAX_VALUE;
        }

        ans[0] = 0;
        Queue<List<Integer>> queue = new ArrayDeque<>();
        queue.add(Arrays.asList(0,0));

        while(!queue.isEmpty()){
            List<Integer> l = queue.poll();
            int ele = l.get(0);
            int dis = l.get(1);
            for(int temp: list.get(ele)){
                if(ans[temp] > dis+1){
                    int d = dis+1;
                    ans[temp] = d;
                    queue.add(Arrays.asList(temp, d));
                }
            }
        }

        for(int i=0; i<v; i++){
            if(ans[i] == Integer.MAX_VALUE){
                ans[i] = -1;
            }
        }


        return ans;
    }
}
