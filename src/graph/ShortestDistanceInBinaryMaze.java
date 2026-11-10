package graph;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class ShortestDistanceInBinaryMaze {
    /*
    * Given a binary matrix mat[][] containing values 0 and 1, and a source cell src[] and destination cell dest[], find the minimum number of steps required to reach the destination cell from the source cell. From any cell, you can move to its adjacent cells in the up, down, left, and right directions.
    1 represents a traversable cell.
    0 represents a blocked cell that cannot be visited.
    If the destination cannot be reached from the source, return -1.

    Examples:

    Input: mat[][] = [[1, 1, 1, 1], [1, 1, 0, 1], [1, 1, 1, 1], [1, 1, 0, 0], [1, 0, 0, 1]], src[] = [0, 1], dest[] = [2, 2]
    Output: 3
    Explanation: From (0,1), the minimum number of steps to reach (2,2) is 3.

    Input: mat[][] = [[1, 1, 1, 1, 1], [1, 1, 1, 1, 1], [1, 1, 1, 1, 0], [1, 0, 1, 0, 1]], src[] = [0, 0], dest[] = [3, 4]
    Output: -1
    Explanation: From (0,0), the destination (3,4) cannot be reached because all possible paths are blocked by 0 cells, so no valid route exists.

    Constraints:

    1 ≤ mat.size(), mat[i].size() ≤ 500
    0 ≤ mat[i][j] ≤ 1
    The source and destination cells are always inside the given matrix.
     */

    //SC: O(M*N) + O(4*M*N) = O(M*N)
    //TC: O(M*N) + O(M*N) = O(M*N)


    /*
    Problem: Shortest Path in a Binary Matrix using BFS

    Approach:
        1. Initialize the distance matrix:
           - Create a distance matrix of size M*N.
           - Initialize every cell with Integer.MAX_VALUE (infinity).
           - This represents that initially no cell is reachable.

        2. Check the source:
           - If the source cell contains 0, return -1 because it is blocked.
           - Set the source distance to 0.

        3. Initialize the queue:
           - Add the source cell (row, col, distance) to the queue.
           - Use BFS to explore the matrix in all 4 directions:
             Top, Left, Bottom, Right.

        4. Process the queue:
           - Remove the front element from the queue.
           - Extract its row, column and current distance.
           - If the current cell is the destination, stop the traversal.
           - Explore all 4 neighboring cells.
           - Check whether the neighbor:
               a. Is within matrix boundaries.
               b. Contains 1 (not blocked).
               c. Has not been visited yet (distance is MAX_VALUE).
           - Update its distance as current distance + 1.
           - Add the neighbor to the queue.

        5. Return the answer:
           - If the destination distance is still Integer.MAX_VALUE,
             return -1 because no path exists.
           - Otherwise, return the shortest distance.

    Why does this work?
        BFS explores an unweighted graph level by level.
        Therefore, the first time we reach a cell, we have found
        the shortest distance to that cell.

        Since each movement between adjacent cells has a cost of 1,
        BFS guarantees the shortest path from source to destination.

    1. Time Complexity (TC)

        Your calculation:

        O(M*N) + O(M*N) + O(4*M*N)

        The main steps are:
        - O(M*N): Initialize the distance matrix.
        - O(M*N): Initialize all distances to Integer.MAX_VALUE.
        - O(4*M*N): BFS traversal.
            - Each cell is added to the queue at most once.
            - Each cell checks 4 directions.

        Final TC: O(M*N)

    2. Space Complexity (SC)

        Your calculation:

        O(M*N) + O(M*N) + O(1)

        - O(M*N): Distance matrix.
        - O(M*N): Queue in the worst case.
        - O(1): Direction arrays (delRow and delCol).

        Final SC: O(M*N)

        Note:
        - The input matrix is not included in auxiliary space.
        - Each cell is added to the queue at most once.
        - We can use Integer.MAX_VALUE in the distance matrix
          instead of maintaining a separate visited matrix.
*/
    public static int shortestPath(int[][] mat, int[] src, int[] dest) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] dis = new int[m][n];

        if(mat[src[0]][src[1]] == 0) return -1;

        for (int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                dis[i][j] = Integer.MAX_VALUE;
            }
        }

        dis[src[0]][src[1]] = 0;
        Queue<List<Integer>> queue = new ArrayDeque<>();

        queue.add(Arrays.asList(src[0], src[1], 0));
        int[] delRow = new int[]{-1,0,1,0};
        int[] delCol = new int[]{0,-1,0,1};

        while(!queue.isEmpty()){
            List<Integer> list = queue.poll();
            int r = list.getFirst();
            int c = list.get(1);
            int d = list.getLast();
            if(r == dest[0] && c == dest[1]) break;
            for(int i=0; i<4; i++){
                int row = r+delRow[i];
                int col = c+delCol[i];
                int ud = d+1;
                if(row>=0 && row<m && col>=0 && col<n && dis[row][col] > ud && mat[row][col] == 1){
                    dis[row][col] = ud;
                    queue.add(Arrays.asList(row,col, ud));
                }
            }
        }
        if(dis[dest[0]][dest[1]] == Integer.MAX_VALUE)  return -1;
        return dis[dest[0]][dest[1]];

    }
}
