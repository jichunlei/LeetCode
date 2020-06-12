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
        List<List<String>> result = new ArrayList<>();
        String[][] trackTemp = new String[n][n];
        List<String[][]> resultTemp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                trackTemp[i][j] = ".";
            }
        }
        backtrack(n, 0, trackTemp, resultTemp);
        for (String[][] temp : resultTemp) {
            List<String> list = new ArrayList<>(n * n);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    list.add(temp[i][j]);
                }
            }
            result.add(list);
        }
        return result;
    }

    private static void backtrack(int n, int row, String[][] trackTemp, List<String[][]> resultTemp) {
        if (row == n) {
            resultTemp.add(trackTemp);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (!isValid(row, col, trackTemp)) {
                continue;
            }
            trackTemp[row][col] = "Q";
            backtrack(n, row + 1, trackTemp, resultTemp);
            trackTemp[row][col] = ".";
        }

    }

    private static boolean isValid(int row, int col, String[][] trackTemp) {
        int n = trackTemp.length;
        for (int i = 0; i < n; i++) {
            if (i != row && trackTemp[i][col].equals("Q")) {
                return false;
            }
        }
        for (int j = 0; j < n; j++) {
            if (j != col && trackTemp[row][j].equals("Q")) {
                return false;
            }
        }
        for (int i = row + 1, j = col + 1; i < n && j < n; i++, j++) {
            if (trackTemp[i][j].equals("Q")) {
                return false;
            }
        }
        for (int i = row + 1, j = col - 1; i < n && j < n; i++, j--) {
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
