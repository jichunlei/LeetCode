package solutions.dp;

/**
 * 打家劫舍
 *
 * @author : xianzilei
 * @date : 2020/5/29 17:15
 */
public class Solution198 {
    /**
     * 解法一：动态规划
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/5/29 17:17
     **/
    public static int rob1(int[] nums) {
        //状态转移方程dp[i]：打劫范围为[0,i]可以获得的最大打劫金钱数
        //（1）dp[0]=nums[0];
        //（2）dp[1]=max{nums[0],nums[1]};
        //（3）dp[x]（当x>=2时）
        //（3.1）选择打劫x位置时，dp[x]=dp[x-2]+nums[x];
        //（3.2）选择不打劫x位置时，dp[x]=dp[x-1];
        //（3.3）所以dp[x]=Math.max(dp[i-1],dp[i-2]+nums[i]);
        //最终所求结果为dp[nums.length-1]

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
        //定义状态转移方程
        int[] dp = new int[length];
        //初始化已知值
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        //逐步求解数组剩余位置的值
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        //返回结果
        return dp[length - 1];
    }

    /**
     * 解法二：解法一优化（状态压缩）
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/5/29 17:17
     **/
    public static int rob2(int[] nums) {
        //观察到上面的状态转移方程dp[i]只与dp[i-1]和dp[i-2]有关，
        //因此可以将一维数组压缩为2个数，
        //分别保存dp[i-1]和dp[i-2]，每次更新一下这两个数即可

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
        //定义压缩后的两个前置数dp[i-2]和dp[i-1]
        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        //状态转移时同步更新这两个数
        for (int i = 2; i < length; i++) {
            int tmp = second;
            second = Math.max(second, first + nums[i]);
            first = tmp;
        }
        return second;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1};
        int[] nums2 = {2, 7, 9, 3, 1};
        System.out.println(rob1(nums1));
        System.out.println(rob2(nums1));
        System.out.println(rob1(nums2));
        System.out.println(rob2(nums2));
    }
}
