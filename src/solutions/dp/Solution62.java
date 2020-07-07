package solutions.dp;

/**
 * 不同路径
 *
 * @author : xianzilei
 * @date : 2020/7/7 09:53
 */
public class Solution62 {
    /**
     * 解法一：动态规划（自顶向下：递归写法）
     *
     * @param m 1
     * @param n 2
     * @return int
     * @author xianzilei
     * @date 2020/7/7 11:17
     **/
    public static int uniquePaths1(int m, int n) {
        //状态转移方程dp[i][j]：表示从起点到坐标(i,j)位置的路径数
        //dp[0][j]=1;
        //dp[i][0]=1;
        //dp[i][j]=dp[i-1][j]+dp[i][j-1]
        //本题返回结果为dp[m-1][n-1]
        return uniquePathsRecursion(m - 1, n - 1);
    }

    //递归三要素
    //递归方法定义f(i,j)：从起点(0,0)到坐标(i,j)的路径数
    //递归结束条件：i==0或j==0
    //等价关系式：f(i,j)=f(i-1,j)+f(i,j-1)
    private static int uniquePathsRecursion(int i, int j) {
        if (i == 0 || j == 0) {
            return 1;
        }
        return uniquePathsRecursion(i - 1, j) + uniquePathsRecursion(i, j - 1);
    }

    /**
     * 解法二：动态规划（自底向上：迭代写法）
     *
     * @param m 1
     * @param n 2
     * @return int
     * @author xianzilei
     * @date 2020/7/7 11:25
     **/
    public static int uniquePaths2(int m, int n) {
        //定义状态方程
        int[][] dp = new int[m][n];
        //双层循环求二维数组的每一位值
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //边界值处理
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                }
                //非边界的状态转移
                else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        //返回结果
        return dp[m - 1][n - 1];
    }

    /**
     * 解法三：解法二优化（状态压缩）
     *
     * @param m 1
     * @param n 2
     * @return int
     * @author xianzilei
     * @date 2020/7/7 11:37
     **/
    public static int uniquePaths3(int m, int n) {
        //考虑到数组每个元素（非边界）只与其左和上元素有关，可以使用一维数组保存上一行的数据
        int[] dp = new int[m];
        //双层循环更新一维数组
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                //边界值处理
                if (i == 0) {
                    dp[i] = 1;
                }
                //更新一维数组数据
                else {
                    dp[i] = dp[i - 1] + dp[i];
                }
            }
        }
        //返回结果
        return dp[m - 1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths1(7, 3));
        System.out.println(uniquePaths2(7, 3));
        System.out.println(uniquePaths3(7, 3));
    }
}
