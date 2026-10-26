package graph;

import java.util.*;

public class DetectCycleInOrderedGraph {

    /*
    * Directed Cycle Detection Using DFS
    1. Initialize: Create two arrays, visited and pathVisited.
    2. Start DFS: For each unvisited vertex, start DFS to handle disconnected components.
    3. Mark both arrays: When entering a vertex, set visited[ele] = 1 and pathVisited[ele] = 1.
    4. Explore neighbors:
       - If a neighbor is unvisited, recursively call DFS.
       - If a neighbor is already in the current DFS path (pathVisited[i] == 1), a cycle exists.
       - If a neighbor is visited but not in the current DFS path, it does not by itself indicate a cycle.
    5. Backtrack: Set pathVisited[ele] = 0 when finishing that vertex.
    6. Return: If a cycle is found, return true; otherwise, return false after all components are explored.

    * TC: O(V) + O(V + E) = O(V + E)
    - O(V) — outer loop visits each vertex at most once.
    - O(V + E) — DFS visits each vertex and processes each directed edge at most once.
    Final TC: O(V + E)
    *
    Space Complexity (SC)
    Your calculation is correct:
    SC: O(V) + O(V) + O(V) = O(V)
    - O(V) — visited array.
    - O(V) — pathVisited array.
    - O(V) — recursive DFS call stack in the worst case.
     */

    public static boolean detectCycleDFS(List<List<Integer>> list){
        int[] visited = new int[list.size()];
        int[] pathVisited = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            if(visited[i] == 0 && dfs(list, visited, pathVisited, i)){
                return true;
            }
        }
        return false;
    }

    public static boolean dfs(List<List<Integer>> list, int[] visited, int[] pathVisited, int ele){
        visited[ele] = 1;
        pathVisited[ele] = 1;
        for (int i: list.get(ele)){
            if(visited[i] == 1 && pathVisited[i] == 1) return true;
            if(visited[i] == 0 && dfs(list, visited, pathVisited, i)) return true;
        }
        pathVisited[ele] = 0;
        return false;
    }


    /*
    This approach is based on Kahn's Algorithm.
    1. Calculate in-degree: Count incoming edges for every vertex.
    2. Initialize queue: Add all vertices with in-degree 0.
    3. Process vertices: Remove a vertex from the queue and increment the count of processed vertices.
    4. Reduce in-degree: For each outgoing neighbor, decrease its in-degree. If it becomes 0, add it to the queue.
    5. Detect cycle:
       - If the number of processed vertices equals V, there is no cycle.
       - If the number of processed vertices is less than V, a cycle exists.
       TC: O(V + E) + O(V) + O(V)
        - O(V + E) — calculate the in-degree of all vertices.
        - O(V) — add all vertices with in-degree 0 to the queue.
        - O(V + E) — process the queue and decrease the in-degree of neighboring vertices.
        Final TC: O(V + E)

        Space Complexity (SC)
        Your calculation:

        SC: O(V) + O(V)
        - O(V) — inDegree array.
        - O(V) — queue, which can contain up to V vertices.
        - O(V) — ansList, which can contain up to V vertices.
   */
    public static boolean detectCycleBfs(List<List<Integer>> list){
        int[] inDegree = new int[list.size()];
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=0; i<list.size(); i++){
            List<Integer> tempList = list.get(i);
            for(int j: tempList){
                inDegree[j]++;
            }
        }

        for(int i=0; i<inDegree.length; i++){
            if(inDegree[i] == 0)  queue.add(i);
        }
        List<Integer> ansList = new ArrayList<>();

        while (!queue.isEmpty()){
            int ele = queue.poll();
            ansList.add(ele);
            for(int i: list.get(ele)){
                inDegree[i]--;
                if(inDegree[i] == 0) queue.add(i);
            }
        }

        return ansList.size() != list.size();
    }
}
