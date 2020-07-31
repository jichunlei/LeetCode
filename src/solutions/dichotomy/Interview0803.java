package solutions.dichotomy;

/**
 * 魔术索引
 *
 * @author : xianzilei
 * @date : 2020/7/31
 */
public class Interview0803 {
    /**
     * 解法一：直接遍历法
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/7/31 8:48
     **/
    public static int findMagicIndex1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 解法二：分治法（二分）
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/7/31 8:49
     **/
    public static int findMagicIndex2(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    //查询区间[left,right]之间的满足条件的最小索引值，如果不存在则返回-1
    private static int helper(int[] nums, int left, int right) {
        //如果区间没有任何值，直接返回-1
        if (left > right) {
            return -1;
        }
        //二分划分出左右区域，所以查找的范围包含三个部分（区间[left,mid-1],区间[mid+1,right],点mid处）
        int mid = left + (right - left) / 2;
        //因为题目要求取最小的索引值，所以查找的顺序为：左区域->mid点->右区域
        //（1）递归查找左区域
        int leftIndex = helper(nums, left, mid - 1);
        //如果左区域存在，直接返回，否则进行查找下一区域
        if (leftIndex != -1) {
            return leftIndex;
        }
        //（2）查找mid点，满足条件就返回，否则查找下一区域
        if (nums[mid] == mid) {
            return mid;
        }
        //（3）递归查找右区域
        return helper(nums, mid + 1, right);
    }

    public static void main(String[] args) {
        int[] nums = {0, 2, 3, 4, 5};
        System.out.println(findMagicIndex1(nums));
        System.out.println(findMagicIndex2(nums));
    }
}
