package arrays;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

    /*
    * Pascal Tiangle
    * Eg: 1                1
    *     2              1   1
    *     3            1   2   1
    *     4          1   3   3   1
    *     5        1   4   6   4   1
    *     6      1   5   10  10  5   1
    */

    //Q1 Given row number and column number find the element there

    /*
    * If we observe the element in 5th row  3rd element it can be computed using r-1
    *                                                                               C
    *                                                                                 c-1
    * n!/(n-r)! * r! = 4*3*2*1/(2*1)*(2*1) = 6
    * But the above formula will take O(n) + O(n-r) + O(r) time complexity
    * On observation we can see that
    * 4C2 = 4*3/2*1 =6
    * But In computer term it should be 4/1 * 3/2 Because if we do reverse division there might be a case where we get decimals and decimal point is ignored
    * TC: O(N)
    * */

    public static int pascalTriangleElement(int row, int column){
        int ans = 1;
        for (int i=1; i<column; i++) {
            ans = ans * (row - i);
            ans = ans / i;
        }
        return ans;
    }


    //Q2: Print the entire row of pascalTriangle

    /*
    * BruteForce: Use pascalTriangleElement function and get entire row
    * TC: O(N^2)
    * */
    public static List<Integer> pascalTriangleRow(int row){
        List<Integer> list = new ArrayList<>();
        for (int i=1; i<=row; i++) {
            list.add(pascalTriangleElement(row, i));
        }
        return list;
    }


    /*
    * Optimal:
    * If observe Row 6=  1  5  10  10  5  1
    *  leave 1st and last elements they are 1
    *  observe 2nd element 1* (6-1)/1
    *  observe 3rd element 1* (6-1)/1 * (6-2)/2
    *  observe 4th element 1* (6-1)/1 * (6-2)/2 * (6-3)/3
    *  observe 5th element 1* (6-1)/1 * (6-2)/2 * (6-3)/3 * (6-4)/4
    *
    * TC : O(N)
    * */
    public static List<Integer> pascalTriangleRowOptimal(int row){
        List<Integer> list = new ArrayList<>();
        int ans = 1;
        list.add(ans);
        for (int i=1; i<row; i++) {
            ans = ans * (row-i);
            ans = ans/i;
            list.add(ans);
        }
        return list;
    }

    //Q3: Find entire Pascal Triangle

    /*
    * Brute force:
    * Iterate over row count and find each element
    * TC: O(N^3)
    * */
    public static List<List<Integer>> pascalTriangle(int row){
        List<List<Integer>> ansList = new ArrayList<>();
        for (int i=1; i<=row; i++){
            List<Integer> list = new ArrayList<>();
            for (int j=1; j<=i; j++){
                list.add(pascalTriangleElement(i,j));
            }
            ansList.add(list);
        }
        return ansList;
    }


    /*
    * Optimal:
    * Use pascal Triangle Row method
    * TC: O(N^2)
    * */
    public static List<List<Integer>> pascalTriangleOptimal(int row){
        List<List<Integer>> ansList = new ArrayList<>();
        for (int i=1; i<=row; i++){
            ansList.add(pascalTriangleRowOptimal(i));
        }
        return ansList;
    }


}
