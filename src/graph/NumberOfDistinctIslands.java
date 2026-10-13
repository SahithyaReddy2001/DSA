package graph;

import java.util.*;

public class NumberOfDistinctIslands {
    /*
        Given a grid grid[][] of size n × m, consisting of characters 'L' and 'W', where 'L' represents Land and 'W' represents Water, find the number of distinct islands in the grid. An island is a group of one or more land cells connected horizontally or vertically.
        Two islands are considered distinct if their shapes are different.
        Two islands have the same shape if one can be translated to match the other exactly. Rotation and reflection are not allowed.

        Examples :
        Input: grid[][] = [['L', 'W', 'W'], ['W', 'W', 'L'], ['L', 'W', 'W']]
        Output: 1
        Explanation: The grid contains three islands. All these islands have the same shape (a 1 × 1 block of land), so they are counted as a single distinct island.

        Input: grid[][] = [['L', 'L', 'W', 'L', 'L'], ['L', 'W', 'W', 'W', 'W'], ['W', 'W', 'L', 'W', 'L'], ['L', 'W', 'W', 'L', 'L']]
        Output: 4
        Explanation: There are five islands in the grid. Two islands have the same shape (a 1 × 1 block of land), while the other three have different shapes. Therefore, the number of distinct island shapes is 4.

        Constraints:
        1 ≤ n, m ≤ 500
        grid[i][j] == L or grid[i][j] == W
    **/
    public int countDistinctIslands(char[][] grid) {
        Set<List<List<Integer>>> set = new HashSet<>();
        int m= grid.length;
        int n = grid[0].length;
        int[][] visited = new int[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 'L' && visited[i][j] == 0){
                    set.add(dfs(grid, visited, i, j, new ArrayList(), i, j));
                }
            }
        }



        return set.size();
    }

    public List<List<Integer>> dfs(char[][] grid, int[][] visited, int row, int col, List<List<Integer>> temp, int baseRow, int baseCol){
        visited[row][col] = 1;
        temp.add(Arrays.asList(row-baseRow, col-baseCol));
        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,-1,0,1};
        for(int i=0; i<4; i++){
            int r = row+delRow[i];
            int c = col+delCol[i];
            if(r>=0 && r<grid.length && c>=0 && c<grid[0].length && grid[r][c] == 'L' && visited[r][c] == 0){
                dfs(grid, visited, r, c, temp, baseRow, baseCol);
            }
        }
        return temp;
    }
}
