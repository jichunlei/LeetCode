package solutions.dp;

/**
 * 编辑距离
 *
 * @author : xianzilei
 * @date : 2020/6/29 15:20
 */
public class Solution72 {

    public static int minDistance(String word1, String word2) {
        //状态转移方程dp[i][j]：从word1(0,i)变化到word2(0,j)所需要的最少操作数==>i,j表示第几个字母，非索引
        //dp[i][0]=i;
        //dp[0][j]=j;
        //(1)、当word1[i]=word2[j]，则dp[i][j]=dp[i-1][j-1]
        //(2)、当word1[i]!=word2[j]
        //(2.1)、如果考虑修改word1[i]为word2[j]，则dp[i][j]=dp[i-1][j-1]+1
        //(2.2)、如果考虑删除word1[i]，则dp[i][j]=dp[i-1][j]+1
        //(2.3)、如果考虑新增word2[j]，则dp[i][j]=dp[i][j-1]+1
        //因此此时取三种情况下的最小值dp[i][j]=min{dp[i-1][j-1]+1,dp[i-1][j]+1,dp[i][j-1]+1}
        //即dp[i][j]=min{dp[i-1][j-1],dp[i-1][j],dp[i][j-1]}+1
        //本题结果为dp[word1.length()][word2.length()]
        int l1 = word1.length();
        int l2 = word2.length();
        //定义状态转移数组
        int[][] dp = new int[l1 + 1][l2 + 1];
        //初始化已知值
        //dp[i][0]=i
        for (int i = 0; i <= l1; i++) {
            dp[i][0] = i;
        }
        //dp[0][j]=j
        for (int j = 0; j <= l2; j++) {
            dp[0][j] = j;
        }
        //双层循环计算状态数组的每一个值
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                }
            }
        }
        //返回结果
        return dp[l1][l2];
    }

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
    }
}
