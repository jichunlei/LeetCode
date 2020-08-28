package solutions.string;

/**
 * 机器人能否返回原点
 *
 * @author : xianzilei
 * @date : 2020/8/28 8:13
 */
public class Solution657 {

    /**
     * 解法：常规思路
     *
     * @param moves 1
     * @return boolean
     * @author xianzilei
     * @date 2020/8/28 8:19
     **/
    public boolean judgeCircle(String moves) {
        //其实就是校验U和D，L和R的个数是否相等

        if (moves == null || moves.length() == 0) {
            return true;
        }
        int leftNum = 0;
        int rightNum = 0;
        int upNum = 0;
        int downNum = 0;
        for (int i = 0; i < moves.length(); i++) {
            if (moves.charAt(i) == 'U') {
                upNum++;
            } else if (moves.charAt(i) == 'D') {
                downNum++;
            } else if (moves.charAt(i) == 'L') {
                leftNum++;
            } else {
                rightNum++;
            }
        }
        return leftNum == rightNum && upNum == downNum;
    }

    public static void main(String[] args) {
        Solution657 solution657 = new Solution657();
        System.out.println(solution657.judgeCircle("DD"));
    }
}
