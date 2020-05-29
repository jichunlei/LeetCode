package questions.stack;

/**
 * 打家劫舍
 *
 * @author : xianzilei
 * @date : 2020/5/29 17:15
 */
public class Question198 {
    /**
     * 功能描述: 解法一-动态规划
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/5/29 17:17
     **/
    public static int rob1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    /**
     * 功能描述: 解法二-动态规划（优化）
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/5/29 17:17
     **/
    public static int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1};
        int[] nums2 = {2, 7, 9, 3, 1,3};
        System.out.println(rob1(nums1));
        System.out.println(rob2(nums1));
        System.out.println(rob1(nums2));
        System.out.println(rob2(nums2));
    }
}
