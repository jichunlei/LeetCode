package solutions.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 插入区间
 *
 * @author : xianzilei
 * @date : 2020/11/4 8:19
 */
public class Solution57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        //解题思路：遍历区间数组，没有交集的区间根据大小放在新区间的前面或后面，有交集则合并出新的区间

        //定义动态数组存放结果
        List<int[]> list = new ArrayList<>();
        //记录新区间是否已经插入
        boolean placed = false;
        //新区间的左右边界
        int left = newInterval[0];
        int right = newInterval[1];
        for (int[] interval : intervals) {
            //当前区间在新插入的区间的左侧
            if (left > interval[1]) {
                list.add(interval);
            }
            //当前区间在新插入的区间的右侧
            else if (right < interval[0]) {
                if (!placed) {
                    list.add(new int[]{left, right});
                    placed = true;
                }
                list.add(interval);
            }
            //当前区间与新区间存在交集
            else {
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        //如果新区间没有插入，则插入结果集中
        if (!placed) {
            list.add(new int[]{left, right});
        }
        int[][] result = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution57 solution57 = new Solution57();
        int[][] result1 = solution57.insert(new int[][]{{1, 5}, {6, 9}}, new int[]{2, 5});
        for (int[] ints : result1) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("---------------------------");
        int[][] result2 = solution57.insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8});
        for (int[] ints : result2) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
