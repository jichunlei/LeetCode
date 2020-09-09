package solutions.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和
 *
 * @author : xianzilei
 * @date : 2020/9/9 8:09
 */
public class Solution39 {
    /**
     * 解法：回溯法
     *
     * @param candidates 1
     * @param target     2
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/9/9 8:47
     **/
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //定义结果集
        List<List<Integer>> result = new ArrayList<>();
        //定义临时路径
        List<Integer> tmpList = new ArrayList<>();
        //排序
        Arrays.sort(candidates);
        //回溯法
        backtrack(0, tmpList, candidates, target, result);
        //返回结果
        return result;
    }

    /**
     * @param sum        当前和
     * @param tmpList    临时路径
     * @param candidates 目标数组
     * @param target     目标值
     * @param result     结果集
     **/
    private void backtrack(int sum, List<Integer> tmpList, int[] candidates, int target,
                           List<List<Integer>> result) {
        //满足条件直接放入结果集中
        if (sum == target) {
            result.add(new ArrayList<>(tmpList));
            return;
        }
        //如果结果大于目标值，直接结束当前分支
        else if (sum > target) {
            return;
        }
        //走分支
        for (int candidate : candidates) {
            //剪枝去重，每次从路径中的最大值出开始遍历，其余直接剪枝
            if (!tmpList.isEmpty() && candidate < tmpList.get(tmpList.size() - 1)) {
                continue;
            }
            //做选择
            tmpList.add(candidate);
            //进入下一层决策树
            backtrack(sum + candidate, tmpList, candidates, target, result);
            //回溯
            tmpList.remove(tmpList.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution39 solution39 = new Solution39();
        System.out.println(solution39.combinationSum(new int[]{1, 2}, 4));
    }
}
