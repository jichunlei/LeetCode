package questions.array;

/**
 * @author : xianzilei
 * @date : 2019/10/16 08:34
 * @description: 二分查找
 */
public class Question704 {

    /**
     * 写法一
     *
     * @param nums   1
     * @param target 2
     * @return: int
     * @author : xianzilei
     * @date : 2019/10/16 8:36
     **/
    public static int searchBinary1(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("写法一：" + searchBinary1(nums, -1));
    }
}
