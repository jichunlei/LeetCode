package solutions.topologicalsort;

import com.sun.javafx.robot.FXRobotImage;

import java.util.*;

/**
 * 课程表
 *
 * @author : xianzilei
 * @date : 2020/8/4
 */
public class Solution207 {
    /**
     * 解法一：BFS（拓扑排序）
     *
     * @param numCourses    1
     * @param prerequisites 2
     * @return boolean
     * @author xianzilei
     * @date 2020/8/4 8:25
     **/
    public boolean canFinishBfs(int numCourses, int[][] prerequisites) {
        //保存每个节点的入度数
        int[] degree = new int[numCourses];
        //组装每个节点的邻居节点（即以邻接表的形式表示图）
        List<List<Integer>> adjacency = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            int i = prerequisite[0];
            int j = prerequisite[1];
            degree[i] += 1;
            adjacency.get(j).add(i);
        }
        //定义队列，存放入度为0的节点
        Queue<Integer> queue = new LinkedList<>();
        //首先将入度为0的所有节点放入队列中
        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }
        //遍历队列
        while (!queue.isEmpty()) {
            //出队
            Integer poll = queue.poll();
            //当前剩余节点数减一
            numCourses--;
            //获取当前节点的邻居节点
            List<Integer> list = adjacency.get(poll);
            //遍历当前节点的邻居节点
            for (Integer neighbor : list) {
                //每个邻居节点的入度减一
                degree[neighbor]--;
                //如果入度减为0，则加入到队列中
                if (degree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        //返回剩余元素是否为0
        return numCourses == 0;
    }

    /**
     * 解法二：DFS（拓扑排序）
     *
     * @param numCourses    1
     * @param prerequisites 2
     * @return boolean
     * @author xianzilei
     * @date 2020/8/4 9:13
     **/
    public boolean canFinishDfs(int numCourses, int[][] prerequisites) {
        //组装每个节点的邻居节点（即以邻接表的形式表示图）
        List<List<Integer>> adjacency = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            adjacency.get(prerequisite[1]).add(prerequisite[0]);
        }
        //定义状态数组（0-未访问，1-访问中，2-访问结束）
        int[] status = new int[numCourses];
        //循环访问每一个节点，避免有些孤立节点或入度为0的节点访问不到
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, adjacency, status)) {
                return false;
            }
        }
        return true;
    }

    //遍历当前节点，返回遍历是否成功
    private boolean dfs(int curNode, List<List<Integer>> adjacency, int[] visited) {
        //如果当前节点已经被其访问过，无需继续访问，直接返回true
        if (visited[curNode] == 2) {
            return true;
        }
        //如果当前节点在访问中，说明自己还未结束访问就又被访问到了，即存在环，直接返回false
        if (visited[curNode] == 1) {
            return false;
        }
        //设置当前节点处于访问中状态
        visited[curNode] = 1;
        //访问当前节点的邻居节点（有向）
        List<Integer> neighbors = adjacency.get(curNode);
        for (Integer neighborNode : neighbors) {
            //递归访问每个邻居节点
            if (!dfs(neighborNode, adjacency, visited)) {
                return false;
            }
        }
        //设置当前节点访问结束
        visited[curNode] = 2;
        //当前节点访问结束，返回true
        return true;
    }

    public static void main(String[] args) {
        int[][] prerequisites = {{1, 0}, {3, 0}, {2, 1}, {3, 2}, {1, 3}};
        Solution207 solution207 = new Solution207();
        System.out.println(solution207.canFinishBfs(4, prerequisites));
        System.out.println(solution207.canFinishDfs(4, prerequisites));
    }
}
