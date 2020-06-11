package solutions.backtracking;

/**
 * 划分为k个相等的子集--TODO
 *
 * @author : xianzilei
 * @date : 2020/6/9 13:07
 */
public class Solution698 {

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums.length == 1 || k == 1) {
            return true;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % k > 0) {
            return false;
        }
        int target = sum / k;
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        System.out.println(canPartitionKSubsets(nums, k));
    }
}
