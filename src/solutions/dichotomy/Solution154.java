package solutions.dichotomy;

/**
 * 寻找旋转排序数组中的最小值 II
 *
 * @author : xianzilei
 * @date : 2020/7/31
 */
public class Solution154 {
    /**
     * 解法一：常规思路
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/7/31 14:40
     **/
    public static int findMin1(int[] nums) {
        //由于数组没有重复元素，所以找到第一个打破升序规则的元素就直接返回，否则返回第一个元素
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                return nums[i];
            }
        }
        return nums[0];
    }

    /**
     * 解法二：二分法
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/7/31 14:40
     **/
    public static int findMin2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        //二分缩短区间[left,right]，结束条件为left==right
        while (left < right) {
            int mid = left + (right - left) / 2;
            //因为mid总是偏左的，所以每次跟右边界进行比较
            //当mid位置值比右边界大时，说明最小值必然在右区间内，即下次查找的区间为[mid+1,right]
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            }
            //当mid位置值比右边界小时，说明最小值必然在左区间内或mid点处，即下次查找的区间为[left,mid]
            else if (nums[mid] < nums[right]) {
                right = mid;
            }
            //当mid位置值等于右边界时，因为有可能会有重复元素，不好判断目标值到底属于左右区间，
            //但是可以肯定一定不是右边界位置，所以下次查找的区间为[left,right-1]
            else {
                right--;
            }
        }
        //结束条件left==right，该区间只有一个值，即为所求的结果
        return nums[left];
    }

    public static void main(String[] args) {
        System.out.println(findMin1(new int[]{1, 3, 3}));
        System.out.println(findMin1(new int[]{2, 2, 2, 0, 1}));
        System.out.println(findMin1(new int[]{3, 3, 1, 3}));
        System.out.println(findMin2(new int[]{1, 3, 3}));
        System.out.println(findMin2(new int[]{2, 2, 2, 0, 1}));
        System.out.println(findMin2(new int[]{3, 3, 1, 3}));
    }
}
