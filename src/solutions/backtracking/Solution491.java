package solutions.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 递增子序列
 *
 * @author : xianzilei
 * @date : 2020/8/25 8:14
 */
public class Solution491 {
    /**
     * 解法：回溯法
     *
     * @param nums 1
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/8/25 9:14
     **/
    public List<List<Integer>> findSubsequences1(int[] nums) {
        if (nums == null || nums.length < 2) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Set<String> memory = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            List<Integer> tmpList = new ArrayList<>();
            tmpList.add(nums[i]);
            backtrack(i + 1, tmpList, nums, memory, result);
        }
        return result;

    }

    private void backtrack(int index, List<Integer> tmpList, int[] nums, Set<String> memory,
                           List<List<Integer>> result) {
        if (tmpList.size() >= 2 && !memory.contains(tmpList.toString())) {
            result.add(new ArrayList<>(tmpList));
            memory.add(tmpList.toString());
        }
        if (index == nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (tmpList.get(tmpList.size() - 1) <= nums[i]) {
                tmpList.add(nums[i]);
                backtrack(i + 1, tmpList, nums, memory, result);
                tmpList.remove(tmpList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Solution491 solution491 = new Solution491();
        System.out.println(solution491.findSubsequences1(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}));
    }
}
