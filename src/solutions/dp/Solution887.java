package solutions.dp;

/**
 * 鸡蛋掉落
 *
 * @author : xianzilei
 * @date : 2020/6/29 16:49
 */
public class Solution887 {

    /**
     * 解法一：动态规划+备忘录（自顶向下：递归）
     *
     * @param K 1
     * @param N 2
     * @return int
     * @author xianzilei
     * @date 2020/6/30 7:58
     **/
    public static int superEggDrop1(int K, int N) {
        //状态转移方程
        //dp(i,j)：当鸡蛋个数为i个，楼层为j时的最坏情况下的最少扔鸡蛋的次数
        //(1)、dp(i,0)=0;
        //(2)、dp(1,j)=j;
        //(3)、dp(i,j)
        //(3.1)、在k层扔鸡蛋，如果鸡蛋没碎，则dp(i,j)=dp(i,j-k)+1
        //(3.2)、在k层扔鸡蛋，如果鸡蛋碎了，则dp(i,j)=dp(i-1,k-1)+1
        //(3.3)、则在k层下最差情况下的最少扔鸡蛋的次数dp(i,j)=max{dp(i,j-k),dp(i-1,j-1)}+1
        //(3.4)、所以结果取尝试每一层得到的次数的最小值，
        // 即dp(i,j)=min{max{dp(i,j-k),dp(i-1,k-1)}+1|当k位于[1,j]中}

        //备忘录
        int[][] memory = new int[K + 1][N + 1];
        //递归获取结果
        return dp(K, N, memory);
    }

    //dp(i,j)：当鸡蛋个数为i个，楼层为j时的最坏情况下的最少扔鸡蛋的次数
    private static int dp(int i, int j, int[][] memory) {
        //边界值处理
        //dp(1,j)=j
        if (i == 1) {
            return j;
        }
        //dp(i,0)=0
        if (j == 0) {
            return 0;
        }
        //备忘录查找
        if (memory[i][j] > 0) {
            return memory[i][j];
        }
        //初始化结果为int最大值
        int result = Integer.MAX_VALUE;
        //遍历楼层进行计算
        for (int k = 1; k <= j; k++) {
            result = Math.min(result, Math.max(dp(i - 1, k - 1, memory), dp(i, j - k, memory)) + 1);
        }
        //结果保存到备忘录中
        memory[i][j] = result;
        //返回结果
        return result;
    }

    /**
     * 解法二：动态规划（自底向上：迭代）
     *
     * @param K 1
     * @param N 2
     * @return int
     * @author xianzilei
     * @date 2020/6/30 8:32
     **/
    public static int superEggDrop2(int K, int N) {
        //状态数组dp[i][j]：当鸡蛋个数为i个，楼层为j时的最坏情况下的最少扔鸡蛋的次数
        int[][] dp = new int[K + 1][N + 1];
        //初始化边界值：dp(1,j)=j（数组默认值均为0，故dp(i,0)=0无需操作）
        for (int j = 0; j <= N; j++) {
            dp[1][j] = j;
        }
        //状态转移方程：dp(i,j)=min{max{dp(i,j-k),dp(i-1,k-1)}+1|当k位于[1,j]中}
        //根据上述方程计算填充数组值
        for (int i = 2; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                int result = Integer.MAX_VALUE;
                for (int k = 1; k <= j; k++) {
                    result = Math.min(Math.max(dp[i - 1][k - 1], dp[i][j - k]) + 1, result);
                }
                dp[i][j] = result;
            }
        }
        //返回结果
        return dp[K][N];
    }

    /**
     * 解法三：解法二的优化（使用二分法进行计算dp(i,j)的值）
     *
     * @param K 1
     * @param N 2
     * @return int
     * @author xianzilei
     * @date 2020/6/30 15:33
     **/
    public static int superEggDrop3(int K, int N) {
        //状态数组dp[i][j]：当鸡蛋个数为i个，楼层为j时的最坏情况下的最少扔鸡蛋的次数
        int[][] dp = new int[K + 1][N + 1];
        //初始化边界值：dp(1,j)=j（数组默认值均为0，故dp(i,0)=0无需操作）
        for (int j = 0; j <= N; j++) {
            dp[1][j] = j;
        }
        //状态转移方程：dp(i,j)=min{max{dp(i,j-k),dp(i-1,k-1)}+1|当k位于[1,j]中}
        //根据上述方程计算填充数组值
        for (int i = 2; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                //这里利用二分法进行查找，降低复杂度为O(log)
                //初始化左右边界
                int left = 1;
                int right = j;
                //初始化结果为int最大值
                int result = Integer.MAX_VALUE;
                //二分查找，这里采用闭区间查找法
                //这里注意：我们不一定正好等找到碎与不碎的交点（可能交点对应的mid值不是整数），
                // 如果这样，我们可以找到交点附近的整数位置的两个值，取最大值即可
                while (left <= right) {
                    int mid = (left + right) / 2;
                    //broken随着mid是单调递增的
                    int broken = dp[i - 1][mid - 1];
                    //noBroken随着mid是单调递减的
                    int noBroken = dp[i][j - mid];
                    //res = min(max(碎，没碎) + 1)
                    //如果找到结果，直接退出循环
                    if (broken == noBroken) {
                        //更新result
                        result = Math.min(broken + 1, result);
                        break;
                    }
                    //此时需要往左收缩区间，同时更新result
                    else if (broken > noBroken) {
                        right = mid - 1;
                        result = Math.min(broken + 1, result);
                    }
                    //此时需要往右收缩区间，同时更新result
                    else {
                        left = mid + 1;
                        result = Math.min(noBroken + 1, result);
                    }
                }
                //当前结果赋值
                dp[i][j] = result;
            }
        }
        //返回结果
        return dp[K][N];
    }

    /**
     * 解法四：逆向思维（重新定义状态方程）
     *
     * @param K 1
     * @param N 2
     * @return int
     * @author xianzilei
     * @date 2020/6/30 15:36
     **/
    public static int superEggDrop4(int K, int N) {
        //状态转移方程
        //dp(i,j)：当鸡蛋个数为i个，允许尝试扔j次鸡蛋时的最多可以准确测试的楼层数
        //dp(0,j)=0;
        //dp(i,0)=0;
        //dp(i,j)=dp(i,j-1)+dp(i-1,j-1)+1;
        //这里因为扔鸡蛋的次数最大不会超过N次
        //定义状态数组dp[i][j]
        int[][] dp = new int[K + 1][N + 1];
        //双层循环计算数组每一位的值
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1] + 1;
            }
        }
        int result = 0;
        //找到我们需要的结果
        //因为dp[i][j]是双向递增的
        //一旦找到dp[i][j]>=N，即为我们需要的结果
        while (dp[K][result] < N) {
            result++;
        }
        //返回结果
        return result;
    }

    /**
     * 解法五：解法四优化（及时剪枝）
     *
     * @param K 1
     * @param N 2
     * @return int
     * @author xianzilei
     * @date 2020/6/30 16:24
     **/
    public static int superEggDrop5(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        int result = 0;
        ////因为dp[i][j]是双向递增的
        //一旦找到dp[i][j]>=N，即为我们需要的结果
        //这里while来控制计算的终止
        //我们只需要计算到我们需要的那个位置即可
        while (dp[K][result] < N) {
            result++;
            //计算当前层的数据
            for (int i = 1; i <= K; i++) {
                dp[i][result] = dp[i][result - 1] + dp[i - 1][result - 1] + 1;
            }
        }
        return result;
    }

    /**
     * 解法六：解法五优化（状态压缩）
     *
     * @param K 1
     * @param N 2
     * @return int
     * @author xianzilei
     * @date 2020/6/30 17:09
     **/
    public static int superEggDrop6(int K, int N) {
        //考虑到dp[i][j]的值只与它的上一行元素有关
        //因此就可以使用滚动数组的方式，即使用一位数组保存当前节点的上一行的所有数据
        int[] dp = new int[K + 1];
        //初始化结果
        int result = 0;
        while (dp[K] < N) {
            result++;
            //注意这里需要从后往前进行遍历更新该一维数组
            //如果从前往后的话，则会导致dp[i]的数据被更新导致dp[i+1]的数据更新错误
            //该循环结束后dp数组存储当前层的每个位置的值
            for (int i = K; i >= 1; i--) {
                dp[i] = dp[i] + dp[i - 1] + 1;
            }
        }
        //返回结果
        return result;
    }

    public static void main(String[] args) {
        System.out.println(superEggDrop1(6, 5000));
        System.out.println(superEggDrop2(6, 5000));
        System.out.println(superEggDrop3(6, 5000));
        System.out.println(superEggDrop4(6, 5000));
        System.out.println(superEggDrop5(6, 5000));
        System.out.println(superEggDrop6(6, 5000));
    }
}
