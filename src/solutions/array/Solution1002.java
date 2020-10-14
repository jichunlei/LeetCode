package solutions.array;

import java.util.*;

/**
 * 查找常用字符
 *
 * @author : xianzilei
 * @date : 2020/10/14 8:09
 */
public class Solution1002 {
    /**
     * 解法一：哈希表法
     *
     * @param A 1
     * @return java.util.List<java.lang.String>
     * @author xianzilei
     * @date 2020/10/14 8:09
     **/
    public List<String> commonChars(String[] A) {
        List<String> result = new ArrayList<>();
        //特殊情况下的排除
        if (A == null || A.length == 0) {
            return result;
        }
        //定义初始哈希表来记录字符和出现的次数
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < A[0].length(); i++) {
            countMap.put(A[0].charAt(i), countMap.getOrDefault(A[0].charAt(i), 0) + 1);
        }
        //从第二个数组开始遍历
        for (int i = 1; i < A.length; i++) {
            //定义当前层的哈希表
            Map<Character, Integer> tmpMap = new HashMap<>();
            //遍历字符串每个字符
            for (int j = 0; j < A[i].length(); j++) {
                Character ch = A[i].charAt(j);
                //获取当前字符在上层哈希表的出现次数
                Integer count = countMap.getOrDefault(ch, 0);
                //如果出现次数大于0
                if (count > 0) {
                    //将当前字符加入到当层的哈希表
                    tmpMap.put(ch, tmpMap.getOrDefault(ch, 0) + 1);
                    //上层哈希表次数减一
                    countMap.put(ch, count - 1);
                }
            }
            //遍历结束，当前层哈希表替换上层哈希表
            countMap = tmpMap;
        }
        //上面处理技术后，countMap存储是就是公共字符和出现的次数，遍历放入结果集即可
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            for (int i = 0; i < value; i++) {
                result.add(key.toString());
            }
        }
        return result;
    }

    /**
     * 解法二：数组定位法
     *
     * @param A 1
     * @return java.util.List<java.lang.String>
     * @author xianzilei
     * @date 2020/10/14 8:53
     **/
    public List<String> commonChars2(String[] A) {
        //题意说明了字符串都是小写字母，可以采用26大小的数组存放信息，数组的索引代表字符
        List<String> result = new ArrayList<>();
        if (A == null || A.length == 0) {
            return result;
        }
        //存储公共字符的出现次数
        int[] count = new int[26];
        //第一个字符串直接填充，无需比较
        for (int i = 0; i < A[0].length(); i++) {
            int index = A[0].charAt(i) - 'a';
            count[index]++;
        }
        //从第二个字符串开始遍历
        for (int i = 1; i < A.length; i++) {
            //定义数组保存当前字符串的出现次数
            int[] tmpCount = new int[26];
            for (int j = 0; j < A[i].length(); j++) {
                int index = A[i].charAt(j) - 'a';
                tmpCount[index]++;
            }
            //更新count数组，取count和tmpCount的最小值，表示公共元素的出现次数
            for (int j = 0; j < 26; j++) {
                if (count[j] > tmpCount[j]) {
                    count[j] = tmpCount[j];
                }
            }
        }
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < count[i]; j++) {
                result.add(String.valueOf((char) (i + 'a')));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution1002 solution1002 = new Solution1002();
        System.out.println(solution1002.commonChars(new String[]{"bella", "label", "roller"}));
        System.out.println(solution1002.commonChars(new String[]{"cool", "lock", "cook"}));
        System.out.println(solution1002.commonChars2(new String[]{"bella", "label", "roller"}));
        System.out.println(solution1002.commonChars2(new String[]{"cool", "lock", "cook"}));
    }
}
