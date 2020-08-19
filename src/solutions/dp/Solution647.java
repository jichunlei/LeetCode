package solutions.dp;

/**
 * 回文子串
 *
 * @author : xianzilei
 * @date : 2020/8/19
 */
public class Solution647 {

    /**
     * 解法一：动态规划
     *
     * @param s 1
     * @return int
     * @author xianzilei
     * @date 2020/8/19 9:10
     **/
    public int countSubstrings1(String s) {
        //状态转移方程dp[i][j]：区间[i,j]是否为回文子串，此处0<=i<=j<字符串长度
        //（1）dp[i][j]=true(当i==j时)
        //（2）dp[i][j]=(s[i]==s[j])(当i+1=j)
        //（3）dp[i][j]=(s[i]==s[j])&&dp[i+1,j-1](当i+1<j)
        //本题结果为dp数组的true个数

        if (s == null || s.length() == 0) {
            return 0;
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        }
        for (int i = s.length() - 2; i >= 0; i--) {
            for (int j = s.length() - 1; j > i + 1; j--) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1];
            }
        }
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (dp[i][j]) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * 解法二：中心扩展法
     *
     * @param s 1
     * @return int
     * @author xianzilei
     * @date 2020/8/19 9:11
     **/
    public int countSubstrings2(String s) {
        //特殊情况的排除
        if (s == null || s.length() == 0) {
            return 0;
        }
        //由于单个字符串也是回文串，直接累加到结果中
        int result = s.length();
        //定义左右边界
        int left = 0;
        int right = 0;
        //回文数为奇数时的计算
        for (int i = 1; i < s.length() - 1; i++) {
            //初始化左右边界值
            left = i - 1;
            right = i + 1;
            //以i位置为回文串的中心，向两边扩散
            while (left >= 0 && right < s.length()) {
                //如果左右边界满足条件，结果+1，继续扩散
                if (s.charAt(left) == s.charAt(right)) {
                    result++;
                    left--;
                    right++;
                }
                //否则直接结束循环，进行下一个中心点的计算
                else {
                    break;
                }
            }
        }

        //回文数为偶数时的计算
        for (int i = 0; i < s.length() - 1; i++) {
            //初始化左右边界
            left = i;
            right = i + 1;
            //以i和i+1的空隙作为回文数中心
            while (left >= 0 && right < s.length()) {
                if (s.charAt(left) == s.charAt(right)) {
                    result++;
                    left--;
                    right++;
                } else {
                    break;
                }
            }
        }

        //返回结果
        return result;
    }

    public static void main(String[] args) {
        Solution647 solution647 = new Solution647();
        System.out.println(solution647.countSubstrings1("aaaaa"));
        System.out.println(solution647.countSubstrings2("aaaaa"));
    }
}
