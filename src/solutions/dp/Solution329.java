package solutions.dp;

/**
 * 矩阵中的最长递增路径--TODO
 *
 * @author : xianzilei
 * @date : 2020/7/31
 */
public class Solution329 {
    /**
     * 解法一：dfs+记忆化
     *
     * @param matrix 1
     * @return int
     * @author xianzilei
     * @date 2020/7/31 19:06
     **/
    public static int longestIncreasingPath(int[][] matrix) {
        //特殊情况下的排除
        if (matrix.length == 0) {
            return 0;
        }
        int m = matrix[0].length;
        int n = matrix.length;
        int result = 0;
        //定义记忆数组
        int[][] memory = new int[n][m];
        //遍历每个节点（以每一个节点作为起始位置计算每一种情况下的最大路径和）
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(result, dfs(i, j, m, n, matrix, memory));
            }
        }
        //返回结果
        return result;
    }

    /**
     * 方法定义：以当前位置的点为起点的最大递增路径长度
     *
     * @param i      坐标
     * @param j      坐标
     * @param m      矩阵长
     * @param n      矩阵宽
     * @param matrix 矩阵
     * @param memory 备忘录
     * @return int
     * @author xianzilei
     * @date 2020/7/31 19:11
     **/
    private static int dfs(int i, int j, int m, int n, int[][] matrix, int[][] memory) {
        //如果已经计算过了，直接返回结果
        if (memory[j][i] > 0) {
            return memory[j][i];
        }
        //最少路径为1，即以自己作为起始和终止节点
        int max = 1;
        //如果可以向左走，则该情况下最大路径和为以左边节点作为起始节点的最大路径和+1
        if (i > 0 && matrix[j][i] < matrix[j][i - 1]) {
            max = Math.max(dfs(i - 1, j, m, n, matrix, memory) + 1, max);
        }
        //如果可以向上走，则该情况下最大路径和为以上边节点作为起始节点的最大路径和+1
        if (j > 0 && matrix[j][i] < matrix[j - 1][i]) {
            max = Math.max(dfs(i, j - 1, m, n, matrix, memory) + 1, max);
        }
        //如果可以向右走，则该情况下最大路径和为以右边节点作为起始节点的最大路径和+1
        if (i < m - 1 && matrix[j][i] < matrix[j][i + 1]) {
            max = Math.max(dfs(i + 1, j, m, n, matrix, memory) + 1, max);
        }
        //如果可以向下走，则该情况下最大路径和为以下边节点作为起始节点的最大路径和+1
        if (j < n - 1 && matrix[j][i] < matrix[j + 1][i]) {
            max = Math.max(dfs(i, j + 1, m, n, matrix, memory) + 1, max);
        }
        //记忆当前结果
        memory[j][i] = max;
        //返回结果
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix1 = {{9, 10, 4},
                {6, 6, 8},
                {2, 1, 1}};
        int[][] matrix2 = {{3, 4, 5},
                {3, 9, 6},
                {2, 8, 7}};
        int[][] matrix3 = {{3, 4, 5}};
        System.out.println(longestIncreasingPath(matrix1));
        System.out.println(longestIncreasingPath(matrix2));
        System.out.println(longestIncreasingPath(matrix3));
    }
}
