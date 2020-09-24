package solutions.tree;

import pojo.TreeNode;

import java.util.*;

/**
 * 二叉搜索树中的众数
 *
 * @author : xianzilei
 * @date : 2020/9/24 8:19
 */
public class Solution501 {
    /**
     * 解法一：常规思路+哈希表
     *
     * @param root 1
     * @return int[]
     * @author xianzilei
     * @date 2020/9/24 8:19
     **/
    public int[] findMode1(TreeNode root) {
        List<Integer> tmpList = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        dfs(root, map);
        if (map.isEmpty()) {
            return new int[]{};
        }
        int maxCount = 0;
        for (Integer value : map.values()) {
            if (maxCount < value) {
                maxCount = value;
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(maxCount)) {
                tmpList.add(entry.getKey());
            }
        }
        int[] result = new int[tmpList.size()];
        for (int i = 0; i < tmpList.size(); i++) {
            result[i] = tmpList.get(i);
        }
        return result;
    }

    //中序遍历
    private void dfs(TreeNode node, Map<Integer, Integer> map) {
        if (node == null) {
            return;
        }
        dfs(node.left, map);
        map.put(node.val, map.getOrDefault(node.val, 0) + 1);
        dfs(node.right, map);
    }


    /**
     * 解法二：中序遍历（空间复杂度降低）
     *
     * @param root 1
     * @return int[]
     * @author xianzilei
     * @date 2020/9/24 8:43
     **/
    public int[] findMode2(TreeNode root) {
        dfs(root);
        int[] result = new int[maxNumList.size()];
        for (int i = 0; i < maxNumList.size(); i++) {
            result[i] = maxNumList.get(i);
        }
        return result;
    }

    //遍历到的当前元素
    int curNum = 0;
    //当前元素的出现次数
    int curCount = 0;
    //历史以来的最大出现次数
    int maxCount = 0;
    //最大出现次数的元素集合
    List<Integer> maxNumList = new ArrayList<>();

    //中序遍历
    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        //更新最大元素集合
        updateMaxNumList(node.val);
        dfs(node.right);
    }

    //更新最大元素集合
    private void updateMaxNumList(Integer val) {
        //如果上次元素等于当前元素，次数+1
        if (curNum == val) {
            curCount++;
        }
        //否则更新当前元素的值，次数重置为1
        else {
            curNum = val;
            curCount = 1;
        }
        //如果当前元素的出现次数等于最大次数，将当前元素添加到最大元素集合中
        if (curCount == maxCount) {
            maxNumList.add(curNum);
        }
        //如果当前元素的出现次数大于最大次数，则清空数组，将当前元素添加进去，最大次数更新为当前元素的出现次数
        else if (curCount > maxCount) {
            maxCount = curCount;
            maxNumList.clear();
            maxNumList.add(curNum);
        }
        //如果当前元素的出现次数小于最大此时，无操作
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        Solution501 solution501 = new Solution501();
        System.out.println(Arrays.toString(solution501.findMode2(root)));
    }
}
