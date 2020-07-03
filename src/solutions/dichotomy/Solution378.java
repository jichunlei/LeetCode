package solutions.dichotomy;

import java.util.Arrays;

/**
 * 有序矩阵中第K小的元素
 *
 * @author : xianzilei
 * @date : 2020/7/3 08:58
 */
public class Solution378 {

    /**
     * 解法一：暴力法
     *
     * @param matrix 1
     * @param k      2
     * @return int
     * @author xianzilei
     * @date 2020/7/3 10:48
     **/
    public static int kthSmallest1(int[][] matrix, int k) {
        int n = matrix.length;
        int[] tmp = new int[n * n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp[idx++] = matrix[i][j];
            }
        }
        Arrays.sort(tmp);
        return tmp[k - 1];
    }

    /**
     * 解法二：二分法
     *
     * @param matrix 1
     * @param k      2
     * @return int
     * @author xianzilei
     * @date 2020/7/3 10:48
     **/
    public static int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        //定义二分法的起点和终点
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        //二分法求值
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            //计算所有小于等于mid值的元素的个数
            int count = getCount(mid, matrix);
            //如果数量大于等于k，表明数据肯定在[left,mid]内
            if (count >= k) {
                right = mid;
            }
            //如果数量小于k，表明数据肯定在[mid+1,right]内
            else {
                left = mid + 1;
            }
        }
        //上述的终止条件为left==right，返回left或right都行
        //这里为啥left就一定是矩阵中的数据？
        //我们大可不必关心这个，我们只需要知道我们要找的元素肯定在[left,right]区间内，
        //当left==right，即区间内只有一个元素，那必然是我们需要的结果啦
        return left;
    }

    //计算所有小于等于mid值的元素的个数
    public static int getCount(int mid, int[][] matrix) {
        int n = matrix.length;
        //从matrix[n-1][0]位置开始计算
        int i = n - 1;
        int j = 0;
        int count = 0;
        while (i >= 0 && j < n) {
            //如果当前位置的值小于等于目标值
            if (matrix[i][j] <= mid) {
                //计算当前列的总数
                count += i + 1;
                //位置右移一位
                j++;
            }
            //如果当前位置的值大于目标值
            else {
                //位置上移一位
                i--;
            }
        }
        //返回结果
        return count;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        System.out.println(kthSmallest1(matrix, 8));
        System.out.println(kthSmallest2(matrix, 8));
    }
}
