package solutions.array;

import java.util.Arrays;

/**
 * @author : xianzilei
 * @date : 2019/9/26 08:32
 * @Description: 合并两个有序数组
 */
public class Solution88 {

    /**
     * @param nums1 1
     * @param m     2
     * @param nums2 3
     * @param n     4
     * @Description: 直接排序法
     * @return: void
     * @author : xianzilei
     * @date : 2019/9/26 8:37
     **/
    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        //将nums数组数放入nums1中
        for (int i = 0; i < n; i++) {
            nums1[m++] = nums2[i];
        }
        //使用排序
        Arrays.sort(nums1);
    }

    /**
     * @param nums1 1
     * @param m     2
     * @param nums2 3
     * @param n     4
     * @Description: 双指针法
     * @return: void
     * @author : xianzilei
     * @date : 2019/9/26 8:53
     **/
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        //先将nums数组copy到另外的空间
        int temp[] = new int[m];
        for (int i = 0; i < m; i++) {
            temp[i] = nums1[i];
        }

        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < m + n; ) {
            if (temp[index1] <= nums2[index2]) {
                nums1[i++] = temp[index1++];
            } else {
                nums1[i++] = nums2[index2++];
            }
            if (index1 == m) {
                while (index2 < n) {

                    nums1[i++] = nums2[index2++];
                }
            }
            if (index2 == n) {
                while (index1 < m) {
                    nums1[i++] = temp[index1++];
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums11 = {1, 2, 3, 0, 0, 0};
        int[] nums12 = {1, 2, 3, 0, 0, 0};
        int[] nums13 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        int m = 3;
        int n = 3;
        merge1(nums11, m, nums2, n);
        merge2(nums12, m, nums2, n);
        merge1(nums13, m, nums2, n);
        System.out.println("解法一结果：" + Arrays.toString(nums11));
        System.out.println("解法二结果：" + Arrays.toString(nums12));
        System.out.println("解法三结果：" + Arrays.toString(nums13));
    }
}
