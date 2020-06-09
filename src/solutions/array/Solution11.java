package solutions.array;

/**
 * @author : xianzilei
 * @date : 2019/10/10 07:58
 * @Description: 盛最多水的容器
 */
public class Solution11 {

    /**
     * 解法一：暴力解决
     *
     * @param height
     * @return: int
     * @author : xianzilei
     * @date : 2019/10/10 8:00
     **/
    public static int maxArea1(int[] height) {
        int result = 0;
        for (int i = 0; i < height.length-1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int temp = Math.min(height[i], height[j]) * (j - i);
                if (temp > result) {
                    result = temp;
                }
            }
        }
        return result;
    }

    /**
     * 解法二：双指针法
     *
     * @param height 1
     * @return: int
     * @author : xianzilei
     * @date : 2019/10/10 8:18
     **/
    public static int maxArea2(int[] height) {
        int i = 0, j = height.length - 1;
        int result = 0;
        while (i < j) {
            if (height[i] < height[j]) {
                result = Math.max((j - i) * height[i++], result);
            } else {
                result = Math.max((j - i) * height[j--], result);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("解法一结果：" + maxArea1(height));
        System.out.println("解法二结果：" + maxArea2(height));
//        System.out.println("解法三结果：" + maxArea1(height));
    }
}
