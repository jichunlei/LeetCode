package solutions.dp;

/**
 * 四键键盘
 *
 * @author : xianzilei
 * @date : 2020/7/8 13:46
 */
public class Solution651 {

    /**
     * 常规思路+经验
     *
     * @param N 1
     * @return int
     * @author xianzilei
     * @date 2020/7/8 13:47
     **/
    public static int maxA(int N) {
        //CA的位置前至少两个A操作
        //最后一步操作要么为A，要么为CV
        //CA,CC,CV操作一定相连
        //贪心思想：为了得到最大A个数,CA和CC的操作必然只能有一次
        //操作流程类似：A,...,A,CA,CC,CV...CV
        //根据经验可以计算出N为6及以内直接写A最优
        if (N <= 6) {
            return N;
        }
        //初始化结果
        int result = 0;
        //CA,CC,CV整体从3位置向右滑动，左边补A，右边补CV，每次滑动一位，计算当前值，更新result
        for (int i = 3; i <= N - 2; i++) {
            //当前结果为：左边A的个数+右边CV个数*左边A的个数=左边A的个数*(右边CV个数+1)
            //其中左边A的个数为i-1
            //右边CV个数为N-CA操作-CC操作-左边A的个数，即N-1-1-(i-1)=N-i-1
            //所以当前结果为：(i-1)*(N-i-1+1)=(i-1)*(N-i)
            result = Math.max(result, (i - 1) * (N - i));
        }
        //返回结果
        return result;
    }

    public static void main(String[] args) {
        System.out.println(maxA(3));
    }
}
