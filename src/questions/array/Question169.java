package questions.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * @author : xianzilei
 * @date : 2019/9/22 22:18
 * @Description: 求众数
 */
public class Question169 {

    /**
     * @param nums 目标数组
     * @Description: 解法一：暴力解决
     * @return: int
     * @author : xianzilei
     * @date : 2019/9/22 22:20
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
     * @param nums 目标数组
     * @Description: 解法二：使用map
     * @return: int
     * @author : xianzilei
     * @date : 2019/9/22 22:20
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

    /**
     * @param nums 目标数组
     * @Description: 解法三：排序法
     * @return: int
     * @author : xianzilei
     * @date : 2019/9/26 7:56
     **/
    public static int majorityElement3(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * @param nums 目标数组
     * @Description: 解法四：随机法
     * @return: int
     * @author : xianzilei
     * @date : 2019/9/26 8:02
     **/
    public static int majorityElement4(int[] nums) {
        Random random = new Random();
        while (true) {
            int tempIndex = random.nextInt(nums.length);
            int temp = nums[tempIndex];
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == temp) {
                    count++;
                }
            }
            if (count > nums.length / 2) {
                return temp;
            }
        }
    }

    /**
     * @param nums 目标数组
     * @Description: 解法五：投票法
     * @return: int
     * @author : xianzilei
     * @date : 2019/9/26 8:22
     **/
    public static int majorityElement5(int[] nums) {
        //初始候选人为nums[0]
        int candidate = nums[0];
        //初始票数为1
        int count = 1;
        //循环数组
        for (int i = 1; i < nums.length; i++) {
            //如果票数减为0，则换当前数为候选人，票数初始化为1
            if (count == 0) {
                candidate = nums[i];
            }
            //如果与当前候选人相同则票数+1，否则票数-1
            count += candidate == nums[i] ? 1 : -1;
        }
        return candidate;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 2, 3, 2, 3, 3, 2};
        int result1 = majorityElement1(nums);
        int result2 = majorityElement2(nums);
        int result4 = majorityElement4(nums);
        int result5 = majorityElement5(nums);
        int result3 = majorityElement3(nums);
        System.out.println("解法一结果：" + result1);
        System.out.println("解法二结果：" + result2);
        System.out.println("解法三结果：" + result3);
        System.out.println("解法四结果：" + result4);
        System.out.println("解法五结果：" + result5);
    }
}
