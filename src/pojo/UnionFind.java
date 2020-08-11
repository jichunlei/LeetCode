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

    public void union(int node1, int node2) {
        int parent1 = find(node1);
        int parent2 = find(node2);
        if (parent1 == parent2) {
            return;
        }
        parents[parent1] = parent2;
    }

    public int find(int node) {
        if (node >= parents.length) {
            throw new IllegalArgumentException("node must be in parents!");
        }
        int tmp = parents[node];
        while (parents[node] != node) {
            node = parents[node];
        }
        return node;
    }

    public boolean isConnect(int node1, int node2) {
        return find(node1) == find(node2);
    }

}
