package questions;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Auther: xianzilei
 * @Date: 2019/9/19 22:08
 * @Description: 两数之和
 */
public class Question1 {

    /**
     * @Description: 解法一：暴力解决
     * @param nums   给定数组
     * @param target 目标和
     * @return: void
     * @auther: xianzilei
     * @date: 2019/9/19 22:15
     **/
    public static int[] twoSumSolutionOne(int[] nums, int target) {
        //定义初始结果，默认为[-1,-1]
        int[] result = {-1, -1};
        //从第一个要素循环查找
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                //如果满足结果，则直接结束程序，返回结果
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * @Description: 解法二：两次遍历HashMap
     * @param nums   给定数组
     * @param target 目标和
     * @return: void
     * @auther: xianzilei
     * @date: 2019/9/19 22:15
     **/
    public static int[] twoSumSolutionTwo(int[] nums, int target) {
        //定义初始结果，默认为[-1,-1]
        int[] result = {-1, -1};
        //使用hashMap，使用查询元素的复杂度由原来的O(n)降低为近似O(1)
        HashMap<Integer, Integer> map = new HashMap<>();
        //先将数据元素入map，其中key为元素值，value为索引位置
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        //遍历数组元素
        for (int i = 0; i < nums.length; i++) {
            //先求出目标和与当前元素的差
            int targetNum = target - nums[i];
            //利用差去map中查找是否存在对应的key，如果存在且二者的索引位置不同，则返回结果
            if (map.containsKey(targetNum) && i != map.get(targetNum)) {
                result[0] = i;
                result[1] = map.get(targetNum);
                break;
            }
        }
        return result;
    }

    /**
     * @Description: 解法三：一次遍历hashMap（最优）
     * @param nums   给定数组
     * @param target 目标和
     * @return: int[]
     * @auther: xianzilei
     * @date: 2019/9/21 22:17
     **/
    public static int[] twoSumSolutionThree(int[] nums, int target) {
        //定义初始结果，默认为[-1,-1]
        int[] result = {-1, -1};
        //使用hashMap
        HashMap<Integer, Integer> map = new HashMap<>();
        //遍历数组元素
        for (int i = 0; i < nums.length; i++) {
            //先求出目标和与当前元素的差
            int targetNum = target - nums[i];
            //利用差去map中查找是否存在对应的key，如果存在且二者的索引位置不同，则返回结果
            if (map.containsKey(targetNum) && i != map.get(targetNum)) {
                result[0] = map.get(targetNum);
                result[1] = i;
                break;
            }
            //否则将数据元素入map，其中key为元素值，value为索引位置
            else {
                map.put(nums[i], i);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] nums = {-2, 7, 12, 7,9,12,9};
        int target = 18;
        int[] result1 = twoSumSolutionOne(nums, target);
        int[] result2 = twoSumSolutionTwo(nums, target);
        int[] result3 = twoSumSolutionThree(nums, target);
        System.out.println("解法一结果：" + Arrays.toString(result1));
        System.out.println("解法二结果：" + Arrays.toString(result2));
        System.out.println("解法三结果：" + Arrays.toString(result3));
    }
}
