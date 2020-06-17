package solutions.array;

/**
 * 最佳观光组合
 *
 * @author : xianzilei
 * @date : 2020/6/17 07:54
 */
public class Solution1014 {

    /**
     * 解法一：暴力法
     *
     * @param A 1
     * @return int
     * @author xianzilei
     * @date 2020/6/17 7:59
     **/
    public static int maxScoreSightseeingPair1(int[] A) {
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                max = Math.max(A[i] + A[j] + i - j, max);
            }
        }
        return max;
    }

    /**
     * 解法二：暴力法（优化）
     * (A[i] + i) + (A[j]  - j)
     *
     * @param A 1
     * @return int
     * @author xianzilei
     * @date 2020/6/17 8:00
     **/
    public static int maxScoreSightseeingPair2(int[] A) {
        //初始化最大值
        int max = 0;
        //初始化A[i]+i的最大值（即j之前的A[i]+i的最大值）
        int partMax = A[0];
        //循环数组查找
        for (int j = 1; j < A.length; j++) {
            //更新最大值
            max = Math.max(A[j] - j + partMax, max);
            //更新A[i]+i的最大值
            partMax = Math.max(A[j] + j, partMax);
        }
        //返回结果
        return max;
    }

    public static void main(String[] args) {
        int[] A = {8, 1, 5, 2, 6};
        System.out.println(maxScoreSightseeingPair1(A));
        System.out.println(maxScoreSightseeingPair2(A));
    }
}
