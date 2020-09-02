package solutions.string;

/**
 * 反转字符串中的单词 III
 *
 * @author : xianzilei
 * @date : 2020/9/2 8:41
 */
public class Solution557 {
    /**
     * 解法：常规思路
     *
     * @param s 1
     * @return java.lang.String
     * @author xianzilei
     * @date 2020/9/2 9:03
     **/
    public String reverseWords(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        String[] args = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : args) {
            for (int i = str.length() - 1; i >= 0; i--) {
                stringBuilder.append(str.charAt(i));
            }
            stringBuilder.append(" ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Solution557 solution557 = new Solution557();
        System.out.println(solution557.reverseWords("Let's take LeetCode contest"));
    }
}
