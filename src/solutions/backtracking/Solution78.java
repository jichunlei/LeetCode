package solutions.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 子集
 *
 * @author : xianzilei
 * @date : 2020/6/13 15:42
 */
public class Solution78 {

    /**
     * 解法一：回溯法
     *
     * @param nums 1
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/6/13 16:17
     **/
    public static List<List<Integer>> subsets(int[] nums) {
        //结果集
        List<List<Integer>> result = new ArrayList<>();
        //回溯
        backtrack(nums, new LinkedList<>(), result);
        //返回结果
        return result;
    }

    /**
     * @param nums   可选择的元素集合
     * @param track  临时路径
     * @param result 结果集
     **/
    private static void backtrack(int[] nums, LinkedList<Integer> track, List<List<Integer>> result) {
        //首先将路径添加到结果集中
        result.add(new LinkedList<>(track));
        //终止条件，即到达决策树的底层
        if (nums.length == track.size()) {
            return;
        }
        //循环选择
        for (int i = 0; i < nums.length; i++) {
            //剪枝
            if (!track.isEmpty() && track.getLast() >= nums[i]) {
                continue;
            }
            //做选择
            track.addLast(nums[i]);
            //进入下一层决策树
            backtrack(nums, track, result);
            //撤销选择（即回溯）
            track.removeLast();
        }
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(subsets(nums));
    }
}
