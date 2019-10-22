package com.lyy.datastructure.stack;

/**
 * 使用栈实现计算器功能
 * 3+2*6-2
 * 1、使用索引index，遍历表达式
 * 2、如果是数字，直接入数栈
 * 3、如果是字符：
 * 3.1：符号栈为空，直接入栈
 * 3.2：和栈顶符号比较，当前符号优先级大于栈顶符号 直接入栈，
 * 否则，从数栈取出2个数字&&取出符号栈顶元素进行运算，运算结果压入数栈，当前符号压入符号栈
 * 4、表达式扫描完毕，顺序的从数栈弹出2个元素，符号栈弹出1个元素进行运算，并将运算结果压入数栈
 * 5、最后数栈只有一个元素就是运算的结果
 */
public class Calculator {
    public static void main(String[] args) {

        //表达式
        //String expression = "3+2*6-2";
        String expression = "70+2*6-4";
        //索引index 用于扫描字符串
        int index = 0;
        //字符串每次扫描到的字符
        char ch = ' ';
        //最终运算结果
        int res = 0;
        //拼接字符串 多位数需拼接
        String str = "";

        //数栈
        ArrayStack2 numStack = new ArrayStack2(10);
        //符号栈
        ArrayStack2 operStack = new ArrayStack2(10);

        //遍历表达式 遇到的问题

        while (true) {
            //依次得到expression的相关字符
            ch = expression.substring(index, index + 1).charAt(0);

            //判断ch是什么做相应的处理
            if (!operStack.isSymbol(ch)) {
                //不是符号 直接入数栈 只能一位数 多位数呢？
                //多位数 遍历下下一个字符 不是符号继续拼接
                str += ch;
                //如果下一个字符是符号时入栈 判断下一个字符 可能发生空指针异常 虚判断是不是已经到最后一个字符
                if (index == expression.length() - 1) {
                    numStack.push(Integer.valueOf(str)); //? '1+3' '1'
                } else {
                    if (operStack.isSymbol(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.valueOf(str)); //? '1+3' '1'
                    }
                }
            } else {
                //是符号
                //先清空拼接字符串 str = ""
                str = "";
                //1）符号栈空 直接压入
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                } else {
                    //不为空 优先级比栈顶元素优先级高 直接入栈
                    if (operStack.priority(ch) > operStack.priority(operStack.top())) {
                        operStack.push(ch);
                    } else {
                        //优先级比栈顶元素小
                        //从数栈弹出2个元素
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        //符号栈弹出栈顶符号
                        int oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //将结果压入数栈
                        numStack.push(res);
                        //将当前符号压入符号栈
                        operStack.push(ch);
                    }
                }
            }
            //index ++ 并且判断是否扫描到expression最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //遍历完符号表达式
        //循环从数栈弹出2个元素，符号栈弹出1个元素，计算，并将计算结果压入数栈，直到数栈还剩一个元素
//        while (numStack.size() > 1) {
//            int num1 = numStack.pop();
//            int num2 = numStack.pop();
//            int oper = operStack.pop();
//            res = numStack.cal(num1, num2, oper);
//            //将结果压入数栈
//            numStack.push(res);
//        }
        while (true) {
            //符号栈为空 则计算到最后，数栈中只有一个数字【结果】
            if (operStack.isEmpty()) {
                break;
            }
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            int oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            //将结果压入数栈
            numStack.push(res);
        }

        System.out.printf("表达式运算结果：%d\n", res);
    }
}

//使用数组模拟栈
class ArrayStack2 {
    //栈的最大容量
    private int maxSize;
    //存放数据
    private int[] stack;
    //栈顶 默认-1
    private int top = -1;

    public ArrayStack2(int maxSize) {
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

    //判断是否是符号
    public boolean isSymbol(char val) {
        if (val == '+' || val == '-' || val == '*' || val == '/') {
            return true;
        }
        return false;
    }

    //符号优先级 数字越大 优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            throw new RuntimeException("符号暂不支持");
        }
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

}

