package solutions.array;

/**
 * 上升下降字符串
 *
 * @author : xianzilei
 * @date : 2020/11/25 8:22
 */
public class Solution1370 {
    /**
     * 解法：桶排法
     *
     * @param s 1
     * @return java.lang.String
     * @author xianzilei
     * @date 2020/11/25 8:48
     **/
    public String sortString(String s) {
        //解题思路：本题的题意就是讲字符串修改为升序-降序交替的排列，其中升序和降序是严格递增和递减
        //考虑到题目只包含小写字母

        //特殊情况下的排除
        int length = s.length();
        if (length <= 1) {
            return s;
        }
        //定义桶数组，并初始化（下标索引表示字母，数组值表示个数）
        int[] array = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < length; i++) {
            array[chars[i] - 'a']++;
        }
        //当前填充的字符个数
        int index = 0;
        StringBuilder res = new StringBuilder();
        while (index < length) {
            //升序填充
            for (int i = 0; i < 26; i++) {
                //判断是否已填充结束
                if (index == length) {
                    break;
                }
                if (array[i] > 0) {
                    res.append((char) ('a' + i));
                    index++;
                    array[i]--;
                }
            }
            //降序填充
            for (int i = 25; i >= 0; i--) {
                //判断是否已填充结束
                if (index == length) {
                    break;
                }
                if (array[i] > 0) {
                    res.append((char) ('a' + i));
                    index++;
                    array[i]--;
                }
            }
        }
        //返回结果
        return res.toString();
    }

    public static void main(String[] args) {
        Solution1370 solution1370 = new Solution1370();
        System.out.println(solution1370.sortString("aaaabbbbcccc"));
    }
}
