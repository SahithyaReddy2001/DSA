package graph;

public class NumberOfEnclaves {
    /*
    You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
    A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
    Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.


    Example 1:
    Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
    Output: 3
    Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.

    Example 2:
    Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
    Output: 0
    Explanation: All 1s are either on the boundary or can reach the boundary.

    */

    public static int numEnclaves(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] visited = new int[m][n];
        for(int i=0; i<m; i++){
            int j = 0;
            if(board[i][j] == 1 && visited[i][j] == 0){
                dfs(board,visited,i,j);
            }
        }
        for(int i=0; i<m; i++){
            int j = n-1;
            if(board[i][j] == 1 && visited[i][j] == 0){
                dfs(board,visited,i,j);
            }
        }
        for(int j=0; j<n; j++){
            int i = 0;
            if(board[i][j] == 1 && visited[i][j] == 0){
                dfs(board,visited,i,j);
            }
        }
        for(int j=0; j<n; j++){
            int i = m-1;
            if(board[i][j] == 1 && visited[i][j] == 0){
                dfs(board,visited,i,j);
            }
        }

        int count = 0;
        for(int i=1; i<m-1; i++){
            for(int j=1; j<n-1; j++){
                if(board[i][j] == 1 && visited[i][j] == 0){
                    count++;
                }
            }
        }
        return count;

    }

    public static void dfs(int[][] board, int[][] visited, int row, int col){
        visited[row][col] = 1;
        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,-1,0,1};
        for(int i=0; i<4; i++){
            int r = row+delRow[i];
            int c = col+delCol[i];
            if(r>=0 && r<board.length && c>=0 && c<board[0].length && visited[r][c] == 0 && board[r][c] == 1){
                dfs(board, visited, r, c);
            }
        }
    }
}
