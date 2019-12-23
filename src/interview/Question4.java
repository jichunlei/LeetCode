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
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(10);
            String str = null;
            while ((str = bfr.readLine()) != null) {
                Integer temp = Integer.parseInt(str);
                if (maxHeap.size() < 10) {
                    maxHeap.add(temp);
                } else {
                    if (maxHeap.peek() < temp) {
                        maxHeap.poll();
                        maxHeap.add(temp);
                    }
                }
            }
            List<Integer> list = new ArrayList<>(10);
            while (!maxHeap.isEmpty()) {
                list.add(maxHeap.poll());
            }
            for (int i = list.size() - 1; i >= 0; i--) {
                System.out.println(list.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
