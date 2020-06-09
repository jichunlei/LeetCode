package solutions.array;

import java.util.Arrays;

/**
 * 删除排序数组中的重复项
 *
 * @author : xianzilei
 * @date : 2020/6/1 16:36
 */
public class Solution26 {

    /**
     * 功能描述: 解法一-循环遍历+数组移动
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/6/1 17:06
     **/
    public static int removeDuplicates1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int newLength = nums.length;
        int temp = nums[0];
        for (int i = 1; i < newLength; ) {
            if (nums[i] == temp) {
                newLength--;
                for (int j = i + 1; j < nums.length; j++) {
                    nums[j - 1] = nums[j];
                }
            } else {
                temp = nums[i++];
            }
        }
        return newLength;
    }

    /**
     * 功能描述: 解法二-双指针法
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/6/1 17:06
     **/
    public static int removeDuplicates2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates2(nums));
        System.out.println(Arrays.toString(nums));
    }
}
