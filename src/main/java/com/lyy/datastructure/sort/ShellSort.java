package com.lyy.datastructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序 对选择排序的优化
 * 先分组 组内进行排序
 * 先arr.length/2分组 直到arr.length/2<=0
 */
public class ShellSort {
    public static void main(String[] args) {
//        int arr[] = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        sort2(arr);
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序前时间：" + sdf.format(new Date()));
        //sort(arr);
        sort2(arr);
        System.out.println("排序后时间：" + sdf.format(new Date()));
    }

    /**
     * 交换法
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        int temp = 0;
        int count = 0;
        //定义循环几次 gap歩长
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("第" + (++count) + "次排序后数组：" + Arrays.toString(arr));
        }
    }


    /**
     * 移位法
     *
     * @param arr
     */
    public static void sort2(int[] arr) {
        int count = 0;
        //定义循环几次 gap歩长
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                //当前要插入的数据
                int temp = arr[j];
                //temp 当前要插入的值 arr[j-gap] 当前要比较的有序数组的值
                while (j - gap >= 0 && temp < arr[j - gap]) {
                    //arr[j-gap]后移到arr[j]的位置
                    arr[j] = arr[j - gap];
                    //但是temp位置还未找到  有序数组下标继续往前移动
                    j = j - gap;
                }
                //当退出while循环后，就给temp找到了插入的位置
                arr[j] = temp;
            }
            //System.out.println("第" + (++count) + "次排序后数组：" + Arrays.toString(arr));
        }
    }


//    public static void sort(int[] arr) {
//        //第一次分组分组10/2=5组 分别是[8,3] [9,5] [1,4] [7,6] [2,0]
//        //第一次分组后数组{3,5,1,6,0,8,9,4,7,2}
//
//        //第二次分组 5/2=2组 [3,1,0,9,7] [5,6,8,4,2]
//        //第二次分组后数组{0,2,1,4,3,5,7,6,9,8}
//
//        //第三次分组 2/2=1组 [0,2,1,4,3,5,7,6,9,8]
//        //分组后结果{0,1,2,3,4,5,6,7,8,9}
//
//
//        //1、先确定要分的组数 2、然后根据组数确定每组的元素元素 3、组内元素比较 交换位置
//        int temp = 0;
//        //第一轮排序将10个数据分成了5组
//        for (int i = 5; i < arr.length; i++) {
//            //遍历各组中的所有元素（共5组，每组2个元素）,歩长5
//            for (int j = i - 5; j >= 0; j -= 5) {
//                if (arr[j] > arr[j + 5]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 5];
//                    arr[j + 5] = temp;
//                }
//            }
//        }
//        System.out.println("第一次排序后数组：" + Arrays.toString(arr));
//
//        //第二次排序 将10个=数据分成了2组
//        //i=2 j=0 arr[0]和arr[2]比较 j=-2
//        //i=3 j=1 arr[1]和arr[3]比较 j=-1
//        //i=4 j=2 arr[2]和arr[4]比较 j=0 arr[0]和arr[2]比较 j=-2
//        //i=5 j=3 arr[3]和arr[5]比较 j=1 arr[1]和arr[3]比较 j=-1
//        //i=6 j=4 arr[4]和arr[6]比较 j=2 arr[2]和arr[4]比较 j=0 arr[0]和arr[2]比较 j=-2
//
//        for (int i = 2; i < arr.length; i++) {
//            for (int j = i - 2; j >= 0; j -= 2) {
//                if (arr[j] > arr[j + 2]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 2];
//                    arr[j + 2] = temp;
//                }
//            }
//        }
//        System.out.println("第二次排序后数组：" + Arrays.toString(arr));
//
//        //第三次比较 1组
//        for (int i = 1; i < arr.length; i++) {
//            for (int j = i - 1; j >= 0; j -= 1) {
//                if (arr[j] > arr[j + 1]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//        }
//        System.out.println("第二次排序后数组：" + Arrays.toString(arr));
//    }
}
