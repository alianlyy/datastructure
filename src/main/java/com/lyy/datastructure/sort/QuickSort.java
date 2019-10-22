package com.lyy.datastructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 快速排序 冒泡排序的改进
 * 以某个数为基准值 对这个数的左边和右边比较
 * 左边比基准值大的数放到基准值的右边
 * 右边比基准值小的数放到基准值左边
 * 依次递归
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9, 78, 0, 23, -567, 70};
//        sort(arr, 0, arr.length - 1);
//        System.out.println("arr=" + Arrays.toString(arr));
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序前时间：" + sdf.format(new Date()));
        sort(arr, 0, arr.length - 1);
        System.out.println("排序后时间：" + sdf.format(new Date()));
    }


    public static void sort(int[] arr, int left, int right) {
        //最左边下标
        int l = left;
        //最右边下标
        int r = right;
        //pivot 中轴值
        int pivot = arr[(left + right) / 2];
        //临时变量
        int temp = 0;
        //左边下标 <右边下标 一直可以循环
        //比pivot小的 放到pivot的左边
        //比pivot大的 放到pivot的右边
        while (l < r) {
            //在pivot的左边一直找，找到大于等于pivot值，才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot的右边一直找，找到小于等于pivot的值，才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果 l>=r 说明pivot的左右两边的值，已经按照左边全部是小于等于pivot的值，
            //右边全是大于等于pivot的值
            if (l >= r) {
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现arr[l] == pivot的值 r--,前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后，发现arr[r] == pivot的值 l++,后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        //如果 l==r 必须l++,r-- 否则出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }

        //向左递归
        if (left < r) {
            sort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            sort(arr, l, right);
        }
    }

}
