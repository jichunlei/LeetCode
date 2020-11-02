package solutions.hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 两个数组的交集
 *
 * @author : xianzilei
 * @date : 2020/11/2 8:15
 */
public class Solution349 {

    /**
     * 解法：哈希表
     *
     * @param nums1 1
     * @param nums2 2
     * @return int[]
     * @author xianzilei
     * @date 2020/11/2 8:23
     **/
    public int[] intersection(int[] nums1, int[] nums2) {
        //将第一个数组保存到哈希表中，然后查询第二个数组是否在哈希表中，最后提取结果返回

        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[]{};
        }
        HashSet<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums1) {
            set.add(num);
        }
        for (int num : nums2) {
            if (set.contains(num)) {
                list.add(num);
                set.remove(num);
            }
        }
        int[] result = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
