package interview;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * top-K问题
 *
 * @author : xianzilei
 * @date : 2019/12/23 18:42
 */
public class Question4 {
    private static int num = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入文件名：");
        String input = scanner.nextLine();
        BufferedReader bfr = null;
        try {
            bfr = new BufferedReader(new FileReader("src/" + input));
            //定义一个堆的数据结构进行数据存储（默认为小根堆）
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(10);
            String str = null;
            while ((str = bfr.readLine()) != null) {
                Integer temp = Integer.parseInt(str);
                //如果长度小于10，则入堆
                if (maxHeap.size() < 10) {
                    maxHeap.add(temp);
                }
                //否则需要进行筛选工作
                else {
                    //如果根元素小于当前元素，则剔除根元素，当前元素入堆
                    if (maxHeap.peek() < temp) {
                        maxHeap.poll();
                        maxHeap.add(temp);
                    }
                }
            }
            //定义list来接收堆数据
            List<Integer> list = new ArrayList<>(10);
            //循环将元素加到list中
            while (!maxHeap.isEmpty()) {
                list.add(maxHeap.poll());
            }
            //逆序输出list，即为结果
            for (int i = list.size() - 1; i >= 0; i--) {
                System.out.println(list.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
