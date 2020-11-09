package solutions.array;

import java.util.Arrays;

/**
 * 根据数字二进制下 1 的数目排序
 *
 * @author : xianzilei
 * @date : 2020/11/6 8:55
 */
public class Solution1356 {

    /**
     * 解法一：
     *
     * @param arr 1
     * @return int[]
     * @author xianzilei
     * @date 2020/11/9 9:25
     **/
    public int[] sortByBits(int[] arr) {
        //给每个数组加上比特位的前缀，方便排序
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] + getBitCount(arr[i]) * 10000000;
        }
        Arrays.sort(arr);
        //还原数组
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] % 10000000;
        }
        return arr;
    }

    //获取num的二进制1的个数
    private int getBitCount(int num) {
        int count = 0;
        while (num > 0) {
            num &= num - 1;
            count++;
        }
        return count;
    }
}
