package solutions.dp;

/**
 * 买卖股票的最佳时机
 *
 * @author : xianzilei
 * @date : 2020/7/10 08:04
 */
public class Solution121 {
    /**
     * 解法一：暴力法
     *
     * @param prices 1
     * @return int
     * @author xianzilei
     * @date 2020/7/10 8:08
     **/
    public static int maxProfit1(int[] prices) {
        //思路：找出后的某个元素与前面某个元素的最大差值（注意后面必须大于前面）,双层循环求解

        //特殊情况的排除
        if (prices == null || prices.length < 2) {
            return 0;
        }
        //初始化结果为0
        int result = 0;
        //双层循环求最大值
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int tmp = prices[j] - prices[i];
                //更新结果
                if (tmp > result) {
                    result = tmp;
                }
            }
        }
        //返回结果
        return result;
    }

    /**
     * 解法二：解法一的优化
     *
     * @param prices 1
     * @return int
     * @author xianzilei
     * @date 2020/7/10 8:23
     **/
    public static int maxProfit2(int[] prices) {
        //特殊情况的排除
        if (prices == null || prices.length < 2) {
            return 0;
        }
        //初始化结果为0
        int result = 0;
        //定义变量min保存前面元素的最小值
        int min = prices[0];
        //循环遍历
        for (int i = 1; i < prices.length; i++) {
            int tmp = prices[i];
            //如果后面的值比当前最小值还小时，更新最小值
            if (min > tmp) {
                min = tmp;
            }
            //否则计算差值，更新result
            else {
                result = Math.max(result, tmp - min);
            }
        }
        //返回结果
        return result;
    }

    /**
     * 解法三：动态规划
     *
     * @param prices 1
     * @return int
     * @author xianzilei
     * @date 2020/7/10 8:47
     **/
    public static int maxProfit3(int[] prices) {
        //解题思路：定义数组存放i位置与i-1位置的差值，然后根据最大子序列和的求解方式进行求解即可
        //最大子序列和：假设上面求出的差分数组为diff[N]（N=prices.length-1）
        //dp[x]：以diff[x]为结尾的最大子序列的和
        //dp[0]=diff[0];
        //dp[i]:i>0
        //--当dp[i-1]>0时，dp[i]=dp[i-1]+diff[i]
        //--否则dp[i]=diff[i]
        //本题结果为dp[x]数组的最大值

        //特殊情况的排除
        if (prices == null || prices.length < 2) {
            return 0;
        }
        //初始化差分数组（注意：数组的长度为prices.length - 1）
        int[] diff = new int[prices.length - 1];
        diff[0] = prices[0];
        for (int i = 0; i < diff.length; i++) {
            diff[i] = prices[i + 1] - prices[i];
        }
        //定义状态转移数组
        int[] dp = new int[diff.length];
        //初始化已知值
        dp[0] = diff[0];
        //初始化结果为0（注意必须初始化为0）
        int result = 0;
        //求数组每一项的值
        for (int i = 1; i < diff.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + diff[i];
            } else {
                dp[i] = diff[i];
            }
            //更新最大值
            if (dp[i] > result) {
                result = dp[i];
            }
        }
        //返回结果
        return result;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit1(prices));
        System.out.println(maxProfit2(prices));
        System.out.println(maxProfit3(prices));
    }
}
