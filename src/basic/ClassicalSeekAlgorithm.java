package basic;

/**
 * 经典查找算法
 *
 * @author : xianzilei
 * @date : 2020/7/17 08:32
 */
public class ClassicalSeekAlgorithm {

    /*===================================二分查找======================================***/

    /**
     * 普通的二分查找（数组无重复元素）
     *
     * @param nums   1
     * @param target 2
     * @return int
     * @author xianzilei
     * @date 2020/7/17 10:58
     **/
    public static int binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 6, 7, 9, 12};
        int target = 13;
        System.out.println(binarySearch(nums, target));
    }
}
