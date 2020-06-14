package solutions.dichotomy;

/**
 * 转变数组后最接近目标值的数组和
 *
 * @author : xianzilei
 * @date : 2020/6/14 18:58
 */
public class Solution1300 {

    public static int findBestValue(int[] arr, int target) {
        //数组最大值
        int max = 0;
        //预期平均值
        int avg = target / arr.length;
        //求数组中最大值
        for (int value : arr) {
            max = Math.max(max, value);
        }
        //如果最大值都小于等于预期平均值，则直接返回最大值
        if (max <= avg) {
            return max;
        }

        //初始化差值
        int diff = Integer.MAX_VALUE;
        //从预期平均值开始找
        for (int i = avg; ; i++) {
            //求每次的数组和
            int sum = 0;
            //当value为i时，求此时的数组和
            for (int value : arr) {
                sum += Math.min(value, i);
            }
            //因为从avg开始，因此sum与target的差绝对值是先降后增，
            //当递增时说明到达前一个位置为最优解
            if (Math.abs(sum - target) >= diff) {
                return i - 1;
            }
            //每次更新差的绝对值
            diff = Math.min(Math.abs(sum - target), diff);
        }
    }

    public static void main(String[] args) {
        int[] arr = {60864, 25176, 27249, 21296, 20204};
        int target = 56803;
        System.out.println(findBestValue(arr, target));
    }
}
