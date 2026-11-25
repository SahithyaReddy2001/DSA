package graph;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public record PathWithMinimumEffort() {
    /*
    * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
    A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
    Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

    Example 1:
    Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
    Output: 2
    Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
    This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.

    Example 2:
    Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
    Output: 1
    Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].

    Example 3:
    Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
    Output: 0
    Explanation: This route does not require any effort.

    Constraints:
    rows == heights.length
    columns == heights[i].length
    1 <= rows, columns <= 100
    1 <= heights[i][j] <= 106*/


    //TODO: NEED PROPER REVISION
    public static int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] dist = new int[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[0][0] = 0;
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>((a, b) -> Integer.compare(a.get(0) , b.get(0)));
        queue.add(Arrays.asList(0,0,0));
        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,-1,0,1};
        while(!queue.isEmpty()){
            List<Integer> temp = queue.poll();
            int diff = temp.getFirst();
            int r = temp.get(1);
            int c = temp.get(2);
            if(r == m-1 && c == n-1) return diff;
            for(int i=0; i<4; i++){
                int row = delRow[i] + r;
                int col = delCol[i] + c;
                if(row>=0 && row<m && col >=0 && col<n){
                    int currDist = Math.abs(heights[r][c]-heights[row][col]);
                    int newEffort = Math.max(diff, currDist);

                    if(dist[row][col] > newEffort){
                        queue.add(Arrays.asList(newEffort, row,col));
                        dist[row][col] = newEffort;
                    }
                }
            }
        }

        return 0;
    }
}
