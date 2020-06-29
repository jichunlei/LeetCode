package solutions.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 鸡蛋掉落--TODO
 *
 * @author : xianzilei
 * @date : 2020/6/29 16:49
 */
public class Solution887 {

    public static int superEggDrop(int K, int N) {
        //状态转移方程
        //dp(i,j)：当鸡蛋个数为i个，楼层为j时的最坏情况下的最少扔鸡蛋的次数
        //(1)、dp(i,0)=0;
        //(2)、dp(1,j)=j;
        //(3)、dp(i,j)
        //(3.1)、在k层扔鸡蛋，如果鸡蛋没碎，则dp(i,j)=dp(i,j-k)+1
        //(3.2)、在k层扔鸡蛋，如果鸡蛋碎了，则dp(i,j)=dp(i-1,k-1)+1
        //(3.3)、则在k层下最差情况下的最少扔鸡蛋的次数dp(i,j)=max{dp(i,j-k),dp(i-1,j-1)}+1
        //(3.4)、所以结果取尝试每一层得到的次数的最小值，
        // 即dp(i,j)=min{max{dp(i,j-k),dp(i-1,k-1)}+1|当k位于[1,j]中}

        //备忘录
        Map<String, Integer> memoryMap = new HashMap<>();
        //递归获取结果
        return dp(K, N, memoryMap);
    }

    //dp(i,j)：当鸡蛋个数为i个，楼层为j时的最坏情况下的最少扔鸡蛋的次数
    private static int dp(int i, int j, Map<String, Integer> memoryMap) {
        //边界值处理
        //dp(1,j)=j
        if (i == 1) {
            return j;
        }
        //dp(i,0)=0
        if (j == 0) {
            return 0;
        }
        String key = i + "-" + j;
        //备忘录查找
        if (memoryMap.containsKey(key)) {
            return memoryMap.get(key);
        }
        //初始化结果为int最大值
        int result = Integer.MAX_VALUE;
        //遍历楼层进行计算
        for (int k = 1; k <= j; k++) {
            result = Math.min(result, Math.max(dp(i - 1, k - 1, memoryMap), dp(i, j - k, memoryMap)) + 1);
        }
        //结果保存到备忘录中
        memoryMap.put(key, result);
        //返回结果
        return result;
    }

    public static void main(String[] args) {
        System.out.println(superEggDrop(4, 5000));
    }
}
