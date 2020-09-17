package pojo;

/**
 * 并查集
 *
 * @author : xianzilei
 * @date : 2020/8/11
 */
public class UnionFind {
    private int[] parents;

    public UnionFind(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be greatest than 0!");
        }
        parents = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            parents[i] = i;
        }
    }

    /**
     * 合并两个节点，如果节点已经属于一组，返回false，否则返回true
     *
     * @param node1 1
     * @param node2 2
     * @return boolean
     * @author xianzilei
     * @date 2020/9/17 20:10
     **/
    public boolean union(int node1, int node2) {
        //查找节点1的组别1
        int parent1 = find(node1);
        //查找节点2的组别2
        int parent2 = find(node2);
        //如果二者相等，直接返回false
        if (parent1 == parent2) {
            return false;
        }
        //合并节点
        parents[parent1] = parent2;
        return true;
    }

    /**
     * 获取节点的组别
     *
     * @param node 1
     * @return int
     * @author xianzilei
     * @date 2020/9/17 20:12
     **/
    public int find(int node) {
        if (node >= parents.length) {
            throw new IllegalArgumentException("node must be in parents!");
        }
        //查找组别
        while (parents[node] != node) {
            node = parents[node];
        }
        return node;
    }

    /**
     * 判断两个节点是否连通
     *
     * @param node1 1
     * @param node2 2
     * @return boolean
     * @author xianzilei
     * @date 2020/9/17 20:13
     **/
    public boolean isConnect(int node1, int node2) {
        return find(node1) == find(node2);
    }

}
