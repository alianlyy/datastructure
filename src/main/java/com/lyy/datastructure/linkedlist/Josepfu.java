package com.lyy.datastructure.linkedlist;

/**
 * 约瑟夫问题
 * n个人围成一圈 第k个人从1开始报数，报数到m的人出列，依次循环，求出列顺序
 * 使用单向循环链表解决
 */
public class Josepfu {
    public static void main(String[] args) {
        CircleSignleLinkedList circleLinkedList = new CircleSignleLinkedList();
        //创建小孩结点
        circleLinkedList.createCircle(5);
        //显示链表
        System.out.println("初始化单向循环链表");
        circleLinkedList.showCircle();

        //数数出队列
        System.out.println("数数出队列");
        circleLinkedList.getBoy(1, 2);
    }
}

//创建一个环形的单向链表
class CircleSignleLinkedList {
    //表示当前开始报数的那个小孩
    private Boy first;
    //当前结点的上一个结点 用于绑定关联关系
    private Boy helper = null;

    //创建循环节点
    public void createCircle(int nums) {
        //nums数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        //根据编号创建小孩结点
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            //第一个小孩 头结点 固定不动
            if (i == 1) {
                first = boy;
                first.setNext(boy);
            } else {
                helper.setNext(boy);
                boy.setNext(first);
            }
            helper = boy;
        }
    }

    //小孩出队列
    //思路分析 第k个人报数1 报数到m的小孩出列 即从位置k开始，向后移动m-1的位置即是要出列的人

    /**
     * @param startNum 起始位置
     * @param countNum 报数多少
     */
    public void getBoy(int startNum, int countNum) {
        //first是一直移动的
        //1、first结点位置为k[startNum]
        for (int i = 1; i < startNum; i++) {
            helper = first;
            first = first.getNext();
        }
        //2、从位置k及first位置后移m-1[countNum-1]
        //结束循环：还剩下最后一个结点 即first.next = first
        //temp 当前结点
        //helper 当前结点的前一个结点
        //first 头结点
        while (true) {
            //只剩下最后一个结点
            if (first.getNext() == first) {
                System.out.println(first);
                break;
            }
            Boy temp = first;
            for (int i = 1; i <= countNum - 1; i++) {
                temp = temp.getNext();
                helper = helper.getNext();
            }
            //遍历完 temp要删除的结点 helper 要删除节点的前一个结点
            helper.setNext(temp.getNext());
            first = temp.getNext();
            System.out.println(temp);
        }

    }

    //展示链表[循环遍历]
    public void showCircle() {
        if (first == null) {
            System.out.println("链表空");
            return;
        }
        Boy temp = first;
        //循环遍历条件 当前结点的下一个结点是first
        while (true) {
            //输出节点
            System.out.println(temp);
            if (temp.getNext() == first) {
                break;
            }
            //temp后移
            temp = temp.getNext();
        }
    }
}

//表示一个结点
class Boy {
    //编号
    private int no;
    //下一个
    private Boy next;

    public int getNo() {
        return no;
    }

    public Boy getNext() {
        return next;
    }

    public Boy(int no) {
        this.no = no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
