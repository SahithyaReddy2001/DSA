package graph;

import java.util.*;

public class TopologicalSort {
    //TOPOLOGICAL SORT: ANY LINEAR ORDERING OF VERTICES SUCH THAT IF THERE IS AN EDGE BETWEEN U&V, U APPEAR BEFORE V IN THE ORDERING


    /*
    * Topological Sort Using DFS
    1. Initialize: Create a visited array and an empty stack.
    2. Handle disconnected components: Iterate through every vertex. If it is unvisited, start DFS from it.
    3. Mark visited: Mark the current vertex as visited.
    4. Explore neighbors: Recursively visit every unvisited neighbor.
    5. Push after DFS: Once all neighbors have been processed, push the current vertex onto the stack. This is the key step.
    6. Build the answer: Pop all vertices from the stack to obtain the topological ordering.
    * */
    //SC: O(V+V+V+V) = O(V) (stack+visited+ansList+dfs stack can go upto)
    //TC: O(V+V+(V+E)) = O(V+E) (visited loop + popping from stack + visiting elements through DFS)
    public static List<Integer> topologicalSort(List<List<Integer>> list) {
        Stack<Integer> stack = new Stack<>();
        int[] visited = new int[list.size()];
        for(int i=0; i<visited.length; i++){
            if(visited[i] == 0){
                dfs(stack, visited, list, i);
            }
        }
        List<Integer> ansList = new ArrayList<>();
        while (!stack.isEmpty()){
            ansList.add(stack.pop());
        }
        return ansList;
    }

    public static void dfs(Stack<Integer> stack, int[] visited, List<List<Integer>> list, int ele){
        visited[ele] = 1;
        for(int i: list.get(ele)){
            if(visited[i] == 0){
                dfs(stack, visited, list, i);
            }
        }
        stack.add(ele);
    }



    /* BFS Topological Sort
    This algorithm is called Kahn's Algorithm.
    1. Calculate in-degree: Count the incoming edges for every vertex.
    2. Initialize the queue: Add every vertex whose in-degree is 0.
    3. Process the queue:
       - Remove a vertex and add it to ansList.
       - Visit each outgoing neighbor and decrement its in-degree.
       - If a neighbor's in-degree becomes 0, add it to the queue.
    4. Finish: When the queue becomes empty, check whether all vertices have been added to ansList.*/
    //SC: O(V+V+V) = O(V)
    //TC: O(V+E) + O(V) + O(V+E) = O(V+E)
    public static List<Integer> topologicalSortBFS(List<List<Integer>> list){
        int[] inDegree = new int[list.size()];
        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> ansList = new ArrayList<>();
        for (List<Integer> integers : list) {
            for (int j : integers) {
                inDegree[j]++;
            }
        }

        for (int i=0; i<list.size(); i++){
            if(inDegree[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()){
            int ele = queue.poll();
            ansList.add(ele);
            for(int i: list.get(ele)){
                inDegree[i]--;
                if(inDegree[i] == 0) queue.add(i);
            }
        }

        return ansList;
    }
}
