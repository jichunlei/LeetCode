package solutions.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合
 *
 * @author : xianzilei
 * @date : 2020/6/15 08:18
 */
public class Solution77 {
    /**
     * 解法一：回溯法
     *
     * @param n 1
     * @param k 2
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/9/8 8:39
     **/
    public List<List<Integer>> combine1(int n, int k) {
        //结果集
        List<List<Integer>> result = new ArrayList<>();
        //临时路径
        LinkedList<Integer> track = new LinkedList<>();
        //回溯
        backtrack(n, k, track, result);
        //返回结果集
        return result;
    }

    private void backtrack(int n, int k, LinkedList<Integer> track, List<List<Integer>> result) {
        //终止条件，到达决策树底层
        if (track.size() == k) {
            //将该次路径加入结果集
            result.add(new ArrayList<>(track));
        }
        //循环做选择
        for (int i = 1; i <= n; i++) {
            //剪枝
            if (!track.isEmpty() && i <= track.getLast()) {
                continue;
            }
            //做选择
            track.addLast(i);
            //进入下一层决策树
            backtrack(n, k, track, result);
            //撤销选择（回溯）
            track.removeLast();
        }
    }

    /**
     * 解法二：回溯法（另一种写法）
     *
     * @param n 1
     * @param k 2
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/9/8 8:44
     **/
    public List<List<Integer>> combine2(int n, int k) {
        //结果集
        List<List<Integer>> result = new ArrayList<>();
        //临时路径
        List<Integer> tmpList = new ArrayList<>();
        backtrack(1, k, n, tmpList, result);
        //返回结果集
        return result;
    }

    private static void backtrack(int index, int k, int n, List<Integer> tmpList, List<List<Integer>> result) {
        //当满足条件时，将当前结果保存到结果集中
        if (tmpList.size() == k) {
            result.add(new ArrayList<>(tmpList));
            return;
        }
        //到达决策树底层，退出递归
        if (index > n) {
            return;
        }
        //走分支
        for (int i = index; i <= n; i++) {
            //剪枝
            if (tmpList.size() + (n - i + 1) < k) {
                break;
            }
            //做选择
            tmpList.add(i);
            //进入下一层决策树
            backtrack(i + 1, k, n, tmpList, result);
            //回溯
            tmpList.remove(tmpList.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution77 solution77 = new Solution77();
        System.out.println(solution77.combine1(5, 3));
        System.out.println(solution77.combine2(5, 3));
    }
}
