package solutions.string;

import java.util.*;

/**
 * 重构字符串
 *
 * @author : xianzilei
 * @date : 2020/11/30 8:19
 */
public class Solution767 {
    /**
     * 解法一：贪心+排序
     *
     * @param S 1
     * @return java.lang.String
     * @author xianzilei
     * @date 2020/11/30 9:16
     **/
    public String reorganizeString(String S) {
        //解题思路：计算出各个字符的出现次数，按照出现次数从高到低进行插空排列，得出结果

        //特殊情况的排除
        int length = S.length();
        if (length <= 1) {
            return S;
        }
        //定义哈希表存储字符及个数
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            char ch = S.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        //按照value值进行降序排序
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        //如果最大次数大于一半，则不可能组合成满足题意的字符串，直接返回空串
        if (list.get(0).getValue() > (length + 1) / 2) {
            return "";
        }
        //存储结果集
        char[] res = new char[length];
        //定义元素排列的初始索引值
        int index = 0;
        //遍历排序后的哈希表
        for (Map.Entry<Character, Integer> entry : list) {
            Integer count = entry.getValue();
            Character ch = entry.getKey();
            //插入存入字符
            while (count > 0) {
                res[index] = ch;
                //跳跃
                index += 2;
                //表示偶数索引用完，需要切换为奇数索引
                if (index >= length) {
                    index = 1;
                }
                count--;
            }
        }
        //返回结果
        return new String(res);
    }

    /**
     * 解法二：解法一小优化
     *
     * @param S 1
     * @return java.lang.String
     * @author xianzilei
     * @date 2020/11/30 9:32
     **/
    public String reorganizeString2(String S) {
        //解法一把所有的元素进行了排序，但是实际上我们只需要知道最大次数的元素，将其排列到偶数索引位置即可
        //其余插空即可

        //特殊情况的排序
        int length = S.length();
        if (length <= 1) {
            return S;
        }
        //使用数组存储字符及个数，索引代表字符，数组值表示个数
        int[] array = new int[26];
        //最大次数
        int maxCount = 0;
        //最大次数对应的字符
        char maxChar = ' ';
        for (int i = 0; i < length; i++) {
            char ch = S.charAt(i);
            int index = ch - 'a';
            array[index]++;
            if (array[index] > maxCount) {
                maxCount = array[index];
                maxChar = ch;
            }
        }
        //如果最大次数大于一半，则不可能组合成满足题意的字符串，直接返回空串
        if (maxCount > (length + 1) / 2) {
            return "";
        }
        //偶数索引
        int evenIndex = 0;
        //奇数索引
        int oddIndex = 1;
        char[] res = new char[length];
        //遍历数组，如果是maxChar，则优先放入偶数索引处，否则优先放入奇数索引处
        for (int i = 0; i < 26; i++) {
            if (array[i] == 0) {
                continue;
            }
            char ch = (char) (i + 'a');
            int count = array[i];
            //非maxChar优先存放奇数索引处
            if (ch != maxChar) {
                while (count > 0 && oddIndex < length) {
                    res[oddIndex] = ch;
                    oddIndex += 2;
                    count--;
                }
            }
            //maxChar优先放偶数索引，或者奇数索引使用完了，直接放入到偶数索引位置
            while (count > 0) {
                res[evenIndex] = ch;
                evenIndex += 2;
                count--;
            }
        }
        //返回结果
        return new String(res);
    }

    public static void main(String[] args) {
        Solution767 solution767 = new Solution767();
        System.out.println(solution767.reorganizeString("vvvlo"));
        System.out.println(solution767.reorganizeString("aab"));
        System.out.println(solution767.reorganizeString("aaab"));
        System.out.println(solution767.reorganizeString2("vvvlo"));
        System.out.println(solution767.reorganizeString2("aab"));
        System.out.println(solution767.reorganizeString2("aaab"));
    }
}
