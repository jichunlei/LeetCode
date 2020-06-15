package solutions.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列 II
 *
 * @author : xianzilei
 * @date : 2020/6/15 08:43
 */
public class Solution47 {
    public static List<List<Integer>> permuteUnique(int[] nums) {
        //结果集
        List<List<Integer>> result = new ArrayList<>();
        //临时路径存放
        LinkedList<Integer> track = new LinkedList<>();
        //数组访问记录表
        boolean[] visited = new boolean[nums.length];
        //回溯
        backtrack(nums, track, visited, result);
        //返回结果集
        return result;
    }

    private static void backtrack(int[] nums, LinkedList<Integer> track, boolean[] visited,
                                  List<List<Integer>> result) {
        //终止条件,即到达决策树的底层
        if (track.size() == nums.length) {
            //由于结果集不能重复，而数组存在重复的数字，因此需要去重
            List<Integer> tmp = new ArrayList<>(track);
            //当不存在相同元素时才添加到结果集中
            if (!result.contains(tmp)) {
                result.add(new ArrayList<>(track));
            }
            //结束递归
            return;
        }

        //循环做选择
        for (int i = 0; i < nums.length; i++) {
            //剪枝
            if (visited[i]) {
                continue;
            }
            //做选择
            track.addLast(nums[i]);
            //当前元素标记为已访问
            visited[i] = true;
            //进入下一层决策树
            backtrack(nums, track, visited, result);
            //撤销选择
            track.removeLast();
            //撤销当前元素的访问标记
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        System.out.println(permuteUnique(nums));
    }
}
