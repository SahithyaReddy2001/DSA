package graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class MinimumMultiplicationsToReachEnd {
    /*
    Given two integers, start and end, along with an array of integers arr[]. In one operation, you can multiply the current value by any element from arr[], and then take the result modulo 1000 to obtain a new value.
    Find the minimum steps in which end can be achieved starting from start. If it is not possible to reach end, then return -1.

    Examples :
    Input: arr[] = [2, 5, 7], start = 3, end = 30
    Output: 2
    Explanation:
    Step 1: 3*2 = 6 % 1000 = 6
    Step 2: 6*5 = 30 % 1000 = 30

    Input: arr[] = [3, 4, 65], start = 7, end = 175
    Output: 4
    Explanation:
    Step 1: 7 * 3 = 21 % 1000 = 21
    Step 2: 21 * 3 = 63 % 1000 = 63
    Step 3: 63 * 65 = 4095 % 1000 = 95
    Step 4: 95 * 65 = 6175 % 1000 = 175

    Input: arr[] = [2, 4], start = 3, end = 5
    Output: -1

    Explanation: Starting from 3 and multiplying by 2 or 4 always produces even numbers after the first step. Since 5 is odd, it can never be reached.

    Constraints:
    1 ≤ arr.size(), arr[i] ≤ 103
    0 ≤ start, end < 103
    */

    public static int minSteps(int[] arr, int start, int end) {
        if(start == end) return 0;
        int[] dis = new int[1000];
        for(int i=0; i<1000; i++){
            dis[i] = Integer.MAX_VALUE;
        }
        dis[start] = 0;
        Queue<List<Integer>> queue = new ArrayDeque<>();

        queue.add(Arrays.asList(0,start));

        while(!queue.isEmpty()){
            List<Integer> list = queue.poll();
            int distance = list.getFirst();
            int ele = list.getLast();
            for(int i: arr) {
                int newEle = (ele * i) % 1000;
                if (dis[newEle] > distance + 1) {
                    dis[newEle] = distance + 1;
                    queue.add(Arrays.asList(distance + 1, newEle));
                }
            }
        }

        if(dis[end] == Integer.MAX_VALUE) return -1;
        return dis[end];

    }
}
