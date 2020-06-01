package questions.array;

/**
 * @author : xianzilei
 * @date : 2019/10/15 08:26
 * @Description: 在排序数组中查找元素的第一个和最后一个位置
 */
public class Question34 {

    /**
     * 解法一：二分法
     *
     * @param nums
     * @param target
     * @return: int[]
     * @author : xianzilei
     * @date : 2019/10/15 8:27
     **/
    public int[] searchRange1(int[] nums, int target) {

        int[] result = {-1, -1};
        return result;
    }

    private static int getLeftBound(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        //求左边界
        while (start < end) {
            int mid = start + ((end - start) >> 1);
            if (target == nums[mid]) {
                end = mid;
            } else if (target > nums[mid]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return (nums[start] == target) ? start : -1;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 3, 4, 5};
        int leftBound = getLeftBound(nums, 9);
        System.out.println(leftBound);

    }
}
