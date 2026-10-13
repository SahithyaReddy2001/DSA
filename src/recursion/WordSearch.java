package recursion;

public class WordSearch {
    /*
    * Given an m x n grid of characters board and a string word, return true if word exists in the grid.

    The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

    Example 1:
    Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
    Output: true

    Example 2:
    Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
    Output: true

    Example 3:
    Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
    Output: false

    Constraints:
    m == board.length
    n = board[i].length
    1 <= m, n <= 6
    1 <= word.length <= 15
    board and word consists of only lowercase and uppercase English letters.
    * */
    public static boolean exist(char[][] board, String word) {
        for(int i =0; i<board.length; i++){
            for(int j = 0; j<board[0].length; j++){
                if(board[i][j] == word.charAt(0)){
                    boolean isFound = search(i,j, board, word, 0);
                    if(isFound) return true;
                }
            }
        }
        return false;
    }

    public static boolean search(int i, int j, char[][] board, String word, int index){
        if(index == word.length()) return true;
        if(i < 0 || j < 0 || i == board.length || j == board[0].length || board[i][j] != word.charAt(index))
            return false;
        char c = board[i][j];
        board[i][j] = '#';
        boolean prev = search(i-1, j, board, word, index+1);
        boolean next = search(i+1, j, board, word, index+1);
        boolean up = search(i, j-1, board, word, index+1);
        boolean down = search(i, j+1, board, word, index+1);
        board[i][j] = c;
        return prev || next || up || down;
    }
}
