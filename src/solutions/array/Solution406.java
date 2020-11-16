package solutions.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 根据身高重建队列
 *
 * @author : xianzilei
 * @date : 2020/11/16 8:21
 */
public class Solution406 {
    /**
     * 解法一：从高到低
     *
     * @param people 1
     * @return int[][]
     * @author xianzilei
     * @date 2020/11/16 9:01
     **/
    public int[][] reconstructQueue(int[][] people) {
        //考虑到身高高的人的位置不受身高低的人影响，因此我们可以根据身高从高到低排列
        //每次排列时，大于等于当前身高的人肯定先排列好了，因此我们可以直接插入到h的位置
        //后续的身高都比小于等于自身，即使位置变化了也不会存在影响

        //排序数组：先按照身高从高到低排序，再按照人数h从小的到大排序
        Arrays.sort(people, (person1, person2) -> person1[0] == person2[0] ?
                person1[1] - person2[1] : person2[0] - person1[0]);
        List<int[]> res = new ArrayList<>();
        //根据排序好的数组添加到list中，添加的位置由h决定
        for (int[] person : people) {
            res.add(person[1], person);
        }
        //返回结果
        return res.toArray(new int[people.length][2]);
    }

    public static void main(String[] args) {
        Solution406 solution406 = new Solution406();
        int[][] result = solution406.reconstructQueue(new int[][]{
                {7, 0},
                {4, 4},
                {7, 1},
                {5, 0},
                {6, 1},
                {5, 2}
        });
        //[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
        for (int[] ints : result) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 解法二：从低到高--TODO
     *
     * @param people 1
     * @return int[][]
     * @author xianzilei
     * @date 2020/11/16 9:29
     **/
    public int[][] reconstructQueue2(int[][] people) {
        Arrays.sort(people, (person1, person2) -> person1[0] == person2[0] ?
                person2[1] - person1[1] : person1[0] - person2[0]);
        int n = people.length;
        int[][] ans = new int[n][];
        for (int[] person : people) {
            int spaces = person[1] + 1;
            for (int i = 0; i < n; ++i) {
                if (ans[i] == null) {
                    --spaces;
                    if (spaces == 0) {
                        ans[i] = person;
                        break;
                    }
                }
            }
        }
        return ans;
    }
}
