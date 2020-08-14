package solutions.string;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.List;

/**
 * 字符串相乘--TODO
 *
 * @author : xianzilei
 * @date : 2020/8/13
 */
public class Solution43 {
    /**
     * 解法一：常规思路
     *
     * @param num1 1
     * @param num2 2
     * @return java.lang.String
     * @author xianzilei
     * @date 2020/8/13 9:24
     **/
    public String multiply1(String num1, String num2) {
        //解题思路：num1与num2的每一位乘积求出保存，在将这些乘积求和（即常规的竖式思路）

        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int length1 = num1.length();
        int length2 = num2.length();
        int maxLength = 0;
        List<String> productList = new ArrayList<>();
        for (int i = length2 - 1; i >= 0; i--) {
            StringBuilder curProduct = new StringBuilder();
            int idx = length1 - 1;
            int k = num2.charAt(i) - '0';
            int carry = 0;
            while (idx >= 0) {
                int j = num1.charAt(idx) - '0';
                int p = j * k + carry;
                if (p >= 10) {
                    carry = p / 10;
                } else {
                    carry = 0;
                }
                curProduct.append(p % 10);
                idx--;
            }
            if (carry > 0) {
                curProduct.append(carry);
            }
            for (int l = i; l < length2 - 1; l++) {
                curProduct.insert(0, 0);
            }
            if (maxLength < curProduct.length()) {
                maxLength = curProduct.length();
            }
            productList.add(curProduct.toString());
        }
        StringBuilder result = new StringBuilder();
        int index = 0;
        int carry = 0;
        while (index < maxLength) {
            int sum = 0;
            sum += carry;
            for (String product : productList) {
                if (index < product.length()) {
                    sum += (product.charAt(index) - '0');
                }
            }
            if (sum >= 10) {
                carry = sum / 10;
            } else {
                carry = 0;
            }
            result.append(sum % 10);
            index++;
        }
        if (carry > 0) {
            result.append(carry);
        }
        return result.reverse().toString();
    }

    /**
     * 解法二：解法一优化
     *
     * @param num1 1
     * @param num2 2
     * @return java.lang.String
     * @author xianzilei
     * @date 2020/8/14 8:40
     **/
    public String multiply2(String num1, String num2) {
        //M位乘以N位的结果最大为M+N位，最小为M+N-1位，所以可以定义长度为M+N位的数组保存每一位结果，
        //再根据竖式乘法的规律进行每个数字的相乘，
        //即nums1的i位数字乘以nums2[j]位数组，结果存放在数组的i+j和i+j+1的位置（这里的位置i和j指的是从右往左数的位置）

        //特殊情况的排除
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int length1 = num1.length();
        int length2 = num2.length();
        //定义数组保存计算的结果
        int[] resultArray = new int[length1 + length2];
        //循环遍历数组
        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length2; j++) {
                //计算当前两个一位数的乘积
                int product = (num1.charAt(length1 - 1 - i) - '0') * (num2.charAt(length2 - 1 - j) - '0');
                //将结果保存到指定位置处
                //注意这里可能会导致下面两个位置的数大于或等于1，这里会在后面的将数组转化为字符串时处理，当然你也可以在这里直接处理
                resultArray[length1 + length2 - 1 - i - j] += product % 10;
                resultArray[length1 + length2 - 2 - i - j] += product / 10;
            }
        }
        StringBuilder result = new StringBuilder();
        //遍历数组，进行处理
        for (int i = resultArray.length - 1; i > 0; i--) {
            int tmp = resultArray[i];
            result.append(tmp % 10);
            resultArray[i - 1] += tmp / 10;
        }
        //首位数组的处理
        if (resultArray[0] != 0) {
            result.append(resultArray[0]);
        }
        //返回结果
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        Solution43 solution43 = new Solution43();
        System.out.println(solution43.multiply1("2345688", "762453"));
        System.out.println(solution43.multiply2("2345688", "762453"));
        System.out.println("1788476852664");
    }
}
