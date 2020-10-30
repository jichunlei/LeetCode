package solutions.hash;

/**
 * 岛屿的周长
 *
 * @author : xianzilei
 * @date : 2020/10/30 8:20
 */
public class Solution463 {
    /**
     * 解法一：迭代法
     *
     * @param grid 1
     * @return int
     * @author xianzilei
     * @date 2020/10/30 8:44
     **/
    public int islandPerimeter(int[][] grid) {
        //解题思路：遍历数组，计算每个土地方块的贡献边数（即土地方块与水域相交或处于边界处）
        int row = grid.length;
        int col = grid[0].length;
        int result = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                //上
                if (i == 0 || grid[i - 1][j] == 0) {
                    result++;
                }
                //下
                if (i == row - 1 || grid[i + 1][j] == 0) {
                    result++;
                }
                //左
                if (j == 0 || grid[i][j - 1] == 0) {
                    result++;
                }
                //右
                if (j == col - 1 || grid[i][j + 1] == 0) {
                    result++;
                }
            }
        }
        //返回结果
        return result;
    }

    /**
     * 解法二：迭代法（数学法）
     *
     * @param grid 1
     * @return int
     * @author xianzilei
     * @date 2020/10/30 8:50
     **/
    public int islandPerimeter2(int[][] grid) {
        //解题思路：每块土地可以提供4个边界，如果两个土地存在交界需要扣除2，
        //所以可以求出土地个数land和交界个数border
        //则结果就为land*4-border*2

        int row = grid.length;
        int col = grid[0].length;
        //土地方块数
        int land = 0;
        //交界个数
        int border = 0;
        //遍历数组
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //如果是水域，直接跳过
                if (grid[i][j] == 0) {
                    continue;
                }
                //土地数+1
                land++;
                //为了避免重复计算交界的个数，每个土地方块只计算右下是否存在交界
                //判断下边界是否存在交界
                if (i < row - 1 && grid[i + 1][j] == 1) {
                    border++;
                }
                //判断右边界是否存在交界
                if (j < col - 1 && grid[i][j + 1] == 1) {
                    border++;
                }
            }
        }
        //返回结果
        return land * 4 - border * 2;
    }

    public static void main(String[] args) {
        Solution463 solution463 = new Solution463();
        System.out.println(solution463.islandPerimeter2(new int[][]{
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}}));
    }
}
