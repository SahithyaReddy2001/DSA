package graph;

import java.util.*;

public class BFSInGraph {

    /*
    * Algorithm: for nodes from 1 to n
    * Required: Queue and Visited Array of length n+1
    * Step 1: add starting node to queue and marks it as visited
    * Step 2: while queue is not empty pop first element and add its neighbors to queue if not visited and mark them visited
    * */
    //TC: O(N + 2E)
    //SC: O(N+N+N) = 0(3N) = O(N)
    public static List<Integer> bfsInGraph(List<List<Integer>> nodesList, int startEle){
        List<Integer> ansList = new ArrayList<>();

        Queue<Integer> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(startEle);

        int[] visitedArray = new int[nodesList.size()+1];
        visitedArray[startEle] = 1;

        bfs(nodesList,visitedArray , bfsQueue, ansList);
        return ansList;
    }


    public static void bfs(List<List<Integer>> nodesList, int[] visitedArray, Queue<Integer> bfsQueue, List<Integer> ansList){
        int count = 0;
        while(!bfsQueue.isEmpty()){
            count++;
            int ele = bfsQueue.poll();
            ansList.add(ele);
            for(int i: nodesList.get(ele-1)){
                if(visitedArray[i] == 0){
                    bfsQueue.add(i);
                    visitedArray[i] = 1;
                }
            }
        }
        System.out.println(count);
    }

    public static void rec(List<List<Integer>> nodesList, int[] visitedArray, Queue<Integer> bfsQueue, List<Integer> ansList){
        if(bfsQueue.isEmpty()) return;

        int ele = bfsQueue.poll();
        if(visitedArray[ele] != 1) {
            visitedArray[ele] = 1;
            ansList.add(ele);
            for (int i : nodesList.get(ele - 1)) {
                if (visitedArray[i] == 0) {
                    bfsQueue.add(i);
                }
            }
        }
        rec(nodesList, visitedArray, bfsQueue, ansList);

    }
}


