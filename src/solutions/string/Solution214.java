package solutions.string;

/**
 * 最短回文串--TODO
 *
 * @author : xianzilei
 * @date : 2020/9/2 9:08
 */
public class Solution214 {

    /**
     * 解法一：常规思路
     *
     * @param s 1
     * @return java.lang.String
     * @author xianzilei
     * @date 2020/9/2 9:53
     **/
    public String shortestPalindrome(String s) {
        //特殊情况下的排除
        int length = s.length();
        if (length < 2) {
            return s;
        }

        //由于只能从前面添加，所以只需要遍历一半即可
        for (int i = length / 2; i >= 0; i--) {
            //第一种回文串格式abba
            int left = i;
            int right = i + 1;
            while (left >= 0 && right < length) {
                if (s.charAt(left) == s.charAt(right)) {
                    left--;
                    right++;
                } else {
                    break;
                }
            }
            if (left < 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = length - 1; j >= right; j--) {
                    stringBuilder.append(s.charAt(j));
                }
                stringBuilder.append(s);
                return stringBuilder.toString();
            }
            //第二种回文串格式：aba
            left = i - 1;
            right = i + 1;
            while (left >= 0 && right < length) {
                if (s.charAt(left) == s.charAt(right)) {
                    left--;
                    right++;
                } else {
                    break;
                }
            }
            if (left < 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = length - 1; j >= right; j--) {
                    stringBuilder.append(s.charAt(j));
                }
                stringBuilder.append(s);
                return stringBuilder.toString();
            }
        }
        return "";
    }

    public static void main(String[] args) {
        Solution214 solution214 = new Solution214();
        System.out.println(solution214.shortestPalindrome("aabba"));
    }
}
