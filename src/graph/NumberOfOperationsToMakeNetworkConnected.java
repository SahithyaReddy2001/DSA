package graph;

public class NumberOfOperationsToMakeNetworkConnected {
    /*
        There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi.
        Any computer can reach any other computer directly or indirectly through the network.
        You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.
        Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.

        Example 1:
        Input: n = 4, connections = [[0,1],[0,2],[1,2]]
        Output: 1
        Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.

        Example 2:
        Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
        Output: 2

        Example 3:
        Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
        Output: -1
        Explanation: There are not enough cables.


        Constraints:
        1 <= n <= 105
        1 <= connections.length <= min(n * (n - 1) / 2, 105)
        connections[i].length == 2
        0 <= ai, bi < n
        ai != bi
        There are no repeated connections.
        No two computers are connected by more than one cable.
    */

    /*
    * 1. Time Complexity (TC)
        - O(N): Initialize parent and size arrays.
        - O(M * α(N)): Traverse all M connections and perform
          Find + Union operations using Disjoint Set.
        - O(N): Count the number of connected components.

        Final TC: O(N) + O(M * α(N)) + O(N)
                  = O(N + M * α(N))


       2. Space Complexity (SC)
        - O(N): Parent array.
        - O(N): Size array.
        - O(N): Recursive stack space for findUltimateParent
          (worst case before path compression).

        Final SC: O(N) + O(N) + O(N)
                  = O(N)
    */
    public int makeConnected(int n, int[][] isConnected) {
        int[] parent = new int[n];
        int[] size = new int[n];
        for(int i=0; i<parent.length; i++){
            parent[i] = i;
            size[i] = 1;
        }

        int cables = 0;

        for(int i=0; i<isConnected.length; i++){
            int n1 = isConnected[i][0];
            int n2 = isConnected[i][1];

            int upn1 = findUltimateParent(n1, parent);
            int upn2 = findUltimateParent(n2, parent);

            if(upn1 == upn2) {
                cables++;
                continue;
            }

            if(size[upn1] > size[upn2]){
                size[upn1] += size[upn2];
                parent[upn2] = upn1;
            }else{
                size[upn2] += size[upn1];
                parent[upn1] = upn2;
            }
        }

        int count = 0;
        for(int i=0; i<parent.length; i++){
            if(i == parent[i]) count++;
        }

        if(cables >= count-1) return count-1;

        return -1;
    }

    public static int findUltimateParent(int n, int[] arr){
        if(arr[n] == n) return n;
        arr[n] = findUltimateParent(arr[n], arr);
        return arr[n];
    }
}
