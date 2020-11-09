package solutions.array;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 最接近原点的 K 个点
 *
 * @author : xianzilei
 * @date : 2020/11/9 8:21
 */
public class Solution973 {
    /**
     * 解法一：选择排序
     *
     * @param points 1
     * @param K      2
     * @return int[][]
     * @author xianzilei
     * @date 2020/11/9 8:44
     **/
    public int[][] kClosest(int[][] points, int K) {
        int[][] result = new int[K][2];

        if (K == points.length) {
            for (int i = 0; i < points.length; i++) {
                result[i] = new int[]{points[i][0], points[i][1]};
            }
            return result;
        }
        boolean[] visited = new boolean[points.length];
        for (int i = 0; i < K; i++) {
            int minDistance = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int j = 0; j < points.length; j++) {
                if (visited[j]) {
                    continue;
                }
                int x = points[j][0];
                int y = points[j][1];
                int curDistance = x * x + y * y;
                if (curDistance < minDistance) {
                    minDistance = curDistance;
                    minIndex = j;
                }
            }
            result[i] = new int[]{points[minIndex][0], points[minIndex][1]};
            visited[minIndex] = true;
        }
        return result;
    }

    /**
     * 解法二：堆
     *
     * @param points 1
     * @param K      2
     * @return int[][]
     * @author xianzilei
     * @date 2020/11/9 9:06
     **/
    public int[][] kClosest2(int[][] points, int K) {
        int[][] result = new int[K][2];

        if (K == points.length) {
            for (int i = 0; i < points.length; i++) {
                result[i] = new int[]{points[i][0], points[i][1]};
            }
            return result;
        }
        PriorityQueue<int[]> priorityQueue =
                new PriorityQueue<>((point1, point2) -> point2[0] * point2[0] + point2[1] * point2[1] - point1[0] * point1[0] - point1[1] * point1[1]);
        for (int[] point : points) {
            if (priorityQueue.size() < K) {
                priorityQueue.offer(point);
            } else {
                int[] top = priorityQueue.peek();
                if (point[0] * point[0] + point[1] * point[1] < top[0] * top[0] + top[1] * top[1]) {
                    priorityQueue.poll();
                    priorityQueue.offer(point);
                }
            }
        }
        for (int i = 0; i < K; i++) {
            int[] point = priorityQueue.poll();
            result[i] = new int[]{point[0], point[1]};
        }
        return result;
    }

    /**
     * 解法三：快排--TODO
     *
     * @param points 1
     * @param K      2
     * @return int[][]
     * @author xianzilei
     * @date 2020/11/9 9:06
     **/
    public int[][] kClosest3(int[][] points, int K) {
        int n = points.length;
        randomSelect(points, 0, n - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }
    Random rand = new Random();

    public void randomSelect(int[][] points, int left, int right, int K) {
        int pivotId = left + rand.nextInt(right - left + 1);
        int pivot = points[pivotId][0] * points[pivotId][0] + points[pivotId][1] * points[pivotId][1];
        swap(points, right, pivotId);
        int i = left - 1;
        for (int j = left; j < right; ++j) {
            int dist = points[j][0] * points[j][0] + points[j][1] * points[j][1];
            if (dist <= pivot) {
                ++i;
                swap(points, i, j);
            }
        }
        ++i;
        swap(points, i, right);
        // [left, i-1] 都小于等于 pivot, [i+1, right] 都大于 pivot
        if (K < i - left + 1) {
            randomSelect(points, left, i - 1, K);
        } else if (K > i - left + 1) {
            randomSelect(points, i + 1, right, K - (i - left + 1));
        }
    }

    public void swap(int[][] points, int index1, int index2) {
        int[] temp = points[index1];
        points[index1] = points[index2];
        points[index2] = temp;
    }

    public static void main(String[] args) {
        Solution973 solution973 = new Solution973();
        int[][] result = solution973.kClosest(new int[][]{
                {3, 3},
                {5, -1},
                {-2, 4}}, 2);
        for (int[] ints : result) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("----------------------------------");
        int[][] result2 = solution973.kClosest2(new int[][]{
                {-5, 4},
                {-6, 5},
                {4, 6}}, 2);
        for (int[] ints : result2) {
            System.out.println(Arrays.toString(ints));
        }

        int[][] result3 = solution973.kClosest3(new int[][]{
                {-5, 4},
                {-6, 5},
                {4, 6}}, 2);
        for (int[] ints : result3) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
