package solutions.recursion;

/**
 * 求1+2+…+n
 *
 * @author : xianzilei
 * @date : 2020/6/2 08:00
 */
public class Solution64 {

    /**
     * 功能描述: 解法：递归+短路运算
     *
     * @param n 1
     * @return int
     * @author xianzilei
     * @date 2020/6/2 8:10
     **/
    public static int sumNums(int n) {
        boolean flag = n > 1 && (n += sumNums(n - 1)) > 0;
        return n;
    }

    public static void main(String[] args) {
        System.out.println(sumNums(9));
    }
}
