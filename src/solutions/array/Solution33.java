package solutions.array;

/**
 * @author : xianzilei
 * @date : 2019/10/12 08:55
 * @Description: 搜索旋转排序数组
 */
public class Solution33 {

    /**
     * 解法一：二分法（判断逻辑较繁琐）
     *
     * @param nums   1
     * @param target 2
     * @return: int
     * @author : xianzilei
     * @date : 2019/10/14 8:55
     **/
    public static int search1(int[] nums, int target) {
        //如果数组为空，直接返回-1
        if (nums == null || nums.length == 0) {
            return -1;
        }
        //定义起始和截止位置
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            //先将三节点（start、end和mid）排除，省去后面的判断需要考虑等于的情况
            if (target == nums[mid]) {
                return mid;
            }
            if (target == nums[start]) {
                return start;
            }
            if (target == nums[end]) {
                return end;
            }
            //情况一：mid>start
            if (nums[mid] > nums[start]) {
                if ((target > nums[start] && target < nums[mid])) {
                    //向左归约
                    end = mid - 1;
                } else {
                    //向右归约
                    start = mid + 1;
                }
            }
            //情况二：mid<start
            else {
                if (target > nums[start] || target < nums[mid]) {
                    //向左归约
                    end = mid - 1;
                } else {
                    //向右归约
                    start = mid + 1;
                }
            }
        }
        return start == end && nums[start] == target ? start : -1;
    }

    /**
     * 解法二：二分法（使用异或优化判断逻辑）
     *
     * @param nums   1
     * @param target 2
     * @return: int
     * @author : xianzilei
     * @date : 2019/10/14 8:55
     **/
    public static int search2(int[] nums, int target) {
        //定义起止点
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            //使用三个条件的异或，省去繁杂的判断逻辑
            if ((nums[start] > target) ^ (nums[start] > nums[mid]) ^ (target > nums[mid]))
                start = mid + 1;
            else
                end = mid;
        }
        return start == end && nums[start] == target ? start : -1;
    }

    public static void main(String[] args) {
        int[] nums = {5, 1, 2, 3, 4};
        System.out.println(search1(nums, 1));
        System.out.println(search2(nums, 1));
    }
}
