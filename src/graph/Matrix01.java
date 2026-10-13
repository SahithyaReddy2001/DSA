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
    //SC: O(N*M) + O(N*M) + O(N*M)
    //TC: O(N*M) + O(N*M*4)
    public static int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] distance = new int[m][n];
        int[][] visited = new int[m][n];
        Queue<List<Integer>> queue = new ArrayDeque<>();
        for(int i =0; i<m; i++){
            for(int j =0; j<n; j++){
                if(mat[i][j] == 0){
                    queue.add(Arrays.asList(i,j,0));
                    visited[i][j] = 1;
                }
            }
        }
        bfs(mat, distance, visited, queue);
        return distance;
    }

    public static void bfs(int[][] mat, int[][] distance, int[][] visited, Queue<List<Integer>> queue){
        while(!queue.isEmpty()){
            List<Integer> list = queue.poll();
            int row = list.get(0);
            int col = list.get(1);
            int dist = list.get(2);
            int[] delRow = {-1, 0, 1, 0};
            int[] delCol = {0, -1, 0, 1};
            for(int i=0; i<4; i++){
                int r = row + delRow[i];
                int c = col + delCol[i];
                if(r >=0 && r<mat.length && c>=0 && c<mat[0].length && visited[r][c] == 0 && mat[r][c] == 1){
                    queue.add(Arrays.asList(r,c,dist+1));
                    visited[r][c] = 1;
                    distance[r][c] = dist+1;
                }
            }
        }
    }


}
