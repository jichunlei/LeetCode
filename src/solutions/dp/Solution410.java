package solutions.dp;

import com.sun.javafx.robot.FXRobotImage;

import java.util.Arrays;

/**
 * 分割数组的最大值--TODO
 *
 * @author : xianzilei
 * @date : 2020/7/28
 */
public class Solution410 {
    /**
     * 解法一：动态规划
     *
     * @param nums 1
     * @param m    2
     * @return int
     * @author xianzilei
     * @date 2020/7/29 9:04
     **/
    public static int splitArray1(int[] nums, int m) {
        //状态转移方程dp[i][j]：表示前i个数即nums[0...i−1]之间的数被分成j段，所组成的j个子数组各自和的最大值中的最小值
        //dp[i][j]=min{max{dp[k][j-1],sum(nums[k..i-1])},0<=k<=i-1}
        //因为当j>i不合法，可以初始化dp[i][j]=MAX(当j>i)，这样在遇到这样的情况下最大值始终为MAX，不影响最终取的最小值
        //对于dp[0][0]本身也没有啥意义，可以观察状态转移方程
        //当j=1时，k=0时，会有dp[0][0]和sum[0..i-1]进行比较，该种情况就是这个数组作为一组取最大值，取值肯定是sum[0..i-1]
        //因为数组都是非负整数，可以设置dp[0][0]=0，这样不影响最后的结果输出

        int length = nums.length;
        //定义状态转移数组
        int[][] dp = new int[nums.length + 1][m + 1];
        //给数组填充最大值
        for (int[] args : dp) {
            Arrays.fill(args, Integer.MAX_VALUE);
        }
        //初始化已知值
        dp[0][0] = 0;
        //双层循环求二维数组的每一个值
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= m; j++) {
                //存放临时最小值
                int min = Integer.MAX_VALUE;
                //存放第j组的数组和
                int sum = 0;
                //求解最小值
                for (int k = i - 1; k >= 0; k--) {
                    sum += nums[k];
                    min = Math.min(min, Math.max(dp[k][j - 1], sum));
                }
                //赋值
                dp[i][j] = min;
            }
        }
        //返回结果
        return dp[length][m];
    }

    /**
     * 解法二：贪心+二分
     *
     * @param nums 1
     * @param m 2
     * @return int
     * @author xianzilei
     * @date 2020/7/29 17:24
     **/
    public static int splitArray2(int[] nums, int m) {
        //计算上限（数组全部和）和下限值（数组最大值）
        int max = 0;
        int sum = 0;
        for (int num : nums) {
            if (max < num) {
                max = num;
            }
            sum += num;
        }
        //定义左右边界
        int left = max;
        int right = sum;
        //二分查找（终止条件为left==right，即区间内只有一个值）
        while (left < right) {
            int mid = left + (right - left) / 2;
            //计算在当前的最大分组和的情况下的最小分割的区间个数
            int split = split(nums, mid);
            //如果计算出来的区间个数大于题目要求的区间个数，则表示我们知道的区间和的值小了，整个二分范围取右半部分
            //即下次二分的区间为[mid+1,right]
            if (split > m) {
                left = mid + 1;
            }
            //如果计算出来的区间个数小于题目要求的区间个数，则表示我们知道的区间和的值大了，整个二分范围取左半部分
            //即下次二分的区间为[left,mid-1]
            else if (split < m) {
                right = mid - 1;
            }
            //如果计算出来的区间个数等于题目要求的区间个数，由于我们需要取得最小值，整个二分范围取左半部分+当前mid位置
            //即下次二分的区间为[left,mid]
            else {
                right = mid;
            }
        }
        //返回结果
        return left;
    }

    //根据指定的最大值分割数组，返回分割的区间个数
    private static int split(int[] nums, int maxIntervalSum) {
        //初始化分割区间数
        int split = 1;
        //当前区间和
        int curIntervalSum = 0;
        for (int num : nums) {
            //如果当前区间和大于最大值，则开辟一个新的区间
            if (curIntervalSum + num > maxIntervalSum) {
                //重置当前区间和
                curIntervalSum = 0;
                split++;
            }
            curIntervalSum += num;
        }
        //返回分割的区间数
        return split;
    }

    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 10, 8};
        int m = 2;
        System.out.println(splitArray1(nums, m));
        System.out.println(splitArray2(nums, m));
    }
}
