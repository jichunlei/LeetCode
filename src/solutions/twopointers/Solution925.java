package solutions.twopointers;

/**
 * 长按键入
 *
 * @author : xianzilei
 * @date : 2020/10/21 8:20
 */
public class Solution925 {

    /**
     * 解法一：双指针解法
     *
     * @param name  1
     * @param typed 2
     * @return boolean
     * @author xianzilei
     * @date 2020/10/21 9:01
     **/
    public boolean isLongPressedName(String name, String typed) {
        //解题思路：一个一个比较字符，字符相等则移动指针，
        //否则当typed的字符与前项相等，则一直找到后面第一个不相等的字符进行比较
        //否则直接返回false
        //中间需要处理一些特殊情况

        int length1 = name.length();
        int length2 = typed.length();
        //特殊情况的排除
        //（1）name与typed均为空串，直接返回true
        //（2）name与typed只有一个字符串为空串，直接返回false
        //（3）name比typed长度还长，直接返回false
        //（4）name与typed的开头字符串或结尾字符串不相等
        if (length1 == 0 && length2 == 0) {
            return true;
        } else if (length1 == 0) {
            return false;
        } else if (length1 > length2) {
            return false;
        }
        if (name.charAt(0) != typed.charAt(0) || name.charAt(length1 - 1) != typed.charAt(length2 - 1)) {
            return false;
        }
        //定义双指针
        int index1 = 0;
        int index2 = 0;
        //前缀字符
        char pre = ' ';
        while (index1 < length1 || index2 < length2) {
            if (index1 < length1 && index2 == length2) {
                return false;
            }
            if (index1 == length1 && index2 < length2) {
                while (index2 < length2 && typed.charAt(index2) == pre) {
                    index2++;
                }
                if (index2 == length2) {
                    return true;
                } else {
                    return false;
                }
            }
            char ch1 = name.charAt(index1);
            char ch2 = typed.charAt(index2);
            if (ch1 == ch2) {
                index1++;
                index2++;
                pre = ch2;
            } else {
                while (index2 < length2 && typed.charAt(index2) == pre) {
                    index2++;
                }
                if (index2 == length2) {
                    return false;
                } else {
                    if (typed.charAt(index2) == ch1) {
                        index1++;
                        index2++;
                        pre = ch1;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 解法二：双指针优化
     *
     * @param name  1
     * @param typed 2
     * @return boolean
     * @author xianzilei
     * @date 2020/10/21 9:01
     **/
    public boolean isLongPressedName2(String name, String typed) {
        //解题思路：双指针法，解法一的处理细节太多，解法二使用统一的做法，撇去了解法一的大量细节的考虑
        //满足以下两个条件时则继续遍历，否则返回false
        //（1）字符相等（属于键入字符）
        //（2）字符不相等，但是等于前一字符（属于长按键的重复字符）
        int length1 = name.length();
        int length2 = typed.length();
        //定义双指针
        int index1 = 0;
        int index2 = 0;
        //定义字段保存前项字符的值
        char pre = ' ';
        //遍历typed字符串（这里以typed的字符串作为循环结束条件，因为typed2是作为比较的对象，且typed2字符串的长度必然大于等于字符串name的）
        while (index2 < length2) {
            char ch2 = typed.charAt(index2);
            //如果两个字符相等
            if (index1 < length1 && name.charAt(index1) == ch2) {
                //则双指针均进一位
                index1++;
                index2++;
                //保存前项节点
                pre = ch2;
            }
            //如果字符不相等或者name已经遍历完
            else {
                //如果与前项字符相等，则作为前项字符的长按键输入字符消去
                if (pre == ch2) {
                    //这里进移动index2指针
                    index2++;
                }
                //如果与前项字符不相等，表示是键入字符，则直接返回false
                else {
                    return false;
                }
            }
        }
        //返回name是否全部被比较结束
        return index1 == length1;
    }

    public static void main(String[] args) {
        Solution925 solution925 = new Solution925();
        System.out.println(solution925.isLongPressedName2("alex", "aaleex"));
        System.out.println(solution925.isLongPressedName2("saeed", "ssaaedd"));
        System.out.println(solution925.isLongPressedName2("leelee", "lleeelee"));
        System.out.println(solution925.isLongPressedName2("laiden", "laidens"));

    }
}
