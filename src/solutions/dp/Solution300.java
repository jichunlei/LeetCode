package solutions.dp;

/**
 * 最长上升子序列--TODO
 *
 * @author : xianzilei
 * @date : 2020/6/17 15:48
 */
public class Solution300 {
    public static int lengthOfLIS(int[] nums) {
        int maxLength =0;
        for (int i = 0; i < nums.length; i++) {
            int tmpMaxLength = 1;
            int tmp = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] > tmp) {
                    tmpMaxLength++;
                    tmp = nums[j];
                }
            }
            maxLength = Math.max(maxLength, tmpMaxLength);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,4};
        System.out.println(lengthOfLIS(nums));
    }
}
