package graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class RottenOranges {
    /*
        You are given an m x n grid where each cell can have one of three values:
        0 representing an empty cell,
        1 representing a fresh orange, or
        2 representing a rotten orange.
        Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

        Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

        Example 1:
        Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
        Output: 4

        Example 2:
        Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
        Output: -1
        Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

        Example 3:
        Input: grid = [[0,2]]
        Output: 0
        Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.

        Constraints:
        m == grid.length
        n == grid[i].length
        1 <= m, n <= 10
        grid[i][j] is 0, 1, or 2.
    */

    /*
    * Approach
        1. Traverse the grid:
           - Count fresh oranges (1).
           - Add all initially rotten oranges (2) to the queue with time 0.
        2. Handle edge cases:
           - If there are no rotten oranges but fresh oranges exist, return -1.
           - If there are no fresh oranges, return 0.
        3. Perform multi-source BFS:
           - Remove a cell from the queue and extract its row, column, and time.
           - Update max with the maximum time seen so far.
           - Check its 4 neighbors: up, left, down, right.
           - If a neighbor is in bounds and contains a fresh orange (1), mark it rotten (2), decrement the fresh-orange count, and enqueue it with time + 1.
        4. After BFS:
           - If fresh oranges remain (count > 0), return -1.
           - Otherwise, return max, the total minutes required.
        TC: O(M * N) — initial traversal + BFS.
        SC: O(M * N) — queue in the worst case.
    * */
    public static int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        int count = 0;
        int count2 = 0;
        int max = -1;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1){
                    count++;
                }
                if(grid[i][j] == 2){
                    count2++;
                    queue.add(new int[]{i,j,0});
                }
            }
        }

        if(count2 == 0 && count >0) return -1;
        if(count == 0) return 0;

        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,-1,0,1};

        while(!queue.isEmpty()){
            int[] arr = queue.poll();
            int row = arr[0];
            int col = arr[1];
            int time = arr[2];
            max = Math.max(max, time);

            for(int i=0; i<4; i++){
                int r = row+delRow[i];
                int c = col+delCol[i];
                if(r>=0 && r<m && c>=0 && c<n && grid[r][c] == 1){
                    queue.add(new int[]{r,c, time+1});
                    grid[r][c] = 2;
                    count--;
                }
            }
        }

        if(count >0) return -1;
        return max;
    }
}
