package solutions.twopointers;

import java.util.Arrays;

/**
 * 反转字符串
 *
 * @author : xianzilei
 * @date : 2020/10/9 8:17
 */
public class Solution344 {
    /**
     * 解法：双指针法
     *
     * @param s 1
     * @return void
     * @author xianzilei
     * @date 2020/10/9 8:23
     **/
    public void reverseString(char[] s) {
        if (s == null || s.length < 2) {
            return;
        }
        int left = 0;
        int right = s.length - 1;
        char tmp = ' ';
        while (left < right) {
            tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        Solution344 solution344 = new Solution344();
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        solution344.reverseString(s);
        System.out.println(Arrays.toString(s));
    }
}
