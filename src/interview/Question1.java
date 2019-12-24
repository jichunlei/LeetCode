package interview;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 打印1-n的全排列
 *
 * @author : xianzilei
 * @date : 2019/12/23 18:42
 */
public class Question1 {
    private static int num = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入一个正整数：");
        int n = input.nextInt();
        while (n <= 0) {
            System.out.print("n必须大于0，请重新输入：");
            n = input.nextInt();
        }
        print(n);
    }

    public static void print(int n) {
        //初始化数组
        int[] a = new int[n];
        for (int i = 1; i <= n; i++) {
            a[i - 1] = i;
        }
        fun(a, 0, n - 1);
        System.out.println("共有" + num + "种排列");
    }

    public static void fun(int[] a, int start, int end) {
        //递归结束标志
        if (start == end) {
            System.out.println(Arrays.toString(a));
            //记录组合数
            num++;
        } else {
            //从起始位置开始递归处理
            for (int i = start; i <= end; i++) {
                //将第i位置的元素放到第一位
                swap(a, start, i);
                //递归获取剩下元素的全排列
                fun(a, start + 1, end);
                //恢复数组位置
                swap(a, start, i);
            }

        }
    }

    //交换数组的i和j位置
    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
