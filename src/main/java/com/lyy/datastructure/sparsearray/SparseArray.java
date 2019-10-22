package com.lyy.datastructure.sparsearray;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 下棋问题：黑子 蓝子
 * 稀疏数组：固定三列
 * 1、二维数组->稀疏数组->保存到磁盘
 * 2、磁盘读取->稀疏数组->二维数组
 */
public class SparseArray {

    public static void main(String[] args) {
        //定义一个11*11的二维数组
        int[][] chessarr = new int[11][11];
        //二维数组赋值 模拟棋盘下棋 0-没有棋子  1-黑子 2-蓝子
        chessarr[1][2] = 1;
        chessarr[2][3] = 2;
        chessarr[5][6] = 2;

        System.out.println("打印二维数组");
        for (int[] row : chessarr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            //换行
            System.out.println();
        }

        //将二维数组->稀疏数组 列数固定3 行数：二维数组棋子个数sum+1
        //遍历二维数组 计算二维数组棋子个数
        int sum = 0;
        for (int[] row : chessarr) {
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }
            }
        }
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;


        //遍历二维数组 棋子所在位置赋值给稀疏数组
        int count = 0;
        for (int i = 0; i < chessarr.length; i++) {
            for (int j = 0; j < chessarr[i].length; j++) {
                if (chessarr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessarr[i][j];
                }
            }
        }

        //打印稀疏数组
        System.out.println("打印稀疏数组");
        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //稀疏数组->磁盘
        FileWriter writer = null;
        try {
            writer = new FileWriter("save.data");
            for (int[] row : sparseArr) {
                for (int data : row) {
                    writer.write(data);
                }
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //磁盘->稀疏数组
        int[][] sparseArr2 = new int[sum + 1][3];
        FileReader reader = null;
        try {
            reader = new FileReader("save.data");
            for (int i = 0; i < sparseArr2.length; i++) {
                for (int j = 0; j < sparseArr2[i].length; j++) {
                    sparseArr2[i][j] = reader.read();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //稀疏数组->二维数组
        //二维数组初始化
        int[][] chessarr2 = new int[sparseArr2[0][0]][sparseArr2[0][1]];
        //数据初始化
        for (int i = 1; i < sparseArr2.length; i++) {
            chessarr2[sparseArr2[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];
        }

        //遍历转化后的二维数组
        System.out.println("打印转化后的稀疏数组");
        for (int[] row : chessarr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }


}
