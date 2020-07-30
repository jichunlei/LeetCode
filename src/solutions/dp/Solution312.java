package solutions.dp;

/**
 * 戳气球
 *
 * @author : xianzilei
 * @date : 2020/7/30
 */
public class Solution312 {

    /**
     * 解法：动态规划
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/7/30 13:19
     **/
    public static int maxCoins(int[] nums) {
        //首先将nums数组包装成point数组，其中point.length=nums.length+2
        //假设nums数组长度为n，则point[0]=point[n+1]=1,point[x]=nums[x-1](1<=x<=n)
        //所以本题的问题可以转化为：
        //对于数组则point，戳破0-n+1（不包括0和n+1，即开区间）之间的气球，可以获得的最大硬币数
        //状态转移方程dp[i][j]：表示戳破i-j（不包括i和j，即开区间）之间的气球可以获得的最大硬币数
        //所以本题结果为dp[0][n+1]
        //（1）因为是开区间，所以对于任何i，当j<=i+1时，区间内没有任何气球，所以此时的dp[i][j]=0
        //（2）对于其余的i和j，假设第k位置是最后戳破的气球，则i<k<j
        // 则在k下，dp[i][j]=dp[i][k]+dp[k][j]+point[i]*point[k]*point[j]
        //所以dp[i][j]=max{dp[i][k]+dp[k][j]+point[i]*point[k]*point[j],i<k<j}

        int length = nums.length;
        //特殊情况的排除
        if (length == 0) {
            return 0;
        }
        //将原数组转化成我们需要的数组
        int[] point = new int[length + 2];
        //边界值初始化
        point[0] = point[length + 1] = 1;
        //区间内赋值
        System.arraycopy(nums, 0, point, 1, length);
        //定义状态转移数组
        int[][] dp = new int[length + 2][length + 2];
        //遍历求解（注意这里是由上到下，由右到左进行遍历求值）
        for (int j = 1; j <= length + 1; j++) {
            for (int i = j - 1; i >= 0; i--) {
                int max = 0;
                //遍历k，求取最大值
                for (int k = i + 1; k < j; k++) {
                    //状态转移
                    max = Math.max(max, dp[i][k] + dp[k][j] + point[i] * point[k] * point[j]);
                }
                dp[i][j] = max;
            }
        }
        //返回结果
        return dp[0][length + 1];
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8};
        System.out.println(maxCoins(nums));
    }
}
