package solutions.dp;

/**
 * 最长上升子序列
 *
 * @author : xianzilei
 * @date : 2020/6/17 15:48
 */
public class Solution300 {

    /**
     * 解法一：动态规划（递归）
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/6/18 7:57
     **/
    public static int lengthOfLIS1(int[] nums) {
        //特殊情况的处理
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //定义结果
        int maxLength = 1;
        //循环找最大值
        for (int i = 0; i < nums.length; i++) {
            //更新最大值
            maxLength = Math.max(maxLength, dp1(nums, i));
        }
        //返回结果
        return maxLength;
    }

    //dp(x)：以x位置结尾的元素的最长上升子序列长度
    //状态转移方程：
    //(1)dp(0)=1;
    //(2)dp(x)=max{dp(y)+1|y<x且nums[y]<nums[x]}
    private static int dp1(int[] nums, int pos) {
        //终止条件
        //dp(0)=1
        if (pos == 0) {
            return 1;
        }
        int index = pos - 1;
        int max = 1;
        //查找pos位置前面的比num[pos]小的元素，求最大值
        for (int i = index; i >= 0; i--) {
            if (nums[i] < nums[pos]) {
                max = Math.max(max, dp1(nums, i) + 1);
            }
        }
        //返回结果
        return max;
    }

    /**
     * 解法二：动态规划（递归+备忘录）
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/6/18 9:01
     **/
    public static int lengthOfLIS2(int[] nums) {
        //特殊情况的处理
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxLength = 1;
        //备忘录，memory[x]=dp(x)
        int[] memory = new int[nums.length];
        //初始化0索引位置为1
        memory[0] = 1;
        //循环找最大值
        for (int i = 0; i < nums.length; i++) {
            //更新最大值
            maxLength = Math.max(maxLength, dp2(nums, i, memory));
        }
        //返回结果
        return maxLength;
    }

    private static int dp2(int[] nums, int pos, int[] memory) {
        //从备忘录查找，如果存在，则直接返回
        if (memory[pos] > 0) {
            return memory[pos];
        }
        int index = pos - 1;
        int max = 1;
        //查找pos位置前面的比num[pos]小的元素，计算最大值
        for (int i = index; i >= 0; i--) {
            if (nums[i] < nums[pos]) {
                max = Math.max(max, dp2(nums, i, memory) + 1);
            }
        }
        //结果保存备忘录中
        memory[pos] = max;
        //返回结果
        return max;
    }

    /**
     * 解法三：动态规划（迭代）
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/6/18 13:15
     **/
    public static int lengthOfLIS3(int[] nums) {
        //dp(x)：以x位置结尾的元素的最长上升子序列长度
        //状态转移方程：
        //(1)dp(0)=1;
        //(2)dp(x)=max{dp(y)+1|y<x且nums[y]<nums[x]}
        //特殊情况的处理
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //dp[x]：以x位置结尾的元素的最长上升子序列长度
        int[] dp = new int[nums.length];
        //初始化dp[0]
        dp[0] = 1;
        //计算dp[x] x>0的值
        for (int i = 1; i < nums.length; i++) {
            int max = 1;
            //查找i位置前面的比num[i]小的元素，计算最大值
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            //填充当前结果
            dp[i] = max;
        }

        //获取dp数组的最大值
        int result = dp[0];
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] > result) {
                result = dp[i];
            }
        }
        //返回结果
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS1(nums));
        System.out.println(lengthOfLIS2(nums));
        System.out.println(lengthOfLIS3(nums));
    }
}
