package com.lyy.datastructure.queue;

import java.util.Scanner;

/**
 * 环形数组队列
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //说明设置4，其队列的有效数据最大是3
        CircleArrayQueue queue = new CircleArrayQueue(4);
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

    static class CircleArrayQueue {
        //队列最大容量
        private int maxSize;
        //队列头 指向队列的第一个元素，也就是arr[front]
        private int front;
        //队列尾 指向队列的最后一个元素的后一个位置
        private int rear;
        //存放数据数组
        private int[] arr;

        public CircleArrayQueue(int maxSize) {
            this.maxSize = maxSize;
            this.front = 0;
            this.rear = 0;
            arr = new int[maxSize];
        }

        //判断队列是否满 (rear+1)%maxSize == front
        private boolean isFull() {
            return (rear + 1) % maxSize == front;
        }

        //判断队列是否空 (rear+maxSize-front)%maxSize=front
        private boolean isEmpty() {
            return (rear + maxSize - front) % maxSize == front;
        }

        //队列添加元素
        public void addQueue(int n) {
            if (isFull()) {
                System.out.println("队列满，无法添加元素");
                return;
            }
            //直接将数据加入
            arr[rear] = n;
            //将rear后移，这里必须考虑取模
            rear = (rear + 1) % maxSize;
        }

        //队列取出元素
        public int getQueue() {
            if (isEmpty()) {
                throw new RuntimeException("队列空，无法取出元素");
            }
            //这里需要分析出front是指向队列的第一个元素
            //1、先把front对应的值保留到一个临时变量
            //2、将front后移，考虑取模
            //3、将临时保存的变量返回
            //当前队头元素
            int value = arr[front];
            //队头往后移
            front = (front + 1) % maxSize;
            return value;
        }

        //显示队列所有数据
        public void showQueue() {
            if (isEmpty()) {
                System.out.println("队列空，无法遍历");
            }
            //思路：从front开始遍历，遍历多少个元素

            for (int i = front; i < front + size(); i++) {
                System.out.printf("arr[%d]=%d\t", i % maxSize, arr[i % maxSize]);
            }
            System.out.println();
        }

        //显示对列头数据 不是取出数据
        public int headQueue() {
            if (isEmpty()) {
                System.out.println("队列空，没有数据");
            }
            return arr[front];
        }

        //求出当前队列有效数据个数
        private int size() {
            //rear = 2
            //front = 1
            //maxSize = 3
            return (rear + maxSize - front) % maxSize;
        }
    }
}
