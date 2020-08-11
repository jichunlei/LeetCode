package solutions.unionfind;

import pojo.UnionFind;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 被围绕的区域
 *
 * @author : xianzilei
 * @date : 2020/8/11
 */
public class Solution130 {
    /**
     * 解法一：DFS解法
     *
     * @param board 1
     * @return void
     * @author xianzilei
     * @date 2020/8/11 8:46
     **/
    public void solveDfs(char[][] board) {
        int length = board.length;
        //特殊情况的排除
        if (length <= 2) {
            return;
        }
        int width = board[0].length;
        if (width <= 2) {
            return;
        }
        //定义数组表示当前元素0是否可以被边界O访问到
        boolean[][] visited = new boolean[length][width];
        //从边界（纵向）为O出开始深度遍历
        for (int i = 0; i < length; i++) {
            dfs(i, 0, board, visited);
            dfs(i, width - 1, board, visited);
        }
        //从边界（横向）为O出开始深度遍历
        for (int j = 0; j < width; j++) {
            dfs(0, j, board, visited);
            dfs(length - 1, j, board, visited);
        }

        //循环查找替代O为X
        for (int i = 1; i < length - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                //当该位置为O且无法访问到，替换字符
                if (board[i][j] == 'O' && !visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    //深度优先遍历
    //方法作用：标记所有当前节点可以走到的节点O
    private void dfs(int i, int j, char[][] board, boolean[][] visited) {
        //递归结束条件：越界、已被标记、数值不为O
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length
                || visited[i][j] || board[i][j] != 'O') {
            return;
        }
        //标记当前节点
        visited[i][j] = true;
        //四个方向遍历
        dfs(i - 1, j, board, visited);
        dfs(i, j - 1, board, visited);
        dfs(i + 1, j, board, visited);
        dfs(i, j + 1, board, visited);
    }

    /**
     * 解法二：BFS
     *
     * @param board 1
     * @return void
     * @author xianzilei
     * @date 2020/8/11 8:48
     **/
    public void solveBfs(char[][] board) {
        //思路同DFS，只是遍历方式修改为广度优先遍历

        int length = board.length;
        //特殊情况的排除
        if (length <= 2) {
            return;
        }
        int width = board[0].length;
        if (width <= 2) {
            return;
        }
        //定义数组表示当前元素0是否可以被边界O访问到
        boolean[][] visited = new boolean[length][width];
        //定义队列保存坐标
        Queue<int[]> queue = new LinkedList<>();
        //将所有满足条件的边界节点入队
        for (int i = 0; i < length; i++) {
            if (board[i][0] == 'O') {
                visited[i][0] = true;
                queue.offer(new int[]{i, 0});
            }
            if (board[i][width - 1] == 'O') {
                visited[i][width - 1] = true;
                queue.offer(new int[]{i, width - 1});
            }
        }
        for (int j = 0; j < width; j++) {
            if (board[0][j] == 'O') {
                visited[0][j] = true;
                queue.offer(new int[]{0, j});
            }
            if (board[length - 1][j] == 'O') {
                visited[length - 1][j] = true;
                queue.offer(new int[]{length - 1, j});
            }
        }
        //遍历队列操作
        while (!queue.isEmpty()) {
            //出队
            int[] poll = queue.poll();
            //提取坐标
            int i = poll[0];
            int j = poll[1];
            //向上
            if (i > 0 && board[i - 1][j] == 'O' && !visited[i - 1][j]) {
                visited[i - 1][j] = true;
                queue.offer(new int[]{i - 1, j});
            }
            //向左
            if (j > 0 && board[i][j - 1] == 'O' && !visited[i][j - 1]) {
                visited[i][j - 1] = true;
                queue.offer(new int[]{i, j - 1});
            }
            //向下
            if (i < length - 1 && board[i + 1][j] == 'O' && !visited[i + 1][j]) {
                visited[i + 1][j] = true;
                queue.offer(new int[]{i + 1, j});
            }
            //向右
            if (j < width - 1 && board[i][j + 1] == 'O' && !visited[i][j + 1]) {
                visited[i][j + 1] = true;
                queue.offer(new int[]{i, j + 1});
            }
        }

        //遍历查找替换
        for (int i = 1; i < length - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void solveUnionFind(char[][] board) {
        int rows = board.length;
        //特殊情况的排除
        if (rows <= 2) {
            return;
        }
        int cols = board[0].length;
        if (cols <= 2) {
            return;
        }
        //定义边界值统一存放的组
        int boundary = rows * cols;
        //定义并查集
        //这里将二维矩阵展开为一维矩阵
        UnionFind unionFind = new UnionFind(rows * cols + 1);
        //循环矩阵进行分组合并
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //不为O直接跳过
                if (board[i][j] != 'O') {
                    continue;
                }
                //边界节点直接存放到boundary组
                if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                    unionFind.union(i * cols + j, boundary);
                }
                //非边界节点合并到满足条件的上下左右的组
                else {
                    if (board[i - 1][j] == 'O') {
                        unionFind.union(i * cols + j, (i - 1) * cols + j);
                    }
                    if (board[i][j - 1] == 'O') {
                        unionFind.union(i * cols + j, i * cols + j - 1);
                    }
                    if (board[i + 1][j] == 'O') {
                        unionFind.union(i * cols + j, (i + 1) * cols + j);
                    }
                    if (board[i][j + 1] == 'O') {
                        unionFind.union(i * cols + j, i * cols + j + 1);
                    }
                }

            }
        }
        //循环二维数组替换值
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //不属于边界组的O节点进行替换
                if (board[i][j] == 'O' && unionFind.find(i * cols + j) != boundary) {
                    board[i][j] = 'X';
                }
            }
        }
    }


    public static void main(String[] args) {
        char[][] board1 = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};
        char[][] board2 = {
                {'X', 'O', 'X', 'X'},
                {'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X'}};
        char[][] board3 = {
                {'X', 'O', 'X', 'X'},
                {'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X'}};
        Solution130 solution130 = new Solution130();
        solution130.solveDfs(board1);
        for (char[] chars : board1) {
            System.out.println(Arrays.toString(chars));
        }
        solution130.solveBfs(board2);
        for (char[] chars : board2) {
            System.out.println(Arrays.toString(chars));
        }
        solution130.solveUnionFind(board3);
        for (char[] chars : board3) {
            System.out.println(Arrays.toString(chars));
        }
    }
}
