package solutions.array;

import java.util.Arrays;

/**
 * @author : xianzilei
 * @date : 2019/10/15 08:26
 * @description: 在排序数组中查找元素的第一个和最后一个位置
 */
public class Solution34 {

    /**
     * 功能描述: 解法一-二分法（闭区间）****强记****
     *
     * @param nums   1
     * @param target 2
     * @return int[]
     * @author xianzilei
     * @date 2020/6/2 16:22
     **/
    public static int[] searchRange1(int[] nums, int target) {

        int[] result = {-1, -1};
        result[0] = getLeftBound1(nums, target);
        result[1] = getRightBound1(nums, target);
        return result;
    }

    //获取左边界（闭区间）
    private static int getLeftBound1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        //求左边界
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (target == nums[mid]) {
                // 收缩右侧边界
                right = mid - 1;
            } else if (target > nums[mid]) {
                //搜索区间为[mid+1,right]
                left = mid + 1;
            } else {
                //搜索区间变为[left,mid-1]
                right = mid - 1;
            }
        }
        //检查left越界问题
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    //获取右边界（闭区间）
    private static int getRightBound1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (target == nums[mid]) {
                //收缩左侧边界
                left = mid + 1;
            } else if (target > nums[mid]) {
                //搜索区间为[mid+1,right]
                left = mid + 1;
            } else {
                //搜索区间为[left,mid-1]
                right = mid - 1;
            }
        }
        //检查right越界的情况
        if (right < 0 || nums[right] != target) {
            return -1;
        }
        return right;
    }

    /**
     * 功能描述: 解法二-二分法（左闭右开区间）
     *
     * @param nums   1
     * @param target 2
     * @return int[]
     * @author xianzilei
     * @date 2020/6/2 16:22
     **/
    public static int[] searchRange2(int[] nums, int target) {
        int[] result = {-1, -1};
        result[0] = getLeftBound2(nums, target);
        result[1] = getRightBound2(nums, target);
        return result;
    }

    //获取左边界（左闭右开区间）
    private static int getLeftBound2(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (target == nums[mid]) {
                // 收缩右侧边界，搜索区域为[left,mid)
                right = mid;
            } else if (target > nums[mid]) {
                //搜索区间为[mid+1,right)
                left = mid + 1;
            } else {
                //搜索区间变为[left,mid)
                right = mid;
            }
        }
        return left;
    }

    //获取右边界（左闭右开区间）
    private static int getRightBound2(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (target == nums[mid]) {
                //收缩左侧边界
                left = mid + 1;
            } else if (target > nums[mid]) {
                //搜索区间为[mid+1,right)
                left = mid + 1;
            } else {
                //搜索区间为[left,mid)
                right = mid;
            }
        }
        return right - 1;
    }


    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
//        System.out.println(getRightBound2(nums, 8));
        System.out.println(Arrays.toString(searchRange1(nums, 10)));
//        System.out.println(Arrays.toString(searchRange2(nums, 8)));

    }
}
