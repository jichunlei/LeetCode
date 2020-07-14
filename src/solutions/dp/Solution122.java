package solutions.dp;

/**
 * 买卖股票的最佳时机 II--TODO
 *
 * @author : xianzilei
 * @date : 2020/7/11 14:00
 */
public class Solution122 {
    /**
     * 解法一：贪心算法
     *
     * @param prices 1
     * @return int
     * @author xianzilei
     * @date 2020/7/11 17:29
     **/
    public static int maxProfit1(int[] prices) {
        //解题思路：绘画出价格随时间的折线图，只需求出所有上升折线的高度之和即可
        int result = 0;
        //遍历数组
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            //贪心做法，只加正数
            if (diff > 0) {
                result += diff;
            }
        }
        //返回结果
        return result;
    }

    public static int maxProfit2(int[] prices) {
        int result = 0;
        //解题思路：
        //状态转移方程dp[i][j]：第i天持有状态为j时的最大收益，j：0-不持有，1-持有
        //
        //返回结果
        return result;
    }

    public static void main(String[] args) {
        int[] prices = {7, 6, 4, 3, 1};
        System.out.println(maxProfit1(prices));
    }
}
