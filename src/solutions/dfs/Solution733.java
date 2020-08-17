package solutions.dfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图像渲染
 *
 * @author : xianzilei
 * @date : 2020/8/17
 */
public class Solution733 {
    /**
     * 解法一：DFS
     *
     * @param image    1
     * @param sr       2
     * @param sc       3
     * @param newColor 4
     * @return int[][]
     * @author xianzilei
     * @date 2020/8/17 9:11
     **/
    public int[][] floodFillBfs(int[][] image, int sr, int sc, int newColor) {
        int srcColor = image[sr][sc];
        if (srcColor == newColor) {
            return image;
        }
        int row = image.length;
        int col = image[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int curRow = poll[0];
            int curCol = poll[1];
            image[curRow][curCol] = newColor;
            //上
            if (curRow >= 1 && image[curRow - 1][curCol] == srcColor) {
                queue.add(new int[]{curRow - 1, curCol});
            }
            //下
            if (curRow < row - 1 && image[curRow + 1][curCol] == srcColor) {
                queue.add(new int[]{curRow + 1, curCol});
            }
            //左
            if (curCol >= 1 && image[curRow][curCol - 1] == srcColor) {
                queue.add(new int[]{curRow, curCol - 1});
            }
            //右
            if (curCol < col - 1 && image[curRow][curCol + 1] == srcColor) {
                queue.add(new int[]{curRow, curCol + 1});
            }
        }
        return image;
    }

    /**
     * 解法二：DFS
     *
     * @param image    1
     * @param sr       2
     * @param sc       3
     * @param newColor 4
     * @return int[][]
     * @author xianzilei
     * @date 2020/8/17 9:11
     **/
    public int[][] floodFillDfs(int[][] image, int sr, int sc, int newColor) {
        int srcColor = image[sr][sc];
        if (srcColor == newColor) {
            return image;
        }
        dfs(sr, sc, srcColor, newColor, image);
        return image;
    }

    private void dfs(int curRow, int curCol, int srcColor, int newColor, int[][] image) {
        if (curRow < 0 || curRow >= image.length || curCol < 0 || curCol >= image[0].length || image[curRow][curCol] != srcColor) {
            return;
        }
        image[curRow][curCol] = newColor;
        //上
        dfs(curRow - 1, curCol, srcColor, newColor, image);
        //下
        dfs(curRow + 1, curCol, srcColor, newColor, image);
        //左
        dfs(curRow, curCol - 1, srcColor, newColor, image);
        //右
        dfs(curRow, curCol + 1, srcColor, newColor, image);
    }

    public static void main(String[] args) {
        Solution733 solution733 = new Solution733();
        int[][] image1 = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int[][] result1 = solution733.floodFillBfs(image1, 1, 1, 2);
        for (int[] arr : result1) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("-----------------------------------------------");
        int[][] image2 = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int[][] result2 = solution733.floodFillDfs(image2, 1, 1, 2);
        for (int[] arr : result2) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
