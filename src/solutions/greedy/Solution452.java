package solutions.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 用最少数量的箭引爆气球
 *
 * @author : xianzilei
 * @date : 2020/11/23 8:42
 */
public class Solution452 {
    /**
     * 解法：排序+贪心
     *
     * @param points 1
     * @return int
     * @author xianzilei
     * @date 2020/11/23 8:57
     **/
    public int findMinArrowShots(int[][] points) {
        int length = points.length;
        if (length < 2) {
            return length;
        }
        Arrays.sort(points, Comparator.comparingInt(point -> point[1]));
        int target = Integer.MIN_VALUE;
        int res = 0;
        for (int[] point : points) {
            if (point[0] > target) {
                res++;
                target = point[1];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution452 solution452 = new Solution452();
        System.out.println(solution452.findMinArrowShots(new int[][]{
                {10, 16}, {2, 8}, {1, 6}, {7, 12}
        }));
    }
}
