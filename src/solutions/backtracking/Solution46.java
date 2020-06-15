package solutions.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列
 *
 * @author : xianzilei
 * @date : 2020/6/11 17:01
 */
public class Solution46 {
    public static List<List<Integer>> permute(int[] nums) {
        //结果集
        List<List<Integer>> result = new ArrayList<>();
        //记录路径
        LinkedList<Integer> track = new LinkedList<>();
        //回溯
        backtrack(nums, track, result);
        //返回结果
        return result;
    }

    private static void backtrack(int[] nums, LinkedList<Integer> track, List<List<Integer>> result) {
        //终止条件，即到达了决策树底层
        if (nums.length == track.size()) {
            result.add(new LinkedList<>(track));
            return;
        }

        //循环数组
        for (int i = 0; i < nums.length; i++) {
            //剪枝操作
            if (track.contains(nums[i])) {
                continue;
            }
            //做选择
            track.addLast(nums[i]);
            //进入下一层决策树
            backtrack(nums, track, result);
            //回溯
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
    }
}
