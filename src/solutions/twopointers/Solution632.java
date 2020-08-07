package solutions.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最小区间
 *
 * @author : xianzilei
 * @date : 2020/8/7
 */
public class Solution632 {
    /**
     * 解法一：多指针法
     *
     * @param nums 1
     * @return int[]
     * @author xianzilei
     * @date 2020/8/7 9:23
     **/
    public int[] smallestRange(List<List<Integer>> nums) {
        int size = nums.size();
        //初始化结果
        int[] result = new int[2];
        result[1] = Integer.MAX_VALUE;
        //定义多个指针，个数与数组的个数相同
        int[] pointers = new int[size];
        //开始指针滑动，每次只滑动最小的数对应的指针
        while (true) {
            //保存当前指针对应的最小数
            int min = Integer.MAX_VALUE;
            //保存当前指针对应的最大数
            int max = Integer.MIN_VALUE;
            //保存当前指针对应的最小数的最大
            int minIndex = 0;
            //求当前指针指向的最大值和最小值
            for (int i = 0; i < size; i++) {
                Integer cur = nums.get(i).get(pointers[i]);
                if (min > cur) {
                    min = cur;
                    minIndex = i;
                }
                if (max < cur) {
                    max = cur;
                }
            }
            //如果所求区间范围小于之前的结果，更新结果
            if (result[1] - result[0] > max - min) {
                result[0] = min;
                result[1] = max;
            }
            //最小位置指针滑动
            pointers[minIndex]++;
            //判断是否滑动到了终点，如果是直接退出循环
            if (pointers[minIndex] >= nums.get(minIndex).size()) {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution632 solution632 = new Solution632();
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(4, 10, 15, 24, 26));
        nums.add(Arrays.asList(0, 9, 12, 20));
        nums.add(Arrays.asList(5, 18, 22, 30));
        System.out.println(Arrays.toString(solution632.smallestRange(nums)));
    }
}
