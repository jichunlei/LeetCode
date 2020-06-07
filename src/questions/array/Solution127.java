package questions.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 单词接龙
 *
 * @author : xianzilei
 * @date : 2020/6/7 10:08
 */
public class Solution127 {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {

        return 0;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> strings = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        List<String> wordList = new ArrayList<>(strings);
        System.out.println(ladderLength(beginWord, endWord, wordList));
    }
}
