package recursion;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    //Leetcode: 51
    /*
    The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
    Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
    Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

    Example 1:
    Input: n = 4
    Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
    Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above

    Example 2:
    Input: n = 1
    Output: [["Q"]]

    Constraints:
    1 <= n <= 9
    * */


    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> finList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for(int i=0; i<n; i++){
            list.add("....");
        }
        recCheck(n, list, finList, 0);
        return finList;
    }


    public static List<List<String>> recCheck(int n, List<String> currentList, List<List<String>> finList, int row){
        if(row == n){
            finList.add(new ArrayList<>(currentList));
            return finList;
        }
        for(int i=0; i<n; i++){
            if(isPlacingQueenPossible(currentList, row, i)){
                StringBuilder sb = new StringBuilder(currentList.get(row));
                sb.setCharAt(i, 'Q');
                currentList.set(row, sb.toString());

                recCheck(n, currentList, finList, row + 1);

                sb.setCharAt(i, '.');
                currentList.set(row, sb.toString());
            }else{
                break;
            }
        }
        return finList;
    }

    public static boolean isPlacingQueenPossible(List<String> s, int col, int row){
        //Row and Col check
        for(int i=0; i<s.size(); i++){
            if(s.get(row).charAt(i) != '.') return false;
            if(s.get(i).charAt(col) != '.') return false;
        }

        //Diagonal check
        for(int i=0; i<s.size(); i++){
            if(s.get(i).charAt(i) != '.') return  false;
        }

        if(s.size()%2 != 0) {
            for (int i = 0; i < s.size(); i++) {
                if (s.get(i).charAt(s.size() - 1 - i) != '.') return false;
            }
        }else{
            for(int i =1;i<s.size(); i++){
                if(s.get(i).charAt(s.size()-1-i) != '.') return false;
            }
        }
        return true;
    }
}
