package com.lyy.datastructure.recursion;

/**
 * 8皇后问题
 * 问题描述：
 * 8行8列的棋盘 每行放一个皇后，不能有皇后出现在同一行，同一列，同一个斜线
 * 思路：
 * 看着是二维数组，实际可以用一维数组来代替 数组的下标代表行 数组下标对应的值代表列
 */
public class Queue8 {
    //表示有多少皇后
    private static int max = 8;
    //定义一个一维数组 下标表示行 下标对应的值表示列
    private static int[] arr = new int[max];

    //表示有多少种方法
    private static int count;


    public static void main(String[] args) {
        check(0);
        System.out.printf("总共有%d种方法",count);
    }


    public static void check(int n) {
        //放第9个皇后了，说明前面8个皇后已经全部放好没有冲突
        if (n == max) {
            print();
            return;
        }
        //放第n行皇后时，从第一列依次判断 是否有冲突
        //没有冲突 放下一行
        //有冲突 列往后移一位
        for (int i = 0; i < max; i++) {
            //赋值  说明当前放的是第n行的第i列
            arr[n] = i;
            //当前行 当前列 放的没有冲突 继续下一行
            if (judge(n)) {
                check(n + 1);
            }
            //有冲突的额话，列后移一位i+1
        }
    }


    /**
     * 放第n行皇后的时候 判断是否和之前的皇后有冲突（在同一列或同一斜线）
     *
     * @param n 表示行
     * @return
     */
    public static boolean judge(int n) {
        //判断和之前的皇后有没有冲突
        for (int i = 0; i < n; i++) {
            //arr[i] == arr[n] 表示在同一列
            //Math.abs(n - i) == Math.abs(arr[n] - arr[i])表示在同一斜线
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印数组
     */
    public static void print() {
        count++;
        for (int anArr : arr) {
            System.out.print(anArr + " ");
        }
        System.out.println();
    }

}
