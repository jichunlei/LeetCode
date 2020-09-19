package solutions.backtracking;

import java.util.Arrays;

/**
 * 解数独
 *
 * @author : xianzilei
 * @date : 2020/6/15 12:52
 */
public class Solution37 {

    public void solveSudoku(char[][] board) {
        //回溯（这里不需要接受返回的参数是因为题目已经说明肯定存在唯一解）
        backtrack(board, 0, 0);
    }

    private boolean backtrack(char[][] board, int row, int col) {
        //当列遍历到边界时，进入下一行起始处
        if (col == 9) {
            return backtrack(board, row + 1, 0);
        }
        //当列遍历完最后一行，即找到可行解，直接返回
        if (row == 9) {
            return true;
        }
        //如果存在数字，直接跳过，进入下一步
        if (board[row][col] != '.') {
            return backtrack(board, row, col + 1);
        }

        //循环做选择
        for (char c = '1'; c <= '9'; c++) {
            //剪枝操作
            if (!isValid(board, row, col, c)) {
                continue;
            }
            //做选择
            board[row][col] = c;
            //如果找到一个可行解，直接返回
            if (backtrack(board, row, col + 1)) {
                return true;
            }
            //撤销选择
            board[row][col] = '.';
        }
        //穷举完依然没有找到可行解，表示此路不通，返回false
        return false;
    }

    private boolean isValid(char[][] board, int row, int col, char ch) {
        // 三个方向，任一方向重复，ch就不能放在这个位置
        for (int i = 0; i < 9; i++) {
            // 同一行九个位置已出现 ch
            if (board[row][i] == ch) {
                return false;
            }
            // 同一列九个位置中已出现 ch
            if (board[i][col] == ch) {
                return false;
            }
            // 同一个子数独九个位置中已出现 ch
            if (board[(row / 3) * 3 + i / 3][(col / 3) * 3 + i % 3] == ch) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        Solution37 solution37 = new Solution37();
        solution37.solveSudoku(board);
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
    }
}
