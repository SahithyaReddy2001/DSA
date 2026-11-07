package graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Matrix01 {
    /*
    Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
    The distance between two cells sharing a common edge is 1.

    Example 1:
    Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
    Output: [[0,0,0],[0,1,0],[0,0,0]]

    Example 2:
    Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
    Output: [[0,0,0],[0,1,0],[1,2,1]]

    Constraints:
    m == mat.length
    n == mat[i].length
    1 <= m, n <= 104
    1 <= m * n <= 104
    mat[i][j] is either 0 or 1.
    There is at least one 0 in mat.
    */


    /*
    * Approach: Multi-Source BFS
        1. Create a distance matrix dis.
        2. Traverse the input matrix:
           - If the cell is 0, add its coordinates and distance 0 to the queue.
           - If the cell is 1, initialize its distance to -1 to indicate that it is unvisited.
        3. Process the queue using BFS.
        4. For each cell, explore its four neighbors: up, left, down, right.
        5. If a neighbor is within bounds, contains 1, and has distance -1:
           - Set its distance to currentDistance + 1.
           - Add it to the queue with its updated distance.
        6. Return dis.

        Time Complexity: O(M * N)
        - Initial traversal to identify all 0 cells: O(M * N).
        - BFS processes each cell at most once because you set dis[r][c] = distance + 1 when adding it to the queue.
        - Each cell checks four neighbors: O(4 * M * N).
        Therefore, total TC is O(M * N) + O(4 * M * N) = O(M * N).

        Space Complexity: O(M * N)
        - dis: O(M * N).
        - queue: O(M * N) in the worst case.
        Therefore, total SC is O(M * N) + O(M * N) = O(M * N).
        */

    public int[][] updateMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dis = new int[m][n];

        Queue<int[]> queue = new ArrayDeque<>();

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 0){
                    queue.add(new int[]{i,j,0});
                }
                if(grid[i][j] == 1){
                    dis[i][j] = -1;
                }
            }
        }


        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,-1,0,1};

        while(!queue.isEmpty()){
            int[] arr = queue.poll();
            int row = arr[0];
            int col = arr[1];
            int distance = arr[2];

            for(int i=0; i<4; i++){
                int r = row+delRow[i];
                int c = col+delCol[i];
                if(r>=0 && r<m && c>=0 && c<n && grid[r][c] == 1 && dis[r][c] == -1){
                    queue.add(new int[]{r,c, distance+1});
                    dis[r][c] = distance+1;
                }
            }
        }

        return dis;
    }



    public int[][] updateMatrixDfs(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] visited = new int[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1){
                    visited[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 0){
                    dfs(grid,visited, i, j, 0);
                }
            }
        }
        return visited;
    }


    public void dfs(int[][] grid, int[][] visited, int row, int col, int dis){
        visited[row][col] = dis;
        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,-1,0,1};
        for(int i=0; i<4; i++){
            int r = delRow[i]+row;
            int c = delCol[i]+col;
            if(r>=0 && r<grid.length && c>=0 && c<grid[0].length && grid[r][c] == 1 && dis+1<visited[r][c]){
                int d = Math.min(dis+1, visited[r][c]);
                dfs(grid, visited, r, c, d);
            }
        }
    }


}
