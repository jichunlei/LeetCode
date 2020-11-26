package solutions.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最大间距
 *
 * @author : xianzilei
 * @date : 2020/11/26 8:20
 */
public class Solution164 {
    /**
     * 解法一：直接排序法
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/11/26 8:37
     **/
    public int maximumGap(int[] nums) {
        //特殊情况下的排除
        if (nums == null || nums.length < 2) {
            return 0;
        }
        //排序
        Arrays.sort(nums);
        int res = 0;
        //遍历求最大差值
        for (int i = 1; i < nums.length; i++) {
            int tmp = nums[i] - nums[i - 1];
            if (tmp > res) {
                res = tmp;
            }
        }
        return res;
    }

    /**
     * 解法二：基数排序
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/11/26 8:42
     **/
    public int maximumGap2(int[] nums) {
        //特殊情况下的排除
        if (nums == null || nums.length < 2) {
            return 0;
        }
        List<List<Integer>> list = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            list.add(new ArrayList<>());
        }
        int maxVal = 0;
        int exp = 1;
        for (int num : nums) {
            maxVal = Math.max(num, maxVal);
        }
        while (maxVal > 0) {
            for (int i = 0; i < 10; i++) {
                list.set(i, new ArrayList<>());
            }
            for (int num : nums) {
                list.get(num / exp % 10).add(num);
            }
            int index = 0;
            for (List<Integer> integers : list) {
                for (Integer number : integers) {
                    nums[index++] = number;
                }
            }
            maxVal /= 10;
            exp *= 10;
        }
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            res = Math.max(res, nums[i] - nums[i - 1]);
        }
        return res;
    }

    /**
     * 解法三：桶排法--TODO
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/11/26 9:04
     **/
    public int maximumGap3(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        int n = nums.length;
        int min = nums[0];
        int max = nums[0];
        //找出最大值、最小值
        for (int i = 1; i < n; i++) {
            min = Math.min(nums[i], min);
            max = Math.max(nums[i], max);
        }
        if(max - min == 0) {
            return 0;
        }

        //算出每个箱子的范围
        int interval = (int) Math.ceil((double)(max - min) / (n - 1));

        //每个箱子里数字的最小值和最大值
        int[] bucketMin = new int[n - 1];
        int[] bucketMax = new int[n - 1];

        //最小值初始为 Integer.MAX_VALUE
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        //最小值初始化为 -1，因为题目告诉我们所有数字是非负数
        Arrays.fill(bucketMax, -1);

        //考虑每个数字
        for (int i = 0; i < nums.length; i++) {
            //当前数字所在箱子编号
            int index = (nums[i] - min) / interval;
            //最大数和最小数不需要考虑
            if(nums[i] == min || nums[i] == max) {
                continue;
            }
            //更新当前数字所在箱子的最小值和最大值
            bucketMin[index] = Math.min(nums[i], bucketMin[index]);
            bucketMax[index] = Math.max(nums[i], bucketMax[index]);
        }

        int maxGap = 0;
        //min 看做第 -1 个箱子的最大值
        int previousMax = min;
        //从第 0 个箱子开始计算
        for (int i = 0; i < n - 1; i++) {
            //最大值是 -1 说明箱子中没有数字，直接跳过
            if (bucketMax[i] == -1) {
                continue;
            }

            //当前箱子的最小值减去前一个箱子的最大值
            maxGap = Math.max(bucketMin[i] - previousMax, maxGap);
            previousMax = bucketMax[i];
        }
        //最大值可能处于边界，不在箱子中，需要单独考虑
        maxGap = Math.max(max - previousMax, maxGap);
        return maxGap;
    }

    public static void main(String[] args) {
        Solution164 solution164 = new Solution164();
        System.out.println(solution164.maximumGap(new int[]{3, 6, 9, 1}));
        System.out.println(solution164.maximumGap(new int[]{10}));
        System.out.println(solution164.maximumGap2(new int[]{1, 10000000}));
    }
}
