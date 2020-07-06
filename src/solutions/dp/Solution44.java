package solutions.dp;

/**
 * 通配符匹配-4TODO
 *
 * @author : xianzilei
 * @date : 2020/7/6 13:09
 */
public class Solution44 {
    public static boolean isMatch(String s, String p) {
        //状态转移方程dp[i][j]：表示字符串s的前i个字符和模式串p的前j个字符是否匹配
        //--dp[0][0]=true;
        //--dp[i][0]=false（i>0）;
        //--dp[0][j]（i>0）;当模式串p前j个元素全部为*时为true，否则为false
        //--dp[i][j](i>0且j>0),假设字符串第i个字符为s(i),模式串第j个字符为p(j)
        //----(1)、当p(j)为字母时，s(i)==p(j)才能匹配
        //------当p(j)==s(j),dp[i][j]=dp[i-1][j-1]
        //------当p(j)!=s(j),dp[i][j]=false
        //----(2)、当p(j)为?时，对于s(i)无任何要求，即dp[i][j]=dp[i-1][j-1]
        //----(3)、当p(j)为*时，对于s(i)也无任何要求
        //------当使用*号时，dp[i][j]=dp[i-1][j]
        //------当不使用*号时，dp[i][j]=dp[i][j-1]
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j-1) == '*') {
                dp[0][j] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char chs = s.charAt(i - 1);
                char cha = p.charAt(j - 1);
                if (cha >= 'a' && cha <= 'z') {
                    dp[i][j] = cha == chs && dp[i - 1][j - 1];
                } else if (cha == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));
        System.out.println(isMatch("aa", "*"));
        System.out.println(isMatch("cb", "?a"));
        System.out.println(isMatch("adceb", "*a*b"));
        System.out.println(isMatch("acdcb", "a*c?b"));
    }
}
