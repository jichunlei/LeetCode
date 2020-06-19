package solutions.array;

/**
 * 验证回文串
 *
 * @author : xianzilei
 * @date : 2020/6/19 08:00
 */
public class Solution125 {
    /**
     * 双指针法
     *
     * @param s 1
     * @return boolean
     * @author xianzilei
     * @date 2020/6/19 8:26
     **/
    public static boolean isPalindrome1(String s) {
        //特殊情况的处理
        if (s == null || (s = s.trim()).length() == 0) {
            return true;
        }
        int left = 0;
        String tmp = s.toLowerCase();
        int right = tmp.length() - 1;
        while (left <= right) {
            while (left <= right && !(tmp.charAt(left) >= 'a' && tmp.charAt(left) <= 'z')
                    && !(tmp.charAt(left) >= '0' && tmp.charAt(left) <= '9')) {
                left++;
            }
            while (left <= right && !(tmp.charAt(right) >= 'a' && tmp.charAt(right) <= 'z')
                    && !(tmp.charAt(right) >= '0' && tmp.charAt(right) <= '9')) {
                right--;
            }
            if (left <= right && tmp.charAt(left++) != tmp.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 双指针法（使用Java内置函数）
     *
     * @param s 1
     * @return boolean
     * @author xianzilei
     * @date 2020/6/19 8:28
     **/
    public static boolean isPalindrome2(String s) {
        //特殊情况的处理
        if (s == null || (s = s.trim()).length() == 0) {
            return true;
        }
        //左指针
        int left = 0;
        //右指针
        int right = s.length() - 1;
        //双指针进行校验
        while (left <= right) {
            //左指针向后滑动到数字或字母位置
            while (left <= right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            //右指针向前滑动到数字或字母位置
            while (left <= right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            //比较位置是否合法及二者满足条件，一旦不满足直接返回false
            if (left <= right && Character.toLowerCase(s.charAt(left++)) != Character.toLowerCase(s.charAt(right--))) {
                return false;
            }
        }
        //全部满足则返回true
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome1("A man, a plan, a canal: Panama"));
    }
}
