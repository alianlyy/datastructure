package com.lyy.datastructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 选择排序 从小到大排序
 * 第一次排序：第一个位置元素排好（最大或最小）
 * 第二次排序：第二个位置元素排好（第二大或第二小）
 * 第三次排序：第三个位置元素排好（第三大或第三小）
 * ........
 * 第N次排序：第N个位置元素排好（第N大或第N小）
 * 时间复杂度：O(n^2)
 * <p>
 * 选择排序：内层循环：每次比较只是记录位置 和目前最小值 是循环完才交换位置,所以选择排序性能比交换排序性能好
 */
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1};
//        sort(arr);
        //测试80000个随机数排序
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序前时间：" + sdf.format(new Date()));
        sort(arr);
        System.out.println("排序后时间：" + sdf.format(new Date()));
//        minIndex = 1;
//        min = arr[1];
//        //第二次排序
//        for (int j = 1 + 1; j < arr.length; j++) {
//            if (min > arr[j]) {
//                minIndex = j;
//                min = arr[j];
//            }
//        }
//        temp = arr[1];
//        arr[1] = min;
//        arr[minIndex] = temp;
//
//        System.out.println("第二次排序后数组：" + Arrays.toString(arr));
//
//        minIndex = 2;
//        min = arr[2];
//
//        //第三次排序
//        for (int j = 2 + 1; j < arr.length; j++) {
//            if (min > arr[j]) {
//                minIndex = j;
//                min = arr[j];
//            }
//        }
//        temp = arr[2];
//        arr[2] = min;
//        arr[minIndex] = temp;
//
//        System.out.println("第三次排序后数组：" + Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            int temp;
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    minIndex = j;
                    min = arr[j];
                }
            }
            temp = arr[i];
            arr[i] = min;
            arr[minIndex] = temp;
            //System.out.println("第" + (i + 1) + "次排序后数组：" + Arrays.toString(arr));
        }

    }
}
