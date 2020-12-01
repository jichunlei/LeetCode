package solutions.dichotomy;

import java.util.Arrays;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 *
 * @author : xianzilei
 * @date : 2020/12/1 8:16
 */
public class Solution34 {
    /**
     * 解法一：暴力法
     *
     * @param nums   1
     * @param target 2
     * @return int[]
     * @author xianzilei
     * @date 2020/12/1 8:24
     **/
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        //直接遍历数组
        for (int i = 0; i < nums.length; i++) {
            //如果找到目标值
            if (nums[i] == target) {
                //左边界填写，填入左边界
                if (res[0] == -1) {
                    res[0] = i;
                }
                //更新右边界
                res[1] = i;
            }
        }
        //返回结果
        return res;
    }

    /**
     * 解法二：二分法
     *
     * @param nums   1
     * @param target 2
     * @return int[]
     * @author xianzilei
     * @date 2020/12/1 8:25
     **/
    public int[] searchRange2(int[] nums, int target) {
        int[] res = {-1, -1};
        int left = 0;
        int right = nums.length - 1;
        //左边界
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                right = mid - 1;
            }
        }
        if (left < 0 || left >= nums.length || nums[left] != target) {
            return res;
        } else {
            res[0] = left;
        }

        //右边界
        right = nums.length - 1;
        //右边界
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        res[1] = right;
        return res;
    }

    public static void main(String[] args) {
        Solution34 solution34 = new Solution34();
        System.out.println(Arrays.toString(solution34.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
        System.out.println(Arrays.toString(solution34.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
        System.out.println(Arrays.toString(solution34.searchRange(new int[]{}, 0)));
        System.out.println(Arrays.toString(solution34.searchRange2(new int[]{5, 7, 7, 8, 8, 10}, 8)));
        System.out.println(Arrays.toString(solution34.searchRange2(new int[]{5, 7, 7, 8, 8, 10}, 6)));
        System.out.println(Arrays.toString(solution34.searchRange2(new int[]{}, 0)));
        System.out.println(Arrays.toString(solution34.searchRange2(new int[]{1}, 1)));
    }
}
