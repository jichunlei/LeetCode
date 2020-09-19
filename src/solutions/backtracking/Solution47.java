package solutions.backtracking;

import java.util.*;

/**
 * 全排列 II
 *
 * @author : xianzilei
 * @date : 2020/6/15 08:43
 */
public class Solution47 {
    /**
     * 解法一：回溯法+hash去重
     *
     * @param nums 1
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/9/18 8:31
     **/
    public List<List<Integer>> permuteUnique1(int[] nums) {
        //结果集
        List<List<Integer>> result = new ArrayList<>();
        //临时路径存放
        List<Integer> track = new ArrayList<>();
        //数组访问记录表
        boolean[] visited = new boolean[nums.length];
        //路径备忘录
        Set<String> memory = new HashSet<>();
        //回溯
        backtrack1(nums, track, visited, memory, result);
        //返回结果集
        return result;
    }

    /**
     * @param nums    目标数组
     * @param track   临时路径
     * @param visited 元素访问记录表
     * @param memory  路径备忘录
     * @param result  结果集
     **/
    private void backtrack1(int[] nums, List<Integer> track, boolean[] visited,
                            Set<String> memory, List<List<Integer>> result) {
        //终止条件,即到达决策树的底层
        if (track.size() == nums.length) {
            String tmpStr = track.toString();
            //当不存在相同元素时才添加到结果集中
            if (!memory.contains(tmpStr)) {
                result.add(new ArrayList<>(track));
                memory.add(tmpStr);
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
            track.add(nums[i]);
            //当前元素标记为已访问
            visited[i] = true;
            //进入下一层决策树
            backtrack1(nums, track, visited, memory, result);
            //撤销选择
            track.remove(track.size() - 1);
            //撤销当前元素的访问标记
            visited[i] = false;
        }
    }

    /**
     * 解法二：回溯法+排序剪枝
     *
     * @param nums 1
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/9/18 9:00
     **/
    public List<List<Integer>> permuteUnique2(int[] nums) {
        //结果集
        List<List<Integer>> result = new ArrayList<>();
        //临时路径存放
        List<Integer> track = new ArrayList<>();
        //数组访问记录表
        boolean[] visited = new boolean[nums.length];
        //数组排序
        Arrays.sort(nums);
        //回溯
        backtrack2(nums, track, visited, result);
        //返回结果集
        return result;
    }

    private void backtrack2(int[] nums, List<Integer> track, boolean[] visited,
                            List<List<Integer>> result) {
        //终止条件,即到达决策树的底层
        if (track.size() == nums.length) {
            //添加当前路径到结果集
            result.add(new ArrayList<>(track));
            //结束递归
            return;
        }
        //保存同层的上一访问的节点，便于剪枝
        Integer pre = null;
        //循环做选择
        for (int i = 0; i < nums.length; i++) {
            //已访问过的剪枝
            if (visited[i]) {
                continue;
            }
            //如果当前值与同层的上一个访问过的节点相同，可以直接剪枝，达到去重效果
            if (pre != null && pre == nums[i]) {
                continue;
            }
            //做选择
            track.add(nums[i]);
            //当前元素标记为已访问
            visited[i] = true;
            //进入下一层决策树
            backtrack2(nums, track, visited, result);
            //撤销选择
            track.remove(track.size() - 1);
            //撤销当前元素的访问标记
            visited[i] = false;
            //记录访问的节点
            pre = nums[i];
        }
    }

    public static void main(String[] args) {
        Solution47 solution47 = new Solution47();
        System.out.println(solution47.permuteUnique1(new int[]{1, 1, 2}));
        System.out.println(solution47.permuteUnique2(new int[]{1, 1, 2}));
    }
}
