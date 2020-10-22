package solutions.twopointers;

import java.util.*;

/**
 * 划分字母区间
 *
 * @author : xianzilei
 * @date : 2020/10/22 8:13
 */
public class Solution763 {
    /**
     * 解法一：贪心算法+计数
     *
     * @param S 1
     * @return java.util.List<java.lang.Integer>
     * @author xianzilei
     * @date 2020/10/22 8:47
     **/
    public List<Integer> partitionLabels(String S) {
        //解题思路：先使用26大小的数组记录每个字符的出现次数，
        //其次遍历字符串，记录当前遍历的字符串的每个字符数是否满格，
        //如果是则截取，重新到下一位进行计算
        //我们每次都是从最小的长度进行截取，一旦满足条件就直接截取，即每次都是最优解，因此最终结果也是最优解

        //定义结果集
        List<Integer> result = new ArrayList<>();
        //定义数组记录字符的出现个数（索引代表字符，值代表出现次数）
        int[] countArray = new int[26];
        for (int i = 0; i < S.length(); i++) {
            countArray[S.charAt(i) - 'a']++;
        }
        int index = 0;
        //当前当前位置包含的字符串的未满格的字符及个数
        Map<Character, Integer> tmpCountMap = new HashMap<>();
        while (index < S.length()) {
            char cur = S.charAt(index);
            int arrayIndex = cur - 'a';
            int curCount = tmpCountMap.getOrDefault(cur, 0) + 1;
            //如果当前个数不满格，则记录继续下次循环
            if (curCount < countArray[arrayIndex]) {
                tmpCountMap.put(cur, curCount);
            }
            //如果当前字符个数满格
            else {
                //删除当前字符
                tmpCountMap.remove(cur);
                //如果map为空，即代表当前位置包含的字符串满足题意，这里保存索引位置
                if (tmpCountMap.isEmpty()) {
                    result.add(index + 1);
                }
            }
            index++;
        }
        //结果集的处理：我们保存的是索引位置，而题目要求的是每段的长度，因此需要处理一下
        for (int i = result.size() - 1; i >= 1; i--) {
            //后项-前项
            result.set(i, result.get(i) - result.get(i - 1));
        }
        //返回结果集
        return result;
    }

    /**
     * 解法二：贪心算法+双指针
     *
     * @param S 1
     * @return java.util.List<java.lang.Integer>
     * @author xianzilei
     * @date 2020/10/22 8:56
     **/
    public List<Integer> partitionLabels2(String S) {
        //解题思路：解法一的思路是使用Map计数，其实大可不必，
        //我们只需要记录字符的最后一次出现的索引位置，即当前可能的最短区间位置，
        //然后遍历区间内的字符，不断更新区间的右边界，直到遍历到区间右边界

        //定义结果集
        List<Integer> result = new ArrayList<>();
        //定义数组记录字符的出现个数（索引代表字符，值代表最后出现的索引位置）
        int[] countArray = new int[26];
        for (int i = 0; i < S.length(); i++) {
            countArray[S.charAt(i) - 'a'] = i;
        }
        int index = 0;
        //定义双指针
        int left;
        int right;
        //遍历字符串
        while (index < S.length()) {
            //初始化区间左边界
            left = index;
            //初始化区间右边界
            right = countArray[S.charAt(index) - 'a'];
            //遍历区间[left,right]，同时动态移动右边界，知道遍历到区间右边界
            while (index < right) {
                //动态移动右边界
                right = Math.max(countArray[S.charAt(index) - 'a'], right);
                index++;
            }
            //截取区间
            result.add((right - left + 1));
            index++;
        }
        //返回结果集
        return result;
    }

    public static void main(String[] args) {
        Solution763 solution763 = new Solution763();
        System.out.println(solution763.partitionLabels("ababcbacadefegdehijhklij"));
        System.out.println(solution763.partitionLabels2("ababcbacadefegdehijhklij"));
    }
}
