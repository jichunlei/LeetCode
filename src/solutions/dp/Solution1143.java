package solutions.dp;

/**
 * 最长公共子序列
 *
 * @author : xianzilei
 * @date : 2020/7/1 16:12
 */
public class Solution1143 {
    /**
     * 动态规划
     *
     * @param text1 1
     * @param text2 2
     * @return int
     * @author xianzilei
     * @date 2020/7/1 16:48
     **/
    public static int longestCommonSubsequence(String text1, String text2) {
        //dp[i][j]：text1前i的子串和text2前j的子串的最长公共子序列长度
        //dp[i][0]=0;
        //dp[0][j]=0;
        //dp[i][j]=dp[i-1][j-1]+1，当text1的第i字符等于text2的第j字符
        //dp[i][j]=max{dp[i][j - 1], dp[i - 1][j]}，当text1的第i字符不等于text2的第j字符
        //注意：这里不写max{dp[i][j-1],dp[i-1][j],dp[i-1][j-1]}是因为dp[i-1][j-1]永远是最三个最小的
        int length1 = text1.length();
        int length2 = text2.length();
        //定义状态方程
        int[][] dp = new int[length1 + 1][length2 + 1];
        //双层循环求数组值
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                //当text1的第i字符等于text2的第j字符
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                //当text1的第i字符不等于text2的第j字符
                else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        //返回结果
        return dp[length1][length2];
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "ace"));
    }
}
