package solutions.dp;

import java.util.Arrays;

/**
 * 视频拼接
 *
 * @author : xianzilei
 * @date : 2020/10/27 8:45
 */
public class Solution1024 {

    /**
     * 解法一：贪心算法
     *
     * @param clips 1
     * @param T     2
     * @return int
     * @author xianzilei
     * @date 2020/10/27 9:03
     **/
    public int videoStitching(int[][] clips, int T) {
        //解题思路：贪心算法，定义长度T的数组，索引代表数值，数组值记录当前索引可以到达的最大位置
        //每次都取最大的可达位置，最近得到的就是最优解（或者没有解）

        //初始化数组（索引表示数值，数组值表示当前索引可以到达的最大位置）
        int[] maxReach = new int[T];
        for (int[] clip : clips) {
            if (clip[0] < T) {
                maxReach[clip[0]] = Math.max(clip[1], maxReach[clip[0]]);
            }
        }
        //结果中上一次的最大结束位置
        int pre = 0;
        //当前遍历到的区间的最大结束位置
        int cur = 0;
        //结果
        int count = 0;
        for (int i = 0; i < T; i++) {
            //计算当前可以遍历到的最大结束位置
            cur = Math.max(maxReach[i], cur);
            //如果当前可以遍历到的最大结束位置等于当前位置索引，表示区间发生了断裂，无法链接起来，直接返回-1
            if (i == cur) {
                return -1;
            }
            //当遍历到上次的最大结束位置，表示上次的区间被征用了，计数+1，同时更新pre为cur
            if (i == pre) {
                count++;
                pre = cur;
            }
        }
        //返回结果
        return count;
    }

    /**
     * 解法二：动态规划
     *
     * @param clips 1
     * @param T     2
     * @return int
     * @author xianzilei
     * @date 2020/10/27 16:10
     **/
    public int videoStitching2(int[][] clips, int T) {
        //状态转移数组：dp[i]：拼接区间[0,i]所需要到的最小区间数量，本题所求结果为dp[T]
        //dp[0]=0;
        //遍历二维数组所有值，当clip[0]<x<=clip[1]，dp[x]=dp[clip[0]]+1
        //综合则为当clip[0]<x<clip[1]，dp[x]=min{dp[clip[0]]+1}
        //依次求出dp[1],dp[2],...,dp[T]

        int[] dp = new int[T + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for (int i = 1; i <= T; i++) {
            for (int[] clip : clips) {
                if (clip[0] < i && i <= clip[1]) {
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                }
            }
        }
        return dp[T] == Integer.MAX_VALUE - 1 ? -1 : dp[T];
    }

    public static void main(String[] args) {
        Solution1024 solution1024 = new Solution1024();
        System.out.println(solution1024.videoStitching(new int[][]{
                {0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}}, 10));
        System.out.println(solution1024.videoStitching(new int[][]{
                {0, 1}, {1, 2}}, 5));
        System.out.println(solution1024.videoStitching(new int[][]{
                {0, 1}, {6, 8}, {0, 2}, {5, 6}, {0, 4}, {0, 3}, {6, 7}, {1, 3},
                {4, 7}, {1, 4}, {2, 5}, {2, 6}, {3, 4}, {4, 5}, {5, 7}, {6, 9}}, 9));
        System.out.println(solution1024.videoStitching2(new int[][]{
                {0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}}, 10));
        System.out.println(solution1024.videoStitching2(new int[][]{
                {0, 1}, {1, 2}}, 5));
        System.out.println(solution1024.videoStitching2(new int[][]{
                {0, 1}, {6, 8}, {0, 2}, {5, 6}, {0, 4}, {0, 3}, {6, 7}, {1, 3},
                {4, 7}, {1, 4}, {2, 5}, {2, 6}, {3, 4}, {4, 5}, {5, 7}, {6, 9}}, 9));
    }
}
