package solutions.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 三角形最小路径和
 *
 * @author : xianzilei
 * @date : 2020/7/14 07:56
 */
public class Solution120 {

    private static int result = Integer.MAX_VALUE;

    /**
     * 解法一：回溯法
     *
     * @param triangle 1
     * @return int
     * @author xianzilei
     * @date 2020/7/14 8:46
     **/
    public static int minimumTotal1(List<List<Integer>> triangle) {
        if (triangle.size() == 0) {
            return 0;
        }
        backtrack(1, 0, triangle.get(0).get(0), triangle);
        return result;
    }

    //回溯方法
    private static void backtrack(int i, int j, int sum, List<List<Integer>> triangle) {
        if (i == triangle.size()) {
            result = Math.min(result, sum);
            return;
        }
        sum += triangle.get(i).get(j);
        backtrack(i + 1, j, sum, triangle);
        sum -= triangle.get(i).get(j);
        sum += triangle.get(i).get(j + 1);
        backtrack(i + 1, j + 1, sum, triangle);
    }

    /**
     * 解法二：dfs
     *
     * @param triangle 1
     * @return int
     * @author xianzilei
     * @date 2020/7/14 13:08
     **/
    public static int minimumTotal2(List<List<Integer>> triangle) {
        return dfs(0, 0, triangle);
    }

    //递归方法：dfs(i,j)表示坐标(i,j)到底边的最小路径和
    //条件方程：dfs(i,j)=min{dfs(i+1,j),dfs(i+1,j+1)}+triangle[i][j]
    //递归终止条件：i==triangle.size();
    private static int dfs(int i, int j, List<List<Integer>> triangle) {
        //递归终止条件
        if (i == triangle.size()) {
            return 0;
        }
        return Math.min(dfs(i + 1, j, triangle), dfs(i + 1, j + 1, triangle)) + triangle.get(i).get(j);
    }

    /**
     * 解法三：解法二优化（备忘录）
     *
     * @param triangle 1
     * @return int
     * @author xianzilei
     * @date 2020/7/14 13:46
     **/
    public static int minimumTotal3(List<List<Integer>> triangle) {
        int size = triangle.size();
        //定义备忘录
        int[][] memory = new int[size][size];
        return dfs(0, 0, triangle, memory);
    }

    //添加了备忘录的dfs
    private static int dfs(int i, int j, List<List<Integer>> triangle, int[][] memory) {
        //递归终止条件
        if (i == triangle.size()) {
            return 0;
        }
        //备忘录查询（非0则表示有值）
        if (memory[i][j] > 0) {
            return memory[i][j];
        }
        //递归求值
        return memory[i][j] = Math.min(dfs(i + 1, j, triangle, memory), dfs(i + 1, j + 1, triangle, memory))
                + triangle.get(i).get(j);
    }

    /**
     * 解法四：动态规划
     *
     * @param triangle 1
     * @return int
     * @author xianzilei
     * @date 2020/7/14 8:47
     **/
    public static int minimumTotal4(List<List<Integer>> triangle) {
        //解题思路
        //状态转移方程dp[i][j]：到达坐标(i,j)位置的最小路径和
        //dp[0][0]=triangle[0][0];
        //dp[i][0]=dp[i-1][0]+triangle[i][0];（当i>0）
        //dp[i][j]=min{dp[i-1][j],dp[i-1][j-1]}+triangle[i][j];（当i>0,j>0且j不为右边界）
        //dp[i][j]=dp[i-1][j-1]+triangle[i][j];（当i>0,j>0且j为右边界）
        int size = triangle.size();
        //定义状态转移数组
        int[][] dp = new int[size][size];
        //初始化已知值
        dp[0][0] = triangle.get(0).get(0);
        //初始化结果
        int res = Integer.MAX_VALUE;
        //初始化已知值
        for (int i = 1; i < size; i++) {
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
        }
        for (int i = 1; i < size; i++) {
            for (int j = 1; j < triangle.get(i).size(); j++) {
                if (j == triangle.get(i).size() - 1) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                }
            }
        }
        for (int i = 0; i < size; i++) {
            res = Math.min(res, dp[size - 1][i]);
        }
        return res;

    }

    /**
     * 解法五：解法四优化（状态压缩）
     *
     * @param triangle 1
     * @return int
     * @author xianzilei
     * @date 2020/7/14 17:37
     **/
    public static int minimumTotal5(List<List<Integer>> triangle) {
        //考虑到dp[i][j]只与dp[i-1][j]和dp[i-1][j-1]有关，即只与它的前一行有关
        //所以二维数组可以压缩为一维数组
        int size = triangle.size();
        int[] dp = new int[size];
        dp[0] = triangle.get(0).get(0);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < size; i++) {
            for (int j = triangle.get(i).size() - 1; j >= 0; j--) {
                //左边界的处理
                if (j == 0) {
                    dp[0] = dp[0] + triangle.get(i).get(0);
                }
                //右边界的处理
                else if (j == triangle.get(i).size() - 1) {
                    dp[j] = dp[j - 1] + triangle.get(i).get(j);
                }
                //非边界的处理
                else {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + triangle.get(i).get(j);
                }
            }
        }
        //求最小值
        for (int i = 0; i < size; i++) {
            res = Math.min(res, dp[i]);
        }
        //返回结果
        return res;
    }


    public static void main(String[] args) {
        // [2]
        // [3,4]
        // [6,5,7]
        // [4,1,8,3]
        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        List<Integer> list3 = new ArrayList<>();
        list3.add(6);
        list3.add(5);
        list3.add(7);
        List<Integer> list4 = new ArrayList<>();
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(list1);
        triangle.add(list2);
        triangle.add(list3);
        triangle.add(list4);
        System.out.println(minimumTotal1(triangle));
        System.out.println(minimumTotal2(triangle));
        System.out.println(minimumTotal3(triangle));
        System.out.println(minimumTotal4(triangle));
        System.out.println(minimumTotal5(triangle));
    }
}
