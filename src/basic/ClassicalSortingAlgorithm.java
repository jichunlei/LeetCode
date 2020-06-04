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


    public static int[] QuickSort(int[] nums, int start, int end) {
        int target = nums[start];
        int i = start;
        int j = end;
        while (i < j) {
            while (i < j && nums[j] >= target) {
                j--;
            }
        }
        return nums;
    }

    private static int partition(int[] nums, int start, int end) {
        int index = start;
        int target = nums[index];
        for (int i = start + 1; i <= end; i++) {
            if (target > nums[i]){

            }
        }
        return index;
    }

    //交换数组内两个元素
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 6, 2, 6, 9, 10, 3, 6, 9, 0, 13, 8, 1};
//        int[] nums = {1, 2, 3, 10, 5, 7, 8, 9};
//        System.out.println(Arrays.toString(bubbleSort(nums)));
//        System.out.println(Arrays.toString(selectionSort(nums)));
        System.out.println(Arrays.toString(insertionSort(nums)));
    }
}
