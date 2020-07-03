package solutions.dp;

/**
 * 分割等和子集
 *
 * @author : xianzilei
 * @date : 2020/6/29 07:57
 */
public class Solution416 {
    /**
     * 解法一：动态规划（0-1背包问题）
     *
     * @param nums 1
     * @return boolean
     * @author xianzilei
     * @date 2020/6/29 8:27
     **/
    public static boolean canPartition1(int[] nums) {
        //先进行特殊下的判断：如果数组为空或者长度小于2，直接返回false
        if (nums == null || nums.length < 2) {
            return false;
        }
        //求和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //如果和为奇数，直接返回false
        if (sum % 2 != 0) {
            return false;
        }
        //求目标值，这样问题就转化成了标准的0-1背包问题
        int partition = sum / 2;
        //0-1背包问题：背包重量为partition，物品有nums.length个，每个价值为nums[i]，每个物品只能使用一次
        //dp[i][j]:表示当只使用前i个物品，背包容量为j时，是否可以恰好装满背包
        //dp[i][0]=true;(0<=i<=nums.length)
        //dp[0][j]=false;(0<j<=partition)
        //dp[i][j]=dp[i-1][j]||dp[i-1][j-nums[i-1]]
        //最终结果：dp[nums.length][partition]
        boolean[][] dp = new boolean[nums.length + 1][partition + 1];
        //初始化初值
        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = true;
        }
        //双层循环填充数组的元素
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= partition; j++) {
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        //返回结果
        return dp[nums.length][partition];
    }

    /**
     * 解法二：动态规划（0-1背包问题：状态压缩）
     *
     * @param nums 1
     * @return boolean
     * @author xianzilei
     * @date 2020/6/29 8:43
     **/
    public static boolean canPartition2(int[] nums) {
        //先进行特殊下的判断：如果数组为空或者长度小于2，直接返回false
        if (nums == null || nums.length < 2) {
            return false;
        }
        //求和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //如果和为奇数，直接返回false
        if (sum % 2 != 0) {
            return false;
        }
        //求目标值，这样问题就转化成了标准的0-1背包问题
        int partition = sum / 2;
        //考虑到状态转移方程为dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
        //即每个元素只与它的上一层元素有关，所以可以使用一维数据存储它的上一层的元素值
        //从而将二维数组转化成一维数组
        boolean[] dp = new boolean[partition + 1];
        //初始化初值，即原二维数组的dp[i][0]
        dp[0] = true;
        //双层循环填充数组的元素，i表示层数
        for (int i = 1; i <= nums.length; i++) {
            //这里j从后往前遍历，因为这样的话j - nums[i - 1]是单调递减的，
            // 一旦不满足if条件可以直接退出该层循环，减少不必要的循环次数，
            // 可以理解为剪枝
            for (int j = partition; j >= 1; j--) {
                if (j - nums[i - 1] >= 0) {
                    dp[j] = dp[j] || dp[j - nums[i - 1]];
                } else {
                    break;
                }
            }
        }
        //返回结果，即原二维数组的dp[nums.length][partition]
        return dp[partition];
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition1(nums));
        System.out.println(canPartition2(nums));
    }

}
