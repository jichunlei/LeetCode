package solutions.stack;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 每日温度
 *
 * @author : xianzilei
 * @date : 2020/4/23 08:14
 */
public class Solution739 {
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
     * 功能描述: 解法二--优化遍历
     *
     * @param T 1
     * @return int[]
     * @author xianzilei
     * @date 2020/4/23 8:37
     **/
    public static int[] dailyTemperatures2(int[] T) {
        //结果集
        int[] ans = new int[T.length];
        //定义索引数组，索引值表示温度值，元素表示温度在T数组的索引位置
        int[] next = new int[101];
        //初始时刻值均为Integer最大值，可以认为是无穷大
        Arrays.fill(next, Integer.MAX_VALUE);
        //倒着遍历数组T
        for (int i = T.length - 1; i >= 0; --i) {
            //warmerIndex保存大于某一温度的最小索引值
            int warmerIndex = Integer.MAX_VALUE;
            //查找大于某一温度的最小索引值
            for (int t = T[i] + 1; t < 101; t++) {
                if (next[t] < warmerIndex) {
                    warmerIndex = next[t];
                }
            }
            //当最小索引值不是无穷大，则说明存在大于当前温度的元素
            if (warmerIndex < Integer.MAX_VALUE) {
                //求值
                ans[i] = warmerIndex - i;
            }
            //将当前元素保存到索引数组中，如果有重复温度值的会覆盖索引值，正好满足最近原则
            next[T[i]] = i;
        }
        //返回结果
        return ans;
    }

    /**
     * 解法三：单调栈法
     * <p>
     * 单调栈的应用：得到数组某索引对应的值，距离其最近的左侧或者右侧的比其大或者小的值的索引
     * 单调递增栈：从 栈底 到 栈顶 递增，栈顶大
     * 单调递减栈：从 栈底 到 栈顶 递减，栈顶小
     *
     * @param T 1
     * @return int[]
     * @author xianzilei
     * @date 2020/6/11 7:58
     **/
    public static int[] dailyTemperatures3(int[] T) {
        int length = T.length;
        //结果集，每个位置初始默认值为0
        int[] result = new int[length];
        //使用单调栈
        LinkedList<Integer> stack = new LinkedList<>();
        //遍历数组
        for (int i = 0; i < length; i++) {
            //当栈不为空的时候，需要维护栈的单调性
            if (!stack.isEmpty()) {
                //当栈不为空且栈顶元素小于栈当前数组元素，即破坏了栈的单调性，需要出栈
                while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                    //出栈
                    Integer top = stack.poll();
                    //保存结果
                    result[top] = i - top;
                }
            }
            //当排除一切破坏单调的元素后，入栈
            stack.push(i);
        }
        //返回结果
        return result;
    }

    public static void main(String[] args) {
        int[] T1 = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dailyTemperatures1(T1)));
        System.out.println(Arrays.toString(dailyTemperatures2(T1)));
        System.out.println(Arrays.toString(dailyTemperatures3(T1)));
        int[] T2 = {30, 30, 30, 31, 50, 30, 30, 30};
        System.out.println(Arrays.toString(dailyTemperatures1(T2)));
        System.out.println(Arrays.toString(dailyTemperatures2(T2)));
        System.out.println(Arrays.toString(dailyTemperatures3(T2)));
        int[] T3 = {30};
        System.out.println(Arrays.toString(dailyTemperatures1(T3)));
        System.out.println(Arrays.toString(dailyTemperatures2(T3)));
        System.out.println(Arrays.toString(dailyTemperatures3(T3)));
    }
}
