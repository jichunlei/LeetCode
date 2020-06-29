package solutions.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 零钱兑换 II--TODO
 *
 * @author : xianzilei
 * @date : 2020/6/24 08:53
 */
public class Solution518 {

    /**
     * 解法一：动态规划（递归写法，带备忘录）
     *
     * @param amount 1
     * @param coins  2
     * @return int
     * @author xianzilei
     * @date 2020/6/28 13:09
     **/
    public static int change1(int amount, int[] coins) {
        //求dp[coins.length][amount]
        return dp(coins.length, amount, coins, new HashMap<>());
    }

    //dp[n][amount]：当只使用前n个物品，背包容量为amount时，可以装满背包的方案数
    //dp[0][0]=0
    //dp[i][0]=1 (i>0)
    //dp[i][j]=dp[i-1][j]+dp[i][j-coins[i-1]]
    //本题结果为dp[coins.length][amount]
    private static int dp(int i, int j, int[] coins, Map<String, Integer> memoryMap) {
        if (i == 0 && j == 0) {
            return 1;
        }
        if (i == 0) {
            return 0;
        }
        if (j == 0) {
            return 1;
        }
        String key = i + "-" + j;
        if (memoryMap.containsKey(key)) {
            return memoryMap.get(key);
        }
        int result = 0;
        if (j - coins[i - 1] < 0) {
            result = dp(i - 1, j, coins, memoryMap);
        } else {
            result = dp(i - 1, j, coins, memoryMap) + dp(i, j - coins[i - 1], coins, memoryMap);
        }
        memoryMap.put(key, result);
        return result;
    }

    /**
     * 解法二：动态规划（迭代法）
     *
     * @param amount 1
     * @param coins  2
     * @return int
     * @author xianzilei
     * @date 2020/6/28 13:11
     **/
    public static int change2(int amount, int[] coins) {
        //dp[n][amount]：当只使用前n个物品，背包容量为amount时，可以装满背包的方案数
        //dp[i][0]=1 (0<=i<=n)
        //dp[0][j]=0 (0<j<=amount)
        //dp[i][j]=dp[i-1][j]+dp[i][j-coins[i-1]]
        //定义状态数组
        int[][] dp = new int[coins.length + 1][amount + 1];
        //初始化数组部分初值==>dp[i][0]=1 (0<=i<=n)
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }
        //双层循环，根据方程求去状态数组的值
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                //可能存在的越界处理
                if (j - coins[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                }
                //如果硬币值比当前容量还大，则不选择该硬币
                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        //返回结果
        return dp[coins.length][amount];
    }

    //--TODO--看不懂
    public static int change3(int amount, int[] coins) {
        //dp(x)：必须使用第x个硬币凑出金额x时的方案数
        //dp(0)=1;
        //dp(x)=dp(x-coin[0])+dp(x-coin[1])+..+dp(x-coin[coin.length-1])

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int x = coin; x <= amount; x++) {
                dp[x] += dp[x - coin];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println(change1(5, coins));
        System.out.println(change2(5, coins));
        System.out.println(change3(5, coins));
    }
}
