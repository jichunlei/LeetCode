package solutions.dp;

/**
 * 不同路径 II
 *
 * @author : xianzilei
 * @date : 2020/7/6 08:19
 */
public class Solution63 {
    /**
     * 动态规划
     *
     * @param obstacleGrid 1
     * @return int
     * @author xianzilei
     * @date 2020/7/6 8:21
     **/
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //状态转移方程dp[i][j]：从起点到位置(i,j)处的路径数
        //dp[0][j]=0或1;(当obstacleGridp[0][0到j-1]存在1时为0，否则为1)
        //dp[i][0]=0或1;(当obstacleGridp[0到i-1][0]存在1时为0，否则为1)
        //dp[i][j]=0;(当obstacleGrid[i][j]=1)
        //dp[i][j]=dp[i-1][j]+dp[i][j-1]

        //特殊情况下的判断
        if (obstacleGrid == null || obstacleGrid.length == 0
                || obstacleGrid[0].length == 0) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        //定义状态数组dp[i][j]：表示从起点到位置(i,j)处的路径数
        int[][] dp = new int[m][n];
        //初始化横向已知值
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }
        //初始化纵向已知值
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                break;
            }
            dp[0][j] = 1;
        }
        //双层循环，从(1,1)处开始求数组中的每个值
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //如果该处数组值为1，即表示该处为障碍
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                }
                //否则进行状态转移
                else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        //返回结果
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] obstacleGrid = {
                {1}
        };
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }
}
