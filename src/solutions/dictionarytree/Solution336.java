package solutions.dictionarytree;

import java.util.*;

/**
 * 回文对
 *
 * @author : xianzilei
 * @date : 2020/8/6
 */
public class Solution336 {
    /**
     * 解法一：常规解法
     *
     * @param words 1
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/8/6 8:41
     **/
    public List<List<Integer>> palindromePairs1(String[] words) {
        int length = words.length;
        if (length < 2) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (isPalindrome(words[i] + words[j])) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    result.add(list);
                }
                if (isPalindrome(words[j] + words[i])) {
                    List<Integer> list = new ArrayList<>();
                    list.add(j);
                    list.add(i);
                    result.add(list);
                }
            }
        }
        return result;
    }

    private boolean isPalindrome(String targetStr) {
        int length = targetStr.length();
        if (length < 2) {
            return true;
        }
        int left = 0;
        int right = length - 1;
        while (left < right) {
            if (targetStr.charAt(left) != targetStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 解法二：哈希法
     *
     * @param words 1
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/8/6 13:55
     **/
    public List<List<Integer>> palindromePairs2(String[] words) {
        int length = words.length;
        if (length < 2) {
            return new ArrayList<>();
        }
        //定义哈希表，保存每个字符串的逆序串及索引
        Map<String, Integer> dicMap = new HashMap<>(length);
        for (int i = 0; i < length; i++) {
            //长度为0或1的字符串的逆序串即为本身
            if (words[i].length() < 2) {
                dicMap.put(words[i], i);
            }
            //长度大于等于2的需要逆序处理
            else {
                dicMap.put(new StringBuilder(words[i]).reverse().toString(), i);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        //遍历每个字符串
        for (int i = 0; i < length; i++) {
            int wordLength = words[i].length();
            //遍历当前字符串每一位字符
            //把当前字符串分割为两份preStr和suffixStr
            //如果preStr是回文串且哈希表存在suffixStr，则保存索引
            //如果suffixStr是回文串且哈希表存在preStr，则保存索引
            for (int j = 0; j <= wordLength; j++) {
                //分割当前字符串为前缀preStr和后缀suffixStr
                String preStr = words[i].substring(0, j);
                String suffixStr = words[i].substring(j, wordLength);
                //如果suffixStr是回文串且哈希表存在preStr，则保存索引
                if (isPalindrome(suffixStr)) {
                    Integer index = dicMap.get(preStr);
                    if (index != null && index != i) {
                        result.add(Arrays.asList(i, index));
                    }
                }
                //如果preStr是回文串且哈希表存在suffixStr，则保存索引
                //这里需要注意，控制j大于0
                //例如["ab","ba"]
                //如果不控制，
                //遍历第一个字符串处理完结果为[[1,0],[0,1]]
                //遍历第一个字符串处理完结果为[[1,0],[0,1],[1,0],[0,1]]，即出现重复
                if (j > 0 && isPalindrome(preStr)) {
                    Integer index = dicMap.get(suffixStr);
                    if (index != null && index != i) {
                        result.add(Arrays.asList(index, i));
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution336 solution336 = new Solution336();
        System.out.println(solution336.palindromePairs1(new String[]{"abcd", "dcba", "lls", "s", "sssll"}));
        System.out.println(solution336.palindromePairs1(new String[]{"bat", "tab", "cat"}));
        System.out.println(solution336.palindromePairs2(new String[]{"abcd", "dcba", "lls", "s", "sssll"}));
        System.out.println(solution336.palindromePairs2(new String[]{"bat", "tab", "cat"}));
        System.out.println(solution336.palindromePairs2(new String[]{"", " ", "lls", "s", "sssll"}));
    }
}
