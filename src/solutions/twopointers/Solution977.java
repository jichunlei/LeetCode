package solutions.twopointers;

import java.util.Arrays;

/**
 * 有序数组的平方
 *
 * @author : xianzilei
 * @date : 2020/10/16 8:10
 */
public class Solution977 {

    /**
     * 解法一：分界点+双指针
     *
     * @param A 1
     * @return int[]
     * @author xianzilei
     * @date 2020/10/16 8:45
     **/
    public int[] sortedSquares(int[] A) {
        //特殊情况下的排除
        if (A == null || A.length == 0) {
            return A;
        }
        int length = A.length;
        //定义新数组作为结果集
        int[] result = new int[length];
        //如果全部为非负数，直接平方取值即可
        if (A[0] >= 0) {
            for (int i = 0; i < length; i++) {
                result[i] = A[i] * A[i];
            }
            return result;
        }
        //如果全部为非正数，直接平方取值逆序即可
        if (A[length - 1] <= 0) {
            for (int i = 0; i < length; i++) {
                result[i] = A[length - 1 - i] * A[length - 1 - i];
            }
            return result;
        }
        //定义双指针
        //left-初始值为最大负数的位置，向左移动
        int left = 0;
        //right-初始值为最小正数的位置，向右移动
        int right = 0;
        //遍历数组，初始化二者的位置
        for (int i = 0; i < length; i++) {
            if (A[i] < 0) {
                left = i;
            }
            if (A[i] > 0) {
                right = i;
                break;
            }
        }
        //新数组的数据组装索引
        int index = 0;
        //初始化0值
        for (int i = 0; i < right - left - 1; i++) {
            index++;
        }
        //左右指针根据平方值大小各自滑动
        while (left >= 0 && right < length) {
            int leftSquare = A[left] * A[left];
            int rightSquare = A[right] * A[right];
            if (leftSquare < rightSquare) {
                result[index++] = leftSquare;
                left--;
            } else {
                result[index++] = rightSquare;
                right++;
            }
        }
        //剩余值的填充
        while (left >= 0) {
            result[index++] = A[left] * A[left];
            left--;
        }
        while (right < length) {
            result[index++] = A[right] * A[right];
            right++;
        }
        //返回结果
        return result;
    }

    /**
     * 解法二：解法一优化
     *
     * @param A 1
     * @return int[]
     * @author xianzilei
     * @date 2020/10/16 9:15
     **/
    public int[] sortedSquares2(int[] A) {
        //解题思路：上面的解法需要初始化二指针的位置，然后在正序构建新数组，这样较为麻烦。
        //我们可以考虑逆序构建数组，使用双指针指向开头和结尾，每个取最大的平方值逆序构建新数组。

        //特殊情况下的排除
        if (A == null || A.length == 0) {
            return A;
        }
        int length = A.length;
        //构建新数组
        int[] result = new int[length];
        //初始化双指针，分别指向数组开头和结尾
        int left = 0;
        int right = length - 1;
        int index = length - 1;
        //每次取最大值逆序构建新数组
        while (left <= right) {
            int leftSquare = A[left] * A[left];
            int rightSquare = A[right] * A[right];
            if (leftSquare > rightSquare) {
                result[index--] = leftSquare;
                left++;
            } else {
                result[index--] = rightSquare;
                right--;
            }
        }

        //返回结果
        return result;
    }

    public static void main(String[] args) {
        Solution977 solution977 = new Solution977();
        System.out.println(Arrays.toString(solution977.sortedSquares(new int[]{-4, -1, 0, 3, 10})));
        System.out.println(Arrays.toString(solution977.sortedSquares(new int[]{-7, -3, 2, 3, 11})));
        System.out.println(Arrays.toString(solution977.sortedSquares(new int[]{-1})));
        System.out.println(Arrays.toString(solution977.sortedSquares2(new int[]{-4, -1, 0, 3, 10})));
        System.out.println(Arrays.toString(solution977.sortedSquares2(new int[]{-7, -3, 2, 3, 11})));
        System.out.println(Arrays.toString(solution977.sortedSquares2(new int[]{-1})));
    }
}
