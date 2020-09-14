package solutions.backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * 单词搜索
 *
 * @author : xianzilei
 * @date : 2020/9/14 9:21
 */
public class Solution79 {

    /**
     * 解法一：回溯法
     *
     * @param board 1
     * @param word  2
     * @return boolean
     * @author xianzilei
     * @date 2020/9/14 18:46
     **/
    public boolean exist(char[][] board, String word) {
        //记录已经访问过的节点坐标
        Set<String> memory = new HashSet<>();
        //行
        int row = board.length;
        //列
        int col = board[0].length;
        //从坐标(0,0)开始查找
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //一旦找到一个满足条件的路径，直接返回true，否则从下一个位置重新计算
                if (backtrack(i, j, 0, board, word, memory)) {
                    return true;
                }
                //每次需要清理备忘录
                memory.clear();
            }
        }
        //如果找不到满足条件的路径，返回false
        return false;
    }

    /**
     * @param i      横坐标
     * @param j      纵坐标
     * @param index  字符串索引位置
     * @param board  目标二维数组
     * @param word   目标字符串
     * @param memory 备忘录
     **/
    private boolean backtrack(int i, int j, int index, char[][] board, String word, Set<String> memory) {
        //当字符串全部匹配完，直接返回true
        if (index == word.length()) {
            return true;
        }
        //越界剪枝
        if (i < 0 || i == board.length || j < 0 || j == board[0].length) {
            return false;
        }
        //用于备忘录记忆
        String key = i + "-" + j;
        //已经访问过，需剪枝
        if (memory.contains(key)) {
            return false;
        }
        //如果该坐标出的字符与字符串的字符不匹配，直接返回false
        if (word.charAt(index) != board[i][j]) {
            return false;
        }
        //做选择
        memory.add(key);
        //进入下一层决策树
        //上
        if (backtrack(i - 1, j, index + 1, board, word, memory)) {
            return true;
        }
        //左
        if (backtrack(i, j - 1, index + 1, board, word, memory)) {
            return true;
        }
        //下
        if (backtrack(i + 1, j, index + 1, board, word, memory)) {
            return true;
        }
        //右
        if (backtrack(i, j + 1, index + 1, board, word, memory)) {
            return true;
        }
        //回溯
        memory.remove(key);
        return false;
    }

    public static void main(String[] args) {
        Solution79 solution79 = new Solution79();
        System.out.println(solution79.exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}}, "ABCESEEEFS"));

    }
}
