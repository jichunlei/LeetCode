package solutions.array;

import java.util.Arrays;

/**
 * 移动零
 *
 * @author : xianzilei
 * @date : 2020/11/19 8:33
 */
public class Solution283 {
    /**
     * 解法一：冒泡法
     *
     * @param nums 1
     * @return void
     * @author xianzilei
     * @date 2020/11/19 9:00
     **/
    public void moveZeroes(int[] nums) {
        //从后往前找到第一个不为0的位置
        int endIndex = nums.length - 1;
        while (endIndex >= 0 && nums[endIndex] == 0) {
            endIndex--;
        }
        //如果全部是0，直接返回
        if (endIndex == 0) {
            return;
        }
        //从前往后找到第一个为0的位置
        int startIndex = 0;
        while (startIndex < endIndex && nums[startIndex] != 0) {
            startIndex++;
        }
        //这样我们就可以将数组变动的范围缩减到[startIndex,endIndex]中
        //最大限度地减少数组的移动次数

        //记录当前可以放入非0数的位置
        int j = startIndex;
        //遍历数组
        for (int i = startIndex; i <= endIndex; i++) {
            //如果当前值不为0
            if (nums[i] != 0) {
                //则直接交换i和j位置的数值
                swap(j, i, nums);
                //j右移一位
                j++;
            }
        }
    }

    /**
     * 解法二：双指针法
     *
     * @param nums 1
     * @return void
     * @author xianzilei
     * @date 2020/11/19 9:01
     **/
    public void moveZeroes2(int[] nums) {
        //当前可以存放非0元素的位置（也可以理解为非0元素的个数）
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            //遇到非0元素，则移动到可放置的位置，同时j右移
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        //剩余位置直接写0
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    //交换数组两个未知的值
    private void swap(int i, int j, int[] nums) {
        if (i == j) {
            return;
        }
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public static void main(String[] args) {
        Solution283 solution283 = new Solution283();
        int[] nums = new int[]{0, 0, 0, 0, 1, 3, 1, 1, 2, 5, 0, 0, 0};
        solution283.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
