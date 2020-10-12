package solutions.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 *
 * @author : xianzilei
 * @date : 2020/10/10 8:38
 */
public class Solution18 {
    /**
     * 解法一：DFS
     *
     * @param nums   1
     * @param target 2
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/10/10 9:06
     **/
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tmpList = new ArrayList<>();
        Arrays.sort(nums);
        dfs(0, 0, tmpList, nums, target, result);
        return result;
    }

    private void dfs(int index, int sum, List<Integer> tmpList, int[] nums, int target, List<List<Integer>> result) {
        if (tmpList.size() == 4) {
            if (sum == target) {
                result.add(new ArrayList<>(tmpList));
            }
            return;
        }
        if (nums.length - index + tmpList.size() < 4) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            tmpList.add(nums[i]);
            dfs(i + 1, sum + nums[i], tmpList, nums, target, result);
            tmpList.remove(tmpList.size() - 1);
        }
    }

    /**
     * 解法二：双指针法
     *
     * @param nums   1
     * @param target 2
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/10/10 10:43
     **/
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        //思路：双指针法，两层for循环固定两个数，另外两个数使用双指针控制

        List<List<Integer>> result = new ArrayList<>();
        //双指针的使用必须保证数组是有序的（重要）
        Arrays.sort(nums);
        //双层for循环
        //外层循环
        for (int i = 0; i < nums.length - 3; i++) {
            //重复元素直接剪枝
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //如果当前情况下的最小值都比目标和大，剪枝
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                continue;
            }
            //如果当前情况下的最大值都比目标值小，剪枝
            if (nums[i] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] < target) {
                continue;
            }
            //内层循环
            for (int j = i + 1; j < nums.length - 2; j++) {
                //重复元素直接剪枝
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                //如果当前情况下的最小值都比目标和大，剪枝
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    continue;
                }
                //如果当前情况下的最大值都比目标值小，剪枝
                if (nums[i] + nums[j] + nums[nums.length - 1] + nums[nums.length - 2] < target) {
                    continue;
                }
                //双指针解法求另外两个数，遍历区间为[j+1,nums.length-1]
                int left = j + 1;
                int right = nums.length - 1;
                //剩余两个数的目标和
                int tmpTarget = target - nums[i] - nums[j];
                //双指针查找
                while (left < right) {
                    int cur = nums[left] + nums[right];
                    //如果当前和等于目标和，直接加入到结果集中
                    if (cur == tmpTarget) {
                        result.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right])));
                        //往后遍历到不相同的下一个数
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        //往后遍历到不相同的下一个数
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (cur < tmpTarget) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution18 solution18 = new Solution18();
        System.out.println(solution18.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        System.out.println(solution18.fourSum(new int[]{-2, -1, -1, 1, 1, 2, 2}, 0));
        System.out.println(solution18.fourSum2(new int[]{1, 0, -1, 0, -2, 2}, 0));
        System.out.println(solution18.fourSum2(new int[]{-2, -1, -1, 1, 1, 2, 2}, 0));
    }
}
