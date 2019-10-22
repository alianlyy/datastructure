package com.lyy.datastructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序 从小到大
 * 数组分成2个数组 1个排好序的有序数组 1个无序数组
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        sort(arr);
//        int[] arr = new int[80000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) (Math.random() * 8000000);
//        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("排序前时间：" + sdf.format(new Date()));
//        sort(arr);
//        System.out.println("排序后时间：" + sdf.format(new Date()));
    }

    public static void sort(int[] arr) {
        //当前要插入值
        int insertValue;
        //有序数组下标
        int insertIndex;
        //有序数组范围依次为[0],[0,1],[0,2],[0,3]
        //i表示要插入的值得下标
        for (int i = 1; i < arr.length; i++) {
            //要插入值
            insertValue = arr[i];
            insertIndex = i - 1;
            //有序数组下标>=0 && 要插入值<当前比较的有序数组
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                //有序数组当前比较值后移一位
                arr[insertIndex + 1] = arr[insertIndex];
                //有序数组下标向前移动一位 继续比较
                insertIndex--;
            }

            //退出循环 即不满足条件 则找到要插入值的位置
            //优化 当要插入数组下标 不是元素原本的数组下标才交换
            if ((insertIndex + 1) != i) {
                arr[insertIndex + 1] = insertValue;
            }
            System.out.println("第" + (i) + "次排序后数组：" + Arrays.toString(arr));
        }
    }
//    public static void sort(int[] arr) {
//        //第一次 无序数组下标初始1 无序数组范围[1,arr.length-1] 有序数组范围[0]
//        //将无序数组的第一个值 要插入到有序数组 和有序数组的最后一个值比较
//        //要插入有序数组的起始地址
//        int insertIndex = 0;
//        //要插入值
//        int insertValue = arr[1];
//        //要插如有序数组的索引>=0 && 要插入的值，大于有序数组当前比较值
//        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
//            //则有序数组的当前比较值放到后一个位置上
//            arr[insertIndex + 1] = arr[insertIndex];
//            //有序数组下标往前移 在继续比较
//            insertIndex--;
//        }
//        //说明：1、当前要插入的值比有序数组最后一个元素还大 则要插入的值位置不变
//        //要插入值<有序数组最后一个元素值，则有序数组最后一个元素值放入到插入值所在的位置，有序数组下标继续往前移，在继续比较
//        //插入值<有序数组比较数的，则有序数组当前比较元素向后移一个位置 知道找到插入值放置位置
//        //不满足条件
//        arr[insertIndex + 1] = insertValue;
//
//        System.out.println("第一次排序后：" + Arrays.toString(arr));
//
//
//        //第二次排序
//        insertIndex = 1;
//        //要插入值
//        insertValue = arr[2];
//        //要插如有序数组的索引>=0 && 要插入的值，大于有序数组当前比较值
//        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
//            //则有序数组的当前比较值放到后一个位置上
//            arr[insertIndex + 1] = arr[insertIndex];
//            //有序数组下标往前移 在继续比较
//            insertIndex--;
//        }
//        //说明：1、当前要插入的值比有序数组最后一个元素还大 则要插入的值位置不变
//        //要插入值<有序数组最后一个元素值，则有序数组最后一个元素值放入到插入值所在的位置，有序数组下标继续往前移，在继续比较
//        //插入值<有序数组比较数的，则有序数组当前比较元素向后移一个位置 知道找到插入值放置位置
//        //不满足条件
//        arr[insertIndex + 1] = insertValue;
//
//        System.out.println("第二次排序后：" + Arrays.toString(arr));
//
//        //第三次排序
//        insertIndex = 2;
//        //要插入值
//        insertValue = arr[3];
//        //要插如有序数组的索引>=0 && 要插入的值，大于有序数组当前比较值
//        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
//            //则有序数组的当前比较值放到后一个位置上
//            arr[insertIndex + 1] = arr[insertIndex];
//            //有序数组下标往前移 在继续比较
//            insertIndex--;
//        }
//        //说明：1、当前要插入的值比有序数组最后一个元素还大 则要插入的值位置不变
//        //要插入值<有序数组最后一个元素值，则有序数组最后一个元素值放入到插入值所在的位置，有序数组下标继续往前移，在继续比较
//        //插入值<有序数组比较数的，则有序数组当前比较元素向后移一个位置 知道找到插入值放置位置
//        //不满足条件
//        arr[insertIndex + 1] = insertValue;
//
//        System.out.println("第三次排序后：" + Arrays.toString(arr));
//
//
//    }
}
