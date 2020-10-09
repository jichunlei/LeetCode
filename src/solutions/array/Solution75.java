package solutions.array;

import java.util.Arrays;

/**
 * 颜色分类
 *
 * @author : xianzilei
 * @date : 2020/10/9 8:28
 */
public class Solution75 {
    /**
     * 解法一：计数法
     *
     * @param nums 1
     * @return void
     * @author xianzilei
     * @date 2020/10/9 9:11
     **/
    public void sortColors(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        //记录0的个数
        int count0 = 0;
        //记录1的个数
        int count1 = 0;
        //第一次遍历数组，计算0和1的个数
        for (int num : nums) {
            if (num == 0) {
                count0++;
            } else if (num == 1) {
                count1++;
            }
        }
        //第二次遍历数组，根据个数重写数组
        for (int i = 0; i < nums.length; i++) {
            if (i < count0) {
                nums[i] = 0;
            } else if (i >= count0 && i < count0 + count1) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }

    /**
     * 解法二：双指针法
     *
     * @param nums 1
     * @return void
     * @author xianzilei
     * @date 2020/10/9 9:47
     **/
    public void sortColors2(int[] nums) {
        //思路：考虑到0必然在数组的前面，2必然在数组的后面，
        //因此可以遇到0就扔到数组前面，遇到2就扔到数组后面

        if (nums == null || nums.length < 2) {
            return;
        }
        //0的边界
        int position0 = 0;
        //2的边界
        int position2 = nums.length - 1;
        //索引
        int i = position0;
        //遍历的区间始终为[position0,position2]
        while (i <= position2) {
            //如果当前值为0，则需要将当前值扔到数组前面（即交换i和position0位置的数）
            if (nums[i] == 0) {
                swap(i, position0, nums);
                position0++;
                i++;
            }
            //如果当前值为2，则需要将当前值扔到数组前面（即交换i和position2位置的数）
            else if (nums[i] == 2) {
                swap(i, position2, nums);
                position2--;
            }
            //如果当前值为1，则不变化，i值右移
            else {
                i++;
            }
        }
    }

    //交换数组index1和index2位置的值
    private void swap(int index1, int index2, int[] nums) {
        int tmp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmp;
    }

    public static void main(String[] args) {
        Solution75 solution75 = new Solution75();
        int[] nums = {2, 1, 0};
        solution75.sortColors2(nums);
        System.out.println(Arrays.toString(nums));

    }
}
