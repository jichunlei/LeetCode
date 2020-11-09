package solutions.array;

import java.util.*;

/**
 * 前 K 个高频元素
 *
 * @author : xianzilei
 * @date : 2020/9/7 8:23
 */
public class Solution347 {
    /**
     * 解法一：堆排序
     *
     * @param nums 1
     * @param k    2
     * @return int[]
     * @author xianzilei
     * @date 2020/9/7 9:03
     **/
    public int[] topKFrequent1(int[] nums, int k) {
        //通过hash表保存数值及出现的次数
        int[] result = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //定义优先队列
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (priorityQueue.size() < k) {
                priorityQueue.offer(key);
            } else if (map.get(priorityQueue.peek()) < value) {
                priorityQueue.poll();
                priorityQueue.offer(key);
            }
        }
        for (int i = k - 1; i >= 0; i--) {
            result[i] = priorityQueue.poll();
        }
        return result;
    }

    /**
     * 解法二：桶排序
     *
     * @param nums 1
     * @param k    2
     * @return int[]
     * @author xianzilei
     * @date 2020/9/7 9:09
     **/
    public int[] topKFrequent2(int[] nums, int k) {
        //通过hash表保存数值及出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //定义集合类型的数组，数组下标存储数值的出现次数，数组存储该次数下的数值列表（桶排序）
        ArrayList<Integer>[] listArray = new ArrayList[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (listArray[value] == null) {
                listArray[value] = new ArrayList<>();
            }
            listArray[value].add(key);
        }
        int[] result = new int[k];
        int count = 0;
        //逆序遍历集合数组，组装k个数到结果集中
        for (int i = nums.length; i >= 0; i--) {
            if (listArray[i] == null) {
                continue;
            }
            ArrayList<Integer> list = listArray[i];
            for (Integer top : list) {
                if (count < k) {
                    result[count++] = top;
                } else {
                    return result;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution347 solution347 = new Solution347();
        System.out.println(Arrays.toString(solution347.topKFrequent1(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.toString(solution347.topKFrequent1(new int[]{1}, 1)));
        System.out.println(Arrays.toString(solution347.topKFrequent2(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.toString(solution347.topKFrequent2(new int[]{1}, 1)));
    }
}
