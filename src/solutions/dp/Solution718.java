package solutions.dp;

/**
 * 最长重复子数组
 *
 * @author : xianzilei
 * @date : 2020/7/1 07:53
 */
public class Solution718 {
    /**
     * 解法一：暴力法
     *
     * @param A 1
     * @param B 2
     * @return int
     * @author xianzilei
     * @date 2020/7/1 8:15
     **/
    public static int findLength1(int[] A, int[] B) {
        int lengthA = A.length;
        int lengthB = B.length;
        //初始化结果
        int result = 0;
        //双层循环遍历
        for (int i = 0; i < lengthA; i++) {
            for (int j = 0; j < lengthB; j++) {
                //定义双指针，分别指向A,B数组
                int m = i;
                int n = j;
                //保存当层的结果
                int tmp = 0;
                //计算最大公共子数组长度
                while (m < lengthA && n < lengthB && A[m] == B[n]) {
                    m++;
                    n++;
                    tmp++;
                }
                //更新结果
                result = Math.max(result, tmp);
            }
        }
        //返回结果
        return result;
    }

    /**
     * 解法二：暴力法的优化（舍弃无用的计算）
     *
     * @param A 1
     * @param B 2
     * @return int
     * @author xianzilei
     * @date 2020/7/1 8:28
     **/
    public static int findLength2(int[] A, int[] B) {
        int lengthA = A.length;
        int lengthB = B.length;
        int result = 0;
        //当A,B数组剩余的长度小于result时，此时没有必要再进行计算下去了，
        //因为再计算的话最大也大不过result，因此直接在此处终止即可
        for (int i = 0; result < lengthA - i; i++) {
            for (int j = 0; result < lengthB - j; j++) {
                int m = i;
                int n = j;
                int tmp = 0;
                while (m < lengthA && n < lengthB && A[m] == B[n]) {
                    m++;
                    n++;
                    tmp++;
                }
                result = Math.max(result, tmp);
                //当m和n指针都遍历到了对应数组的最末尾，
                //则表示此时已经达到了最大值，后续不可能有大于此结果的值，直接返回结果即可
                if (m == lengthA && n == lengthB) {
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * 解法三：动态规划
     *
     * @param A 1
     * @param B 2
     * @return int
     * @author xianzilei
     * @date 2020/7/1 8:48
     **/
    public static int findLength3(int[] A, int[] B) {
        //状态转移方程
        //dp[i][j]：表示以A[i]和B[j]结尾的最长子数组长度（注意这里的定义：子数组是要包含A[i]和B[j]的哦！）
        //dp[0][j]=(A[0]==B[j]?1:0)
        //dp[i][0]=(A[i]==B[0]?1:0)
        //dp[i][j]=0;当A[i]!=B[j]时
        //dp[i][j]=dp[i-1][j-1]+1;当A[i]=B[j]时
        //改题需要的结果是dp[i][j]中的最大值
        int lengthA = A.length;
        int lengthB = B.length;
        //定义状态数组
        int[][] dp = new int[lengthA][lengthB];
        //初始化已知值dp[i][0]=(A[i]==B[0]?1:0)
        for (int i = 0; i < lengthA; i++) {
            if (A[i] == B[0]) {
                dp[i][0] = 1;
            }
        }
        //初始化已知值dp[0][j]=(A[0]==B[j]?1:0)
        for (int j = 0; j < lengthB; j++) {
            if (B[j] == A[0]) {
                dp[0][j] = 1;
            }
        }
        //双层循环计算数组其余位置的值
        for (int i = 1; i < lengthA; i++) {
            for (int j = 1; j < lengthB; j++) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        //定义结果
        int result = 0;
        //查找数组中的最大值
        for (int i = 0; i < lengthA; i++) {
            for (int j = 0; j < lengthB; j++) {
                result = Math.max(result, dp[i][j]);
            }
        }
        //返回结果
        return result;
    }

    /**
     * 解法四：动态规划（状态压缩）
     *
     * @param A 1
     * @param B 2
     * @return int
     * @author xianzilei
     * @date 2020/7/1 9:35
     **/
    public static int findLength4(int[] A, int[] B) {
        //观察到dp[i][j]只与dp[i-1][j-1]有关，即只与它上一层数据有关，
        //所以考虑将原二维数组压缩为一维数组
        int lengthA = A.length;
        int lengthB = B.length;
        //定义状态数组
        int[] dp = new int[lengthA];
        int result = 0;
        //初始化已知值dp
        for (int i = 0; i < lengthA; i++) {
            if (A[i] == B[0]) {
                dp[i] = 1;
                result = 1;
            }
        }
        //双层循环计算数组其余位置的值
        //从第2层进行遍历
        for (int j = 1; j < lengthB; j++) {
            //这里注意需要倒着遍历
            for (int i = lengthA - 1; i >= 0; i--) {
                if (A[i] == B[j]) {
                    //这里注意需要控制初始位置的值
                    dp[i] = i == 0 ? 1 : dp[i - 1] + 1;
                    //更新结果
                    result = Math.max(result, dp[i]);
                } else {
                    dp[i] = 0;
                }
            }
        }
        //返回结果
        return result;
    }

    /**
     * 解法五：滑动窗口写法
     *
     * @param A 1
     * @param B 2
     * @return int
     * @author xianzilei
     * @date 2020/7/1 14:31
     **/
    public static int findLength5(int[] A, int[] B) {
        int lengthA = A.length;
        int lengthB = B.length;
        int res = 0;
        //先固定A数组，滑动B数组，计算A和B重合部分的最大子串
        for (int i = 0; i < lengthA; i++) {
            //计算重合部分的长度
            int len = Math.min(lengthB, lengthA - i);
            //更新结果
            res = Math.max(res, maxLength(A, B, i, 0, len));
        }
        //固定B数组，滑动A数组，计算A和B重合部分的最大子串
        for (int i = 0; i < lengthB; i++) {
            //计算重合部分的长度
            int len = Math.min(lengthA, lengthB - i);
            //更新结果
            res = Math.max(res, maxLength(A, B, 0, i, len));
        }
        return res;
    }

    //计算A和B重合部分的最大子串
    public static int maxLength(int[] A, int[] B, int indexA, int indexB, int len) {
        //初始化最大值
        int max = 0;
        //存放每次循环比较的临时最大值
        int count = 0;
        for (int i = 0; i < len; i++) {
            //当对应位置值相等时，则计数+1
            if (A[indexA + i] == B[indexB + i]) {
                count++;
            }
            //否则需要重新计数
            else {
                count = 0;
            }
            //更新最大值
            max = Math.max(max, count);
        }
        //返回最大值
        return max;
    }


    public static void main(String[] args) {
        int[] A = {1, 0, 1, 0, 0, 0, 0, 0, 1, 1};
        int[] B = {1, 1, 0, 1, 1, 0, 0, 0, 0, 0};
//        int[] A = {1, 1, 0, 0, 1};
//        int[] B = {1, 1, 0, 0, 0};
        System.out.println(findLength1(A, B));
        System.out.println(findLength2(A, B));
        System.out.println(findLength3(A, B));
        System.out.println(findLength4(A, B));
        System.out.println(findLength5(A, B));
    }
}

