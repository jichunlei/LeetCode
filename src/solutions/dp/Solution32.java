package solutions.dp;

/**
 * 最长有效括号--TODO
 *
 * @author : xianzilei
 * @date : 2020/7/6 16:02
 */
public class Solution32 {
    /**
     * 解法一：动态规划
     *
     * @param s 1
     * @return int
     * @author xianzilei
     * @date 2020/7/6 18:52
     **/
    public static int longestValidParentheses(String s) {
        //状态转移方程dp[i]：表示以字符s[i]结尾的最长有效括号的长度
        //dp[0]=0;
        //dp[i]=0;当是s[i]=='('
        //当s[i]=')'且s[i-1]=='('时，dp[i]=dp[i-2]+2;类似字符串：...()
        //当s[i]=')'且s[i-1]==')'
        //--当s[i-dp[i-1]-1]=='('，dp[i]=dp[i-1]+2+dp[i-dp[i-1]-2];类似字符串：((...))
        //--当s[i-dp[i-1]-1]==')',dp[i]=0;

        //特殊情况的判断
        if (s == null || s.length() < 2) {
            return 0;
        }
        //初始化结果
        int result = 0;
        //状态转移数组
        int[] dp = new int[s.length()];
        //从1位置开始计算填充数组的值
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                //字符串类似...()型
                if (s.charAt(i - 1) == '(') {
                    //边界值处理
                    if (i - 2 > 0) {
                        dp[i] = dp[i - 2] + 2;
                    } else {
                        dp[i] = 2;
                    }
                }
                //字符串类似((...))型
                else {
                    //边界值处理，且判断s[i-dp[i-1]-1]==')'的结果
                    if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        //边界值处理
                        if (i - dp[i - 1] - 2 >= 0) {
                            dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2];
                        } else {
                            dp[i] = dp[i - 1] + 2;
                        }
                    }
                }
            }
            //更新数组最大值
            result = Math.max(result, dp[i]);
        }
        //返回结果
        return result;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")()())"));
    }
}
