package graph;

public class SurroundedRegions {
    /*
        You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
        Connect: A cell is connected to adjacent cells horizontally or vertically.
        Region: To form a region connect every 'O' cell.
        Surround: A region is surrounded if none of the 'O' cells in that region are on the edge of the board. Such regions are completely enclosed by 'X' cells.
        To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.


        Example 1:
        Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
        Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
        Explanation:
        In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.

        Example 2:
        Input: board = [["X"]]
        Output: [["X"]]


        Constraints:
        m == board.length
        n == board[i].length
        1 <= m, n <= 200
        board[i][j] is 'X' or 'O'.
      */
    public static void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] visited = new int[m][n];
        for(int i=0; i<m; i++){
            int j = 0;
            if(board[i][j] == 'O' && visited[i][j] == 0){
                dfs(board,visited,i,j);
            }
        }
        for(int i=0; i<m; i++){
            int j = n-1;
            if(board[i][j] == 'O' && visited[i][j] == 0){
                dfs(board,visited,i,j);
            }
        }
        for(int j=0; j<n; j++){
            int i = 0;
            if(board[i][j] == 'O' && visited[i][j] == 0){
                dfs(board,visited,i,j);
            }
        }
        for(int j=0; j<n; j++){
            int i = m-1;
            if(board[i][j] == 'O' && visited[i][j] == 0){
                dfs(board,visited,i,j);
            }
        }

        for(int i=1; i<m-1; i++){
            for(int j=1; j<n-1; j++){
                if(board[i][j] == 'O' && visited[i][j] == 0){
                    board[i][j] = 'X';
                }
            }
        }

    }

    public static void dfs(char[][] board, int[][] visited, int row, int col){
        visited[row][col] = 1;
        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,-1,0,1};
        for(int i=0; i<4; i++){
            int r = row+delRow[i];
            int c = col+delCol[i];
            if(r>=0 && r<board.length && c>=0 && c<board[0].length && visited[r][c] == 0 && board[r][c] == 'O'){
                dfs(board, visited, r, c);
            }
        }
    }
}
