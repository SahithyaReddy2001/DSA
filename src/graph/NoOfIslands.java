package graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class NoOfIslands {
    /*
        Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
        An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

        Example 1:
        Input: grid = [
          ["1","1","1","1","0"],
          ["1","1","0","1","0"],
          ["1","1","0","0","0"],
          ["0","0","0","0","0"]
        ]
        Output: 1

        Example 2:
        Input: grid = [
          ["1","1","0","0","0"],
          ["1","1","0","0","0"],
          ["0","0","1","0","0"],
          ["0","0","0","1","1"]
        ]
        Output: 3

        Constraints:
        m == grid.length
        n == grid[i].length
        1 <= m, n <= 300
        grid[i][j] is '0' or '1'.
    */

    public int numIslands(char[][] grid) {
        int[][] visitedArr = new int[grid.length][grid[0].length];
        int count = 0;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == '1' && visitedArr[i][j] == 0){
                    count++;
                    bfs(grid, visitedArr, i, j);
                }
            }
        }
        return count;
    }

    public void bfs(char[][] grid, int[][] visitedArr, int i, int j){
        Queue<List<Integer>> queue = new ArrayDeque<>();
        queue.add(Arrays.asList(i,j));
        visitedArr[i][j] = 1;
        while(!queue.isEmpty()){
            List<Integer> list = queue.poll();
            int start = list.get(0);
            int end = list.get(1);

            if(start > 0 && visitedArr[start-1][end] == 0 && grid[start-1][end] == '1'){
                visitedArr[start-1][end] = 1;
                queue.add(Arrays.asList(start-1, end));
            }
            if(start < grid.length-1 && visitedArr[start+1][end] == 0 && grid[start+1][end] == '1'){
                visitedArr[start+1][end] = 1;
                queue.add(Arrays.asList(start+1, end));
            }
            if(end > 0 && visitedArr[start][end-1] == 0 && grid[start][end-1] == '1'){
                visitedArr[start][end-1] = 1;
                queue.add(Arrays.asList(start, end-1));
            }
            if(end < grid[0].length-1 && visitedArr[start][end+1] == 0 && grid[start][end+1] == '1'){
                visitedArr[start][end+1] = 1;
                queue.add(Arrays.asList(start, end+1));
            }
        }
    }



    //TC: O(M*N)
    //SC: O(M*N)
    public static int numIslandsDFS(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int visited[][] = new int[m][n];
        int count = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == '1' && visited[i][j] == 0){
                    count++;
                    visited[i][j] = 1;
                    dfs(grid, visited, i, j);
                }
            }
        }
        return count;
    }

    public static void dfs(char[][] grid, int[][] visitedArr, int i, int j){
        int[] dellRow = {-1,0,1,0};
        int[] dellCol = {0,-1,0,1};
        for(int x=0; x< 4; x++){
            int row = i+dellRow[x];
            int col = j+dellCol[x];
            if(row >= 0 && row < grid.length && col >= 0 && col <grid[0].length && grid[row][col] == '1' && visitedArr[row][col] == 0){
                visitedArr[row][col] = 1;
                dfs(grid, visitedArr, row, col);
            }
        }

    }
}
