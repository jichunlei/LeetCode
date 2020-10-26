package solutions.array;

/**
 * 数组中的最长山脉
 *
 * @author : xianzilei
 * @date : 2020/10/26 8:46
 */
public class Solution845 {
    /**
     * 解法一：遍历法
     *
     * @param A 1
     * @return int
     * @author xianzilei
     * @date 2020/10/26 9:41
     **/
    public int longestMountain(int[] A) {
        //特殊情况的排除
        if (A == null || A.length <= 2) {
            return 0;
        }
        int length = A.length;
        //结果
        int result = 0;
        //遍历的索引位置
        int index = 1;
        //需保证遍历的区间长度大于等于3
        while (length - index > 1) {
            //上升段长度
            int increase = 0;
            //下降段长度
            int decrease = 0;
            //求当前节点开始的上升段长度
            while (index < length && A[index] > A[index - 1]) {
                increase++;
                index++;
            }
            //紧接着求下降段长度
            while (index < length && A[index] < A[index - 1]) {
                decrease++;
                index++;
            }
            //当存在山脉结构（即上升段和下降段均不为0），更新结果
            if (increase > 0 && decrease > 0) {
                result = Math.max(result, increase + decrease + 1);
            }
            //这里是做剪枝，相同元素无需再遍历
            while (index < length && A[index] == A[index - 1]) {
                index++;
            }
        }
        //返回结果
        return result;

    }

    /**
     * 解法二：动态规划
     *
     * @param A 1
     * @return int
     * @author xianzilei
     * @date 2020/10/26 13:42
     **/
    public int longestMountain2(int[] A) {
        //解题思路：从山顶往两边进行遍历
        //具体实现，计算每个节点往左和往右可以扩展的最大长度（不包含自身节点）
        //（1）定义数组left[i]：索引i处位置的元素可以向左扩展的最大长度
        // 边界条件left[0]=0;
        // 则当A[i]>A[i-1]:left[i]=left[i-1]+1
        // 否则left[i]=0;
        //（2）定义数组right[i]：索引i处位置的元素可以向右扩展的最大长度
        // 边界条件right[A.length-1]=0;
        // 则当A[i]>A[i+1]:right[i]=right[i+1]+1
        // 否则right[i]=0;
        //（3）则最终结果就是max{left[i]+right[i]+1,当left[i]>0且right[i]>0}

        //特殊情况的排除
        if (A == null || A.length <= 2) {
            return 0;
        }
        int length = A.length;
        //结果
        int result = 0;
        //初始化left数组
        int[] left = new int[length];
        for (int i = 1; i < length; i++) {
            if (A[i] > A[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        //初始化right数组
        int[] right = new int[length];
        for (int i = length - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        //求最大结果集
        for (int i = 1; i < length - 1; i++) {
            if (left[i] > 0 && right[i] > 0) {
                result = Math.max(result, left[i] + right[i] + 1);
            }
        }
        //返回结果
        return result;
    }

    public static void main(String[] args) {
        Solution845 solution845 = new Solution845();
        System.out.println(solution845.longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5}));
        System.out.println(solution845.longestMountain(new int[]{2, 2, 2}));
        System.out.println(solution845.longestMountain(new int[]{0, 1, 0}));
        System.out.println(solution845.longestMountain2(new int[]{2, 1, 4, 7, 3, 2, 5}));
        System.out.println(solution845.longestMountain2(new int[]{2, 2, 2}));
        System.out.println(solution845.longestMountain2(new int[]{0, 1, 0}));
    }
}
