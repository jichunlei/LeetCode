package solutions.dp;

/**
 * 最大子序和
 *
 * @author : xianzilei
 * @date : 2020/6/19 13:31
 */
public class Solution53 {
    public static int maxSubArray(int[] nums) {
        //特殊情况下的处理
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //循环求数组的值
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            //dp(i)：以位置i为结尾的连续子序列的和的最大值
            int tmp = dp(nums, i);
            //更新结果
            if (tmp > result) {
                result = tmp;
            }
        }
        //返回结果
        return result;
    }

    //dp(i)：以位置i为结尾的连续子序列的和的最大值
    //状态转移方程
    //(1)dp(0)=nums[0]
    //(2)dp(x)=nums[x]+dp(x-1) 当x>0且dp(x-1)>0
    //(3)dp(x)=nums[x] 当x>0且dp(x-1)<=0
    private static int dp(int[] nums, int index) {
        //dp(0)=nums[0]
        if (index == 0) {
            return nums[0];
        }
        int pre = dp(nums, index - 1);
        return pre > 0 ? pre + nums[index] : nums[index];
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }
}
