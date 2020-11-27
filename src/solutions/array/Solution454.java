package solutions.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 四数相加 II
 *
 * @author : xianzilei
 * @date : 2020/11/27 8:34
 */
public class Solution454 {
    /**
     * 解法：哈希表法
     *
     * @param A 1
     * @param B 2
     * @param C 3
     * @param D 4
     * @return int
     * @author xianzilei
     * @date 2020/11/27 8:34
     **/
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        //解题思路：将A和B分为一组，C和D分为一组，计算A和B的所有组合和保存到哈希表中，在遍历计算C和D的组合和，
        //通过哈希表查找满足条件的数据

        //特殊情况下的排除
        int length = A.length;
        if (A.length <= 0) {
            return 0;
        }
        //定义哈希表存储A和B的所有组合和
        //key为和，value为出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int key = A[i] + B[j];
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        int res = 0;
        //遍历C和D
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int key = C[i] + D[j];
                //如果满足条件则加入到结果中
                if (map.containsKey(-key)) {
                    res += map.get(-key);
                }
            }
        }
        //返回结果
        return res;
    }

    public static void main(String[] args) {
        Solution454 solution454 = new Solution454();
        System.out.println(solution454.fourSumCount(
                new int[]{1, 2},
                new int[]{-2, -1},
                new int[]{-1, 2},
                new int[]{0, 2}));
    }
}
