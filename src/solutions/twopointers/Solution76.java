package solutions.twopointers;

import sun.font.Decoration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 最小覆盖子串
 *
 * @author : xianzilei
 * @date : 2020/8/4
 */
public class Solution76 {
    /**
     * 解法：滑动窗口
     *
     * @param s 1
     * @param t 2
     * @return java.lang.String
     * @author xianzilei
     * @date 2020/8/4 13:19
     **/
    public String minWindow(String s, String t) {
        int length1 = s.length();
        int length2 = t.length();
        //特殊情况下的排除
        if (length1 == 0 || length2 == 0) {
            return "";
        }
        //定义滑动窗口的初始边界
        int left = 0, right = 0;
        //定义两个map分别存储字符及其个数
        //存储滑动窗口包含的字符及其个数
        Map<Character, Integer> map1 = new HashMap<>();
        //存储目标字符串包含的字符及其个数
        Map<Character, Integer> map2 = new HashMap<>();
        //初始化map1，方便后面的计算
        for (int i = 0; i < length1; i++) {
            map1.put(s.charAt(i), 0);
        }
        //初始化map2
        for (int i = 0; i < length2; i++) {
            char ch = t.charAt(i);
            map2.put(ch, map2.containsKey(ch) ? map2.get(ch) + 1 : 1);
            map1.put(ch, 0);
        }
        //预先滑动窗口位置为[0,0]，包含s的第一个字符
        map1.put(s.charAt(0), 1);
        String result = "";
        //滑动窗口开始滑动
        while (left <= right) {
            //标识当前滑动窗口是否包含t
            boolean flag = true;
            //循环遍历t进行判断
            for (Character character : map2.keySet()) {
                if (!map1.containsKey(character) || map1.get(character) < map2.get(character)) {
                    flag = false;
                    break;
                }
            }
            //如果满足条件，更新当前结果，并尝试缩小滑动窗口（即左边界右移动）
            if (flag) {
                //更新当前结果
                if (result.length() == 0 || result.length() > right - left + 1) {
                    result = s.substring(left, right + 1);
                }
                //左边界右移前需要将该位置的字符剔除
                map1.put(s.charAt(left), map1.get(s.charAt(left)) - 1);
                //左边界右移
                left++;
            }
            //如果不满足条件则需要扩大滑动窗口（即右边界右移）
            else {
                //右边界右移
                right++;
                //如果右边界越界了，则直接结束循环
                if (right == length1) {
                    break;
                }
                //否则将右边界的值加入到map1中
                else {
                    map1.put(s.charAt(right), map1.get(s.charAt(right)) + 1);
                }
            }
        }
        //返回结果
        return result;
    }

    public static void main(String[] args) {
        Solution76 solution76 = new Solution76();
        System.out.println(solution76.minWindow("W1a1awq1eqdQa", "a1a"));
    }
}
