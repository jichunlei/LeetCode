package solutions.dp;

/**
 * 不同的二叉搜索树
 *
 * @author : xianzilei
 * @date : 2020/7/15 08:04
 */
public class Solution96 {

    /**
     * 解法：动态规划
     *
     * @param n 1
     * @return int
     * @author xianzilei
     * @date 2020/7/15 8:12
     **/
    public static int numTrees(int n) {
        //状态转移方程dp(x)：表示以 1 ... x 为节点组成的二叉搜索树的个数，所以题解答案是dp(n)
        //dp(0)=1;当节点为0个时，只能构造一个空树
        //dp(x)：当x>0时，可以计算以每一个节点作为根节点的树个数，然后累加起来就可以，其左右子树也可以采用该逻辑，因此就具有重复子问题
        //定义函数f(i,x)：表示以i节点作为根节点，x为总结点数组成的二叉搜索树的个数
        //即dp(x)=f(1,x)+f(2,x)+...+f(i,x)+...+f(x,x);(当1<=i<=x)
        //对于函数f(i,x)，它的左子树可构成的二叉搜索树的个数为dp(i-1)，右子树为dp(x-i)
        //所以f(i,x)=dp(i-1)*dp(x-i)
        //所以dp(x)=dp(0)*dp(x-1)+dp(1)*dp(x-2)+...+dp(i-1)*dp(x-i)+...+dp(x-1)*dp(0);(当1<=i<=x)

        //定义状态转移数组
        int[] dp = new int[n + 1];
        //初始化已知值
        dp[0] = 1;
        //求解数组中的每一个值
        for (int i = 1; i <= n; i++) {
            //求和
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        //返回结果
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numTrees(3));
    }
}
