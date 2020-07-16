package solutions.dp;

/**
 * 买卖股票的最佳时机 III--TODO
 *
 * @author : xianzilei
 * @date : 2020/7/16 16:22
 */
public class Solution123 {

    /**
     * 解法一：动态规划
     *
     * @param prices 1
     * @return int
     * @author xianzilei
     * @date 2020/7/16 17:46
     **/
    public static int maxProfit1(int[] prices) {
        //解题思路：
        //状态转移方程dp[i][j][k]：第i天持有状态为j时，最多可进行k次交易的最大收益（本题最终答案为dp[n][0][K]）
        //dp[0][0][k]=0;
        //dp[1][0][k]=0;
        //dp[1][1][0]=0;
        //dp[1][1][k]=-prices[0];当k>0
        //dp[i][j][k]：有两种情况
        //（1）dp[i][0][k]：当第i天不持有股票时（注意k以买入为边界点）
        //--当i-1天不持有股票，则dp[i][0][k]=dp[i-1][0][k]
        //--当i-1天不持有股票，第i天卖了，则dp[i][0][k]=dp[i-1][1][k]+prices[i-1]
        //（2）dp[i][1][k]：当第i天持有股票时（注意k以买入为边界点）
        //--当i-1天不持有股票，则第i天是买入，即dp[i][1][k]=dp[i-1][0][k-1]-prices[i-1]
        //--当i-1天持有股票，则第i天是观望，则dp[i][1][k]=dp[i-1][1][k]
        //所以dp[i][0][k]=max{dp[i-1][0][k],dp[i-1][1][k]+prices[i-1]}
        //所以dp[i][1][k]=max{dp[i-1][1][k],dp[i-1][0][k-1]-prices[i-1]}

        int length = prices.length;
        //特殊情况下的排除
        if (length == 0) {
            return 0;
        }
        //定义三维数组
        int[][][] dp = new int[length + 1][2][3];
        //初始化已知值
        dp[1][1][1] = -prices[0];
        dp[1][1][2] = -prices[0];
        //循环求解三维数组的每一位的值
        for (int i = 2; i <= length; i++) {
            for (int k = 1; k <= 2; k++) {
                dp[i][0][k] = Math.max(dp[i - 1][0][k], dp[i - 1][1][k] + prices[i - 1]);
                dp[i][1][k] = Math.max(dp[i - 1][1][k], dp[i - 1][0][k - 1] - prices[i - 1]);
            }
        }
        //返回结果
        return dp[length][0][2];
    }

    /**
     * 解法二：解法一优化（状态压缩）
     *
     * @param prices 1
     * @return int
     * @author xianzilei
     * @date 2020/7/16 17:49
     **/
    public static int maxProfit2(int[] prices) {
        //压缩思路：考虑到dp[i][j][k]只与i-1层数据有关，因此可以压缩为二维数组
        int length = prices.length;
        //特殊情况下的排除
        if (length == 0) {
            return 0;
        }
        //定义三维数组
        int[][] dp = new int[2][3];
        //初始化已知值
        dp[1][1] = -prices[0];
        dp[1][2] = -prices[0];
        //循环求解三维数组的每一位的值
        for (int i = 2; i <= length; i++) {
            for (int k = 1; k <= 2; k++) {
                dp[0][k] = Math.max(dp[0][k], dp[1][k] + prices[i - 1]);
                dp[1][k] = Math.max(dp[1][k], dp[0][k - 1] - prices[i - 1]);
            }
        }
        //返回结果
        return dp[0][2];
    }

    /**
     * 解法三：有限状态机--TODO(看不懂)
     *
     * @param prices 1
     * @return int
     * @author xianzilei
     * @date 2020/7/16 18:13
     **/
    public static int maxProfit3(int[] prices) {
        int length = prices.length;
        //特殊情况下的排除
        if (length == 0) {
            return 0;
        }
        int state1 = -prices[0];
        int state2 = Integer.MIN_VALUE;
        int state3 = Integer.MIN_VALUE;
        int state4 = Integer.MIN_VALUE;

        for (int i = 1; i < length; i++) {
            //第一次买入股票
            state1 = Math.max(state1, -prices[i]);
            //第一次卖出股票
            state2 = Math.max(state2, state1 + prices[i]);
            //第二次买入股票
            state3 = Math.max(state3, state2 - prices[i]);
            //第二次卖出股票
            state4 = Math.max(state4, state3 + prices[i]);
        }
        return Math.max(0, state4);
    }

    public static void main(String[] args) {
        int[] prices = {1, 5, 2, 8, 3, 10};
        System.out.println(maxProfit1(prices));
        System.out.println(maxProfit2(prices));
        System.out.println(maxProfit3(prices));
    }
}
