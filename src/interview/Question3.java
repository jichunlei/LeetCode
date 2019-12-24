package interview;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 多边形面积
 *
 * @author : xianzilei
 * @date : 2019/12/24 19:43
 */
public class Question3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入文件名：");
        String input = scanner.nextLine();
        BufferedReader bfr = null;
        try {
            //读取文件到流中
            bfr = new BufferedReader(new FileReader("src/" + input));
            //存储横坐标
            List<Double> xList = new ArrayList<>();
            //存储纵坐标
            List<Double> yList = new ArrayList<>();
            String str = null;
            while ((str = bfr.readLine()) != null) {
                String[] split = str.split(",");
                xList.add(Double.valueOf(split[0]));
                yList.add(Double.valueOf(split[1]));
            }
            double sum = 0;
            for (int i = 0; i < xList.size() - 1; i++) {
                sum += (xList.get(i) * yList.get(i + 1) - xList.get(i + 1) * yList.get(i));
            }
            double result =
                    (Math.abs(sum + (xList.get(xList.size() - 1) * yList.get(0)) - (xList.get(0) * yList.get(xList.size() - 1)))) / 2;
            System.out.println("面积为：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
