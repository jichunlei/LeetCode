package questions.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 *
 * @author : xianzilei
 * @date : 2020/6/5 07:53
 */
public class Solution54 {

    /**
     * 解法一：四边界法
     *
     * @param matrix 1
     * @return java.util.List<java.lang.Integer>
     * @author xianzilei
     * @date 2020/6/5 8:35
     **/
    public static List<Integer> spiralOrder1(int[][] matrix) {
        //如果数组为空，直接返回空集合
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }
        //left：左边界；right：右边界；top：上边界；bottom：下边界
        int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1;
        //结果集，大小为数组元素个数
        ArrayList<Integer> result = new ArrayList<>((right + 1) * (bottom + 1));
        //循环处理
        while (true) {
            //1.从左往右遍历
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            //判断上下边界是否重合，如果是则退出循环，否则继续执行
            if (++top > bottom) {
                break;
            }

            //2.从上往下遍历
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            //判断左右边界是否重合，如果是则退出循环，否则继续执行
            if (--right < left) {
                break;
            }

            //3.从右往左遍历
            for (int i = right; i >= left; i--) {
                result.add(matrix[bottom][i]);
            }
            //判断上下边界是否重合，如果是则退出循环，否则继续执行
            if (--bottom < top) {
                break;
            }

            //4.从下往上遍历
            for (int i = bottom; i >= top; i--) {
                result.add(matrix[i][left]);
            }
            //判断左右边界是否重合，如果是则退出循环，否则继续执行
            if (++left > right) {
                break;
            }
        }
        return result;
    }

    /**
     * 解法二：路径调整法
     *
     * @param matrix 1
     * @return java.util.List<java.lang.Integer>
     * @author xianzilei
     * @date 2020/6/5 8:36
     **/
    public static List<Integer> spiralOrder2(int[][] matrix) {
        //如果数组为空，直接返回空集合
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }
        //总行数
        int rows = matrix.length;
        //总列数
        int columns = matrix[0].length;
        //总的元素个数
        int total = rows * columns;
        //结果集，长度为total
        List<Integer> result = new ArrayList<>(rows * columns);
        //二维数组，保存该位置是否被访问过
        boolean[][] visited = new boolean[rows][columns];
        //转向集合，顺序为逆时针
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        //行开始位置
        int row = 0;
        //列开始位置
        int column = 0;
        //转向初始位置
        int directionIndex = 0;
        //循环次数为元素个数
        for (int i = 0; i < total; i++) {
            //将遇到的第一个元素加入结果集
            result.add(matrix[row][column]);
            //该位置元素的访问属性为已访问
            visited[row][column] = true;
            //计算下一位置的行和列
            int nextRow = row + directions[directionIndex][0];
            int nextColumn = column + directions[directionIndex][1];
            //如果计算后的位置出界或者已经被访问了，则需要逆时针调整走向
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0
                    || nextColumn >= columns || visited[nextRow][nextColumn]) {
                //逆时针调整走向
                directionIndex = (directionIndex + 1) % 4;
            }
            //计算得到新的行列位置
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(spiralOrder1(matrix));
        System.out.println(spiralOrder2(matrix));
    }
}
