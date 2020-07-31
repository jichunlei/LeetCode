package solutions.dp;

/**
 * 最小路径和
 *
 * @author : xianzilei
 * @date : 2020/7/31
 */
public class Solution64 {
    /**
     * 解法一：动态规划
     *
     * @param grid 1
     * @return int
     * @author xianzilei
     * @date 2020/7/31 17:13
     **/
    public static int minPathSum1(int[][] grid) {
        //定义状态转移方程dp[j][i]：从起点(0,0)到坐标(i,j)的最短距离
        //假设grid数组为int[n][m]
        //dp[0][0]=grid[0][0];
        //dp[0][i]=sum{dp[0][x]}（0<i<m且0<=x<=i）
        //dp[j][0]=sum{dp[y][0]}（0<j<n且0<=y<=j）
        //因为只能向下或者向右走。所以dp[j][i]=min{dp[j][i-1],dp[i][j-1]}+grid[j][i]
        //本题结果为dp[n-1][m-1]

        int m = grid[0].length;
        int n = grid.length;
        //定义状态转移数组
        int[][] dp = new int[n][m];
        //初始化边界值
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        for (int j = 1; j < n; j++) {
            dp[j][0] = grid[j][0] + dp[j - 1][0];
        }
        //循环计算数组中的每一个值
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j][i] = Math.min(dp[j - 1][i], dp[j][i - 1]) + grid[j][i];
            }
        }
        //返回结果
        return dp[n - 1][m - 1];
    }

    /**
     * 解法二：解法一优化（状态压缩）
     *
     * @param grid 1
     * @return int
     * @author xianzilei
     * @date 2020/7/31 17:25
     **/
    public static int minPathSum2(int[][] grid) {
        //状态压缩：由于dp[j][i]只与dp[j - 1][i]和dp[j][i - 1]有关，即只与上一层有关，
        //所以可以将二维数组压缩为一维滚动数组

        int m = grid[0].length;
        int n = grid.length;
        int[] dp = new int[m];
        dp[0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i] = dp[i - 1] + grid[0][i];
        }
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (i == 0) {
                    dp[0] = dp[0] + grid[j][0];
                } else {
                    dp[i] = Math.min(dp[i], dp[i - 1]) + grid[j][i];
                }
            }
        }
        return dp[m - 1];
    }

    public static void main(String[] args) {
        int[][] grid1 = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int[][] grid2 = {{1, 2, 5}, {3, 2, 1}};
        System.out.println(minPathSum1(grid1));
        System.out.println(minPathSum1(grid2));
        System.out.println(minPathSum2(grid1));
        System.out.println(minPathSum2(grid2));
    }
}
