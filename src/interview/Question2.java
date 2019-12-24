package interview;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 次数最多的前10名
 *
 * @author : xianzilei
 * @date : 2019/12/24 19:27
 */
public class Question2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入文件名：");
        String input = scanner.nextLine();
        BufferedReader bfr = null;
        try {
            //读取文件到流中
            bfr = new BufferedReader(new FileReader("src/" + input));
            //定义map存储数据（key表示：关键字  value：出现次数）
            Map<String, Integer> map = new HashMap<>();
            String str = null;
            while ((str = bfr.readLine()) != null) {
                //如果存在，则出现次数+1
                if (map.containsKey(str)) {
                    map.put(str, map.get(str) + 1);
                }
                //否则，put到map中，初始次数为1
                else {
                    map.put(str, 1);
                }
            }
            //用来存储map中的键值对，方便后面对其排序
            List<Map.Entry<String, Integer>> list = new ArrayList<>(map.size());
            list.addAll(map.entrySet());
            //使用1.8特性进行逆序排序
            List<Map.Entry<String, Integer>> result = list.stream().sorted(Comparator.comparing(Map.Entry<String,
                    Integer>::getValue).reversed()).collect(Collectors.toList());
            //截取前十位展示出来
            System.out.println(result.subList(0, 10));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
