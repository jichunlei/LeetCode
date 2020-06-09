package solutions.unionfind;

/**
 * 等式方程的可满足性
 *
 * @author : xianzilei
 * @date : 2020/6/8 08:22
 */
public class Solution990 {

    /**
     * 并查集解法
     *
     * @param equations 1
     * @return boolean
     * @author xianzilei
     * @date 2020/6/8 8:42
     **/
    public static boolean equationsPossible(String[] equations) {
        //结果
        boolean flag = true;
        //存放每个元素的父节点，用0~25表示26个小写字母，当parent[i]=i时，则表示i是所在组的组长
        int[] parent = new int[26];
        //初始化父节点数组，初始时刻每个节点都是自己的父节点
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        //将所有==的元素合并成一组
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                union(equation.charAt(0) - 'a', equation.charAt(3) - 'a', parent);
            }
        }
        //循环所有!=的元素，判断二者是否在一组中，如果是则返回false
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                if (find(equation.charAt(0) - 'a', parent) == find(equation.charAt(3) - 'a', parent)) {
                    flag = false;
                    break;
                }
            }
        }
        //返回结果
        return flag;
    }

    //合并
    private static void union(int i, int j, int[] parent) {
        //查找i所在组的组长
        int parent1 = find(i, parent);
        //查找j所在组的组长
        int parent2 = find(j, parent);
        //如果二者已经在一个组了，则无需合并，直接返回
        if (parent1 == parent2) {
            return;
        }
        //将j所在组合并到i中（只需要将j所在组的原组长的父节点指向i的组长即可）
        parent[parent2] = parent1;
        parent[j] = parent1;
    }

    //查找所在组的组长
    private static int find(int i, int[] parent) {
        //获取i的父节点
        int result = parent[i];
        //如果父节点就是组长，直接返回
        if (result == i) {
            return result;
        } else {
            //递归查找
            result = find(parent[i], parent);
            //路径压缩
            parent[i] = result;
        }
        //返回结果
        return result;
    }


    public static void main(String[] args) {
        String[] equations = {"c==c", "b==d", "x!=z"};
        System.out.println(equationsPossible(equations));
    }
}
