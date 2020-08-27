package solutions.backtracking;

import java.util.*;

/**
 * 重新安排行程
 *
 * @author : xianzilei
 * @date : 2020/8/27 8:14
 */
public class Solution332 {

    /**
     * 解法：回溯法
     *
     * @param tickets 1
     * @return java.util.List<java.lang.String>
     * @author xianzilei
     * @date 2020/8/27 16:53
     **/
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> result = new ArrayList<>();
        //特殊情况下的排除
        if (tickets == null || tickets.isEmpty()) {
            return result;
        }
        //领接表
        Map<String, List<String>> adjacencyMap = new HashMap<>();
        //存储路径及其剩余可走的个数
        Map<String, Integer> pathMap = new HashMap<>();
        //领接表构建有向图，及初始化路径个数
        for (List<String> ticket : tickets) {
            String node = ticket.get(0);
            String adjacency = ticket.get(1);
            if (!adjacencyMap.containsKey(node)) {
                adjacencyMap.put(node, new ArrayList<>());
            }
            adjacencyMap.get(node).add(adjacency);
            String key = node + "-" + adjacency;
            pathMap.put(key, pathMap.getOrDefault(key, 0) + 1);
        }
        //邻接表的邻居节点列表升序排序，方便后面回溯查找第一条即可以结束回溯
        for (List<String> values : adjacencyMap.values()) {
            Collections.sort(values);
        }

        //当前节点入队
        result.add("JFK");
        //回溯开始
        backtrack("JFK", result, adjacencyMap, pathMap, tickets);
        //返回结果
        return result;
    }

    /**
     * @param node         当前节点
     * @param tmpList      临时路径列表
     * @param adjacencyMap 邻接表
     * @param pathMap      路径个数表
     * @param tickets      机票集合
     * @return boolean 是否找到了一条结果
     **/
    private boolean backtrack(String node, List<String> tmpList,
                              Map<String, List<String>> adjacencyMap,
                              Map<String, Integer> pathMap, List<List<String>> tickets) {
        //回溯终止条件，当路径走完，直接结束
        if (tmpList.size() == tickets.size() + 1) {
            return true;
        }
        //如果当前节点没有出口，说明当前回溯方式无解，返回false，走另一分支
        if (!adjacencyMap.containsKey(node)) {
            return false;
        }
        //获取当前节点的出口列表
        List<String> adjacencyList = adjacencyMap.get(node);
        //遍历邻居节点
        for (String adjacency : adjacencyList) {
            //校验当前路径是否被走过
            String key = node + "-" + adjacency;
            Integer count = pathMap.get(key);
            //如果被走过，直接结束当层循环，即剪枝操作
            if (count == 0) {
                continue;
            }
            //做选择
            //当前节点入队
            tmpList.add(adjacency);
            //当前走过的路径剔除
            pathMap.put(key, count - 1);
            //进入下一层选择，如果成功直接返回，否则回溯
            if (backtrack(adjacency, tmpList, adjacencyMap, pathMap, tickets)) {
                return true;
            }
            //回溯
            //剔除当前节点
            tmpList.remove(tmpList.size() - 1);
            //恢复路径可用
            pathMap.put(key, count);
        }
        //如果都没找到有效路径，直接返回false
        return false;
    }


    public static void main(String[] args) {
        //[["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
        //[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
        Solution332 solution332 = new Solution332();
        List<List<String>> tickets = new ArrayList<>();
//        tickets.add(Arrays.asList("MUC", "LHR"));
//        tickets.add(Arrays.asList("JFK", "MUC"));
//        tickets.add(Arrays.asList("SFO", "SJC"));
//        tickets.add(Arrays.asList("LHR", "SFO"));

        tickets.add(Arrays.asList("JFK", "SFO"));
        tickets.add(Arrays.asList("JFK", "ATL"));
        tickets.add(Arrays.asList("SFO", "ATL"));
        tickets.add(Arrays.asList("ATL", "JFK"));
        tickets.add(Arrays.asList("ATL", "SFO"));
        System.out.println(solution332.findItinerary(tickets));
    }
}
