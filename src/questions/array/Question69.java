package questions.array;

/**
 * @author : xianzilei
 * @date : 2019/10/16 18:49
 * @Description: x 的平方根
 */
public class Question69 {

    /**
     * 解法一：二分法
     *
     * @param x 1
     * @return: int
     * @author : xianzilei
     * @date : 2019/10/16 18:58
     **/
    public static int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        int left = 1, right = x >> 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 2);
            if (mid == x / mid) {
                return mid;
            } else if (mid > x / mid) {
                if (mid - 1 < x / (mid - 1)) {
                    return mid - 1;
                }
                right = mid - 1;
            } else {
                if (mid + 1 > x / (mid + 1)) {
                    return mid;
                }

                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("解法一：" + mySqrt(9));
    }
}
