package solutions.dp;

/**
 * 预测赢家
 *
 * @author : xianzilei
 * @date : 2020/9/1 8:10
 */
public class Solution486 {
    /**
     * 解法一：常规思路
     *
     * @param nums 1
     * @return boolean
     * @author xianzilei
     * @date 2020/9/1 9:04
     **/
    public boolean predictTheWinner1(int[] nums) {
        int length = nums.length;
        if (length < 3) {
            return true;
        }
        return dfs(0, length - 1, 0, 0, true, nums);
    }

    private boolean dfs(int left, int right, int score1, int score2, boolean flag, int[] nums) {
        if (left > right) {
            return score1 >= score2;
        }
        if (flag) {
            return dfs(left + 1, right, score1 + nums[left], score2, false, nums)
                    || dfs(left, right - 1, score1 + nums[right], score2, false, nums);
        } else {
            return dfs(left + 1, right, score1, score2 + nums[left], true, nums)
                    && dfs(left, right - 1, score1, score2 + nums[right], true, nums);
        }
    }

    /**
     * 解法二：动态规划
     *
     * @param nums 1
     * @return boolean
     * @author xianzilei
     * @date 2020/9/1 9:07
     **/
    public boolean predictTheWinner2(int[] nums) {
        //状态转移方程dp[i][j]：在数组范围[i,j]内，先手玩家比后手玩家可以获取到的最大分数，注意i<=j
        //（1）当i==j时，区间只能取到一个数，dp[i][j]=nums[i];
        //（2）对于区间[i,j]时，可以选择nums[i],nums[j]，i<j时
        //（2.1）如果选择左端nums[i]，则先手玩家目前手里握着nums[i]分数，后手玩家则可以获得dp[i+1][j]分
        //      则dp[i][j]=nums[i]-dp[i+1][j]
        //（2.2）如果选择右端nums[j]，则先手玩家目前手里握着nums[j]分数，后手玩家则可以获得dp[i][j-1]分
        //      则dp[i][j]=nums[j]-dp[i][j-1]
        //所以汇总得到dp[i][j]=max{nums[i]-dp[i+1][j],nums[j]-dp[i][j-1]}
        //本题所求结果为dp[0][nums.length-1]，判断其是否大于等于0即可

        //特殊情况的排除
        int length = nums.length;
        if (length < 3) {
            return true;
        }
        //定义二维状态数组
        int[][] dp = new int[length][length];
        //初始化已知值
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (i == j) {
                    dp[i][j] = nums[i];
                }
            }
        }
        //遍历二维数值填值
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        //返回结果
        return dp[0][length - 1] >= 0;
    }

    /**
     * 解法三：解法二优化（状态压缩）
     *
     * @param nums 1
     * @return boolean
     * @author xianzilei
     * @date 2020/9/1 13:24
     **/
    public boolean predictTheWinner3(int[] nums) {
        //观察到dp[i][j]只与上一层数据有关，因此可以将二维数组压缩为一维数组

        //特殊情况的排除
        int length = nums.length;
        if (length < 3) {
            return true;
        }
        //定义一维状态数组
        int[] dp = new int[length];
        //初始化已知值
        for (int i = 0; i < length; i++) {
            dp[i] = nums[i];
        }
        //循环求值
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        //返回结果
        return dp[length - 1] >= 0;
    }

    public static void main(String[] args) {
        Solution486 solution486 = new Solution486();
        System.out.println(solution486.predictTheWinner1(new int[]{2, 4, 55, 6, 8}));
        System.out.println(solution486.predictTheWinner2(new int[]{2, 4, 55, 6, 8}));
        System.out.println(solution486.predictTheWinner3(new int[]{2, 4, 55, 6, 8}));
    }
}
