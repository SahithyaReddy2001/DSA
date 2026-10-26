package graph;

import java.util.ArrayDeque;
import java.util.Queue;

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


    /*
    * Number of Enclaves — DFS
        Approach
        1. Traverse the grid and check each cell.
        2. If a cell is a boundary cell and contains land (1), start DFS from that cell.
        3. In DFS, mark the current land cell as 0 because it is connected to the boundary and cannot be an enclave.
        4. Explore all 4 directions: up, left, down, right. If a valid neighboring cell contains 1, recursively call DFS on it.
        5. After processing all boundary-connected land cells, traverse the grid again and count the remaining 1s. These are the enclaves.
        Time Complexity (TC)
        O(M * N) + O(4 * M * N) + O(M * N) = O(M * N)
        - Boundary traversal using nested loops: O(M * N).
        - DFS traversal: O(M * N), as each land cell is visited at most once and 4 neighbors are checked.
        - Final counting loop: O(M * N).
        Overall TC: O(M * N).
        Space Complexity (SC)
        O(M * N)
        - DFS recursion stack: O(M * N) in the worst case.
        - No extra visited array is used because the grid itself is modified.
    * */

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


    /*
    * Number of Enclaves (BFS)
        Approach
        1. Traverse the entire grid and check for boundary land cells (1).
        2. Add each boundary land cell to the queue and mark it as 0 immediately to prevent duplicate insertion.
        3. While the queue is not empty:
           - Remove a cell from the queue.
           - Explore its 4 neighbors: up, left, down, right.
           - If a neighbor is within bounds and contains 1, add it to the queue and mark it as 0.
        4. After BFS finishes, count all remaining 1s. These cells cannot reach the boundary and are the enclaves.
        5. Return the count.

        - TC: O(M * N) — boundary scan + BFS traversal + final counting.
        - SC: O(M * N) — BFS queue in the worst case.
        - Visited tracking: Modify the grid itself by marking cells as 0 when enqueuing them.
        *
        * */

    public static int numEnclavesBFS(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        Queue<int[]> queue = new ArrayDeque<>();

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(((i==0 || i==m-1) || (j==0 || j== n-1)) && board[i][j] == 1){
                    queue.add(new int[]{i,j});
                    board[i][j] = 0;
                }
            }
        }

        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,-1,0,1};

        while(!queue.isEmpty()){
            int[] arr = queue.poll();
            for(int i=0; i<4; i++){
                int r = arr[0]+delRow[i];
                int c = arr[1]+delCol[i];
                if(r>=0 && r<m && c>=0 && c<n && board[r][c] == 1){
                    queue.add(new int[]{r,c});
                    board[r][c] = 0;
                }
            }
        }

        int count = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(board[i][j] == 1)
                    count++;
            }
        }
        return count;
    }

}
