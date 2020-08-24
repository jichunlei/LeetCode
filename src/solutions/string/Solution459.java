package solutions.string;

/**
 * 重复的子字符串
 *
 * @author : xianzilei
 * @date : 2020/8/24 8:11
 */
public class Solution459 {

    /**
     * 解法一：常规思路
     *
     * @param s 1
     * @return boolean
     * @author xianzilei
     * @date 2020/8/24 8:33
     **/
    public boolean repeatedSubstringPattern1(String s) {
        //特殊情况的排除
        if (s == null || s.length() < 2) {
            return false;
        }
        int length = s.length();
        //这里枚举任何可能的子串
        //子串的长度范围为1~length / 2
        for (int i = 1; i <= length / 2; i++) {
            //如果字符串长度不是子串的整数倍，代表该子串肯定不满足条件，直接进入下一层循环
            if (length % i != 0) {
                continue;
            }
            //计算需要校验的子串的个数
            int count = length / i;
            //摘取子串
            String sub = s.substring(0, i);
            //标记当前子串是否满足题意
            boolean flag = true;
            //校验每一位子串
            for (int j = 2, start = i; j <= count; j++, start += i) {
                //截取需校验的子串
                String tmp = s.substring(start, start + i);
                //如果不满足直接结束循环
                if (!tmp.equals(sub)) {
                    flag = false;
                    break;
                }
            }
            //如果满足题意，直接返回true
            if (flag) {
                return true;
            }
        }
        //未找到满足题意的子串，返回false
        return false;
    }

    /**
     * 解法二：字符串移位法
     *
     * @param s 1
     * @return boolean
     * @author xianzilei
     * @date 2020/8/24 9:02
     **/
    public boolean repeatedSubstringPattern2(String s) {
        //特殊情况下的排除
        if (s == null || s.length() < 2) {
            return false;
        }
        int length = s.length();
        //每次将当前子串移动到s末尾，如果s满足题意，则当子串被整个移动到末尾，得到的新字符串与原字符串相等
        //子串的范围为1~length / 2;
        for (int i = 1; i <= length / 2; i++) {
            if (length % i != 0) {
                continue;
            }
            //将子串移动到末尾
            String tmp = s.substring(i) + s.substring(0, i);
            //如果新字符串与原字符串相等，则满足题意，直接返回true
            if (tmp.equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解法三：双字符串法
     *
     * @param s 1
     * @return boolean
     * @author xianzilei
     * @date 2020/8/24 8:47
     **/
    public boolean repeatedSubstringPattern3(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        return (s + s).indexOf(s, 1) != s.length();


    }

    public static void main(String[] args) {
        Solution459 solution459 = new Solution459();
        System.out.println(solution459.repeatedSubstringPattern1("aba"));
        System.out.println(solution459.repeatedSubstringPattern2("aba"));
        System.out.println(solution459.repeatedSubstringPattern3("aba"));
    }
}
