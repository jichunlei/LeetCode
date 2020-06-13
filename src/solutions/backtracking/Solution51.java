package solutions.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * N皇后
 *
 * @author : xianzilei
 * @date : 2020/6/12 08:33
 */
public class Solution51 {
    public static List<List<String>> solveNQueens(int n) {
        //特殊情况下的处理
        if (n < 0) {
            return null;
        }
        //结果集
        List<List<String>> result = new ArrayList<>();
        //路径
        String[][] trackTemp = new String[n][n];
        //初始化路径全部为.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                trackTemp[i][j] = ".";
            }
        }
        //回溯操作
        backtrack(n, 0, trackTemp, result);
        //返回结果集
        return result;
    }

    /**
     * @param n         宫格边长
     * @param row       当前放置的行号（0开始）
     * @param trackTemp 临时路径
     * @param result    结果集
     **/
    private static void backtrack(int n, int row, String[][] trackTemp, List<List<String>> result) {
        //触发结束条件（即到达决策树的底层）
        if (row == n) {
            //将结果添加到结果集中
            List<String> list = new ArrayList<>();
            for (int i = 0; i < trackTemp.length; i++) {
                StringBuilder temp = new StringBuilder();
                for (int j = 0; j < trackTemp[i].length; j++) {
                    temp.append(trackTemp[i][j]);
                }
                list.add(temp.toString());
            }
            result.add(list);
            return;
        }

        //循环列，放置每一行的Q
        for (int col = 0; col < n; col++) {
            //剪枝操作
            if (!isValid(row, col, trackTemp)) {
                continue;
            }
            //做选择
            trackTemp[row][col] = "Q";
            //进入下一层决策树
            backtrack(n, row + 1, trackTemp, result);
            //回溯
            trackTemp[row][col] = ".";
        }
    }

    //合法性校验（只需要校验当前位置的上面元素即可）
    private static boolean isValid(int row, int col, String[][] trackTemp) {
        int n = trackTemp.length;
        //当前列校验
        for (int i = 0; i < row; i++) {
            if (i != row && trackTemp[i][col].equals("Q")) {
                return false;
            }
        }
        //当前左上校验
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (trackTemp[i][j].equals("Q")) {
                return false;
            }
        }
        //当前右上校验
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (trackTemp[i][j].equals("Q")) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }
}
