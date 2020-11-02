package solutions.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 单词拆分
 *
 * @author : xianzilei
 * @date : 2020/11/2 8:28
 */
public class Solution139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        //状态转移方程dp[i]：字符串前i个字符组成的字符串是否可以拆分成数组中的元素（0<=i<=s.length）
        //则我们可以将字符串从j(0<j<i)处开始切割，判断剩下的两部分是否满足条件，即将问题拆分为子问题，
        //这里的j需要从[0,i]中进行枚举（主需要存在一个满足条件即可）
        //其中dp[0]=true;
        //本题结果为dp[s.length]

        Set<String> set = new HashSet<>(wordDict);
        int length = s.length();
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;
        for (int i = 1; i <= length; i++) {
            for (int j = 0; j < i; j++) {
                String sub = s.substring(j, i);
                if (dp[j] && set.contains(sub)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[length];
    }

    public static void main(String[] args) {
        Solution139 solution139 = new Solution139();
        System.out.println(solution139.wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(solution139.wordBreak("applepenapple", Arrays.asList("apple", "pen")));
    }
}
