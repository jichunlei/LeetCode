package solutions.dp;

/**
 * 正则表达式匹配--TODO
 *
 * @author : xianzilei
 * @date : 2020/7/6 11:14
 */
public class Solution10 {
    public static boolean isMatch(String s, String p) {
        //状态转移数组dp[i][j]：表示字符串s前i个字符串与模式串p前j个字符串是否匹配
        //dp[0][0]=true;
        //dp[0][j](j>0)：当模式串偶数位置为*时返回true，否则后面全部为false
        //dp[i][0]=false;
        //dp[i][j]：假设s(i)表示字符串s第i个字符，p(j)表示模式串p的第j个字符
        //--当p(j)为字母时
        //----当p(j)==s(i)：dp[i][j]=dp[i-1][j-1];
        //----当p(j)!=s(i)：dp[i][j]=false;
        //--当p(j)为.时，因为?可以匹配任意一个字符，所以dp[i][j]=dp[i-1][j-1];
        //--当p(j)为*时
        //----当p(j-1)为字母且p(j-1)!=s(i)时（类似###a和###b*），说明此时a和b*无法匹配，只能使用*把b消失，此时dp[i][j]=dp[i][j - 2]
        //----当p(j-1)为.或者p(j-1)==s(i)时（类似###a和###a*，或###a和###.*），此时分三种情况（）
        //------当使用*匹配多个字符时，dp[i][j]=dp[i-1][j]
        //------当使用*匹配单个字符时，dp[i][j]=dp[i][j-1]
        //------当使用*匹配0个字符时，即消除前一个字符，dp[i][j]=dp[i][j-2]
        //------上述三种情况只要有一种情况满足就算匹配，因此dp[i][j]=dp[i-1][j]||dp[i][j-1]||dp[i][j]=dp[i][j-2]

        int m = s.length();
        int n = p.length();
        //定义状态转移数组
        boolean[][] dp = new boolean[m + 1][n + 1];
        //初始化已知值
        dp[0][0] = true;
        for (int j = 2; j <= n; j = j + 2) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = true;
            } else {
                break;
            }
        }
        //计算二维数组每一位的值
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //如果p(j)为字母
                if (p.charAt(j - 1) >= 'a' && p.charAt(j - 1) <= 'z') {
                    //只有当p(j)与s(i)相等时，dp[i][j] = dp[i - 1][j - 1]，否则为false
                    if (p.charAt(j - 1) == s.charAt(i - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
                //如果p(j)为.，可以匹配任何字符
                else if (p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                //如果p(j)为*
                else {
                    //当p(j-1)为字母且p(j-1)!=s(i)时（类似###a和###b*），
                    // 说明此时a和b*无法匹配，只能使用*把b消失，
                    // 此时dp[i][j]=dp[i][j - 2]
                    if (p.charAt(j - 2) != s.charAt(i - 1) && p.charAt(j - 2) != '.') {
                        dp[i][j] = dp[i][j - 2];
                    }
                    //当p(j-1)为.或者p(j-1)==s(i)时（类似###a和###a*，或###a和###.*），此时分三种情况
                    //当使用*匹配多个字符时，dp[i][j]=dp[i-1][j]
                    //当使用*匹配单个字符时，dp[i][j]=dp[i][j-1]
                    //当使用*匹配0个字符时，即消除前一个字符，dp[i][j]=dp[i][j-2]
                    //上述三种情况只要有一种情况满足就算匹配，
                    // 因此dp[i][j]=dp[i-1][j]||dp[i][j-1]||dp[i][j]=dp[i][j-2]
                    else {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i][j - 2];
                    }
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("mississippi", "mis*is*p*."));
    }
}
