package solutions.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 零钱兑换
 *
 * @author : xianzilei
 * @date : 2020/6/17 08:23
 */
public class Solution322 {
    /**
     * 解法一：动态规划（递归）
     *
     * @param coins  1
     * @param amount 2
     * @return int
     * @author xianzilei
     * @date 2020/6/17 13:33
     **/
    public static int coinChange1(int[] coins, int amount) {
        //备忘录
        Map<Integer, Integer> memoryMap = new HashMap<>();
        //动态规划
        return dp(coins, amount, memoryMap);
    }

    //dp(x):当amount=x时所需要的最少硬币数量
    //状态转移方程：
    //(1)当x<0 dp(x)=-1;
    //(2)当x=0 dp(x)=0;
    //(3)当x>0 dp(x)=min{dp(x-coin)|coin in coins}+1
    private static int dp(int[] coins, int amount, Map<Integer, Integer> memoryMap) {
        //dp(0)=0
        if (amount == 0) {
            return 0;
        }
        //dp(x)=-1（当x小于0）
        if (amount < 0) {
            return -1;
        }
        //备忘录
        if (memoryMap.containsKey(amount)) {
            return memoryMap.get(amount);
        }
        //初始化结果，由于求最小值，故先初始化为amount + 1（不可能的结果）
        int result = amount + 1;
        //dp(x)=min{dp(x-coin)|coin in coins}+1 (x>0)
        //求各个x-coin前置状态下的最小值
        for (int coin : coins) {
            //求dp(x-coin)的值
            int tmp = dp(coins, amount - coin, memoryMap);
            //如果返回-1，表示该前置状态无解，直接跳过
            if (tmp == -1) {
                continue;
            }
            //求最小值
            result = Math.min(tmp + 1, result);
        }
        //结果记录备忘录
        memoryMap.put(amount, result == amount + 1 ? -1 : result);
        //返回结果
        return memoryMap.get(amount);
    }

    /**
     * 解法二：迭代法
     *
     * @param coins  1
     * @param amount 2
     * @return int
     * @author xianzilei
     * @date 2020/6/17 14:07
     **/
    public static int coinChange2(int[] coins, int amount) {
        //特殊情况处理
        if (amount < 0) {
            return -1;
        }
        //特殊情况处理
        if (amount == 0) {
            return 0;
        }
        //dp[x]：amount为x时所需要的最少硬币数
        int[] dp = new int[amount + 1];
        //填充amount + 1（不可能的结果）数据
        Arrays.fill(dp, amount + 1);
        //初始化dp[0]
        dp[0] = 0;
        //循环计算dp[x]的数据
        for (int i = 0; i <= amount; i++) {
            //当前i位置的值（最小值）
            for (int coin : coins) {
                //如果当前i小于硬币值，改分支无解，直接跳过
                if (i < coin) {
                    continue;
                }
                //更新dp[i]值（dp[x]在x<i的情况下均已知了）
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        //返回结果（如果dp[amount]没有变化，直接返回无解-1，否则返回dp[amount]）
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {2};
        int amount = 3;
        System.out.println(coinChange1(coins, amount));
        System.out.println(coinChange2(coins, amount));
    }
}
