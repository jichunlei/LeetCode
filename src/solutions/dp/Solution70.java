package solutions.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 爬楼梯
 *
 * @author : xianzilei
 * @date : 2020/6/13 08:41
 */
public class Solution70 {


    /**
     * 动态规划（递归）
     * 状态转移方程：f(n)=f(n-1)+f(n-2)
     * 已知条件：f(1)=1,f(2)=2
     *
     * @param n 1
     * @return int
     * @author xianzilei
     * @date 2020/6/13 8:43
     **/
    public static int climbStairs1(int n) {
        if (n <= 2) {
            return n;
        }
        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }


    //备忘录
    private static Map<Integer, Integer> map = new HashMap<>();

    /**
     * 动态规划（递归，带备忘录，避免重复计算）
     *
     * @param n 1
     * @return int
     * @author xianzilei
     * @date 2020/6/13 8:50
     **/
    public static int climbStairs2(int n) {
        if (n <= 2) {
            return n;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int result = climbStairs2(n - 1) + climbStairs2(n - 2);
        map.put(n, result);
        return result;
    }

    /**
     * 动态规划（迭代）
     *
     * @param n 1
     * @return int
     * @author xianzilei
     * @date 2020/6/13 8:50
     **/
    public static int climbStairs3(int n) {
        if (n <= 2) {
            return n;
        }
        int f1 = 1, f2 = 2;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = f1 + f2;
            f1 = f2;
            f2 = result;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs1(45));
        System.out.println(climbStairs2(45));
        System.out.println(climbStairs3(45));
    }
}
