package solutions.twopointers;

import java.util.Arrays;

/**
 * 两数之和 II - 输入有序数组
 *
 * @author : xianzilei
 * @date : 2020/7/30
 */
public class Solution167 {
    /**
     * 解法一：常规思路（双层for循环）
     *
     * @param numbers 1
     * @param target  2
     * @return int[]
     * @author xianzilei
     * @date 2020/7/30 13:29
     **/
    public static int[] twoSum1(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        return new int[]{0, 0};
    }

    /**
     * 解法二：双指针法
     *
     * @param numbers 1
     * @param target  2
     * @return int[]
     * @author xianzilei
     * @date 2020/7/30 13:42
     **/
    public static int[] twoSum2(int[] numbers, int target) {
        //定义左右指针，初始位置分别在数组的起始和结束位置
        int left = 0;
        int right = numbers.length - 1;
        //指针位置调整
        while (left < right) {
            //计算当前和
            int tmp = numbers[left] + numbers[right];
            //如果满足条件直接返回
            if (tmp == target) {
                return new int[]{left + 1, right + 1};
            }
            //如果当前和大于目标值，说明需要减小当前和，即右指针左移来减小和
            else if (tmp > target) {
                right--;
            }
            //如果当前和小于目标值，说明需要增大当前和，即右指针左移来增大和
            else {
                left++;
            }
        }
        //如果没有找到返回0,0
        return new int[]{0, 0};
    }

    public static int[] twoSum3(int[] numbers, int target) {

        return new int[]{0, 0};
    }

    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum1(numbers, target)));
        System.out.println(Arrays.toString(twoSum2(numbers, target)));
    }
}
