package graph;

import java.util.ArrayDeque;
import java.util.Queue;

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


    /*
    * Approach: Boundary DFS
    * 1. Create a visited matrix to track cells that have been visited.
        2. Traverse the boundary cells of the board.
        3. Whenever a boundary cell contains 'O' and is unvisited, call DFS.
        4. In DFS:
           - Mark the current cell as visited.
           - Explore its four adjacent cells: up, left, down, right.
           - Continue DFS for valid, unvisited neighboring cells containing 'O'.
        5. Traverse the entire board again:
           - If a cell contains 'O' and is unvisited, convert it to 'X'.
           - Keep visited 'O' cells unchanged because they are connected to the boundary.
        Why does this work?

        Any 'O' connected to a boundary 'O' cannot be surrounded, so it must remain unchanged. All other 'O' cells are surrounded by 'X' and must be converted.
        3. Complexity comments for your code
        TC: O(M*N) + O(4*M*N) + O(M*N) = O(M*N)

        SC: O(M*N) + O(M*N) = O(M*N)
        * */


    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] visited = new int[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if((i==0 || i== m-1 || j == 0 || j== n-1) && board[i][j] == 'O' && visited[i][j] == 0){
                    dfs(board, visited, i, j, m, n);
                }
            }
        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(board[i][j] == 'O' && visited[i][j] == 0){
                    board[i][j] = 'X';
                }
            }
        }

    }

    public void dfs(char[][] board, int[][] visited, int row, int col, int m, int n){
        visited[row][col] = 1;
        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,-1,0,1};
        for(int i=0; i<4; i++){
            int r = row + delRow[i];
            int c = col + delCol[i];
            if(r>=0 && r<m && c>=0 && c< n && board[r][c] == 'O' && visited[r][c] == 0){
                dfs(board, visited, r, c, m, n);
            }
        }
    }



    /*Approach: Boundary BFS
    1. Create a visited matrix and a queue.
    2. Traverse the board and find all boundary cells containing 'O'.
    3. Mark each such cell as visited and add it to the queue.
    4. While the queue is not empty:
       - Remove a cell from the queue.
       - Check its four adjacent cells: up, left, down, right.
       - If a neighbor is within bounds, contains 'O', and is unvisited, mark it visited and add it to the queue.
    5. Traverse the entire board again:
       - If a cell contains 'O' and is unvisited, change it to 'X'.
       - Leave visited 'O' cells unchanged because they are connected to the boundary.
    Why does this work?

    An 'O' connected to a boundary 'O' cannot be surrounded, so it must remain unchanged. All other 'O' cells are surrounded by 'X' and must be converted.
    */

    //SC: O(M*N) + O(M*N) = O(MN)
    //TC: O(M*N) + O(4MN) + O(MN) = O(MN)
    public void solveBfs(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] visited = new int[m][n];
        Queue<int[]> queue = new ArrayDeque<>();

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if((i==0 || i== m-1 || j == 0 || j== n-1) && board[i][j] == 'O'){
                    queue.add(new int[]{i,j});
                    visited[i][j] = 1;
                }
            }
        }

        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,-1,0,1};

        while(!queue.isEmpty()){
            int[] arr = queue.poll();
            int row = arr[0];
            int col = arr[1];
            for(int i=0; i<4; i++){
                int r = row + delRow[i];
                int c = col + delCol[i];
                if(r>=0 && r<m && c>=0 && c< n && board[r][c] == 'O' && visited[r][c] == 0){
                    visited[r][c] = 1;
                    queue.add(new int[]{r,c});

                }
            }
        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(board[i][j] == 'O' && visited[i][j] == 0){
                    board[i][j] = 'X';
                }
            }
        }

    }
}
