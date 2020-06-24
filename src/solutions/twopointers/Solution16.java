package solutions.twopointers;

import java.util.Arrays;

/**
 * 最接近的三数之和
 *
 * @author : xianzilei
 * @date : 2020/6/24 08:05
 */
public class Solution16 {
    public static int threeSumClosest(int[] nums, int target) {
        //复制当前数组，避免排序时修改原有的数组
        int[] tmpNums = nums.clone();
        //升序排序
        Arrays.sort(tmpNums);
        //初始化最小差值绝对值
        int min = Integer.MAX_VALUE;
        //初始化结果
        int result = 0;
        //固定一个元素，利用双指针控制另外两个元素
        for (int i = 0; i < tmpNums.length - 2; i++) {
            //指针1：指向i+1位置
            int j = i + 1;
            //指针2：指向数组末尾
            int k = tmpNums.length - 1;
            //当双指针未重合时继续查找
            while (j < k) {
                //计算三数之和与目标值的差值
                int tmp = tmpNums[i] + tmpNums[j] + tmpNums[k] - target;
                //如果差值为0，直接返回目标值（因为不会再有比0更接近的结果了）
                if (tmp == 0) {
                    return target;
                }
                //求差值的绝对值
                int abs = Math.abs(tmp);
                //如果最小差值的绝对值大于当前差值的绝对值
                if (min > abs) {
                    //更新min
                    min = abs;
                    //更新结果
                    result = tmp + target;
                }
                //如果差值大于0，说明三数之和大了，考虑前移k指针使三数之和减小
                if (tmp > 0) {
                    k--;
                }
                //如果差值小于0，说明三数之和小了，考虑后移j指针使三数之和增大
                else {
                    j++;
                }
            }
        }
        //返回结果
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        System.out.println(threeSumClosest(nums, target));
    }
}
