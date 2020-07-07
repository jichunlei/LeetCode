package solutions.dp;

import java.util.LinkedList;

/**
 * 最长有效括号
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
    public static int longestValidParentheses1(String s) {
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

    /**
     * 解法二：栈
     *
     * @param s 1
     * @return int
     * @author xianzilei
     * @date 2020/7/7 12:04
     **/
    public static int longestValidParentheses2(String s) {
        //特殊情况的判断
        if (s == null || s.length() < 2) {
            return 0;
        }
        //初始化结果为0
        int result = 0;
        //定义栈来存储元素的索引
        LinkedList<Integer> stack = new LinkedList<>();
        //初始时刻存放-1，表示当前计算范围的前一位置，目的是为了求当前计算范围的长度
        stack.addLast(-1);
        //遍历字符串
        for (int i = 0; i < s.length(); i++) {
            //如果为前半括号，直接索引入栈
            if (s.charAt(i) == '(') {
                stack.addFirst(i);
            }
            //如果是后半括号
            else {
                //直接出栈（这里不用担心栈为空导致没有元素能够出栈，因为栈底始终存放当前计算范围的前一位置索引值）
                stack.removeFirst();
                //如果此时栈为空，说明当前的计算范围结束，需要进行下一范围的计算，下一范围的前一位置索引即为当前i值
                if (stack.isEmpty()) {
                    //当前索引位置入队
                    stack.addFirst(i);
                }
                //如果栈不为空，则计算当前合法长度，更新result
                else {
                    result = Math.max(result, i - stack.peekFirst());
                }
            }
        }
        //返回结果
        return result;
    }

    /**
     * 解法三：双指针法
     *
     * @param s 1
     * @return int
     * @author xianzilei
     * @date 2020/7/7 13:27
     **/
    public static int longestValidParentheses3(String s) {
        //特殊情况的判断
        if (s == null || s.length() < 2) {
            return 0;
        }
        //定义左右指针，分别表示左右括号的个数
        int left = 0, right = 0;
        //初始化结果为0
        int result = 0;
        //从前往后遍历计算
        for (int i = 0; i < s.length(); i++) {
            //如果是左括号，左括号的个数加1
            if (s.charAt(i) == '(') {
                left++;
            }
            //如果是右括号，右括号的个数加1
            else {
                right++;
            }
            //当左括号和右括号相等，则表示当前属于一个有效区域，更新result
            if (left == right) {
                result = Math.max(result, left + right);
            }
            //当左括号个数小于右括号，说明当前位置不合法，left与right需清零重新计算
            else if (left < right) {
                left = right = 0;
            }
        }
        //前面是从前往后遍历的，但是对于字符串：(()，如果仅仅从前往后遍历是计算不出来的，
        // 所以还需要从后往前遍历计算
        //初始化左右指针
        left = right = 0;
        //从后往前遍历计算（以下同理）
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                right++;
            } else {
                left++;
            }
            if (left == right) {
                result = Math.max(result, left + right);
            } else if (left > right) {
                left = right = 0;
            }
        }
        //返回结果
        return result;
    }

    public static void main(String[] args) {
        //)()())
        System.out.println(longestValidParentheses1(")()())"));
        System.out.println(longestValidParentheses2(")()())"));
        System.out.println(longestValidParentheses3(")()())"));
    }
}
