package solutions.greedy;

/**
 * 加油站--TODO
 *
 * @author : xianzilei
 * @date : 2020/11/19 9:24
 */
public class Solution134 {
    /**
     * 解法一：暴力枚举法
     *
     * @param gas  1
     * @param cost 2
     * @return int
     * @author xianzilei
     * @date 2020/11/20 9:02
     **/
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int count = gas.length;
        for (int i = 0; i < count; i++) {
            int cur = 0;
            boolean flag = true;
            for (int j = 0; j < count; j++) {
                int from = (i + j) % count;
                cur = cur + gas[from] - cost[from];
                if (cur < 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 解法二：解法一优化
     *
     * @param gas  1
     * @param cost 2
     * @return int
     * @author xianzilei
     * @date 2020/11/20 9:17
     **/
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        //从x号加油站出发最多可以到达的站点为y
        //如果x==y，则表示可以环绕一圈
        //如果x!=y，则可以证明如果选择(x,y)中的任意一个站点出发，最多也只能到达y站点
        //所以下一次计算只需要从y站点进行出发的计算即可
        //这样大大减少了计算的次数

        int count = gas.length;
        //i表示当前出发点，这里不自增，而是通过最大的到达站点进行更新
        for (int i = 0; i < count; ) {
            //当前油量剩余
            int cur = 0;
            //标记当前出发点是否跑完全程
            boolean flag = true;
            //记录从开始点跑过的站点数
            int j = 0;
            //开始跑圈
            for (; j < count; j++) {
                //当前所在站点
                int from = (i + j) % count;
                cur = cur + gas[from] - cost[from];
                //如果剩余油量小于0，直接退出计算
                if (cur < 0) {
                    flag = false;
                    break;
                }
            }
            //如果成功，直接返回
            if (flag) {
                return i;
            }
            //否则更新出发点为上一次到达的最远站点
            else {
                i = i + j + 1;
            }
        }
        //没有结果直接返回-1
        return -1;
    }

    public static void main(String[] args) {
        Solution134 solution134 = new Solution134();
        System.out.println(solution134.canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        System.out.println(solution134.canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3}));
        System.out.println(solution134.canCompleteCircuit2(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        System.out.println(solution134.canCompleteCircuit2(new int[]{2, 3, 4}, new int[]{3, 4, 3}));
    }
}
