package solutions.twopointers;

import javax.print.attribute.HashPrintServiceAttributeSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无重复字符的最长子串
 *
 * @author : xianzilei
 * @date : 2020/8/4
 */
public class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        //特殊情况的排除
        if (length == 0) {
            return 0;
        }
        //初始化滑动窗口的左右边界
        int left = 0, right = 0;
        //定义map存储滑动窗口内的元素及其个数
        Map<Character, Integer> map = new HashMap<>();
        int result = 1;
        //首先当前节点添加进来
        map.put(s.charAt(0), 1);
        while (right < length - 1) {
            right++;
            char ch = s.charAt(right);
            if (map.containsKey(ch) && map.get(ch) > 0) {
                while (left < right && s.charAt(left) != ch) {
                    char tmp = s.charAt(left);
                    map.put(tmp, map.get(tmp) - 1);
                    left++;
                }
                left++;
            } else {
                map.put(ch, 1);
            }
            result = Math.max(result, right - left + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        System.out.println(solution3.lengthOfLongestSubstring("p"));
    }
}
