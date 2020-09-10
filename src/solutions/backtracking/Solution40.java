package solutions.backtracking;

import java.util.*;

/**
 * 组合总和 II
 *
 * @author : xianzilei
 * @date : 2020/9/10 8:01
 */
public class Solution40 {

    /**
     * 解法一：回溯法（hash表去重）
     *
     * @param candidates 1
     * @param target     2
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/9/10 8:21
     **/
    public List<List<Integer>> combinationSum21(int[] candidates, int target) {
        //定义结果集
        List<List<Integer>> result = new ArrayList<>();
        //临时路径
        List<Integer> tmpList = new ArrayList<>();
        //预先排序（重要），目的是方便备忘录记忆路径去重
        Arrays.sort(candidates);
        //定义备忘录，记录已存在的路径值
        Set<String> memory = new HashSet<>();
        //回溯
        backtrack1(0, 0, tmpList, candidates, target, result, memory);
        //返回结果集
        return result;
    }

    /**
     * @param index      当前遍历到的数组索引
     * @param sum        当前和
     * @param tmpList    当前路径
     * @param candidates 目标数组
     * @param target     目标值
     * @param result     结果集
     * @param memory     备忘录
     **/
    private void backtrack1(int index, int sum, List<Integer> tmpList, int[] candidates, int target,
                            List<List<Integer>> result, Set<String> memory) {
        //满足条件入结果集（和等于目标值+路径不存在记忆中）
        if (sum == target && !memory.contains(tmpList.toString())) {
            result.add(new ArrayList<>(tmpList));
            memory.add(tmpList.toString());
            return;
        }
        //否则如果和大于等于当前值，直接结果当前分支
        else if (sum >= target) {
            return;
        }

        //走分支
        for (int i = index; i < candidates.length; i++) {
            //做选择
            tmpList.add(candidates[i]);
            //进入下一层决策树
            backtrack1(i + 1, sum + candidates[i], tmpList, candidates, target, result, memory);
            //回溯
            tmpList.remove(tmpList.size() - 1);
        }
    }

    /**
     * 解法二：回溯法（剪枝去重）
     *
     * @param candidates 1
     * @param target     2
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/9/10 8:22
     **/
    public List<List<Integer>> combinationSum22(int[] candidates, int target) {
        //定义结果集
        List<List<Integer>> result = new ArrayList<>();
        //临时路径
        List<Integer> tmpList = new ArrayList<>();
        //预先排序（重要），目的是方便备忘录记忆路径去重
        Arrays.sort(candidates);
        //回溯
        backtrack2(0, 0, tmpList, candidates, target, result);
        //返回结果集
        return result;
    }

    /**
     * @param index      当前遍历到的数组索引
     * @param sum        当前和
     * @param tmpList    当前路径
     * @param candidates 目标数组
     * @param target     目标值
     * @param result     结果集
     **/
    private void backtrack2(int index, int sum, List<Integer> tmpList, int[] candidates, int target,
                            List<List<Integer>> result) {
        //满足条件入队
        if (sum == target) {
            result.add(new ArrayList<>(tmpList));
            return;
        }

        //走分支
        for (int i = index; i < candidates.length; i++) {
            //剪枝：和大于目标值的分支直接抛弃
            if (sum + candidates[i] > target) {
                continue;
            }
            //剪枝：如果下一分支值与上一分支的值相同，直接抛弃，目的是为了去重
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            //做选择
            tmpList.add(candidates[i]);
            //进入下一层决策树
            backtrack2(i + 1, sum + candidates[i], tmpList, candidates, target, result);
            //回溯
            tmpList.remove(tmpList.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution40 solution40 = new Solution40();
        System.out.println(solution40.combinationSum21(new int[]{2, 5, 2, 1, 2}, 5));
        System.out.println(solution40.combinationSum22(new int[]{2, 5, 2, 1, 2}, 5));
    }
}
