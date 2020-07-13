package solutions.array;

import java.util.*;

/**
 * 两个数组的交集 II
 *
 * @author : xianzilei
 * @date : 2020/7/13 08:04
 */
public class Solution350 {
    /**
     * 解法一：双指针法
     *
     * @param nums1 1
     * @param nums2 2
     * @return int[]
     * @author xianzilei
     * @date 2020/7/13 8:27
     **/
    public static int[] intersect1(int[] nums1, int[] nums2) {
        //定义动态数组存放结果
        List<Integer> result = new ArrayList<>();
        //对两个数组进行升序排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        //定义两个指针
        int l1 = 0, l2 = 0;
        //当指针在可用范围内
        while (l1 < nums1.length && l2 < nums2.length) {
            //如果两个元素值相等
            if (nums1[l1] == nums2[l2]) {
                //保存结果
                result.add(nums1[l1]);
                //两个指针同时右移
                l1++;
                l2++;
            }
            //如果nums1元素大于nums2元素
            else if (nums1[l1] > nums2[l2]) {
                //l2指针右移
                l2++;
            }
            //如果nums1元素小于nums2元素
            else {
                //l1指针右移
                l1++;
            }
        }
        //转化成数组后返回
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 解法二：hash法
     *
     * @param nums1 1
     * @param nums2 2
     * @return int[]
     * @author xianzilei
     * @date 2020/7/13 8:37
     **/
    public static int[] intersect2(int[] nums1, int[] nums2) {
        //特殊情况的排除
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        //定义动态数组存放结果
        List<Integer> result = new ArrayList<>();
        //定义hash表存放nums1数组的元素，key为数组元素，value为出现次数
        Map<Integer, Integer> map = new HashMap<>();
        //将nums1的元素放入hash表中
        for (int element : nums1) {
            //如果key存在
            if (map.containsKey(element)) {
                //出现次数加1
                map.put(element, map.get(element) + 1);
            }
            //如果key不存在
            else {
                //元素添加到hash表中，出现次数初始化为1
                map.put(element, 1);
            }
        }

        //遍历nums2
        for (int element : nums2) {
            //查询是否存在当前元素key
            Integer count = map.get(element);
            //如果存在且value大于0
            if (count != null && count > 0) {
                //存放结果
                result.add(element);
                //出现次数-1
                map.put(element, count - 1);
            }
        }
        //转化成数组后返回
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println(Arrays.toString(intersect1(nums1, nums2)));
        System.out.println(Arrays.toString(intersect2(nums1, nums2)));
    }
}
