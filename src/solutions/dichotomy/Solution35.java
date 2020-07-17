package solutions.dichotomy;

/**
 * 搜索插入位置
 *
 * @author : xianzilei
 * @date : 2020/7/17 08:08
 */
public class Solution35 {

    /**
     * 解法一：暴力法
     *
     * @param nums   1
     * @param target 2
     * @return int
     * @author xianzilei
     * @date 2020/7/17 8:08
     **/
    public static int searchInsert1(int[] nums, int target) {
        int index = 0;
        while (index < nums.length && nums[index] < target) {
            index++;
        }
        return index;
    }

    /**
     * 解法二：二分法
     *
     * @param nums   1
     * @param target 2
     * @return int
     * @author xianzilei
     * @date 2020/7/17 8:14
     **/
    public static int searchInsert2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int target = 0;
        System.out.println(searchInsert1(nums, target));
        System.out.println(searchInsert2(nums, target));
    }
}
