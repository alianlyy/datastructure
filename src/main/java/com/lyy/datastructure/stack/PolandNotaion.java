package com.lyy.datastructure.stack;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


/**
 * 逆波兰表达式【后缀表达式】计算
 * 遇到数字 入数栈 遇到字符 从数栈弹出2个元素 运算 运算的结果在压入数栈
 * 思路：
 * 1、逆波兰表达式转化成ArrayList
 * 2、遍历list，是数字入栈，是符号则数栈弹出2个元素计算，计算结果压入数栈
 * 遍历完list，数栈中只有一个元素就是计算的结果
 */

/**
 * 中缀表达式1+((2+3)*4)-5 ==>后缀表达式
 * 中缀表达式转后缀表达式步骤：
 * 1）初始化两个栈：运算符栈s1和存储中间结果的栈s2
 * 2）从左至右扫描中缀表达式
 * 3）遇到操作数时，将其压s2
 * 4）遇到运算符时，比较其与s1栈顶运算符的优先级：
 * (1)如果s1为空，或栈顶运算符为左括号"("，则直接将此运算符入栈
 * (2)否则，若优先级比栈顶运算符的高，也将运算符压入s1
 * (3)否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与S1中新的栈顶运算符相比较
 * 5）遇到括号时：
 * (1)如果是左括号"("，则直接压入s1
 * (2)如果是右括号")"，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
 * 6）重复步骤2至5，直到表达式的最右边
 * 7）将s1中剩余的运算符弹出并压入s2
 * 8）依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
 */
public class PolandNotaion {
    public static void main(String[] args) {

        //定义中缀表达式
        String expression = "10+((21+310)*4)-5";
        //将中缀表达式转化成ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
        List<String> expressionList = toInFixExpressionList(expression);
        //[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        System.out.println("中缀表达式转化后的中缀list=" + expressionList);
        //遍历中缀表达式list 得到后缀表达式list
        List<String> suffixList = toFixSuffixList(expressionList);
        System.out.println("表达式的后缀list=" + suffixList);
        //进行结果运算
        int res = calculate(suffixList);
        System.out.printf("表达式%s运算结果为%d\n", expression, res);


//        //先定义逆波兰表达式
//        //(3+4)*5-6 => 34+5*6-
//        //为了方便 数字和符号使用空格隔开
//        String suffixExpression = "3 4 + 5 * 6 -";
//
//        //先将字符串表达式的字符放入ArrayList[3,4,+,5,*,6,-]
//        ArrayList<String> suffixArrayList = getSuffixArrayList(suffixExpression);
//        System.out.println("suffixArrayList=" + suffixArrayList);
//
//        //将ArrayList[3,4,+,5,*,6,-]传入计算
//        int res = calculate(suffixArrayList);
//        System.out.println("suffixExpression='3 4 + 5 * 6 -' 计算结果：" + res);
    }


    /**
     * 将中缀表达式转化成list
     */
    public static List<String> toInFixExpressionList(String expression) {
        List<String> list = new ArrayList<>();
        //索引位置 从0开始
        int index = 0;
        //遍历的当前字符
        char c;
        //多位数拼接
        StringBuilder str = new StringBuilder();
        do {
            //不是数字 直接添加
            if ((c = expression.charAt(index)) < 48 || (c = expression.charAt(index)) > 57) {
                list.add("" + c);
                index++;
            } else {
                //是数字 需要考虑多位数
                str = new StringBuilder();
                while (index < expression.length() && (c = expression.charAt(index)) >= 48 && (c = expression.charAt(index)) <= 57) {
                    str.append(c);
                    index++;
                }
                list.add(str.toString());
            }
        } while (index < expression.length());
        return list;
    }


    /**
     * 将中缀表达式list ==》 后缀表达式list
     * 思路
     * 原本是用2个栈 1个符号栈 一个数栈 最终结果 是将数栈的结果反向遍历
     * 所以为了方便 数栈使用ArrayList 最后结果将ArrayList返回即可
     *
     * @param list 中缀表达式list
     * @return 后缀表达式list
     */
    public static List<String> toFixSuffixList(List<String> list) {
        //符号栈
        Stack<String> operStack = new Stack<>();
        //数栈
        List<String> suffixList = new ArrayList<>();
        //遍历中缀表达式list
        for (String str : list) {
            //是数字 直接入数栈
            if (str.matches("\\d+")) {
                suffixList.add(str);
            } else if ("(".equals(str) || ")".equals(str)) {
                //不是数字
                //第一种：括号
                if ("(".equals(str)) {
                    //左括号 直接入符号栈
                    operStack.push(str);
                } else {
                    //右括号
                    //将符号栈栈顶元素弹出放入到数栈 直到符号栈 栈顶元素是左括号"(" 将这一对括号丢弃
                    while (true) {
                        String oper = operStack.pop();
                        if ("(".equals(oper)) {
                            break;
                        }
                        suffixList.add(oper);
                    }
                }
            } else if ("+".equals(str) || "-".equals(str) || "*".equals(str) || "/".equals(str)) {
                //第二种：运算符
                //1、如果栈空或者栈顶元素是"("，直接入栈
                while (true) {
                    if (operStack.isEmpty() || "(".equals(operStack.peek())) {
                        operStack.push(str);
                        break;
                    } else {
                        //比较当前运算符和栈顶运算符的优先级
                        //当前运算符大于栈顶元素优先级 直接压入符号栈
                        if (getPropority(str) > getPropority(operStack.peek())) {
                            operStack.push(str);
                            break;
                        } else {
                            //否则，将符号栈的栈顶元素弹出放入数栈，当前符号继续和栈顶元素比较
                            String oper = operStack.pop();
                            suffixList.add(oper);
                        }
                    }
                }

            }
        }

        //中缀表达式list遍历完毕 将符号栈的元素依次弹出放入数栈
        while (!operStack.isEmpty()) {
            String oper = operStack.pop();
            suffixList.add(oper);
        }

        return suffixList;
    }

    /**
     * 从左至右运算
     *
     * @param expression 表达式
     * @return 结果
     */
    public static ArrayList<String> getSuffixArrayList(String expression) {
        if (expression == null || expression.length() == 0) {
            System.out.println("表达式空");
            return null;
        }
        String[] split = expression.split(" ");
        return new ArrayList<>(Arrays.asList(split));
    }

    public static int calculate(List<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            throw new RuntimeException("集合空");
        }
        //定义一个栈 存放数字
        Stack<String> stack = new Stack<String>();
        //遍历集合
        for (String str : list) {
            //如果是数字直接入栈
            //使用正则表达式匹配数字 匹配多位数
            if (str.matches("\\d+")) {
                stack.push(str);
            } else {
                //是符号 从数栈弹出2个元素
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                //计算结果
                int res = cal(num1, num2, str);
                //将结果压入数栈
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }


    public static int cal(int num1, int num2, String oper) {
        int res = 0;
        switch (oper) {
            case "+":
                res = num1 + num2;
                break;
            case "-":
                res = num2 - num1;
                break;
            case "*":
                res = num1 * num2;
                break;
            case "/":
                res = num2 / num1;
                break;
            default:
                throw new RuntimeException("运算符有误");
        }
        return res;
    }

    public static int getPropority(String oper) {
        final int add = 1;
        final int sub = 1;
        final int mul = 2;
        final int div = 2;
        switch (oper) {
            case "+":
                return add;
            case "-":
                return sub;
            case "*":
                return mul;
            case "/":
                return div;
            default:
                throw new RuntimeException("不支持的运算符");
        }
    }

}


