package graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class FloodFill {
    /*
        You are given an image represented by an m x n grid of integers image, where image[i][j] represents the pixel value of the image. You are also given three integers sr, sc, and color. Your task is to perform a flood fill on the image starting from the pixel image[sr][sc].
        To perform a flood fill:
        Begin with the starting pixel and change its color to color.
        Perform the same process for each pixel that is directly adjacent (pixels that share a side with the original pixel, either horizontally or vertically) and shares the same color as the starting pixel.
        Keep repeating this process by checking neighboring pixels of the updated pixels and modifying their color if it matches the original color of the starting pixel.
        The process stops when there are no more adjacent pixels of the original color to update.
        Return the modified image after performing the flood fill.


        Example 1:
        Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
        Output: [[2,2,2],[2,2,0],[2,0,1]]
        Explanation:
        From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
        Note the bottom corner is not colored 2, because it is not horizontally or vertically connected to the starting pixel.

        Example 2:
        Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
        Output: [[0,0,0],[0,0,0]]
        Explanation:
        The starting pixel is already colored with 0, which is the same as the target color. Therefore, no changes are made to the image.


        Constraints:
        m == image.length
        n == image[i].length
        1 <= m, n <= 50
        0 <= image[i][j], color < 216
        0 <= sr < m
        0 <= sc < n
    */

    /*
    * Approach: DFS (Depth-First Search)
        1. Store the original color of the starting pixel image[sr][sc].
        2. If originalColor == color, return the image because no changes are needed.
        3. Create a visited array of size \(M \times N\).
        4. Call DFS from the starting pixel (sr, sc).
        5. In DFS:
           - Mark the current pixel as visited.
           - Change its color to the new color.
           - Explore all 4 directions: up, left, down, right.
           - If a neighbor is within bounds, unvisited, and has the original color, recursively call DFS on it.
        6. Return the modified image.
        Time Complexity (TC)
        O(M*N)
        - In the worst case, all pixels have the same original color.
        - Each pixel is visited at most once.
        - For each pixel, we check 4 neighbors, which takes constant time.
        Therefore, O(4MN) = O(MN).

        * Space Complexity (SC)
        O(MN) + O(MN) = O(MN)

        Component	Complexity
        visited array	O(MN)
        DFS recursion stack (worst case)	O(MN)
        Total auxiliary space	O(MN)
    * */
    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int iColor = image[sr][sc];
        if(iColor == color) return image;
        int m = image.length;
        int n = image[0].length;

        int[][] visited = new int[m][n];
        dfs(image, visited, sr, sc, color, iColor);
        return image;
    }


    public static void dfs(int[][] image, int[][] visited, int sr, int sc, int color, int iColor){
        visited[sr][sc] = 1;
        image[sr][sc] = color;
        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,-1,0,1};
        for(int i=0; i<4; i++){
            int row = sr + delRow[i];
            int col = sc + delCol[i];
            if(row>=0 && row<image.length && col>=0 && col<image[0].length && visited[row][col] == 0 && image[row][col] == iColor){
                dfs(image, visited, row, col, color, iColor);
            }
        }
    }


    /*Approach: BFS (Breadth-First Search)
        1. Store the original color of the starting pixel image[sr][sc].
        2. If originalColor == color, return the image because no changes are needed.
        3. Create a visited array of size M * N.
        4. Create a queue and add the starting pixel (sr, sc). Mark it as visited immediately.
        5. While the queue is not empty:
           - Remove a pixel (row, col) from the queue.
           - Change its color to the target color.
           - Explore its 4 neighbors: up, left, down, right.
           - If a neighbor is within bounds, unvisited, and has the original color, mark it as visited and add it to the queue.
        6. Return the modified image.
        Time Complexity (TC)
        O(M * N)
        - Each pixel is added to the queue at most once.
        - For each pixel, we check 4 neighbors, taking O(4) time.
        - Total: O(4 * M * N) = O(M * N).
        Space Complexity (SC)
        O(M * N) + O(M * N) = O(M * N)
        - Visited array: O(M * N)
        - BFS queue (worst case): O(M * N)
        - Total auxiliary space: O(M * N)
        */
    public static int[][] floodFillBFS(int[][] image, int sr, int sc, int color) {
        int iColor = image[sr][sc];
        if(iColor == color) return image;
        int m = image.length;
        int n = image[0].length;

        int[][] visited = new int[m][n];
        Queue<List<Integer>> queue = new ArrayDeque<>();
        queue.add(Arrays.asList(sr,sc));
        visited[sr][sc] = 1;

        while(!queue.isEmpty()){
            List<Integer> l = queue.poll();
            int row = l.get(0);
            int col = l.get(1);
            image[row][col] = color;

            int[] delRow = {-1,0,1,0};
            int[] delCol = {0,-1,0,1};
            for(int i=0; i<4; i++){
                int r = row + delRow[i];
                int c = col + delCol[i];
                if(r>=0 && r<image.length && c>=0 && c<image[0].length && visited[r][c] == 0 && image[r][c] == iColor){
                    visited[r][c] = 1;
                    queue.add(Arrays.asList(r,c));
                }
            }

        }
        return image;
    }
}
