package com.lyy.datastructure.stack;

/**
 * 数组实现栈
 * 特点：先进后出 栈底不变 栈顶变化
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        //入栈
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);

        //显示栈元素
        stack.list();

        //出栈
        stack.pop();
        stack.pop();
        //显示出栈后的栈
        System.out.println("出栈后的栈");
        stack.list();
        System.out.printf("栈大小%d\n", stack.size());
    }
}

//使用数组模拟栈
class ArrayStack {
    //栈的最大容量
    private int maxSize;
    //存放数据
    private int[] stack;
    //栈顶 默认-1
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈是否满 栈顶是否指向maxSize - 1
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈是否空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int n) {
        if (isFull()) {
            System.out.println("栈满，无法入栈~~");
            return;
        }
        //栈顶后移
        top++;
        stack[top] = n;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        //获取当前栈顶的值
        int value = stack[top];
        //栈顶向下压一位
        top--;
        //返回元素
        return value;
    }

    //获取栈顶元素
    public int top() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，无法获取栈元素");
        }
        return stack[top];
    }

    //遍历栈[从栈顶往下遍历]
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i > -1; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }

    }

    //栈大小
    public int size() {
        if (isEmpty()) {
            return 0;
        }
        return top + 1;
    }
}

