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

    public String multiply2(String num1, String num2) {


        return "";
    }

    public static void main(String[] args) {
        Solution43 solution43 = new Solution43();
        System.out.println(solution43.multiply1("2345688", "762453"));
        System.out.println("1788476852664");
    }
}
