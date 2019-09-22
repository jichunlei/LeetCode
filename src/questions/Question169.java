package questions;

import java.util.HashMap;

/**
 * @Auther: xianzilei
 * @Date: 2019/9/22 22:18
 * @Description: 求众数
 */
public class Question169 {

    /**
     * @Description: 解法一：暴力解决
     * @param nums 目标数组
     * @return: int[]
     * @auther: xianzilei
     * @date: 2019/9/22 22:20
     **/
    public static int majorityElement1(int[] nums) {
        for (int num : nums) {
            //元素num出现次数
            int sum = 0;
            for (int temp : nums) {
                if (num == temp) {
                    sum++;
                }
            }
            if (sum > nums.length / 2) {
                return num;
            }
        }
        throw new IllegalArgumentException("不存在满足要求的众数！");
    }

    /**
     * @Description: 解法二：使用map
     * @param nums 目标数组
     * @return: int[]
     * @auther: xianzilei
     * @date: 2019/9/22 22:20
     **/
    public static int majorityElement2(int[] nums) {
        //定义map存放数组元素，key为元素值，value为元素出现个数
        HashMap<Integer, Integer> map = new HashMap<>();
        //如果数组长度为1，直接返回
        if (nums.length == 1) {
            return nums[0];
        }
        for (int num : nums) {
            //如果key存在
            if (map.containsKey(num)) {
                //判断+1后的次数是否大于数组长度的一半
                if (map.get(num) + 1 > nums.length / 2) {
                    //满足则直接返回结果
                    return num;
                }
                //否则次数+1
                map.put(num, map.get(num) + 1);
            }
            //如果不存在则默认value为1
            else {
                map.put(num, 1);
            }
        }
        throw new IllegalArgumentException("不存在满足要求的众数！");
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        int result1 = majorityElement1(nums);
        int result2 = majorityElement2(nums);
//        int result3 = singleNumberSolutionThree(nums);
//        int result4 = singleNumberSolutionFour(nums);
//        int result5 = singleNumberSolutionFive(nums);
//        int result6 = singleNumberSolutionSix(nums);
        System.out.println("解法一结果：" + result1);
        System.out.println("解法二结果：" + result2);
//        System.out.println("解法三结果：" + result3);
//        System.out.println("解法四结果：" + result4);
//        System.out.println("解法五结果：" + result5);
//        System.out.println("解法六结果：" + result6);
    }
}
