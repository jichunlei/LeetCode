package solutions.dp;

/**
 * 零钱兑换 II--TODO
 *
 * @author : xianzilei
 * @date : 2020/6/24 08:53
 */
public class Solution518 {
    //dp[i][j]：当只使用前i个物品，背包容量为j时，可以装满背包的方案数
    //dp[0][0]=0
    //dp[i][0]=1
    public static int change(int amount, int[] coins) {
        return 0;
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println(change(5, coins));
    }
}
