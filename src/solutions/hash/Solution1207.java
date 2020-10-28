package solutions.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 独一无二的出现次数
 *
 * @author : xianzilei
 * @date : 2020/10/28 8:13
 */
public class Solution1207 {
    /**
     * 解法：哈希表
     *
     * @param arr 1
     * @return boolean
     * @author xianzilei
     * @date 2020/10/28 8:22
     **/
    public boolean uniqueOccurrences(int[] arr) {
        //使用哈希表记录数组中各个元素出现的次数
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int num : arr) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        //使用新的哈希表来进行个数的判重
        HashSet<Integer> countSet = new HashSet<>();
        for (Integer value : hashMap.values()) {
            if (countSet.contains(value)) {
                return false;
            }
            countSet.add(value);
        }
        return true;
    }


}
