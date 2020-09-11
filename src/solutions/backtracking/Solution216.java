package solutions.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合总和 III
 *
 * @author : xianzilei
 * @date : 2020/9/11 8:05
 */
public class Solution216 {

    /**
     * 解法一：回溯法
     *
     * @param k 1
     * @param n 2
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/9/11 8:15
     **/
    public List<List<Integer>> combinationSum31(int k, int n) {
        //结果集
        List<List<Integer>> result = new ArrayList<>();
        //临时路径
        List<Integer> tmpList = new ArrayList<>();
        //回溯
        backtrack(1, 0, k, n, tmpList, result);
        //返回结果
        return result;
    }

    /**
     * @param index   当前遍历到的数组元素（本次选择集在当前元素及之后的元素，避免重复）
     * @param sum     当前和
     * @param k       指定的目标集合个数
     * @param n       目标和
     * @param tmpList 临时路径
     * @param result  结果集
     **/
    private void backtrack(int index, int sum, int k, int n, List<Integer> tmpList, List<List<Integer>> result) {
        //当到达决策树底层，需要结果当前分支
        if (tmpList.size() == k) {
            //和的判断来决定是否加入结果集中
            if (sum == n) {
                result.add(new ArrayList<>(tmpList));
            }
            return;
        }

        //走分支
        for (int i = index; i <= 9; i++) {
            //做选择
            tmpList.add(i);
            //进入下一层决策树
            backtrack(i + 1, sum + i, k, n, tmpList, result);
            //回溯
            tmpList.remove(tmpList.size() - 1);
        }
    }

    /**
     * 解法二：回溯+剪枝
     *
     * @param k 1
     * @param n 2
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/9/11 8:17
     **/
    public List<List<Integer>> combinationSum32(int k, int n) {
        //结果集
        List<List<Integer>> result = new ArrayList<>();
        //临时路径
        List<Integer> tmpList = new ArrayList<>();
        //回溯
        backtrack2(1, 0, k, n, tmpList, result);
        //返回结果
        return result;
    }

    /**
     * @param index   当前遍历到的数组元素（本次选择集在当前元素及之后的元素，避免重复）
     * @param sum     当前和
     * @param k       指定的目标集合个数
     * @param n       目标和
     * @param tmpList 临时路径
     * @param result  结果集
     **/
    private void backtrack2(int index, int sum, int k, int n, List<Integer> tmpList, List<List<Integer>> result) {
        //当到达决策树底层，需要结果当前分支
        if (tmpList.size() == k) {
            //和的判断来决定是否加入结果集中
            if (sum == n) {
                result.add(new ArrayList<>(tmpList));
            }
            return;
        }

        //走分支
        for (int i = index; i <= 9; i++) {
            //剪枝（当本次的选择和大于目标值，直接抛弃当前分支）
            if (sum + i > n) {
                continue;
            }
            //剪枝（当达到指定个数的元素和小于目标值，直接抛弃当前分支）
            if (tmpList.size() == k - 1 && sum + i < n) {
                continue;
            }
            //做选择
            tmpList.add(i);
            //进入下一层决策树
            backtrack2(i + 1, sum + i, k, n, tmpList, result);
            //回溯
            tmpList.remove(tmpList.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution216 solution216 = new Solution216();
        System.out.println(solution216.combinationSum31(3, 9));
        System.out.println(solution216.combinationSum32(3, 9));
    }
}
