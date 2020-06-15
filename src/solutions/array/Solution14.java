package solutions.array;

/**
 * 最长公共前缀
 *
 * @author : xianzilei
 * @date : 2020/6/15 07:55
 */
public class Solution14 {

    /**
     * 常规思路
     *
     * @param strs 1
     * @return java.lang.String
     * @author xianzilei
     * @date 2020/6/15 8:13
     **/
    public static String longestCommonPrefix(String[] strs) {
        //特殊情况处理
        if (strs == null || strs.length == 0) {
            return "";
        }
        //以第一个元素为基准进行字符串前缀匹配
        for (int i = 1; i <= strs[0].length(); i++) {
            //获取待匹配的前缀字符串
            String substring = strs[0].substring(0, i);
            //遍历数组，查找最长前缀
            for (int j = 1; j < strs.length; j++) {
                //一旦存在不匹配的元素，则返回前一级的前缀
                if (!strs[j].startsWith(substring)) {
                    return substring.substring(0, i - 1);
                }
            }
        }
        //循环完全执行表示第一个元素即可最长前缀
        return strs[0];
    }

    public static void main(String[] args) {
        String[] strs = {"flow", "flower", "flower"};
        System.out.println(longestCommonPrefix(strs));
    }
}
