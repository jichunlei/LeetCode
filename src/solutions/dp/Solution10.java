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
        //dp[0][j](j>0)：当模式串前j个字符全部为*，则为true，否则为false
        //dp[i][0]=false;
        //dp[i][j]：假设s(i)表示字符串s第i个字符，p(j)表示模式串p的第j个字符
        //--当p(j)为字母时
        //----当p(j)==s(i)：dp[i][j]=dp[i-1][j-1];
        //----当p(j)!=s(i)：dp[i][j]=false;
        //--当p(j)为?时，因为?可以匹配任意一个字符，所以dp[i][j]=dp[i-1][j-1];
        //--当p(j)为*时
        //----当使用*时

        int m = s.length();
        int n = p.length();
        int[][] dp = new int[m + 1][n + 1];
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isMatch("", ""));
    }
}
