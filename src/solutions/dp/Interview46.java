package solutions.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题46. 把数字翻译成字符串
 *
 * @author : xianzilei
 * @date : 2020/6/9 08:35
 */
public class Interview46 {

    /**
     * 动态规划
     *
     * @param num 1
     * @return int
     * @author xianzilei
     * @date 2020/6/9 11:21
     **/
    public static int translateNum(int num) {
        //如果为0，直接返回
        if (num == 0) {
            return 1;
        }
        List<Integer> numList = new ArrayList<>();
        //将num的每位数字从低到高保存到numList中
        while (num > 0) {
            //截取当前末尾数字
            numList.add(num % 10);
            //去除末尾数字
            num = num / 10;
        }
        //动态规划获取结果
        return dp(numList.size(), numList);
    }

    /**
     * 1、方法的作用：dp(i)=以第i位数字为前缀的数字的最大翻译数
     * 2、递归终止条件：dp(1)=1,dp(2)=1或2（整体可翻译为2，否则为1）
     * 3、状态转移方程
     * 3.1、dp(i)=dp(i-1)+dp(i-2) (i-1和i位可以翻译)
     * 3.2、dp(i)=dp(i-1) (i-1和i位不可以翻译)
     **/
    private static int dp(int i, List<Integer> numList) {
        //递归终止条件：dp(1)
        if (i == 1) {
            return 1;
        }
        //计算后两位i-1和i-2组合后的值
        int sum = numList.get(i - 1) * 10 + numList.get(i - 2);
        //递归终止条件：dp(2)
        if (i == 2) {
            //如果可翻译，则dp(2)=2
            if (10 <= sum && sum < 26) {
                return 2;
            }
            //如果不可翻译，则dp(2)=1
            else {
                return 1;
            }
        }
        //状态转移方程
        //如果可翻译
        if (10 <= sum && sum < 26) {
            //返回dp(i)=dp(i-1)+dp(i-2)
            return dp(i - 1, numList) + dp(i - 2, numList);
        }
        //如果不可翻译
        else {
            //返回dp(i)=dp(i-1)
            return dp(i - 1, numList);
        }
    }


    public static void main(String[] args) {
        System.out.println(translateNum(12258));
    }

}
