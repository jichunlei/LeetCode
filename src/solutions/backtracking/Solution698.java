package solutions.backtracking;

import java.util.Arrays;

/**
 * 划分为k个相等的子集
 *
 * @author : xianzilei
 * @date : 2020/6/9 13:07
 */
public class Solution698 {

    /**
     * 解法一：回溯法
     *
     * @param nums 1
     * @param k    2
     * @return boolean
     * @author xianzilei
     * @date 2020/8/11 17:08
     **/
    public boolean canPartitionKSubsets(int[] nums, int k) {
        //特殊情况的排除
        if (nums.length == 1 || k == 1) {
            return true;
        }
        if (nums.length < k) {
            return false;
        }
        int sum = 0;
        int max = 0;
        for (int num : nums) {
            sum += num;
            if (max < num) {
                max = num;
            }
        }
        //无法整除直接返回false
        if (sum % k > 0) {
            return false;
        }
        int target = sum / k;
        //数组元素存在比目标值还大的元素直接返回false
        if (max > target) {
            return false;
        }
        //定义数组表示元素的使用情况
        boolean[] used = new boolean[nums.length];
        //递归求解
        return backtracking(0, 0, k, target, nums, used);
    }

    /**
     * @param curBucketSum 当前桶内元素和
     * @param start        数组起始点
     * @param k            剩余未组装完的桶数
     * @param target       需要组装的目标和
     * @param nums         目标数组
     * @param used         数组元素的使用情况
     **/
    private boolean backtracking(int curBucketSum, int start, int k, int target, int[] nums,
                                 boolean[] used) {
        //如果组装完成，直接返回成功
        if (k == 0) {
            return true;
        }
        //如果当前和已经组装成功，直接进行下一个桶的组装
        if (curBucketSum == target) {
            return backtracking(0, 0, k - 1, target, nums, used);
        }
        //遍历数组元素做选择
        for (int i = start; i < nums.length; i++) {
            //如果当前元素没有被使用且组装后的和不超过目标值，则做选择
            if (!used[i] && nums[i] + curBucketSum <= target) {
                //当前元素标识为已使用
                used[i] = true;
                //继续组装桶和，如果组装成功，直接返回成功
                if (backtracking(nums[i] + curBucketSum, i + 1, k, target, nums, used)) {
                    return true;
                }
                //取消选择，即回溯
                used[i] = false;
            }
        }
        //如果都没有组装成功，直接返回false
        return false;
    }

    public static void main(String[] args) {
        Solution698 solution698 = new Solution698();
        int[] nums = {605, 454, 322, 218, 8, 19, 651, 2220, 175, 710, 2666, 350, 252, 2264, 327, 1843};
        int k = 4;
        System.out.println(solution698.canPartitionKSubsets(nums, k));
    }
}
