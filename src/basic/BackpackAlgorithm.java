package basic;

/**
 * 背包问题
 *
 * @author : xianzilei
 * @date : 2020/6/23 08:10
 */
public class BackpackAlgorithm {



    /*===========================0-1背包问题（每个物品只有两种情况：取或不取）：=================================*/
    // 给你一个可装载重量为 W 的背包和 N 个物品，每个物品有重量和价值两个属性。
    // 其中第 i 个物品的重量为 wt[i]，价值为 val[i]，现在让你用这个背包装物品，最多能装的价值是多少？
    //解题思路：
    //f[i][w]：表示前i件物品恰放入一个容量为w的背包可以获得的最大价值
    //f[i][w]=max{f[i-1][w],f[i-1][w-wt[i]]+val[i]}
    //f[0][w]=0  f[i][0]=0

    /**
     * 0-1背包问题（递归写法）
     *
     * @param i   物品个数
     * @param w   背包容量
     * @param wt  每个物品的重量数组
     * @param val 每个物品的价值数组
     * @return int
     * @author xianzilei
     * @date 2020/6/23 8:39
     **/
    public static int backpack01Recursion(int i, int w, int[] wt, int[] val) {
        //边界值的直接返回
        if (w == 0 || i == 0) {
            return 0;
        }
        //如果第i个物品比w都大，只能选择不加入背包
        if (w - wt[i - 1] < 0) {
            //这种情况下只能选择不装入背包
            return backpack01Recursion(i - 1, w, wt, val);
        }
        //backpack01Recursion(i - 1, w, wt, val)：不放入背包
        //backpack01Recursion(i - 1, w - wt[i - 1], wt, val) + val[i - 1])：放入背包
        //返回第i个物品放入背包和不放入背包的最大值
        return Math.max(backpack01Recursion(i - 1, w, wt, val),
                backpack01Recursion(i - 1, w - wt[i - 1], wt, val) + val[i - 1]);
    }

    /**
     * 0-1背包问题（迭代写法）
     *
     * @param i   物品个数
     * @param w   背包容量
     * @param wt  每个物品的重量数组
     * @param val 每个物品的价值数组
     * @return int
     * @author xianzilei
     * @date 2020/6/23 8:41
     **/
    public static int backpack01Iteration(int i, int w, int[] wt, int[] val) {
        //dp[i][w]：表示前i件物品恰放入一个容量为w的背包可以获得的最大价值
        //定义数组保存对应的结果
        int[][] dp = new int[i + 1][w + 1];
        //两层循环自下向上求dp数组的值
        for (int j = 1; j <= i; j++) {
            for (int k = 1; k <= w; k++) {
                //如果第j个物品比w都大，只能选择不加入背包
                if (k - wt[j - 1] < 0) {
                    dp[j][k] = dp[j - 1][k];
                }
                //返回第j个物品放入背包和不放入背包的最大值
                else {
                    dp[j][k] = Math.max(dp[j - 1][k], dp[j - 1][k - wt[j - 1]] + val[j - 1]);
                }
            }
        }
        //返回需要的结果
        return dp[i][w];
    }

    /*===========================完全背包问题（每个物品的个数不限制：0或1或多个）=================================*/
    //--TODO
    public static int backpackCompleteIteration(int i, int w, int[] wt, int[] val) {
        return 0;
    }

    public static void main(String[] args) {
        int N = 4;
        int W = 5;
        int[] wt = {2, 1, 3,2};
        int[] val = {12, 10, 20,15};
        System.out.println(backpack01Recursion(N, W, wt, val));
        System.out.println(backpack01Iteration(N, W, wt, val));
    }
}
