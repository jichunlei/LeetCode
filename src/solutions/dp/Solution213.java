package solutions.dp;

/**
 * 打家劫舍 II
 *
 * @author : xianzilei
 * @date : 2020/8/5
 */
public class Solution213 {
    /**
     * 解法：动态规划
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/8/5 8:46
     **/
    public int rob(int[] nums) {
        //这道题相对于打家劫舍的问题来说无非多了一个条件（第一个和最后一个不能同时打劫）
        //因此这道题可以转化为求解两个状态转移方程
        //（1）打劫第一个屋子时，状态转移方程dp1[i]（0=<i<=nums.length-2）:打劫范围为[0,i]可以获得的最大打劫金钱数
        //（2）打劫最后一个屋子时，状态转移方程dp2[j]（1=<j<=nums.length-1）:打劫范围为[1,j]可以获得的最大打劫金钱数
        //最终结果为max{dp1[nums.length-2],dp2[nums.length-1]}
        //下面直接使用状态压缩的方式写

        //特殊情况直接排除
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        if (length == 3) {
            return Math.max(Math.max(nums[0], nums[1]), nums[2]);
        }
        //求两种情况下的最大值
        return Math.max(dp(0, length - 2, nums), dp(1, length - 1, nums));
    }

    //求指定范围内的最大盗窃金额
    private int dp(int start, int end, int[] nums) {
        int first = nums[start];
        int second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int tmp = second;
            second = Math.max(second, first + nums[i]);
            first = tmp;
        }
        return second;
    }

    public static void main(String[] args) {
        Solution213 solution213 = new Solution213();
        System.out.println(solution213.rob(new int[]{2, 3, 2}));
        System.out.println(solution213.rob(new int[]{1, 2, 3, 1}));
    }
}
