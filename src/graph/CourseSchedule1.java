package graph;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule1 {
    /*
    * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
        For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
        Return true if you can finish all courses. Otherwise, return false.

        Example 1:
        Input: numCourses = 2, prerequisites = [[1,0]]
        Output: true
        Explanation: There are a total of 2 courses to take.
        To take course 1 you should have finished course 0. So it is possible.

        Example 2:
        Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
        Output: false
        Explanation: There are a total of 2 courses to take.
        To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

        Constraints:
        1 <= numCourses <= 2000
        0 <= prerequisites.length <= 5000
        prerequisites[i].length == 2
        0 <= ai, bi < numCourses
        All the pairs prerequisites[i] are unique.
        */

    public boolean canFinish(int numCourses, int[][] arr) {
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0; i<numCourses; i++){
            list.add(new ArrayList<>());
        }
        for(int i=0; i<arr.length; i++){
            list.get(arr[i][1]).add(arr[i][0]);
        }
        boolean[] visited = new boolean[numCourses];
        boolean[] pathVisited = new boolean[numCourses];

        for(int i=0; i<numCourses; i++){
            if(!visited[i] && dfs(i, visited, pathVisited, list)){
                return false;
            }
        }
        return true;
    }

    public boolean dfs(int ele, boolean[] visited, boolean[] pathVisited, List<List<Integer>> list){
        visited[ele] = true;
        pathVisited[ele] = true;
        for(int i: list.get(ele)){
            if(visited[i] && pathVisited[i]) return true;
            if(!visited[i] && dfs(i, visited, pathVisited, list)) return true;
        }
        pathVisited[ele] = false;
        return false;
    }
}
