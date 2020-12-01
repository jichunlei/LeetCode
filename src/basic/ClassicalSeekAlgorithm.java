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
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 寻找左边界的二分查找
     *
     * @param nums   1
     * @param target 2
     * @return int
     * @author xianzilei
     * @date 2020/12/1 9:09
     **/
    public static int leftBoundSearch(int[] nums, int target) {
        //采用闭区间方式
        int left = 0;
        int right = nums.length - 1;
        //查找范围为[left,right]
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                //下次搜索区间为[mid+1,right]
                left = mid + 1;
            } else if (nums[mid] > target) {
                //下次搜索区间为[left,mid-1]
                right = mid - 1;
            } else {
                //下次搜索区间为[left,mid-1]
                right = mid - 1;
            }
        }
        //处理left的越界情况及target不存在的情况
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    /**
     * 寻找右边界的二分查找
     *
     * @param nums   1
     * @param target 2
     * @return int
     * @author xianzilei
     * @date 2020/12/1 9:09
     **/
    public static int rightBoundSearch(int[] nums, int target) {
        //采用闭区间方式
        int left = 0;
        int right = nums.length - 1;
        //查找范围为[left,right]
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                //下次搜索区间为[mid+1,right]
                left = mid + 1;
            } else if (nums[mid] > target) {
                //下次搜索区间为[left,mid-1]
                right = mid - 1;
            } else {
                //下次搜索区间为[mid+1,right]
                left = mid + 1;
            }
        }
        //处理right的越界情况及target不存在的情况
        if (right < 0 || nums[right] != target) {
            return -1;
        }
        return right;
    }


    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{1, 2, 4, 6, 7, 9, 12}, 6));
        System.out.println(leftBoundSearch(new int[]{1, 2, 2, 2, 2, 9, 12}, 2));
        System.out.println(rightBoundSearch(new int[]{1, 2, 2, 2, 2, 9, 12}, 2));
    }
}
