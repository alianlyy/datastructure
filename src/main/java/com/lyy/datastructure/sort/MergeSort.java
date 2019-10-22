package com.lyy.datastructure.sort;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {84, 5, 7, 1, 3, 6, 2};

    }


    /**
     * 合并的方法
     *
     * @param arr   排序原始数组
     * @param left  左边有序序列的索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //初始化i，左边有序序列的初始索引
        int i = left;
        //初始化j,右边有序序列的初始索引
        int j = mid + 1;
        //指向temp数组的当前索引
        int t = 0;

        //(-)
        //先把左右两边(有序)的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完为止

        //(二)
        //把有剩余数据的一边的数据全部填充到temp数组

        //(三)
        //将temp数组的元素拷贝到arr
    }
}
