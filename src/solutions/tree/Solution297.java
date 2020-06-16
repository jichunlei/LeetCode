package solutions.tree;

import pojo.Codec;
import pojo.TreeNode;

/**
 * 二叉树的序列化与反序列化
 *
 * @author : xianzilei
 * @date : 2020/6/16 08:10
 */
public class Solution297 {
    public static void main(String[] args) {
        /**
         *        8
         *     7    13
         *  10         6
         *           3    1
         **/
        TreeNode root = new TreeNode(8);
        TreeNode t1 = new TreeNode(7);
        TreeNode t2 = new TreeNode(13);
        TreeNode t3 = new TreeNode(10);
        TreeNode t4 = new TreeNode(6);
        TreeNode t5 = new TreeNode(3);
        TreeNode t6 = new TreeNode(1);
        root.left = t1;
        root.right = t2;
        t1.left = t3;
        t2.right = t4;
        t4.left = t5;
        t4.right = t6;
        Codec codec = new Codec();
        TreeNode node = codec.deserialize("[8, 7, 13, 10, null, null, 6, null, null, 3, 1, null, null, null,null]");
        System.out.println(codec.serialize(node));
    }
}
