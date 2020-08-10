package solutions.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 计数二进制子串
 *
 * @author : xianzilei
 * @date : 2020/8/10
 */
public class Solution696 {

    /**
     * 解法：常规思路
     *
     * @param s 1
     * @return int
     * @author xianzilei
     * @date 2020/8/10 8:24
     **/

    public int countBinarySubstrings(String s) {
        int length = s.length();
        //特殊情况的排除
        if (length == 1) {
            return 0;
        }
        //保存连续字符存放的个数
        //如果字符00110111
        //则会存储为[2,2,1,3]，即两个0，两个1，一个0，三个1
        //则本题结果为该集合每两个相邻元素的最小值之和
        List<Integer> countList = new ArrayList<>();
        int index = 0;
        //遍历字符串
        while (index < length) {
            char c = s.charAt(index);
            int tmp = index;
            //计算连续字符数
            while (tmp < length && c == s.charAt(tmp)) {
                tmp++;
            }
            countList.add(tmp - index);
            index = tmp;
        }

        if (countList.size() <= 1) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < countList.size() - 1; i++) {
            result += Math.min(countList.get(i), countList.get(i + 1));
        }
        return result;
    }

    public static void main(String[] args) {
        Solution696 solution696 = new Solution696();
        System.out.println(solution696.countBinarySubstrings("00110111"));
    }
}
