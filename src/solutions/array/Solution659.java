package solutions.array;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 分割数组为连续子序列
 *
 * @author : xianzilei
 * @date : 2020/12/4 8:21
 */
public class Solution659 {
    /**
     * 解法一：哈希表+最小堆
     *
     * @param nums 1
     * @return boolean
     * @author xianzilei
     * @date 2020/12/4 9:00
     **/
    public boolean isPossible(int[] nums) {
        //定义哈希表
        //key：连续队列的末尾元素
        //value：以key为结尾的所有连续队列的长度（最小堆存储）
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int x : nums) {
            //如果map不存在x，则初始化一个空堆
            if (!map.containsKey(x)) {
                map.put(x, new PriorityQueue<>());
            }
            //如果x-1存在
            if (map.containsKey(x - 1)) {
                //弹出以x-1为结尾的连续队列的最小长度
                int prevLength = map.get(x - 1).poll();
                //如果此时x-1为结尾的连续队列的长度堆为空，则移除x-1
                if (map.get(x - 1).isEmpty()) {
                    map.remove(x - 1);
                }
                //x加入到x-1的队列中
                map.get(x).offer(prevLength + 1);
            }
            //x-1不存在，则x需要自创一个1长度入堆
            else {
                map.get(x).offer(1);
            }
        }
        //取出所有的长度
        Set<Map.Entry<Integer, PriorityQueue<Integer>>> entrySet = map.entrySet();
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : entrySet) {
            PriorityQueue<Integer> queue = entry.getValue();
            //如果存在堆的长度小于3，则返回false
            if (queue.peek() < 3) {
                return false;
            }
        }
        //如果长度均大于等于3，则返回true
        return true;
    }

    /**
     * 解法二：贪心--TODO
     *
     * @param nums 1
     * @return boolean
     * @author xianzilei
     * @date 2020/12/4 9:16
     **/
    public boolean isPossible2(int[] nums) {
        //存储数组元素及出现次数（key：数组元素；value：出现次数）
        Map<Integer, Integer> countMap = new HashMap<>();
        //存储以key结尾的连续序列的数量
        Map<Integer, Integer> endMap = new HashMap<>();
        for (int x : nums) {
            int count = countMap.getOrDefault(x, 0) + 1;
            countMap.put(x, count);
        }
        for (int x : nums) {
            int count = countMap.getOrDefault(x, 0);
            if (count > 0) {
                int prevEndCount = endMap.getOrDefault(x - 1, 0);
                if (prevEndCount > 0) {
                    countMap.put(x, count - 1);
                    endMap.put(x - 1, prevEndCount - 1);
                    endMap.put(x, endMap.getOrDefault(x, 0) + 1);
                } else {
                    int count1 = countMap.getOrDefault(x + 1, 0);
                    int count2 = countMap.getOrDefault(x + 2, 0);
                    if (count1 > 0 && count2 > 0) {
                        countMap.put(x, count - 1);
                        countMap.put(x + 1, count1 - 1);
                        countMap.put(x + 2, count2 - 1);
                        endMap.put(x + 2, endMap.getOrDefault(x + 2, 0) + 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution659 solution659 = new Solution659();
        System.out.println(solution659.isPossible(new int[]{1, 2, 3, 3, 4, 5}));
        System.out.println(solution659.isPossible(new int[]{1, 2, 3, 3, 4, 4, 5, 5}));
        System.out.println(solution659.isPossible(new int[]{1, 2, 3, 4, 4, 5}));
    }
}
