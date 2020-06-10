package solutions.array;

import java.util.LinkedList;

/**
 * 回文数
 *
 * @author : xianzilei
 * @date : 2020/6/10 07:55
 */
public class Solution9 {
    /**
     * 常规解法
     *
     * @param x 1
     * @return boolean
     * @author xianzilei
     * @date 2020/6/10 8:13
     **/
    public static boolean isPalindrome1(int x) {
        if (x < 0) {
            return false;
        }
        if (x == 0) {
            return true;
        }
        LinkedList<Integer> numList = new LinkedList<>();
        int num = x;
        while (num > 0) {
            numList.addFirst(num % 10);
            num = num / 10;
        }
        int sum = 0;
        int step = 1;
        for (Integer i : numList) {
            sum = sum + i * step;
            step *= 10;
        }
        return sum == x;
    }

    /**
     * 空间复杂的优化
     *
     * @param x 1
     * @return boolean
     * @author xianzilei
     * @date 2020/6/10 8:13
     **/
    public static boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        int sum = 0;
        int tmp = x;
        while (tmp > 0) {
            sum = sum * 10 + tmp % 10;
            tmp /= 10;
        }
        return sum == x;
    }

    /**
     * 时间和空间复杂度的优化
     *
     * @param x 1
     * @return boolean
     * @author xianzilei
     * @date 2020/6/10 8:25
     **/
    public static boolean isPalindrome3(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        //反转数
        int revertedNum = 0;
        //当反转不到一半时，继续反转
        while (x > revertedNum) {
            revertedNum = revertedNum * 10 + x % 10;
            x = x / 10;
        }
        return x == revertedNum || x == revertedNum / 10;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome1(121));
        System.out.println(isPalindrome2(121));
        System.out.println(isPalindrome3(0));
    }
}
