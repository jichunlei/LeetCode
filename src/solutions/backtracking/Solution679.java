package solutions.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 24 点游戏
 *
 * @author : xianzilei
 * @date : 2020/8/22 8:43
 */
public class Solution679 {

    /**
     * 解法：回溯法
     *
     * @param nums 1
     * @return boolean
     * @author xianzilei
     * @date 2020/8/22 9:30
     **/
    public boolean judgePoint24(int[] nums) {
        //将数组放入list中，并转化为double类型
        List<Double> list = new ArrayList<>();
        for (int num : nums) {
            list.add((double) num);
        }
        //递归计算
        return solve(list);
    }

    //计算当前list中是否满足24点
    private boolean solve(List<Double> list) {
        //如果list为空，直接返回false
        if (list.size() == 0) {
            return false;
        }
        //如果list中只有一个值，则与24进行比较
        if (list.size() == 1) {
            return Math.abs(list.get(0) - 24) < (1e-6);
        }
        int size = list.size();
        //双层遍历集合，每次取两个数
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                //不能自己选自己
                if (i == j) {
                    continue;
                }
                //定义临时存放的集合，避免操作与遍历的冲突
                List<Double> tmpList = new ArrayList<>();
                //将剩余未被选中的数存放到临时集合中
                for (int k = 0; k < size; k++) {
                    if (k != i && k != j) {
                        tmpList.add(list.get(k));
                    }
                }
                //因为加法和乘法是不区分顺序的，所以这里做了剪枝操作
                //对于不同顺序的i和j，这里只操作一次
                if (i < j) {
                    //加法操作
                    tmpList.add(list.get(i) + list.get(j));
                    //进入到下一个决策树，如果满足条件直接返回true
                    if (solve(tmpList)) {
                        return true;
                    }
                    //否则回溯
                    tmpList.remove(tmpList.size() - 1);

                    //乘法操作
                    tmpList.add(list.get(i) * list.get(j));
                    //进入到下一个决策树，如果满足条件直接返回true
                    if (solve(tmpList)) {
                        return true;
                    }
                    //否则回溯
                    tmpList.remove(tmpList.size() - 1);
                }

                //减法操作
                tmpList.add(list.get(i) - list.get(j));
                //进入到下一个决策树，如果满足条件直接返回true
                if (solve(tmpList)) {
                    return true;
                }
                //否则回溯
                tmpList.remove(tmpList.size() - 1);

                //除法操作（这里需要先判断除数不能为0，为0直接剪枝）
                if (Math.abs(list.get(j)) < (1e-6)) {
                    continue;
                }
                tmpList.add(list.get(i) / list.get(j));
                //进入到下一个决策树，如果满足条件直接返回true
                if (solve(tmpList)) {
                    return true;
                }
                //否则回溯
                tmpList.remove(tmpList.size() - 1);
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Solution679 solution679 = new Solution679();
        System.out.println(solution679.judgePoint24(new int[]{4, 1, 8, 7}));
        System.out.println(solution679.judgePoint24(new int[]{1, 2, 1, 2}));
    }
}
