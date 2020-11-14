package solutions.array;

/**
 * 数组的相对排序
 *
 * @author : xianzilei
 * @date : 2020/11/14 11:41
 */
public class Solution1122 {
    /**
     * 解法：计数排序
     *
     * @param arr1 1
     * @param arr2 2
     * @return int[]
     * @author xianzilei
     * @date 2020/11/14 11:53
     **/
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] array = new int[1001];
        for (int i : arr1) {
            array[i]++;
        }
        int[] result = new int[arr1.length];
        int index = 0;
        for (int i : arr2) {
            int count = array[i];
            while (count > 0) {
                result[index++] = i;
                count--;
            }
            array[i] = 0;
        }
        for (int i = 0; i <= 1000; i++) {
            int count = array[i];
            if (count == 0) {
                continue;
            }
            while (count > 0) {
                result[index++] = i;
                count--;
            }
        }
        return result;
    }
}
