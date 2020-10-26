package solutions.array;

import java.util.Arrays;

/**
 * 有多少小于当前数字的数字
 *
 * @author : xianzilei
 * @date : 2020/10/26 8:13
 */
public class Solution1365 {

    /**
     * 解法一：常规思路（双层循环）
     *
     * @param nums 1
     * @return int[]
     * @author xianzilei
     * @date 2020/10/26 8:17
     **/
    public int[] smallerNumbersThanCurrent(int[] nums) {
        //双层循环查找个数
        int length = nums.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            int count = 0;
            for (int j = 0; j < length; j++) {
                if (i == j || nums[i] <= nums[j]) {
                    continue;
                }
                count++;
            }
            result[i] = count;
        }
        return result;
    }

    /**
     * 解法二：计数排序
     *
     * @param nums 1
     * @return int[]
     * @author xianzilei
     * @date 2020/10/26 8:18
     **/
    public int[] smallerNumbersThanCurrent2(int[] nums) {
        //考虑到数组的值在0~100之间，因此可以使用101长度的数组作为有序的哈希表，保存数组元素的个数
        //然后再将哈希表的每一位处理为小于等于当前数值的元素个数，即可得出结果

        int length = nums.length;
        int[] result = new int[length];
        //定义数组（有序哈希表）
        int[] countArray = new int[101];
        //初始化哈希表
        for (int num : nums) {
            countArray[num]++;
        }
        //将哈希表的每一位处理为小于等于当前数值的元素个数
        for (int i = 1; i < 101; i++) {
            countArray[i] += countArray[i - 1];
        }
        //计算结果
        for (int i = 0; i < length; i++) {
            result[i] = nums[i] == 0 ? 0 : countArray[nums[i] - 1];
        }
        //返回结果
        return result;
    }

    public static void main(String[] args) {
        Solution1365 solution1365 = new Solution1365();
        System.out.println(Arrays.toString(solution1365.smallerNumbersThanCurrent(new int[]{})));
        System.out.println(Arrays.toString(solution1365.smallerNumbersThanCurrent2(new int[]{})));
    }
}
