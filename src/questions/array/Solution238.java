package questions.array;

import java.util.Arrays;

/**
 * 除自身以外数组的乘积
 *
 * @author : xianzilei
 * @date : 2020/6/4 07:55
 */
public class Solution238 {

    /**
     * 解法一：左右乘积列表
     *
     * @param nums 1
     * @return int[]
     * @author xianzilei
     * @date 2020/6/4 8:06
     **/
    public static int[] productExceptSelf1(int[] nums) {
        int len = nums.length;
        //left数组保存每个位置的左侧所有元素乘积
        int[] left = new int[len];
        //首位左侧没有元素，默认为1
        left[0] = 1;
        //right数组保存每个位置的右侧所有元素乘积
        int[] right = new int[len];
        //末尾右侧没有元素，默认为1
        right[len - 1] = 1;
        //result保存结果集
        int[] result = new int[len];
        //循环计算并填充left、right数组每个位置的值
        for (int i = 1, j = len - 2; i < len && j >= 0; i++, j--) {
            left[i] = left[i - 1] * nums[i - 1];
            right[j] = right[j + 1] * nums[j + 1];
        }
        //结果集为i位置=left[i] * right[i]
        for (int i = 0; i < len; i++) {
            result[i] = left[i] * right[i];
        }
        return result;
    }

    /**
     * 解法二：左右乘积列表（优化）
     *
     * @param nums 1
     * @return int[]
     * @author xianzilei
     * @date 2020/6/4 8:28
     **/
    public static int[] productExceptSelf2(int[] nums) {
        int len = nums.length;
        //只定义结果集
        int[] result = new int[len];
        //首先使用result当做上文中的left数组，通过循环填充数据
        result[0] = 1;
        for (int i = 1; i < len; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        //定义right变量保存上文中的right数组每一次的值
        //下面的循环从右往左，所以初始right为1
        int right = 1;
        for (int j = len - 1; j >= 0; j--) {
            //结果集为左侧值*右侧值
            result[j] = result[j] * right;
            //更新right的值
            right = right * nums[j];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(productExceptSelf1(nums)));
        System.out.println(Arrays.toString(productExceptSelf2(nums)));
    }
}
