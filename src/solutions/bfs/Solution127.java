package solutions.bfs;

import java.util.*;

/**
 * 单词接龙
 *
 * @author : xianzilei
 * @date : 2020/6/7 10:08
 */
public class Solution127 {
    /**
     * 解法一：广度优先遍历
     *
     * @param beginWord 1
     * @param endWord   2
     * @param wordList  3
     * @return int
     * @author xianzilei
     * @date 2020/11/5 9:15
     **/
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //如果数组不包含endWord，直接返回0
        if (!wordList.contains(endWord)) {
            return 0;
        }
        //定义队列和访问标记数组
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        boolean[] visited = new boolean[wordList.size()];
        int result = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            //遍历当层的数据
            for (int i = 0; i < size; i++) {
                //获取当前字符串
                String cur = queue.poll();
                //遍历路径集合
                for (int j = 0; j < wordList.size(); j++) {
                    //如果已经访问过，直接结束当层循环
                    if (visited[j]) {
                        continue;
                    }
                    String strWord = wordList.get(j);
                    //如果当前字符串与遍历到的字符串可以一个字符转换
                    if (canConvert(cur, strWord)) {
                        //如果遍历到的字符就是endWord，直接返回结果
                        if (strWord.equals(endWord)) {
                            return result + 1;
                        }
                        //否则加入队列中，作为下一层的数据
                        queue.offer(strWord);
                        //标记为已访问
                        visited[j] = true;
                    }
                }
            }
            //进入下一层访问
            result++;
        }
        //如果没有找到路径，返回0
        return 0;
    }

    //判断cur是否可以转换为target
    private boolean canConvert(String cur, String target) {
        //判断思路为cur和target是否只有一个字符不相同

        int length = target.length();
        //记录不同的字符数
        int count = 0;
        for (int i = 0; i < length; i++) {
            //字符数不同，直接+1
            if (cur.charAt(i) != target.charAt(i)) {
                count++;
                //如果不同的字符数大于1，直接返回false
                if (count > 1) {
                    return false;
                }
            }
        }
        return count == 1;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> strings = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        List<String> wordList = new ArrayList<>(strings);
        Solution127 solution127 = new Solution127();
        System.out.println(solution127.ladderLength(beginWord, endWord, wordList));
    }
}
