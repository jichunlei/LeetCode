package solutions.dp;

/**
 * 判断子序列
 *
 * @author : xianzilei
 * @date : 2020/7/27 21:29
 */
public class Solution392 {
    /**
     * 解法一：双指针法
     *
     * @param s 1
     * @param t 2
     * @return boolean
     * @author xianzilei
     * @date 2020/7/27 21:43
     **/
    public static boolean isSubsequence1(String s, String t) {
        int length1 = s.length();
        int length2 = t.length();
        int point1 = 0;
        int point2 = 0;
        while (point1 < length1 && point2 < length2) {
            if (s.charAt(point1) == t.charAt(point2)) {
                point1++;
            }
            point2++;
        }
        return point1 == length1;
    }

    /**
     * 解法二：动态规划
     *
     * @param s 1
     * @param t 2
     * @return boolean
     * @author xianzilei
     * @date 2020/7/27 21:48
     **/
    public static boolean isSubsequence2(String s, String t) {
        //状态转移数组dp[i][j]：t的前j个字符组成的串是否包含s的前i个字符组成的串
        //dp[0][0]=true;
        //dp[0][j]=true;
        //dp[i][0]=false;当i>0时
        //(1)dp[i][j]=dp[i-1][j-1]  当s[i]=t[j]时
        //(2)dp[i][j]=dp[i][j-1]  当s[i]!=t[j]时
        //该题结果为dp[s.length][t.length]
        int length1 = s.length();
        int length2 = t.length();
        boolean[][] dp = new boolean[length1 + 1][length2 + 1];
        for (int j = 0; j <= length2; j++) {
            dp[0][j] = true;
        }
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[length1][length2];
    }

    public static void main(String[] args) {
        System.out.println(isSubsequence1("ioi", ""));
        System.out.println(isSubsequence1("axc", "ahbgdc"));
        System.out.println(isSubsequence2("abc", "ahbgdc"));
        System.out.println(isSubsequence2("axc", "ahbgdc"));
    }
}
