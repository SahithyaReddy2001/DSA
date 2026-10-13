package graph;

import java.util.ArrayList;
import java.util.List;

public class DFSInGraph {

    /*
    *We will start with initial node if its visited return else
    * mark it visited and start recursion for its neighbors
    * */
    //SC: O(2N)
    //TC: O(N+E)
    public static List<Integer> dfsInGraph(List<List<Integer>> nodesList, int startEle){
        return rec(nodesList, new int[nodesList.size()+1], new ArrayList<>(), startEle);
    }

    public static List<Integer> rec(List<List<Integer>> nodesList, int[] visitedArray, List<Integer> ansList, int ele){
        if(visitedArray[ele] == 1) return ansList;

        visitedArray[ele] = 1;
        ansList.add(ele);

        for(int i : nodesList.get(ele-1)){
            if(visitedArray[i] == 0)
                rec(nodesList, visitedArray, ansList, i);
        }

        return ansList;
    }

}
