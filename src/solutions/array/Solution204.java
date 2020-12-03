package solutions.array;

/**
 * 计数质数
 *
 * @author : xianzilei
 * @date : 2020/12/3 8:16
 */
public class Solution204 {
    /**
     * 解法一：常规思路
     *
     * @param n 1
     * @return int
     * @author xianzilei
     * @date 2020/12/3 8:27
     **/
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        if (n == 3) {
            return 1;
        }
        int res = 1;
        for (int i = 3; i < n; i += 2) {
            if (isPrime1(i)) {
                res++;
            }
        }
        return res;
    }

    /**
     * 解法二：解法一优化
     *
     * @param n 1
     * @return int
     * @author xianzilei
     * @date 2020/12/3 8:41
     **/
    public int countPrimes2(int n) {
        if (n <= 2) {
            return 0;
        }
        if (n == 3) {
            return 1;
        }
        int res = 1;
        for (int i = 3; i < n; i += 2) {
            if (isPrime2(i)) {
                res++;
            }
        }
        return res;
    }

    /**
     * 解法三：解法二优化
     *
     * @param n 1
     * @return int
     * @author xianzilei
     * @date 2020/12/3 8:45
     **/
    public int countPrimes3(int n) {
        if (n <= 2) {
            return 0;
        }
        if (n == 3) {
            return 1;
        }
        int res = 1;
        for (int i = 3; i < n; i += 2) {
            if (isPrime3(i)) {
                res++;
            }
        }
        return res;
    }

    /**
     * 解法四：解法三优化
     *
     * @param n 1
     * @return int
     * @author xianzilei
     * @date 2020/12/3 9:27
     **/
    public int countPrimes4(int n) {
        //特殊情况下的排除
        if (n <= 2) {
            return 0;
        }
        if (n == 3) {
            return 1;
        }
        int res = 1;
        //除了2其余所有的质数都是奇数，所以这里从3开始遍历，且只遍历奇数
        for (int i = 3; i < n; i += 2) {
            //判断是否为质数（采用6倍法）
            if (isPrime4(i)) {
                res++;
            }
        }
        return res;
    }

    /**
     * 解法五：空间换时间法
     *
     * @param n 1
     * @return int
     * @author xianzilei
     * @date 2020/12/3 9:28
     **/
    public int countPrimes5(int n) {
        //定义n长度的数组，数组的索引表示值，数组值表示当前的索引值是否是素数
        //初始时刻都是素数
        boolean[] notPrimes = new boolean[n];
        int res = 0;
        //从质数2开始计算（即忽略0和1）
        for (int i = 2; i < n; i++) {
            //如果该处是质数
            if (!notPrimes[i]) {
                //结果+1
                res++;
                //所有该处索引值的整数倍数的位置均修改为是素数
                for (int j = 2; i * j < n; j++) {
                    notPrimes[i * j] = true;
                }
            }
        }
        //返回结果
        return res;
    }

    //判断一个数是否为素数（方法一）
    private boolean isPrime1(int n) {
        if (n <= 3) {
            return n > 1;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    //判断一个数是否为素数（方法二）
    private boolean isPrime2(int n) {
        //当一个数不是质数时，必定存在两个约数，一个大于等于sqrt(n)，另一个小于sqrt(n)
        if (n <= 3) {
            return n > 1;
        }
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    //判断一个数是否为素数（方法三）
    private boolean isPrime3(int n) {
        //一个数不能被2整除，那么这个数一定不能被其他偶数整除
        if (n <= 3) {
            return n > 1;
        }
        if (n % 2 == 0) {
            return false;
        }
        int sqrt = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrt; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    //判断一个数是否为素数（方法四）
    private boolean isPrime4(int n) {
        //除了2,3,以外对于任意的n，只有6n-1和6n+1有可能是素数
        if (n <= 1) {
            return false;
        }
        if (n == 2 || n == 3) {
            return true;
        }
        //非6n-1和6n+1的数一定不是质数
        if (n % 6 != 1 && n % 6 != 5) {
            return false;
        }
        //采用平方根进行减少遍历次数
        int sqrt = (int) Math.sqrt(n);
        for (int i = 5; i <= sqrt; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution204 solution204 = new Solution204();
//        System.out.println(solution204.countPrimes(10));
//        System.out.println(solution204.countPrimes(0));
//        System.out.println(solution204.countPrimes(1));
//        System.out.println(solution204.countPrimes(2));
        System.out.println(solution204.countPrimes2(499979));
    }
}
