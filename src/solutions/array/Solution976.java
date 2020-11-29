package solutions.array;

import java.util.Arrays;

/**
 * 三角形的最大周长
 *
 * @author : xianzilei
 * @date : 2020/11/29 09:34
 */
public class Solution976 {
    /**
     * 解法一：常规思路
     *
     * @param A 1
     * @return int
     * @author xianzilei
     * @date 2020/11/29 10:15
     **/
    public int largestPerimeter(int[] A) {
        int length = A.length;
        int res = 0;
        //三层循环判断
        for (int i = 0; i < length - 2; i++) {
            for (int j = i + 1; j < length - 1; j++) {
                for (int k = j + 1; k < length; k++) {
                    int a = A[i];
                    int b = A[j];
                    int c = A[k];
                    if (a + b > c && a + c > b && b + c > a) {
                        res = Math.max(a + b + c, res);
                    }
                }
            }
        }
        return res;
    }

    /**
     * 解法二：排序+贪心
     *
     * @param A 1
     * @return int
     * @author xianzilei
     * @date 2020/11/29 10:15
     **/
    public int largestPerimeter2(int[] A) {
        //解题思路：当两条较小的边大于最大边时（a<=b<=c），则可以构成三角形
        //因此我们可以给数组排序，
        //根据贪心的思想，从最大边c开始枚举，且a和b尽可能接近c，此时可以满足最大情况
        //因此我们从数组倒数三个数开始计算是否可组成三角形，找到则直接返回（必然最大），否则往前滑动一位继续判断
        //注意：这三条边必然是连在一起的（才可能最大）

        //排序
        Arrays.sort(A);
        //从后往前计算三个连续的数组值，满足条件直接返回
        for (int i = A.length - 1; i >= 2; i--) {
            if (A[i - 2] + A[i - 1] > A[i]) {
                return A[i - 2] + A[i - 1] + A[i];
            }
        }
        //没有满足条件直接返回0
        return 0;
    }
}
