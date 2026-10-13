package graph;

public class NumberOfProvinces {
    /*
        There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
        A province is a group of directly or indirectly connected cities and no other cities outside of the group.
        You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
        Return the total number of provinces.

        Example 1:
        Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
        Output: 2
        Example 2:

        Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
        Output: 3
      */

    //SC: O(N+N)
    //TC: O(N) + O(N^2)
    public static int findCircleNum(int[][] isConnected) {
        int[] visitedArr = new int[isConnected.length+1];
        int count = 0;
        for(int i=0; i<isConnected.length; i++){
            if(visitedArr[i+1] == 0){
                count++;
                dfs(isConnected, i, visitedArr);
            }
        }
        return count;
    }

    public static void dfs(int[][] isConnected, int element, int[] visitedArr){
        visitedArr[element+1] = 1;
        for(int i=0; i<isConnected.length; i++){
            if(isConnected[element][i] == 1 && visitedArr[i+1] == 0){
                dfs(isConnected, i, visitedArr);
            }
        }
    }
}
