package solutions.dp;

/**
 * 交错字符串
 *
 * @author : xianzilei
 * @date : 2020/7/29
 */
public class Solution97 {
    /**
     * 解法一：动态规划
     *
     * @param s1 1
     * @param s2 2
     * @param s3 3
     * @return boolean
     * @author xianzilei
     * @date 2020/7/29 19:02
     **/
    public static boolean isInterleave1(String s1, String s2, String s3) {
        //状态转移方程dp[i][j]：表示s1的前i个字符与s2的前j个字符是否可以交叉组成s3的前i+j的字符串
        //dp[0][0]=true;
        //当s3[i+j-1]与s1[i-1]和s2[j-1]都不相等时，dp[i][j]=false
        //当s3[i+j-1]=s1[i-1]时，dp[i][j]=dp[i-1][j]
        //当s3[i+j-1]=s2[j-1]时，dp[i][j]=dp[i][j-1]
        //所以dp[i][j]=(dp[i-1][j]&&s3[i+j-1]==s1[i-1])||(dp[i][j-1]&&s3[i+j-1]==s2[j-1])

        int length1 = s1.length();
        int length2 = s2.length();
        //特殊情况的排除
        if (length1 + length2 != s3.length()) {
            return false;
        }
        //定义状态转移数组
        boolean[][] dp = new boolean[length1 + 1][length2 + 1];
        //初始化已知值和边界值
        dp[0][0] = true;
        //计算上边界值
        for (int i = 1; i <= length1; i++) {
            if (s1.charAt(i - 1) == s3.charAt(i - 1)) {
                dp[i][0] = true;
            } else {
                break;
            }
        }
        //计算左边界值
        for (int j = 1; j <= length2; j++) {
            if (s2.charAt(j - 1) == s3.charAt(j - 1)) {
                dp[0][j] = true;
            } else {
                break;
            }
        }
        //双层循环填充二维数组的每一个值
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                dp[i][j] = (dp[i - 1][j] && s3.charAt(i + j - 1) == s1.charAt(i - 1))
                        || (dp[i][j - 1] && s3.charAt(i + j - 1) == s2.charAt(j - 1));
            }
        }
        //返回结果
        return dp[length1][length2];
    }

    public static void main(String[] args) {
        System.out.println(isInterleave1("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleave1("aabcc", "dbbca", "aadbbbaccc"));
    }
}
