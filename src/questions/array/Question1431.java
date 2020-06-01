package questions.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 拥有最多糖果的孩子
 *
 * @author : xianzilei
 * @date : 2020/6/1 13:49
 */
public class Question1431 {
    /**
     * 功能描述: 解法一-双重循环法
     *
     * @param candies      1
     * @param extraCandies 2
     * @return java.util.List<java.lang.Boolean>
     * @author xianzilei
     * @date 2020/6/1 14:43
     **/
    public static List<Boolean> kidsWithCandies1(int[] candies, int extraCandies) {
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < candies.length; i++) {
            int temp = candies[i] + extraCandies;
            boolean flag = true;
            for (int j = 0; j < candies.length; j++) {
                if (candies[j] > temp && j != i) {
                    flag = false;
                }
            }
            result.add(flag);
        }
        return result;
    }

    /**
     * 功能描述: 解法二-两次循环法
     *
     * @param candies      1
     * @param extraCandies 2
     * @return java.util.List<java.lang.Boolean>
     * @author xianzilei
     * @date 2020/6/1 14:43
     **/
    public static List<Boolean> kidsWithCandies2(int[] candies, int extraCandies) {
        List<Boolean> result = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < candies.length; i++) {
            if (max < candies[i]) {
                max = candies[i];
            }
        }
        for (int i = 0; i < candies.length; i++) {
            result.add(candies[i] + extraCandies >= max);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] candies = {2,3,5,1,3};
        int extraCandies = 3;
        System.out.println(kidsWithCandies1(candies, extraCandies));
        System.out.println(kidsWithCandies2(candies, extraCandies));
    }
}
