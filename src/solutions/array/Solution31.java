package solutions.array;

import java.util.Arrays;

/**
 * @author : xianzilei
 * @date : 2019/10/12 07:49
 * @Description: 下一个排列
 */
public class Solution31 {

    /**
     * 尾部扫描法
     *
     * @param nums
     * @return: void
     * @author : xianzilei
     * @date : 2019/10/12 8:47
     **/
    public static void nextPermutation1(int[] nums) {
        //目标位置，标记需要修改的位置，初始化为-1
        int target = -1;
        //从最右往左寻找不满足增序的第一个元素位置
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                target = i - 1;
                break;
            }
        }
        //如果target小于0，表示数组递减，无下一最大值，故只需找最小值（将数组修改为递增即可）
        if (target < 0) {
            reverse(nums, 0, nums.length - 1);
        }
        //否则需要找到对应位置，在其右边找到比大的最小元素（一定存在）
        else {
            //初始化位置为target右边第一位
            int index = target + 1;
            //循环查找右边比target大的最小元素
            while (index < nums.length && nums[target] < nums[index]) {
                index++;
            }
            //交换二者位置的元素
            swap(nums, target, index - 1);
            //将target右边的排列修改为最小，即逆序修改为顺序
            reverse(nums, target + 1, nums.length - 1);
        }

    }

    //交换数组两位置的元素
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //反转数组指定位置的元素
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 5, 5, 4, 3, 7, 7};
        nextPermutation1(nums1);
        System.out.println("解法一：" + Arrays.toString(nums1));
    }
}
