package pojo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树编码
 *
 * @author : xianzilei
 * @date : 2020/6/16 08:08
 */
public class Codec {
    /**
     * 序列化（层序遍历）
     *
     * @param root 根节点
     * @return java.lang.String
     * @author xianzilei
     * @date 2020/6/16 13:11
     **/
    public String serialize(TreeNode root) {
        //队列
        LinkedList<TreeNode> queue = new LinkedList<>();
        //结果集
        List<Integer> result = new ArrayList<>();
        //如果树不为空，根节点入队
        if (root != null) {
            queue.addLast(root);
        }
        //遍历操作
        while (!queue.isEmpty()) {
            //出队
            TreeNode node = queue.removeFirst();
            //如果节点不为空
            if (node != null) {
                //遍历当前节点
                result.add(node.val);
                //左子树入队
                queue.addLast(node.left);
                //右子树入队
                queue.addLast(node.right);
            }
            //如果当前节点为空
            else {
                //遍历当前节点（只不过存放null）
                result.add(null);
            }
        }
        //返回结果集
        return result.toString();
    }

    /**
     * 反序列化
     *
     * @param data 字符串
     * @return pojo.TreeNode
     * @author xianzilei
     * @date 2020/6/16 13:12
     **/
    public TreeNode deserialize(String data) {
        //字符串合法性校验
        if (data == null || data.length() < 2) {
            throw new RuntimeException("序列化字符串格式不合法");
        }
        //获取字符串元素数组
        data = data.substring(1, data.length() - 1);
        //如果数组为空直接返回null
        if (data.length() == 0) {
            return null;
        }
        String[] str = data.split(",");
        //定义队列
        LinkedList<TreeNode> queue = new LinkedList<>();
        //初始化头结点
        TreeNode root = new TreeNode(Integer.parseInt(str[0]));
        //入队
        queue.addLast(root);
        //标记数组索引
        int index = 1;
        //组装二叉树结构，直到队列为空
        while (!queue.isEmpty()) {
            //出队
            TreeNode parent = queue.removeFirst();
            //获取数组元素，即左子树
            String element = str[index].trim();
            //如果左子树不为空则组装成二叉树节点链接到parent的左子树，同时入队（为了找它对应的子节点）
            if (!"null".equals(element)) {
                TreeNode node = new TreeNode(Integer.parseInt(element));
                parent.left = node;
                queue.addLast(node);
            }
            //索引递增1
            index++;
            //获取数组元素，即右子树
            element = str[index].trim();
            //如果右子树不为空则组装成二叉树节点链接到parent的右子树，同时入队（为了找它对应的子节点）
            if (!"null".equals(element)) {
                TreeNode node = new TreeNode(Integer.parseInt(element));
                parent.right = node;
                queue.addLast(node);
            }
            //索引进1
            index++;
        }
        //返回根节点
        return root;
    }
}

