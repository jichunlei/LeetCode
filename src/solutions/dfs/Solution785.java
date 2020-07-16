package solutions.dfs;

import java.util.LinkedList;

/**
 * 判断二分图
 *
 * @author : xianzilei
 * @date : 2020/7/16 08:11
 */
public class Solution785 {


    /**
     * 解法一：BFS
     *
     * @param graph 1
     * @return boolean
     * @author xianzilei
     * @date 2020/7/16 8:13
     **/
    public static boolean isBipartite1(int[][] graph) {
        int length = graph.length;
        //定义着色数组，表示每个节点的数字颜色情况（0-未着色，1-红色，2-黑色）
        int[] color = new int[length];
        //访问数组，表示当前节点是否已经入过队
        boolean[] visited = new boolean[length];
        //定义队列保存节点
        LinkedList<Integer> queue = new LinkedList<>();
        //1-红色
        int RED = 1;
        //2-红色
        int BLACK = 2;
        //这里循环遍历每个节点（主要是避免孤立节点导致着色中断）
        for (int i = 0; i < length; i++) {
            //如果节点已经入过队，直接返回，无需进行着色
            if (visited[i]) {
                continue;
            }
            //当前节点入队
            queue.addLast(i);
            //节点默认染上红色
            color[i] = RED;
            //当队列不为空时
            while (!queue.isEmpty()) {
                //出队
                Integer top = queue.removeFirst();
                //获取当前节点的邻居（即与之相连接的节点）
                int[] neighbor = graph[top];
                //计算这些节点应该被染上的颜色（红->黑，黑->红）
                int toColor = color[top] == RED ? BLACK : RED;
                //遍历这些邻居节点（考虑染色和入队）
                for (int node : neighbor) {
                    //如果已经染过色了，且颜色与当前节点相同，则直接返回false
                    if (color[node] == color[top]) {
                        return false;
                    }
                    //如果没有染过色或颜色与当前节点相反，则满足题意，进行执行染色
                    else {
                        color[node] = toColor;
                    }
                    //如果当前节点没有入过队，则入队，否则不入队（如果不这样处理会导致死循环）
                    if (!visited[node]) {
                        //入队
                        queue.addLast(node);
                        //访问标记为已访问
                        visited[node] = true;
                    }
                }
            }
        }
        //执行到这里说明染色成功，返回true
        return true;
    }

    /**
     * 解法二：DFS
     *
     * @param graph 1
     * @return boolean
     * @author xianzilei
     * @date 2020/7/16 11:56
     **/
    public static boolean isBipartite2(int[][] graph) {
        int length = graph.length;
        //初始化着色列表，表示每个节点的染色情况（0-未着色，1-红色，2-黑色）
        int[] color = new int[length];
        //访问记录表（避免死循环）
        boolean[] visited = new boolean[length];
        //循环遍历每个节点
        for (int i = 0; i < length; i++) {
            //递归进行染色，返回当前染色是否成功
            boolean valid = dfs(i, graph, color, visited, 1);
            //如果不成功直接返回false
            if (!valid) {
                return false;
            }
        }
        //染色成功，返回true
        return true;
    }

    //给node节点及其相邻节点染色，返回染色是否成功（0-未着色，1-红色，2-黑色）
    private static boolean dfs(int node, int[][] graph, int[] color, boolean[] visited, int targetColor) {
        //如果节点已经访问过，无需进行染色，直接返回true
        if (visited[node]) {
            return true;
        }
        //如果当前节点未染色，则默认染上红色
        if (color[node] == 0) {
            color[node] = targetColor;
        }
        //计算期望的相邻节点的颜色
        int neighborColor = targetColor == 1 ? 2 : 1;
        //获取当前节点的相邻节点列表
        int[] neighbors = graph[node];
        //循环为相邻节点染色
        for (int neighbor : neighbors) {
            //如果未染色
            if (color[neighbor] == 0) {
                //递归进行染色
                boolean valid = dfs(neighbor, graph, color, visited, neighborColor);
                //如果染色失败，直接返回false，表示当前染色失败
                if (!valid) {
                    return false;
                }
            }
            //如果已经染过色，则判断颜色是否合法
            else {
                //如果颜色与期望的颜色不一致，直接返回false，表示当前染色失败
                if (neighborColor != color[neighbor]) {
                    return false;
                }
            }
        }
        //当前节点标记为已访问
        visited[node] = true;
        //节点染色成功，返回true
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = {{1}, {0}, {4}, {4}, {2, 3}};
        System.out.println(isBipartite1(graph));
        System.out.println(isBipartite2(graph));
    }
}
