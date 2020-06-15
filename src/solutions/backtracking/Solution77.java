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
    public static List<List<Integer>> combine(int n, int k) {
        //结果集
        List<List<Integer>> result = new ArrayList<>();
        //临时路径
        LinkedList<Integer> track = new LinkedList<>();
        //回溯
        backtrack(n, k, track, result);
        //返回结果集
        return result;
    }

    private static void backtrack(int n, int k, LinkedList<Integer> track, List<List<Integer>> result) {
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

    public static void main(String[] args) {
        System.out.println(combine(9, 4));
    }
}
