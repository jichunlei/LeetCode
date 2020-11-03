package solutions.twopointers;

/**
 * 有效的山脉数组
 *
 * @author : xianzilei
 * @date : 2020/11/3 8:19
 */
public class Solution941 {
    /**
     * 解法一：双指针法
     *
     * @param A 1
     * @return boolean
     * @author xianzilei
     * @date 2020/11/3 8:27
     **/
    public boolean validMountainArray(int[] A) {
        //特殊情况的排除
        if (A == null || A.length <= 2) {
            return false;
        }
        //定义双指针，分别指向数组的两端
        int left = 0;
        int right = A.length - 1;

        //左指针向右找到单调递增的峰值
        while (left < A.length - 1 && A[left] < A[left + 1]) {
            left++;
        }
        //右指针向右找到单调递增减的起点
        while (right > 0 && A[right] < A[right - 1]) {
            right--;
        }
        //如果二者均移动了且找到的峰值点相同，则返回true，否则不满足条件
        return left > 0 && right < A.length - 1 && left == right;
    }

    /**
     * 解法二：线性搜索法
     *
     * @param A 1
     * @return boolean
     * @author xianzilei
     * @date 2020/11/3 8:35
     **/
    public boolean validMountainArray2(int[] A) {
        //解题思路：常规解法，即线性搜索，先找上升的最高点，再接着找下降的最低点

        if (A == null || A.length <= 2) {
            return false;
        }
        int index = 0;
        while (index < A.length - 1 && A[index] < A[index + 1]) {
            index++;
        }
        if (index == 0 || index == A.length - 1) {
            return false;
        }
        while (index < A.length - 1 && A[index] > A[index + 1]) {
            index++;
        }
        return index == A.length - 1;
    }

    public static void main(String[] args) {
        Solution941 solution941 = new Solution941();
        System.out.println(solution941.validMountainArray(new int[]{2, 2, 3}));
        System.out.println(solution941.validMountainArray(new int[]{2, 3, 2, 1}));
        System.out.println(solution941.validMountainArray2(new int[]{2, 2, 3}));
        System.out.println(solution941.validMountainArray2(new int[]{2, 3, 2, 1}));
        System.out.println(solution941.validMountainArray2(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));
    }
}
