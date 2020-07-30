package solutions.dp;

/**
 * 整数拆分--TODO
 *
 * @author : xianzilei
 * @date : 2020/7/30
 */
public class Solution343 {

    public static int integerBreak(int n) {
        //状态转移方程dp[x]：表示x的最大乘积（x>=2）
        //dp[2]=1
        //dp[i]=max{dp[i-1]*1,dp[i-2]*2,...,dp[2]*(i-2)}(i>=3)
        //注意dp[x]是至少拆分成2个正整数，根据上面的递推公式则表示至少拆分成3个整数，估缺少只拆分成2个整数的考虑
        //所以dp[i]=max{(i-1)*1,(i-2)*2,...,2*(i-2)}(i>=3)
        //综合得dp[i]=max{dp[i-1]*1,dp[i-2]*2,...,dp[2]*(i-2),(i-1)*1,(i-2)*2,...,2*(i-2)},当i>=3
        //本题结果为dp[n]

        //定义状态转移方程
        int[] dp = new int[n + 1];
        //初始化已知值
        dp[2] = 1;
        //逐步计算数组中的每一个值
        for (int i = 3; i <= n; i++) {
            int max = 0;
            //求取最大值
            for (int j = 1; j <= i - 2; j++) {
                max = Math.max(Math.max(dp[i - j] * j, max), (i - j) * j);
            }
            dp[i] = max;
        }
        //返回结果
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(integerBreak(2));
        System.out.println(integerBreak(10));
    }
}
