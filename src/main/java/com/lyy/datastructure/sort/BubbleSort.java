package com.lyy.datastructure.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 冒泡排序 从小到大排序 从最后一个位置依次往前排序好
 * 第一次排序：倒数第一个位置元素固定（最大或最小）
 * 第二次排序：倒数第二个位置元素固定（第二大或第二小）
 * ..........
 * 第N次排序：倒数第N个位置元素固定(第N大或第N小)
 * <p>
 * 优化：如果一次比较下来没有交换，则说明已经交换好 数组已经有序 无需在比较
 * 时间复杂度：O(n^2)
 * <p>
 * 冒泡排序：内层循环 每次比较都交换位置 浪费时间
 */
public class BubbleSort {
    public static void main(String[] args) {
        //定义一个数组
//        int[] arr = {3, 9, -1, 10, 20};
//        sort(arr);
//        System.out.println("排序后的数组：" + Arrays.toString(arr));

        //测试80000个随机数排序
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序前时间：" + sdf.format(new Date()));
        sort(arr);
        System.out.println("排序后时间：" + sdf.format(new Date()));

//        //第二次排序 找出第二大数放在倒数第二个位置
//        for (int j = 0; j < arr.length - 1 - 1; j++) {
//            //当前元素比后一个元素大 交换位置
//            if (arr[j] > arr[j + 1]) {
//                temp = arr[j];
//                arr[j] = arr[j + 1];
//                arr[j + 1] = temp;
//            }
//        }
//
//        System.out.println("第二次排序后的数组：" + Arrays.toString(arr));
//
//        //第三次排序 找出第三大数放在倒数第三个位置
//        for (int j = 0; j < arr.length - 1 - 1; j++) {
//            //当前元素比后一个元素大 交换位置
//            if (arr[j] > arr[j + 1]) {
//                temp = arr[j];
//                arr[j] = arr[j + 1];
//                arr[j + 1] = temp;
//            }
//        }
//
//        System.out.println("第三次排序后的数组：" + Arrays.toString(arr));
//
//        //第三次排序 找出第四大数放在倒数第四个位置
//        for (int j = 0; j < arr.length - 1 - 1; j++) {
//            //当前元素比后一个元素大 交换位置
//            if (arr[j] > arr[j + 1]) {
//                temp = arr[j];
//                arr[j] = arr[j + 1];
//                arr[j + 1] = temp;
//            }
//        }
//
//        System.out.println("第四次排序后的数组：" + Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        //第一次排序 找出最大数放在倒数第一个位置
        int temp;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //当前元素比后一个元素大 交换位置
                if (arr[j] > arr[j + 1]) {
                    //有交换 falg=true
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (flag) {
                //这轮有交换 将flag置为false
                flag = false;
            } else {
                //这轮没有交换 直接break 无需再次比较
                break;
            }
        }
    }
}
