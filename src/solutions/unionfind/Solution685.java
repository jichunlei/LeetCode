package solutions.unionfind;

import pojo.UnionFind;

import java.util.Arrays;

/**
 * 冗余连接 II
 *
 * @author : xianzilei
 * @date : 2020/9/17 8:14
 */
public class Solution685 {

    /**
     * 解法：并查集
     *
     * @param edges 1
     * @return int[]
     * @author xianzilei
     * @date 2020/9/17 20:14
     **/
    public int[] findRedundantDirectedConnection(int[][] edges) {
        //节点数
        int n = edges.length;
        //存放各个节点的入度
        int[] degree = new int[n + 1];
        //计算各个节点的入度
        for (int[] edge : edges) {
            degree[edge[1]]++;
        }
        //尝试删除入度为2的边
        for (int i = n - 1; i >= 0; i--) {
            if (degree[edges[i][1]] == 2) {
                //判断剩下的边是否是一颗合法的树
                if (validTree(i, edges)) {
                    return edges[i];
                }
            }
        }
        //尝试删除入度为1的边
        for (int i = n - 1; i >= 0; i--) {
            if (degree[edges[i][1]] == 1) {
                //判断剩下的边是否是一颗合法的树
                if (validTree(i, edges)) {
                    return edges[i];
                }
            }
        }
        throw new IllegalArgumentException("入参不合法！");
    }

    /**
     * 判断剩下的边是否是一颗合法的树
     *
     * @param removeEdgeIndex 需要移除的节点
     * @param edges           目标图
     * @return boolean
     * @author xianzilei
     * @date 2020/9/17 20:16
     **/
    private boolean validTree(int removeEdgeIndex, int[][] edges) {
        int length = edges.length;
        //定义并查集
        UnionFind unionFind = new UnionFind(edges.length + 1);
        //循环遍历每个边
        for (int i = 0; i < length; i++) {
            //删除指定边
            if (i == removeEdgeIndex) {
                continue;
            }
            //连接节点，如果合并失败表示该条边的两个节点已经属于一个连通区间，必然会有环
            if (!unionFind.union(edges[i][0], edges[i][1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution685 solution685 = new Solution685();
        //[[4,2],[1,5],[5,2],[5,3],[2,4]]
        System.out.println(Arrays.toString(
                solution685.findRedundantDirectedConnection(new int[][]{
                        {4, 2},
                        {1, 5},
                        {5, 2},
                        {5, 3},
                        {2, 4}
                })));
    }
}
