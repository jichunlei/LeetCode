package solutions.string;

import java.math.BigInteger;

/**
 * 字符串相加
 *
 * @author : xianzilei
 * @date : 2020/8/3
 */
public class Solution415 {
    /**
     * 解法：常规思路
     *
     * @param num1 1
     * @param num2 2
     * @return java.lang.String
     * @author xianzilei
     * @date 2020/8/3 8:44
     **/
    public String addStrings(String num1, String num2) {
        //存放临时结果
        StringBuilder result = new StringBuilder();
        int length1 = num1.length();
        int length2 = num2.length();
        int index1 = length1 - 1;
        int index2 = length2 - 1;
        //进位
        int carry = 0;
        while (index1 >= 0 || index2 >= 0) {
            //第一个数遍历完，直接遍历完第二个数即可结束循环
            if (index1 < 0) {
                while (index2 >= 0) {
                    int j = num2.charAt(index2) - '0';
                    int sum = j + carry;
                    if (sum >= 10) {
                        result.insert(0, sum - 10);
                        carry = 1;
                    } else {
                        result.insert(0, sum);
                        carry = 0;
                    }
                    index2--;
                }
                break;
            }
            //第二个数遍历完，直接遍历完第一个数即可结束循环
            if (index2 < 0) {
                while (index1 >= 0) {
                    int i = num1.charAt(index1) - '0';
                    int sum = i + carry;
                    if (sum >= 10) {
                        result.insert(0, sum - 10);
                        carry = 1;
                    } else {
                        result.insert(0, sum);
                        carry = 0;
                    }
                    index1--;
                }
                break;
            }
            //取第一和第二个数相同位置的数字进行相加
            int i = num1.charAt(index1) - '0';
            int j = num2.charAt(index2) - '0';
            int sum = i + j + carry;
            if (sum >= 10) {
                result.insert(0, sum - 10);
                carry = 1;
            } else {
                result.insert(0, sum);
                carry = 0;
            }
            index1--;
            index2--;
        }
        if (carry == 1) {
            result.insert(0, 1);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Solution415 solution415 = new Solution415();
        System.out.println(solution415.addStrings("3132142342142342342", "5626142342342"));
        System.out.println(BigInteger.valueOf(3132142342142342342L).add(BigInteger.valueOf(5626142342342L)));
    }
}
