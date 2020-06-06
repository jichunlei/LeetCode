package questions.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 最长连续序列
 *
 * @author : xianzilei
 * @date : 2020/6/6 08:49
 */
public class Solution128 {
    /**
     * 解法一：使用set集合查询
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/6/6 9:19
     **/
    public static int longestConsecutive1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int result = 1;
        for (Integer i : set) {
            int index = 1;
            if (set.contains(i - 1)) {
                continue;
            }
            while (set.contains(i + index)) {
                index++;
            }
            result = Math.max(result, index);
        }
        return result;
    }

    /**
     * 解法二：并查集
     *
     * @param nums 1
     * @return int
     * @author xianzilei
     * @date 2020/6/6 21:02
     **/
    //key：节点；value：节点的父亲
    //当key=value，表示当前节点是节点所在组的组长
    public static Map<Integer, Integer> fatherMap = new HashMap<>();
    //key：节点；value：节点所在组的成员个数
    public static Map<Integer, Integer> sizeMap = new HashMap<>();

    //查找节点所在组的组长
    private static int find(int son) {
        //查找son的父节点
        Integer parent = fatherMap.get(son);
        //如果父节点不是组长，递归查找
        if (parent != son) {
            parent = find(parent);
        }
        //压缩路径（优化下次查找效率）
        fatherMap.put(son, parent);
        //返回组长
        return parent;
    }

    //合并两个节点所在的组，并返回合并后的组大小
    private static int union(int num1, int num2) {
        //查找num1的组长
        int parent1 = find(num1);
        //查找num2的组长
        int parent2 = find(num2);
        //如果二者组长相同，则无需合并，直接返回组大小
        if (parent1 == parent2) {
            return sizeMap.get(num1);
        }
        //否则需要进行合并
        else {
            //获取num1所在组的原大小
            int size1 = sizeMap.get(parent1);
            //获取num2所在组的原大小
            int size2 = sizeMap.get(parent2);
            //如果1组大小大于或等于2组，则将2组合并到1组中来
            if (size1 >= size2) {
                //2组的组长的父节点修改为1组组长
                fatherMap.put(parent2, parent1);
                //1组的大小更新
                sizeMap.put(parent1, size1 + size2);
            }
            //否则将1组合并到2组中来
            else {
                //1组的组长的父节点修改为2组组长
                fatherMap.put(parent1, parent2);
                //2组的大小更新
                sizeMap.put(parent2, size1 + size2);
            }
            //返回合并后的组大小
            return size1 + size2;
        }
    }

    public static int longestConsecutive2(int[] nums) {
        //如果数组为空或者大小为0，则初始值为0，否则为1
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //定义结果初始值为1
        int result = 1;
        //初始化两个map
        for (int num : nums) {
            //初始时刻每个元素都自成一组，各自是自己所在组的组长
            fatherMap.put(num, num);
            //各个组的长度初始都为1
            sizeMap.put(num, 1);
        }
        //循环数组元素
        for (int num : nums) {
            //如果数组发现它的下一个元素存在，则合并
            if (fatherMap.containsKey(num + 1)) {
                result = Math.max(result, union(num, num + 1));
            }
        }
        //返回结果集
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {100, 2, 200, 1, 3, 2};
        System.out.println(longestConsecutive1(nums));
        System.out.println(longestConsecutive2(nums));
    }
}
