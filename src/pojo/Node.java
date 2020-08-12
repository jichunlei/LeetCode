package pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 图节点
 *
 * @author : xianzilei
 * @date : 2020/8/12
 */
public class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        this.val = 0;
        this.neighbors = new ArrayList<Node>();
    }

    public Node(int val) {
        this.val = val;
        this.neighbors = new ArrayList<Node>();
    }

    public Node(int val, ArrayList<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}
