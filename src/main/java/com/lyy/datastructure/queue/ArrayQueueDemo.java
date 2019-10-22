package com.lyy.datastructure.queue;

import java.util.Scanner;

/**
 * 用数组实现一个队列
 * 存在问题：数组不能循环使用
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        char key;
        while (loop) {
            System.out.println("添加元素输入a");
            System.out.println("取出元素输入g");
            System.out.println("显示所有元素输入s");
            System.out.println("查看队列头元素输入h");
            System.out.println("退出程序输入e");
            //接收第一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int n = queue.getQueue();
                        System.out.printf("取出元素：%d\n", n);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 's':
                    queue.showQueue();
                    break;
                case 'h':
                    int i = queue.headQueue();
                    System.out.printf("队列头元素：%d\n", i);
                    break;
                case 'e':
                    loop = false;
                    scanner.close();
                    break;
                default:
                    break;
            }
        }
        System.out.println("退出程序");
    }

    //使用数组模拟队列
    static class ArrayQueue {
        //队列最大长度
        private int maxSize;
        //队列头
        private int front;
        //队列尾
        private int rear;
        //存放队列数据
        private int[] arr;

        //构造器 传递队列最大长度
        public ArrayQueue(int maxSize) {
            this.maxSize = maxSize;
            arr = new int[maxSize];
            //front指向队列头的前一个位置
            front = -1;
            //rear指向队列尾的数据（即就是队列的最后一个元素）
            rear = -1;
        }

        //判断队列是否满 队尾=maxSize-1
        public boolean isFull() {
            return rear == maxSize - 1;
        }

        //判断队列是否空 对头和队尾相等
        public boolean isEmpty() {
            return front == rear;
        }

        //队列添加元素 队列尾移动
        public void addQueue(int n) {
            if (isFull()) {
                System.out.println("队列已满");
                return;
            }
            //rear后移
            rear++;
            arr[rear] = n;
        }

        //队列取出元素 队列头移动
        public int getQueue() {
            if (isEmpty()) {
                throw new RuntimeException("队列空，不能取数据");
            }
            //front后移
            front++;
            return arr[front];
        }

        //显示队列所有数据
        public void showQueue() {
            if (isEmpty()) {
                System.out.println("队列空，无法遍历");
            }
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("arr[%d]=%d\t", i, arr[i]);
            }
            System.out.println();
        }

        //显示对列头数据 不是取出数据
        public int headQueue() {
            if (isEmpty()) {
                System.out.println("队列空，无法查看元素");
            }
            return arr[front + 1];
        }
    }
}
