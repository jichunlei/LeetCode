package solutions.twopointers;

import java.util.Arrays;

/**
 * 长度最小的子数组
 *
 * @author : xianzilei
 * @date : 2020/6/28 07:57
 */
public class Solution209 {
    /**
     * 解法一：暴力法
     *
     * @param s    1
     * @param nums 2
     * @return int
     * @author xianzilei
     * @date 2020/6/28 8:13
     **/
    public static int minSubArrayLen1(int s, int[] nums) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= s) {
                    result = Math.min(result, j - i + 1);
                    break;
                }
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    /**
     * 解法二：二分法
     *
     * @param s    1
     * @param nums 2
     * @return int
     * @author xianzilei
     * @date 2020/6/28 8:41
     **/
    public static int minSubArrayLen2(int s, int[] nums) {
        //初始化结果
        int result = Integer.MAX_VALUE;
        //定义数组（sum[i]表示nums[0]到nums[i]的和，即前i个数的和）
        int[] sum = new int[nums.length + 1];
        //初始化sum数组的各个位置的值，因为nums是正整数数组，所以sum数组严格单调递增，因此可以通过二分法进行操作
        //例如：i到j范围的和可以表示为sum[j]-sum[i-1](i,j>=1)
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        //接下来的思路从暴力法思想一致，从数组第一个元素开始遍历，只不过在求和的过程中使用二分法
        for (int i = 1; i <= nums.length; i++) {
            //目的是sum[j]-sum[i-1]>=s，即sum[j]>=sum[i-1]+s，即在sum中找到接近sum[i-1]+s的值的元素的位置
            int target = s + sum[i - 1];
            int j = Arrays.binarySearch(sum, target);
            //如果查找不到，上述方法返回-(low+1)
            if (j < 0) {
                j = -j - 1;
            }
            //如果找到元素，则更新最小值
            if (j <= nums.length) {
                result = Math.min(result, j - i + 1);
            }
        }
        //返回结果
        return result == Integer.MAX_VALUE ? 0 : result;
    }


    /**
     * 解法三：双指针法
     *
     * @param s    1
     * @param nums 2
     * @return int
     * @author xianzilei
     * @date 2020/6/28 8:33
     **/
    public static int minSubArrayLen3(int s, int[] nums) {
        //初始化结果为int最大值
        int result = Integer.MAX_VALUE;
        //定义双指针，初识位置均为0
        int left = 0, right = 0;
        //存放滑动窗口内的数据之和
        int sum = 0;
        //总体趋势是滑动窗口向右扩张
        while (right < nums.length) {
            //计算和
            sum += nums[right];
            //当达到题设要求时
            while (sum >= s) {
                //更新结果
                result = Math.min(result, right - left + 1);
                //收缩滑动窗口
                sum -= nums[left++];
            }
            //滑动窗口向右扩张
            right++;
        }
        //返回结果
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 4, 4};
        System.out.println(minSubArrayLen1(4, nums));
        System.out.println(minSubArrayLen2(4, nums));
        System.out.println(minSubArrayLen3(4, nums));
    }
}
