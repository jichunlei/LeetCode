package questions.array;

import java.util.*;

/**
 * @Auther: xianzilei
 * @Date: 2019/9/21 22:53
 * @Description: 只出现一次的数字
 */
public class Question136 {

    /**
     * @param nums 目标数组
     * @Description: 解法一：暴力解决
     * @return: int 只出现一次的数字
     * @auther: xianzilei
     * @date: 2019/9/21 23:10
     **/
    public static int singleNumberSolutionOne(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean flag = true;
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j] && i != j) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return nums[i];
            }
        }
        throw new IllegalArgumentException("不存在只出现一次的数字！");
    }

    /**
     * @param nums 目标数组
     * @Description: 解法二：两次遍历map
     * @return: int 只出现一次的数字
     * @auther: xianzilei
     * @date: 2019/9/21 23:10
     **/
    public static int singleNumberSolutionTwo(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        //将数组元素放入map中
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                //如果已存在，则value值加1
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                //否则初始值为1
                map.put(nums[i], 1);
            }
        }
        //循环map中的值，找到value为1的数，即为结果
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("不存在只出现一次的数字！");
    }

    /**
     * @param nums 目标数组
     * @Description: 解法三：一次遍历map
     * @return: int 只出现一次的数字
     * @auther: xianzilei
     * @date: 2019/9/21 23:10
     **/
    public static int singleNumberSolutionThree(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        //将数组元素放入map中
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                //如果已存在，则remove掉
                map.remove(nums[i]);
            } else {
                //否则放入map中
                map.put(nums[i], 0);
            }
        }
        if (map.size() == 1) {
            for (Integer key : map.keySet()) {
                return key;
            }
        }
        throw new IllegalArgumentException("不存在只出现一次的数字！");
    }

    /**
     * @param nums 目标数组
     * @Description: 解法四：排序+暴力（暴力解决的优化）
     * @return: int 只出现一次的数字
     * @auther: xianzilei
     * @date: 2019/9/21 23:10
     **/
    public static int singleNumberSolutionFour(int[] nums) {
        Arrays.sort(nums);
        if (nums.length == 1) {
            return nums[0];
        }
        if(nums[0]!=nums[1]){
            return nums[0];
        }
        if(nums[nums.length-1]!=nums[nums.length-2]){
            return nums[nums.length-1];
        }
        if (nums.length > 2) {
            for (int i = 1; i < nums.length - 1; i++) {
                if (nums[i] != nums[i - 1] && nums[i] != nums[i + 1]) {
                    return nums[i];
                }
            }
        }
        throw new IllegalArgumentException("不存在唯一只出现一次的数字！");
    }

    /**
     * @Description: 解法五：按位异或操作（最优）
     * @param nums 目标数组
     * @return: int
     * @auther: xianzilei
     * @date: 2019/9/22 10:33
     **/
    public static int singleNumberSolutionFive(int[] nums) {
        int result=0;
        for (int num : nums) {
            result^=num;
        }
        return result;
    }

    /**
     * @Description: 解法六：数学运算
     * @param nums 目标数组
     * @return: int
     * @auther: xianzilei
     * @date: 2019/9/22 10:33
     **/
    public static int singleNumberSolutionSix(int[] nums) {
        int sum1=0;//不重复元素和
        int sum2=0;//数组元素和
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            boolean flag = set.add(num);
            if(flag){
               sum1+=num;
            }
            sum2+=num;
        }
        return 2*sum1-sum2;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 8, 4, 8, 4, 0, 9, 0};
        int result1 = singleNumberSolutionOne(nums);
        int result2 = singleNumberSolutionTwo(nums);
        int result3 = singleNumberSolutionThree(nums);
        int result4 = singleNumberSolutionFour(nums);
        int result5 = singleNumberSolutionFive(nums);
        int result6 = singleNumberSolutionSix(nums);
        System.out.println("解法一结果：" + result1);
        System.out.println("解法二结果：" + result2);
        System.out.println("解法三结果：" + result3);
        System.out.println("解法四结果：" + result4);
        System.out.println("解法五结果：" + result5);
        System.out.println("解法六结果：" + result6);
    }
}
