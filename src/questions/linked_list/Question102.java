package questions.linked_list;

import pojo.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xianzilei
 * @Date: 2019/9/28 22:05
 * @Description: 二叉树层次遍历
 */
public class Question102 {

    List<List<Integer>> levels = new ArrayList<>();

    private void helper(TreeNode node, int level) {
        if (levels.size() == level) {
            levels.add(new ArrayList<>());
        }
        levels.get(level).add(node.val);

        if (node.left != null)
            helper(node.left, level + 1);
        if (node.right != null)
            helper(node.right, level + 1);
    }


    public List<List<Integer>> levelOrder(TreeNode root) {

        if (root != null) {
            helper(root, 0);
        }
        return levels;
    }
}
