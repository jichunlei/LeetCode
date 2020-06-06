package basic;

import java.util.Arrays;

/**
 * 经典排序算法
 *
 * @author : xianzilei
 * @date : 2020/6/4 21:12
 */
public class ClassicalSortingAlgorithm {
    /**
     * 冒泡排序
     *
     * @param nums 1
     * @return int[]
     * @author xianzilei
     * @date 2020/6/4 21:14
     **/
    public static int[] bubbleSort(int[] nums) {
        //i：冒泡次数
        for (int i = 0; i < nums.length - 1; i++) {
            //表示当前顺序是否已经完全有序
            boolean flag = true;
            //j：剩余未排序元素范围
            for (int j = 0; j < nums.length - 1 - i; j++) {
                //往后冒泡，即将较大值往后挪
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    //一旦有动过，则修改flag为false
                    flag = false;
                }
            }
            //如果flag为true，则表示当前数据已经有序，无需继续冒泡
            if (flag) {
                break;
            }
        }
        return nums;
    }

    /**
     * 选择排序
     *
     * @param nums 1
     * @return int[]
     * @author xianzilei
     * @date 2020/6/4 21:41
     **/
    public static int[] selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(nums, i, minIndex);
            }
        }
        return nums;
    }

    /**
     * 插入排序
     *
     * @param nums 1
     * @return int[]
     * @author xianzilei
     * @date 2020/6/4 22:16
     **/
    public static int[] insertionSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j, j - 1);
                } else {
                    break;
                }
            }
        }
        return nums;
    }

    /**
     * 快速排序
     *
     * @param nums 1
     * @return int[]
     * @author xianzilei
     * @date 2020/6/6 23:39
     **/
    public static int[] quickSort(int[] nums) {
        qSort(nums, 0, nums.length - 1);
        return nums;
    }

    private static void qSort(int[] nums, int start, int end) {
        if (start < end) {
            //排序中轴位置
            int middle = getMiddle(nums, start, end);
            //递归排序左半部分
            qSort(nums, start, middle - 1);
            //递归排序右半部分
            qSort(nums, middle + 1, end);
        }
    }

    private static int getMiddle(int[] nums, int start, int end) {
        //以首位元素为中轴元素
        int target = nums[start];
        while (start < end) {
            //从右往左找到第一个比中轴元素小的元素位置
            while (start < end && nums[end] >= target) {
                end--;
            }
            //交换比中轴小的元素到左端
            nums[start] = nums[end];
            //从左往右找到第一个比中轴元素大的元素位置
            while (start < end && nums[start] <= target) {
                start++;
            }
            //交换比中轴大的元素到右端
            nums[end] = nums[start];
        }
        //扫描完成，中轴到位
        nums[start] = target;
        //返回中轴位置
        return start;
    }

    //交换数组内两个元素
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 6, 2, 6, 9, 10, 3, 6, 9, 0, 13, 8, 1};
//        int[] nums = {3, 6, 2, 3};
//        int[] nums = {1, 2, 3, 10, 5, 7, 8, 9};
//        System.out.println(Arrays.toString(bubbleSort(nums)));
//        System.out.println(Arrays.toString(selectionSort(nums)));
        System.out.println(Arrays.toString(quickSort(nums)));
    }
}
