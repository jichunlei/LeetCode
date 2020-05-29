package questions.stack;

import java.util.Arrays;

/**
 * 每日温度
 *
 * @author : xianzilei
 * @date : 2020/4/23 08:14
 */
public class Question739 {
    /**
     * 功能描述: 解法一--暴力遍历法
     *
     * @param T 1
     * @return int[]
     * @author xianzilei
     * @date 2020/4/23 8:20
     **/
    public static int[] dailyTemperatures1(int[] T) {
        int len = T.length;
        int[] result = new int[len];
        result[len - 1] = 0;
        for (int i = len - 1; i >= 0; i--) {
            int temp = T[i];
            int index = i;
            for (int j = i + 1; j < len; j++) {
                if (T[j] > T[i]) {
                    index = j;
                    break;
                }
            }
            result[i] = index - i;
        }
        return result;
    }

    /**
     * 功能描述: 解法二--
     *
     * @param T 1
     * @return int[]
     * @author xianzilei
     * @date 2020/4/23 8:37
     **/
    public static int[] dailyTemperatures2(int[] T) {
            int[] ans = new int[T.length];
            int[] next = new int[101];
            Arrays.fill(next, Integer.MAX_VALUE);
            for (int i = T.length - 1; i >= 0; --i) {
                int warmer_index = Integer.MAX_VALUE;
                for (int t = T[i] + 1; t <= 100; ++t) {
                    if (next[t] < warmer_index) {
                        warmer_index = next[t];
                    }
                }
                if (warmer_index < Integer.MAX_VALUE) {
                    ans[i] = warmer_index - i;
                }
                next[T[i]] = i;
            }
            return ans;
    }

    public static void main(String[] args) {
        int[] T1 = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dailyTemperatures1(T1)));
        System.out.println(Arrays.toString(dailyTemperatures2(T1)));
        int[] T2 = {30, 30, 30, 31, 50, 30, 30, 30};
        System.out.println(Arrays.toString(dailyTemperatures1(T2)));
        System.out.println(Arrays.toString(dailyTemperatures2(T2)));
        int[] T3 = {30};
        System.out.println(Arrays.toString(dailyTemperatures1(T3)));
        System.out.println(Arrays.toString(dailyTemperatures2(T3)));
    }
}
