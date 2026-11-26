package graph;

import java.util.*;

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


    public static int findCircleNumBfs(int[][] isConnected) {
        int[] visited = new int[isConnected.length+1];
        int count = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=0; i<isConnected.length; i++){
            if(visited[i+1] == 0){
                count++;
                queue.add(i);
                visited[i+1] = 1;
                while(!queue.isEmpty()){
                    int ele = queue.poll();
                    for(int k=0; k<isConnected[0].length; k++){
                        if(isConnected[ele][k] == 1 && visited[k+1] == 0){
                            queue.add(k);
                            visited[k+1] = 1;
                        }
                    }
                }
            }
        }

        return count;
    }





    //################  Using Disjoint sets ############################

    /*
    * 1. Time Complexity (TC)
        - O(N): Initialize parent and size arrays.
        - O(N^2 * α(N)): Traverse the adjacency matrix and perform
          Disjoint Set Union operations (Find + Union).
        - O(N * α(N)): Count the number of ultimate parents.

        Final TC: O(N) + O(N^2 * α(N)) + O(N * α(N))
                  = O(N^2 * α(N))
                  ≈ O(N^2)


       2. Space Complexity (SC)
        - O(N): Parent array.
        - O(N): Size array.
        - O(N): Recursive stack space for findUltimateParent
          (worst case before path compression).

        Final SC: O(N) + O(N) + O(N)
          = O(N)
    */
    public static int findCircleNumDisjointSet(int[][] isConnected) {
        int[] parent = new int[isConnected.length+1];
        int[] size = new int[isConnected.length+1];
        for(int i=1; i<parent.length; i++){
            parent[i] = i;
            size[i] = 1;
        }

        for(int i=0; i<isConnected.length; i++){
            for(int j=i+1; j<isConnected[i].length; j++){
                if(isConnected[i][j] == 1){
                    int n1 = i+1;
                    int n2 = j+1;

                    int upn1 = findUltimateParent(n1, parent);
                    int upn2 = findUltimateParent(n2, parent);

                    if(upn1 == upn2) continue;

                    if(size[upn1] > size[upn2]){
                        size[upn1] += size[upn2];
                        parent[upn2] = upn1;
                    }else{
                        size[upn2] += size[upn1];
                        parent[upn1] = upn2;
                    }
                }
            }
        }

        int count = 0;
        for(int i=1; i<parent.length; i++){
            if(i == parent[i]) count++;
        }

        return count;
    }

    public static int findUltimateParent(int n, int[] arr){
        if(arr[n] == n) return n;
        arr[n] = findUltimateParent(arr[n], arr);
        return arr[n];
    }

}
