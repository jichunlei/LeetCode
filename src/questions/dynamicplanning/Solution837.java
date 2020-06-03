package questions.dynamicplanning;

/**
 * 新21点
 *
 * @author : xianzilei
 * @date : 2020/6/3 08:05
 */
public class Solution837 {

    /**
     * 功能描述: 解法一-动态规划
     *
     * @param N 分数不超过N（0 <= N <= 10000）
     * @param K 获得不少于K分时，停止抽取（0 <= K <= 10000）
     * @param W [1,W]的范围中随机抽取（1 <= W <= 10000）
     * @return double
     * @author xianzilei
     * @date 2020/6/3 8:11
     **/
    public static double new21Game1(int N, int K, int W) {
        if (K == 0) {
            return 1.0;
        }
        //dp[x]：表示得分为x时开始游戏并且获胜的概率，目的求dp[0]
        double[] dp = new double[K + W + 1];
        for (int i = K; i <= N && i < K + W; i++) {
            dp[i] = 1.0;
        }
        for (int i = K - 1; i >= 0; i--) {
            for (int j = 1; j <= W; j++) {
                dp[i] += dp[i + j] / W;
            }
        }
        return dp[0];
    }

    /**
     * 功能描述: 解法二-动态规划（优化）
     *
     * @param N 1
     * @param K 2
     * @param W 3
     * @return double
     * @author xianzilei
     * @date 2020/6/3 8:31
     **/
    public static double new21Game2(int N, int K, int W) {
        if (K == 0) {
            return 1.0;
        }
        double[] dp = new double[K + W + 1];
        for (int i = K; i <= N && i < K + W; i++) {
            dp[i] = 1.0;
        }
        dp[K - 1] = 1.0 * Math.min(N - K + 1, W) / W;
        for (int i = K - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] - (dp[i + W + 1] - dp[i + 1]) / W;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(new21Game1(21, 17, 10));
    }
}
