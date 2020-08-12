package solutions.dfs;

import pojo.Node;

import java.util.*;

/**
 * 克隆图
 *
 * @author : xianzilei
 * @date : 2020/8/12
 */
public class Solution133 {

    /**
     * 解法一：DFS
     *
     * @param node 1
     * @return pojo.Node
     * @author xianzilei
     * @date 2020/8/12 8:19
     **/

    public Node cloneGraphDfs(Node node) {
        //如果节点为空，直接返回
        if (node == null) {
            return null;
        }
        //定义哈希表保存已经被复制结束的节点
        //key：原节点    value：复制的新节点
        Map<Node, Node> memoryMap = new HashMap<>();
        //递归复制
        return dfs(node, memoryMap);
    }

    //方法作用：复制当前节点，返回复制后的节点
    private Node dfs(Node node, Map<Node, Node> memoryMap) {
        //如果节点已经被复制了，直接返回保存的新节点
        if (memoryMap.containsKey(node)) {
            return memoryMap.get(node);
        }
        //创建新节点
        Node copyNode = new Node(node.val);
        //需要先将新节点放入map中，为了避免后续遍历其邻节点时的死循环（因为是无向连通图）
        memoryMap.put(node, copyNode);
        //定义新的邻居节点列表
        ArrayList<Node> copyNeighbors = new ArrayList<>();
        //循环复制每个邻居节点
        for (Node neighbor : node.neighbors) {
            //将复制得到的每个新的邻居节点放入邻居节点列表中
            copyNeighbors.add(dfs(neighbor, memoryMap));
        }
        //组装邻居节点列表
        copyNode.neighbors = copyNeighbors;
        //返回当前节点
        return copyNode;
    }

    /**
     * 解法二：BFS
     *
     * @param node 1
     * @return pojo.Node
     * @author xianzilei
     * @date 2020/8/12 9:13
     **/
    public Node cloneGraphBfs(Node node) {
        //如果是空图，直接返回
        if (node == null) {
            return null;
        }
        //定义队列保存节点
        Queue<Node> queue = new LinkedList<>();
        //当前节点入队
        queue.offer(node);
        //定义哈希表存储已经复制过的节点
        Map<Node, Node> visitedMap = new HashMap<>();
        //复制当前节点并保存到哈希表中（注意，复制节点的时候只需要复制当前节点的val，邻居节点列表预先定义一个空集合）
        visitedMap.put(node, new Node(node.val, new ArrayList<>()));
        //循环队列
        while (!queue.isEmpty()) {
            //出队（记住入队的节点一定已经被复制过了，复制的新节点存储在哈希表中，通过源节点key查找）
            //出队是为了组装其邻居节点列表，以及将未入队的邻居节点入队
            Node poll = queue.poll();
            //循环复制当前节点的子节点
            for (Node neighbor : poll.neighbors) {
                //如果当前节点还没有被复制过
                if (!visitedMap.containsKey(neighbor)) {
                    //复制当前节点
                    visitedMap.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    //入队
                    queue.offer(neighbor);
                }
                //组装邻居节点列表
                visitedMap.get(poll).neighbors.add(visitedMap.get(neighbor));
            }
        }
        //返回node的新复制节点
        return visitedMap.get(node);
    }

    public static void main(String[] args) {
        //[[2,4],[1,3],[2,4],[1,3]]
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        List<Node> neighbors1 = new ArrayList<>();
        neighbors1.add(node2);
        neighbors1.add(node4);
        node1.neighbors = neighbors1;
        List<Node> neighbors2 = new ArrayList<>();
        neighbors2.add(node1);
        neighbors2.add(node3);
        node2.neighbors = neighbors2;
        List<Node> neighbors3 = new ArrayList<>();
        neighbors3.add(node2);
        neighbors3.add(node4);
        node3.neighbors = neighbors3;
        List<Node> neighbors4 = new ArrayList<>();
        neighbors4.add(node1);
        neighbors4.add(node3);
        node4.neighbors = neighbors4;
        Solution133 solution133 = new Solution133();
        Node nodeDfs = solution133.cloneGraphDfs(node1);
        Node nodeBfs = solution133.cloneGraphBfs(node1);
        System.out.println();
    }
}
