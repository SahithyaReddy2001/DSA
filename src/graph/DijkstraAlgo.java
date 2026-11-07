package graph;

import java.util.*;

public class DijkstraAlgo {
    public static ArrayList<Integer> dijkstra(ArrayList<ArrayList<ArrayList<Integer>>> adj, int src) {
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0; i<adj.size(); i++){
            ans.add(Integer.MAX_VALUE);
        }

        PriorityQueue<List<Integer>> priorityQueue = new PriorityQueue<>((a, b) -> a.get(0) - b.get(0));
        ans.set(src, 0);
        priorityQueue.add(Arrays.asList(0, src));
        while (!priorityQueue.isEmpty()){
            List<Integer> temp = priorityQueue.poll();
            int ele = temp.getLast();
            int dist = temp.getFirst();
            if(ans.get(ele) != dist){
                continue;
            }
            for(List<Integer> l : adj.get(ele)){
                int newDist = l.getLast() + dist;
                if(ans.get(l.getFirst()) > newDist){
                    ans.set(l.getFirst(), newDist);
                    priorityQueue.add(Arrays.asList(newDist, l.getFirst()));
                }
            }

        }

        return ans;
    }

}
