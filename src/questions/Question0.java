package questions;

/**
 * @Auther: xianzilei
 * @Date: 2019/9/28 21:10
 * @Description: 求数组第二大的数
 */
public class Question0 {

    public static int getSecordNum1(int[] nums) {
        int max1Index = 0; //第一大数的索引
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[max1Index]) {
                max1Index = i;
            }
        }
        //初始化第二大数的索引，不同于第一大的数
        int max2Index = max1Index == 0 ? 1 : 0;

        //循环查找
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[max2Index] && nums[i] < nums[max1Index]) {
                max2Index = i;
            }
        }
        return nums[max2Index];
    }

    public static int getSecordNum2(int[] nums) {
        int max1 = nums[0]; //第一大数
        int max2 = nums[1]; //第二大数
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]>=max1){
                max1=nums[i];
            }else if(nums[i]>=max2){
                max2=nums[i];
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3, 4, 5, 6, 7, 6, 7, 8, 8};
        System.out.println(getSecordNum1(nums));
    }
}
