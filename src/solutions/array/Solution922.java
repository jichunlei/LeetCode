package solutions.array;

/**
 * 按奇偶排序数组 II
 *
 * @author : xianzilei
 * @date : 2020/11/12 8:15
 */
public class Solution922 {
    /**
     * 解法一：分组组装法
     *
     * @param A 1
     * @return int[]
     * @author xianzilei
     * @date 2020/11/12 8:22
     **/
    public int[] sortArrayByParityII(int[] A) {
        //定义结果集
        int[] result = new int[A.length];
        //奇数索引
        int oddIndex = 1;
        //偶数索引
        int evenIndex = 0;
        //分组组装
        for (int i : A) {
            if (i % 2 == 0) {
                result[evenIndex] = i;
                evenIndex += 2;
            } else {
                result[oddIndex] = i;
                oddIndex += 2;
            }
        }
        //返回结果
        return result;
    }

    /**
     * 解法二：数组定位法
     *
     * @param A 1
     * @return int[]
     * @author xianzilei
     * @date 2020/11/12 8:35
     **/
    public int[] sortArrayByParityII2(int[] A) {
        //定义索引数组（索引表示数值，值表示个数）
        int[] array = new int[1001];
        //初始化数组
        for (int i : A) {
            array[i]++;
        }
        //奇数索引
        int oddIndex = 1;
        //偶数索引
        int evenIndex = 0;
        //填充数据
        for (int i = 0; i < 1001; i++) {
            int cur = array[i];
            if (cur == 0) {
                continue;
            }
            if (i % 2 == 0) {
                while (cur > 0) {
                    A[evenIndex] = i;
                    evenIndex += 2;
                    cur--;
                }
            } else {
                while (cur > 0) {
                    A[oddIndex] = i;
                    oddIndex += 2;
                    cur--;
                }
            }
        }
        return A;
    }

    /**
     * 解法三：双指针法
     *
     * @param A 1
     * @return int[]
     * @author xianzilei
     * @date 2020/11/12 8:35
     **/
    public int[] sortArrayByParityII3(int[] A) {
        //奇数索引
        int j = 1;
        //偶数索引遍历
        for (int i = 0; i < A.length; i += 2) {
            if (A[i] % 2 == 0) {
                continue;
            }
            //如果偶数索引位置的数是奇数，所以需要与奇数位置的偶数交换
            while (A[j] % 2 == 1) {
                j += 2;
            }
            int tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }
        return A;
    }
}
