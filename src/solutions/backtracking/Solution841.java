package solutions.backtracking;

import java.util.*;

/**
 * 钥匙和房间
 *
 * @author : xianzilei
 * @date : 2020/8/31 7:58
 */
public class Solution841 {
    /**
     * 解法一：DFS
     *
     * @param rooms 1
     * @return boolean
     * @author xianzilei
     * @date 2020/8/31 8:39
     **/
    public boolean canVisitAllRoomsDfs(List<List<Integer>> rooms) {
        //记录已访问过的节点
        Set<Integer> visitedSet = new HashSet<>();
        visitedSet.add(0);
        //从0号房间开始深度遍历
        return backtrack(0, visitedSet, rooms);
    }

    //从node节点开始访问，返回结果表示是否全部节点都访问结束
    private boolean backtrack(int node, Set<Integer> visitedSet, List<List<Integer>> rooms) {
        //如果所有节点都已经访问过，直接返回true
        if (visitedSet.size() == rooms.size()) {
            return true;
        }
        //获取当前节点的可访问的邻居节点列表
        List<Integer> adjacencyList = rooms.get(node);
        //遍历
        for (Integer adjacency : adjacencyList) {
            //如果当前节点已经被访问过，直接剪枝
            if (visitedSet.contains(adjacency)) {
                continue;
            }
            //做选择
            visitedSet.add(adjacency);
            //进入下一节点，如果下一层可以访问成功，则返回成功，否则访问另外的邻居节点
            if (backtrack(adjacency, visitedSet, rooms)) {
                return true;
            }
            //注意这里是不能回溯的，因为根据题意是可以原路返回的
        }
        return false;
    }

    /**
     * 解法二：BFS
     *
     * @param rooms 1
     * @return boolean
     * @author xianzilei
     * @date 2020/8/31 8:38
     **/
    public boolean canVisitAllRoomsBfs(List<List<Integer>> rooms) {
        //记录已访问过的节点
        Set<Integer> visitedSet = new HashSet<>();
        //定义队列存放待访问的节点
        Queue<Integer> queue = new LinkedList<>();
        //从0节点处开始访问
        queue.add(0);
        //遍历队列
        while (!queue.isEmpty()) {
            //出队
            Integer node = queue.poll();
            //标记已访问
            visitedSet.add(node);
            //如果所有节点都已经全部访问，则返回成功
            if (visitedSet.size() == rooms.size()) {
                return true;
            }
            //将未访问过的邻居节点入队
            List<Integer> adjacencyList = rooms.get(node);
            for (Integer adjacency : adjacencyList) {
                if (visitedSet.contains(adjacency)) {
                    continue;
                }
                queue.offer(adjacency);
            }
        }
        //如果队列为空都未全部访问，返回false
        return false;
    }

    public static void main(String[] args) {
        Solution841 solution841 = new Solution841();
        List<List<Integer>> rooms = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        list1.add(3);
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        list3.add(2);
        List<Integer> list4 = new ArrayList<>();
        list4.add(1);
        list4.add(3);
        list4.add(1);
        rooms.add(list1);
        rooms.add(list2);
        rooms.add(list3);
        rooms.add(list4);
        System.out.println(solution841.canVisitAllRoomsDfs(rooms));
        System.out.println(solution841.canVisitAllRoomsBfs(rooms));
    }
}
