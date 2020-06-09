package solutions.array;

/**
 * @author : xianzilei
 * @date : 2019/10/9 07:59
 * @Description: 寻找两个有序数组的中位数
 */
public class Solution4 {

    /**
     * 解法一：根据顺序找到中位数（常规写法）
     *
     * @param nums1 1
     * @param nums2 2
     * @return: double
     * @author : xianzilei
     * @date : 2019/10/9 8:36
     **/
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int len1 = nums1.length;//nums1数组长度
        int len2 = nums2.length;//nums2数组长度
        int index1 = 0;//遍历nums1索引位置
        int index2 = 0;//遍历nums2索引位置
        int left = 0;
        int right = 0;
        for (int i = 0; i <= (len1 + len2) >> 1; i++) {
            left = right;
            if (index1 < len1 && (index2 >= len2 || nums1[index1] < nums2[index2])) {
                right = nums1[index1++];
            } else {
                right = nums2[index2++];
            }
        }
        if ((len1 + len2) % 2 == 0) {
            return (left + right) / 2.0;
        }
        return right;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {};
        System.out.println("解法一结果：" + findMedianSortedArrays1(nums1, nums2));
//        System.out.println("解法二结果：" +  findMedianSortedArrays1(nums1,nums2));
//        System.out.println("解法三结果：" + findMedianSortedArrays1(nums1,nums2));
    }
}