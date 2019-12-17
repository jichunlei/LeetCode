package questions.linked_list;

import questions.pojo.TreeNode;

import java.util.LinkedList;

/**
 * @Auther: xianzilei
 * @Date: 2019/9/29 08:34
 * @Description: TODO
 */
public class Qusetion1021 {
    public void levelIterator(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode current = null;
        queue.offer(root);//将根节点入队
        while (!queue.isEmpty()) {
            current = queue.poll();//出队队头元素并访问
            System.out.print(current.val + "-->");
            if (current.left != null)//如果当前节点的左节点不为空入队
            {
                queue.offer(current.left);
            }
            if (current.right != null)//如果当前节点的右节点不为空，把右节点入队
            {
                queue.offer(current.right);
            }
        }

    }
}
