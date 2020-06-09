package solutions.array;

import java.util.*;

/**
 * @author : xianzilei
 * @date : 2019/10/10 08:54
 * @Description: 三数之和
 */
public class Solution15 {

    /**
     * 解法一：暴力穷举法
     *
     * @param nums
     * @return: java.util.List<java.util.List                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               <                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               java.lang.Integer>>
     * @author : xianzilei
     * @date : 2019/10/10 8:55
     **/
    public static List<List<Integer>> threeSum1(int[] nums) {
        //如果长度小于三，直接返回空list
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        //排序，目的为了后面的去重
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        //三次循环查找
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        HashSet<List<Integer>> set = new HashSet<>(result);
        result.clear();
        result.addAll(set);
        return result;
    }

    /**
     * 解法二：hash快速定位法
     *
     * @param nums 1
     * @return: java.util.List<java.util.List                                                               <                                                               java.lang.Integer>>
     * @author : xianzilei
     * @date : 2019/10/11 8:16
     **/
    public static List<List<Integer>> threeSum2(int[] nums) {
        //如果长度小于三，直接返回空list
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        //排序，目的为了后面的去重
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        //如果要素全为0，直接返回结果
        if (nums[0] == 0 && nums[nums.length - 1] == 0) {
            result.add(Arrays.asList(0, 0, 0));
            return result;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        //双层循环
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int temp = 0 - nums[i] - nums[j];
                //判断map中是否存在目标值
                if (map.containsKey(temp)) {
                    result.add(Arrays.asList(temp, nums[i], nums[j]));
                }
            }
            map.put(nums[i], i);
        }
        HashSet<List<Integer>> set = new HashSet<>(result);
        result.clear();
        result.addAll(set);
        return result;
    }

    /**
     * 解法三：三指针法
     *
     * @param nums
     * @return: java.util.List<java.util.List                                                               <                                                               java.lang.Integer>>
     * @author : xianzilei
     * @date : 2019/10/11 8:17
     **/
    public static List<List<Integer>> threeSum3(int[] nums) {
        //排序
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        List<List<Integer>> result = new ArrayList<>();
        //先固定i值
        for (int i = 0; i < nums.length - 2; i++) {
            //目的为了去重，需注意应该比较当前元素与之前元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;//j初始为i后一位（i后最小值）
            int k = nums.length - 1;//k初始为最后一位（最大值）
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    //去重
                    while (j < k && nums[j] == nums[j + 1]) j++;
                    while (j < k && nums[k] == nums[k - 1]) k--;
                    //如果三者之和等于0，需要同时移动j和k
                    j++;
                    k--;
                } else if (nums[i] + nums[j] + nums[k] > 0) {
                    //如果和大于0，则k需要往前移动，来缩小值
                    k--;
                } else {
                    //如果和小于0，则j需要往后移，来增大值
                    j++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //[-5, -4, -2, -1, -1, -1, 0, 0, 0, 0, 1, 1, 2, 2, 2, 4, 9]
        int[] nums1 = {-1, 0, 1, 2, -1, -4, -2, 0, 0, 2, 4, -1, 2, 1, 0, 9, -5};
        int[] nums2 = {-1, 0, 1, 2, -1, -4, -2, 0, 0, 2, 4, -1, 2, 1, 0, 9, -5};
        int[] nums3 = {-1, 0, 1, 2, -1, -4, -2, 0, 0, 2, 4, -1, 2, 1, 0, 9, -5};
        System.out.println("解法一结果：" + threeSum1(nums1));
        System.out.println("解法二结果：" + threeSum2(nums2));
        System.out.println("解法三结果：" + threeSum3(nums3));
    }
}
