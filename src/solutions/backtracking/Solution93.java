package solutions.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 复原IP地址
 *
 * @author : xianzilei
 * @date : 2020/8/9 15:55
 */
public class Solution93 {
    /**
     * 解法：回溯法
     *
     * @param s 1
     * @return java.util.List<java.lang.String>
     * @author xianzilei
     * @date 2020/8/9 16:45
     **/
    public List<String> restoreIpAddresses(String s) {
        //保存结果集
        List<String> result = new ArrayList<>();
        //开始回溯
        backtrack("", 0, 1, s, result);
        //返回结果
        return result;
    }

    /**
     * @param tmp    当前拼接的ip地址临时字符串
     * @param length 当前拼接用完的字符数
     * @param level  当前拼接到的层级（ip地址有四层）
     * @param s      原始字符串
     * @param result 结果集
     **/
    private void backtrack(String tmp, int length, int level, String s, List<String> result) {
        //添加当前tmp到结果集条件：原始字符串遍历结束+层级达到四级
        //如果已经遍历完字符串
        if (length == s.length()) {
            //如果层级达到四层，则直接将结果添加到结果集中
            if (level > 4) {
                result.add(tmp);
            }
            //不过有没有达到四层，都直接返回
            return;
        }
        //如果层级达到四层，但是字符串没有遍历完，直接返回
        if (level > 4) {
            return;
        }
        //开始回溯，由于有三种切分方式，因此for循环三次进行回溯
        for (int i = 1; i <= 3; i++) {
            //剪枝操作
            //如果不够切分，剪枝
            if (length + i > s.length()) {
                continue;
            }
            String substring = s.substring(length, length + i);
            //如果切割出来的字符串大于1位且以0开头，剪枝
            if (i > 1 && substring.startsWith("0")) {
                continue;
            }
            //如果切割出来的字符串大于255，剪枝
            if (i == 3 && Integer.parseInt(substring) > 255) {
                continue;
            }
            //开始做选择（因为字符串特性，因此无需显示地做选择和取消选择，直接一行回溯搞定 ）
            backtrack("".equals(tmp) ? substring : tmp + "." + substring, length + i,
                    level + 1, s,
                    result);
        }
    }

    public static void main(String[] args) {
        Solution93 solution93 = new Solution93();
        System.out.println(solution93.restoreIpAddresses("010010"));
    }
}
