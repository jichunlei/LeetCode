package solutions.dfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 扫雷游戏
 *
 * @author : xianzilei
 * @date : 2020/8/20
 */
public class Solution529 {

    /**
     * 解法一：DFS
     *
     * @param board 1
     * @param click 2
     * @return char[][]
     * @author xianzilei
     * @date 2020/8/20 9:20
     **/
    public char[][] updateBoardDfs(char[][] board, int[] click) {
        int i = click[0];
        int j = click[1];
        if (board[i][j] == 'M') {
            board[i][j] = 'X';
        } else {
            dfs(i, j, board);
        }
        return board;
    }

    //处理坐标(i,j)
    private void dfs(int i, int j, char[][] board) {
        //递归终止条件
        //当出界或者该点不为E时，结束递归
        //该点不为E时的情况
        //（1）该点是地雷M
        //（2）该点是已经被dfs访问过（可能为B，也可能为数字）
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || board[i][j] != 'E') {
            return;
        }
        //求以(i,j)为中心的3x3的方格内的地雷数
        int num = getLandmineNum(i, j, board);
        //如果地雷数大于0，则该点设置为地雷数
        if (num > 0) {
            board[i][j] = (char) (num + 48);
        }
        //否则该点设置为B，且递归处理其周边8个节点
        else {
            board[i][j] = 'B';
            //上
            dfs(i - 1, j, board);
            //下
            dfs(i + 1, j, board);
            //左
            dfs(i, j - 1, board);
            //右
            dfs(i, j + 1, board);
            //左上
            dfs(i - 1, j - 1, board);
            //右上
            dfs(i - 1, j + 1, board);
            //左下
            dfs(i + 1, j - 1, board);
            //右下
            dfs(i + 1, j + 1, board);
        }
    }

    //获取以(i,j)为中心的3x3的方格内的地雷数
    private int getLandmineNum(int i, int j, char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int num = 0;
        //上
        if (i > 0 && board[i - 1][j] == 'M') {
            num++;
        }
        //下
        if (i < row - 1 && board[i + 1][j] == 'M') {
            num++;
        }
        //左
        if (j > 0 && board[i][j - 1] == 'M') {
            num++;
        }
        //右
        if (j < col - 1 && board[i][j + 1] == 'M') {
            num++;
        }
        //左上
        if (i > 0 && j > 0 && board[i - 1][j - 1] == 'M') {
            num++;
        }
        //右上
        if (i > 0 && j < col - 1 && board[i - 1][j + 1] == 'M') {
            num++;
        }
        //左下
        if (i < row - 1 && j > 0 && board[i + 1][j - 1] == 'M') {
            num++;
        }
        //右下
        if (i < row - 1 && j < col - 1 && board[i + 1][j + 1] == 'M') {
            num++;
        }
        return num;
    }

    public char[][] updateBoardBfs(char[][] board, int[] click) {
        int row = board.length;
        int col = board[0].length;
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
        } else {
            //定义队列
            Queue<int[]> queue = new LinkedList<>();
            //当前节点入队
            queue.offer(click);
            while (!queue.isEmpty()) {
                //出队
                int[] poll = queue.poll();
                int i = poll[0];
                int j = poll[1];
                //求周边地雷数
                int num = getLandmineNum(i, j, board);
                //如果地雷数大于0，则该点设置为地雷数
                if (num > 0) {
                    board[i][j] = (char) (num + 48);
                }
                //否则该点设置为B，并且将周边8个节点入队
                else {
                    board[i][j] = 'B';
                    //上
                    if (i > 0 && board[i - 1][j] == 'E') {
                        queue.offer(new int[]{i - 1, j});
                    }
                    //下
                    if (i < row - 1 && board[i + 1][j] == 'E') {
                        queue.offer(new int[]{i + 1, j});
                    }
                    //左
                    if (j > 0 && board[i][j - 1] == 'E') {
                        queue.offer(new int[]{i, j - 1});
                    }
                    //右
                    if (j < col - 1 && board[i][j + 1] == 'E') {
                        queue.offer(new int[]{i, j + 1});
                    }
                    //左上
                    if (i > 0 && j > 0 && board[i - 1][j - 1] == 'E') {
                        queue.offer(new int[]{i - 1, j - 1});
                    }
                    //右上
                    if (i > 0 && j < col - 1 && board[i - 1][j + 1] == 'E') {
                        queue.offer(new int[]{i - 1, j + 1});
                    }
                    //左下
                    if (i < row - 1 && j > 0 && board[i + 1][j - 1] == 'E') {
                        queue.offer(new int[]{i + 1, j - 1});
                    }
                    //右下
                    if (i < row - 1 && j < col - 1 && board[i + 1][j + 1] == 'E') {
                        queue.offer(new int[]{i + 1, j + 1});
                    }
                }
            }
        }
        return board;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'M'},
                {'E', 'E', 'M', 'E', 'E', 'E', 'E', 'E'},
                {'M', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'M', 'E', 'E', 'E', 'E'}
        };
        int[] click = {0, 0};
        Solution529 solution529 = new Solution529();
        solution529.updateBoardBfs(board, click);
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }

        //"B","B","B","B","B","B","1","E"
        //"B","1","1","1","B","B","1","M"
        //"1","2","M","1","B","B","1","1"
        //"M","2","1","1","B","B","B","B"
        //"1","1","B","B","B","B","B","B"
        //"B","B","B","B","B","B","B","B"
        //"B","1","2","2","1","B","B","B"
        //"B","1","M","M","1","B","B","B"
    }
}

