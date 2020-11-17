package solutions.array;

import java.util.*;

/**
 * 距离顺序排列矩阵单元格
 *
 * @author : xianzilei
 * @date : 2020/11/17 8:21
 */
public class Solution1030 {
    /**
     * 解法一：BFS
     *
     * @param R  1
     * @param C  2
     * @param r0 3
     * @param c0 4
     * @return int[][]
     * @author xianzilei
     * @date 2020/11/17 8:42
     **/
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        //存放结果集
        int[][] res = new int[R * C][2];
        //访问标记数组
        boolean[][] visited = new boolean[R][C];
        //队列
        Queue<int[]> queue = new LinkedList<>();
        //当前节点入队
        queue.offer(new int[]{r0, c0});
        //记录索引位置
        int index = 0;
        //遍历队列
        while (!queue.isEmpty()) {
            //记录当层的元素个数
            int size = queue.size();
            //遍历当层节点
            for (int i = 0; i < size; i++) {
                int[] node = queue.poll();
                int x = node[0];
                int y = node[1];
                //已访问过的节点直接跳过
                if (visited[x][y]) {
                    continue;
                }
                //放入结果集中
                res[index++] = node;
                //标记已访问
                visited[x][y] = true;
                //将当前节点的四个方向的节点放入队列中
                if (x > 0) {
                    queue.offer(new int[]{x - 1, y});
                }
                if (y > 0) {
                    queue.offer(new int[]{x, y - 1});
                }
                if (x < R - 1) {
                    queue.offer(new int[]{x + 1, y});
                }
                if (y < C - 1) {
                    queue.offer(new int[]{x, y + 1});
                }
            }
        }
        //返回结果
        return res;
    }

    /**
     * 解法二：直接排序法
     *
     * @param R  1
     * @param C  2
     * @param r0 3
     * @param c0 4
     * @return int[][]
     * @author xianzilei
     * @date 2020/11/17 8:42
     **/
    public int[][] allCellsDistOrder2(int R, int C, int r0, int c0) {
        //定义结果集
        int[][] res = new int[R * C][2];
        //将所有节点放入结果集中
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                res[i * C + j] = new int[]{i, j};
            }
        }
        //按照题意排序
        Arrays.sort(res, (o1, o2) -> Math.abs(o1[0] - r0) + Math.abs(o1[1] - c0) - Math.abs(o2[0] - r0) - Math.abs(o2[1] - c0));
        //返回结果
        return res;
    }

    /**
     * 解法三：桶排序
     *
     * @param R  1
     * @param C  2
     * @param r0 3
     * @param c0 4
     * @return int[][]
     * @author xianzilei
     * @date 2020/11/17 8:57
     **/
    public int[][] allCellsDistOrder3(int R, int C, int r0, int c0) {
        //定义桶：桶的索引表示距离，桶里面存放距离等于索引值的所有节点
        //定义结果集
        int[][] res = new int[R * C][2];
        //求最大距离（即桶的最大索引值）
        int maxDist = Math.max(r0, R - 1 - r0) + Math.max(c0, C - 1 - c0);
        //定义桶
        List<List<int[]>> bucket = new ArrayList<>(maxDist + 1);
        //初始化桶
        for (int i = 0; i <= maxDist; i++) {
            bucket.add(new ArrayList<>());
        }
        //遍历矩阵节点，求距离并放入指定的桶里
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int disc = Math.abs(i - r0) + Math.abs(j - c0);
                bucket.get(disc).add(new int[]{i, j});
            }
        }
        int index = 0;
        //按照顺序依次放入到结果集中
        for (int i = 0; i <= maxDist; i++) {
            List<int[]> discList = bucket.get(i);
            for (int[] pos : discList) {
                res[index++] = pos;
            }
        }
        //返回结果
        return res;
    }

    public static void main(String[] args) {
        Solution1030 solution1030 = new Solution1030();
        solution1030.allCellsDistOrder(5, 5, 1, 2);
    }
}
