package com.lyy.datastructure.linkedlist;

import java.util.Stack;

/**
 * 单向链表
 * 单链表面试题
 * 1、求单链表中节点的个数
 * 2、查找单链表中的倒数第k个节点【新浪面试题】
 * 3、单链表的反转【腾讯面试题】
 * 4、从尾到头打印链表【百度，要求方式1：反向遍历。方式2：Stack栈】
 * 5、合并两个有序的单链表，合并之后的链表依然有序
 */
public class SignleLinkedListDemo {

//    public static void main(String[] args) {
//        //进行测试
//        //先创建节点
//        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
//        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
//        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
//        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
//
//
//        //创建链表
//        SignleLinkedList signleLinkedList = new SignleLinkedList();
//        //加入节点
//        signleLinkedList.add(hero1);
//        signleLinkedList.add(hero4);
//        signleLinkedList.add(hero2);
//        signleLinkedList.add(hero3);
//
//
//        //按编号顺序加入
////        signleLinkedList.addByOrder(hero1);
////        signleLinkedList.addByOrder(hero4);
////        signleLinkedList.addByOrder(hero2);
////        signleLinkedList.addByOrder(hero3);
////        signleLinkedList.addByOrder(hero3);
//        //显示
//        signleLinkedList.list(signleLinkedList.getHead());
//
//        //修改节点
//        System.out.println("修改后的链表~~");
//        HeroNode hero = new HeroNode(6, "小卢", "玉麒麟~~");
//        signleLinkedList.update(hero);
//        signleLinkedList.list(signleLinkedList.getHead());
//
//        //删除节点
//        System.out.println("删除后的链表~~");
//        signleLinkedList.delete(5);
////        signleLinkedList.delete(2);
////        signleLinkedList.delete(3);
////        signleLinkedList.delete(1);
//        signleLinkedList.list(signleLinkedList.getHead());
//
//        //单链表中节点个数
//        System.out.printf("单链表中节点个数%d\n", signleLinkedList.size());
//
//        signleLinkedList.findLastIndexNode(2);
//
////        System.out.println("反转后的链表~~");
////        signleLinkedList.reverseList(signleLinkedList.getHead());
////        signleLinkedList.list();
//
//        System.out.println("从尾到头打印链表~~");
//        signleLinkedList.printList(signleLinkedList.getHead());
//
//
//    }

    public static void main(String[] args) {
        testMergeList();
    }

    public static void testMergeList() {
        //合并链表
        //先创建节点
        HeroNode node1 = new HeroNode(1, "老大", "老大");
        HeroNode node2 = new HeroNode(5, "老五", "老五");
        HeroNode node3 = new HeroNode(7, "老七", "老七");
        HeroNode node4 = new HeroNode(9, "老九", "老九");
        HeroNode node5 = new HeroNode(2, "老二", "老二");
        HeroNode node6 = new HeroNode(3, "老三", "老三");
        HeroNode node7 = new HeroNode(4, "老四", "老四");
        HeroNode node8 = new HeroNode(8, "老八", "老八");
        SignleLinkedList signleLinkedList1 = new SignleLinkedList();
        signleLinkedList1.addByOrder(node1);
        signleLinkedList1.addByOrder(node2);
        signleLinkedList1.addByOrder(node3);
        signleLinkedList1.addByOrder(node4);
        SignleLinkedList signleLinkedList2 = new SignleLinkedList();
        signleLinkedList2.addByOrder(node5);
        signleLinkedList2.addByOrder(node6);
        signleLinkedList2.addByOrder(node7);
        signleLinkedList2.addByOrder(node8);

        SignleLinkedList signleLinkedList = new SignleLinkedList();
        signleLinkedList.mergeList(signleLinkedList1.getHead(), signleLinkedList2.getHead());
    }
}

/**
 * 用来管理英雄
 */
class SignleLinkedList {
    //创建一个头结点 位置不动 不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1、找到链表的最后节点
    //2、将最后节点的next 指向 新的节点
    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            //不是最后节点，temp后移
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    //按编号顺序添加
    //编号重复 提示不能加入
    public void addByOrder(HeroNode heroNode) {
        //编号是否重复
        boolean flag = false;
        HeroNode temp = head;
        while (true) {
            //最后节点 heroNode直接插入到最后
            if (temp.next == null) {
                break;
            } else if (temp.next.no > heroNode.no) {
                //插入temp节点后面
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            //不是最后节点，temp后移
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("编号%d已存在，不能加入链表\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    //修改队列节点元素
    public void update(HeroNode heroNode) {
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            //temp空，没有找到要修改的节点
            if (temp == null) {
                break;
            }
            //节点no和要修改的节点no相等 则temp就是要修改的结点
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            //temp后移
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else {
            System.out.println("没有找到要修改的节点");
        }
    }

    //删除节点元素
    //思路
    //1、head不能动 需要一个temp节点找到待删除节点的前一个节点
    //2、说明在比较时，是temp.next.no和需要删除的节点no比较
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            //没有找到要删除的元素
            if (temp.next == null) {
                break;
            }
            //当前节点的下一个节点就是要删除的元素
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            //temp的next元素指向temp的后一个的后一个元素
            temp.next = temp.next.next;
        } else {
            System.out.println("没有找到要删除的元素");
        }
    }


    //显示链表[遍历]
    public void list(HeroNode head) {
        if (head.next == null) {
            System.out.println("链表空");
            return;
        }
        //链表不为空
        HeroNode temp = head.next;
        while (true) {
            //是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

    //单链表中节点个数
    public int size() {
        //辅助变量
        HeroNode temp = head.next;
        //节点个数
        int length = 0;
        while (true) {
            if (temp == null) {
                break;
            }
            length++;
            temp = temp.next;
        }
        return length;
    }

    //查找单链表的倒数第k个节点
    //思路
    //1、遍历链表，求出链表节点个数size
    //2、遍历到[size-index]就是倒数第k个元素
    public void findLastIndexNode(int index) {
        //链表节点个数
        int size = size();
        if (index <= 0 || index > size) {
            System.out.println("没找到节点");
            return;
        }
        //初始节点
        HeroNode cur = head;
        for (int i = 0; i <= size - index; i++) {
            cur = cur.next;
        }
        System.out.printf("倒数第k个节点%d为：%s\n", index, cur);
    }

    //单链表反转
    //思路
    //1、新建一个链表，初始化头结点
    //2、遍历单链表，每遍历一个结点，将其取出，放入到新链表头结点的下一个结点，原来头结点的下一个结点加入到当前结点的后面
    //3、遍历完毕，将单链表的头结点指向新链表的头结点的下一个结点
    public void reverseList(HeroNode head) {
        //当前链表为空，或者只有一个结点 无需反转 直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }

        //定义一个辅助的指针(变量) 帮助遍历原来的链表
        HeroNode cur = head.next;
        //指向当前结点[cur]的下一个结点
        HeroNode next = null;
        //反转链表头结点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表
        while (cur != null) {
            //暂时保存当前结点的下一个结点
            next = cur.next;
            //将cur的下一个结点指向新链表的最前端（头结点的下一个结点）
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        //遍历完毕，将单链表头结点指向新链表头结点的下一个结点
        head.next = reverseHead.next;
    }

    //从尾到头打印链表
    //思路
    //1、反转链表 缺点：会改变链表结构
    //2、使用栈Stack
    public void printList(HeroNode head) {
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //循环遍历 往stack里加入节点
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        int szie = stack.size();
        for (int i = 0; i < szie; i++) {
            System.out.println(stack.pop());
        }
    }


    //合并两个有序的单链表 合并后的链表依旧有序
    //1、新建一个头结点
    //2、依次比较2个有序链表节点编号值，编号小的插入到新链表的尾部
    //考虑2个链表长度不一样 长度短的链表都遍历完，长度长的链表剩下的节点直接加入到链表尾部
    public void mergeList(HeroNode firHead, HeroNode secNode) {
        HeroNode node = new HeroNode(0, "", "");
        HeroNode firTmp = firHead.next;
        HeroNode firNxt = null;
        HeroNode secTmp = secNode.next;
        HeroNode secNxt = null;
        while (firTmp != null || secTmp != null) {
            if (firTmp != null && secTmp != null) {
                if (firTmp.no < secTmp.no) {
                    //firTmp加入到node头部
                    //firTmp后移
                    firNxt = firTmp.next;
                    firTmp.next = node.next;
                    node.next = firTmp;
                    firTmp = firNxt;
                } else if (firTmp.no > secTmp.no) {
                    //secTmp加入到node头部
                    //secTmp后移
                    secNxt = secTmp.next;
                    secTmp.next = node.next;
                    node.next = secTmp;
                    secTmp = secNxt;
                } else {
                    //firTmp.no == secTmp.no
                    //firTmp加入到node头部
                    //secTmp加入到node头部
                    //firTmp后移
                    //secTmp后移
                    firNxt = firTmp.next;
                    firTmp.next = node.next;
                    node.next = firTmp;
                    firTmp = firNxt;
                    secNxt = secTmp.next;
                    secTmp.next = node.next;
                    node.next = secTmp;
                    secTmp = secNxt;
                }
            } else if (firTmp == null) {
                //secTmp加入到node头部
                //secTmp后移
                secNxt = secTmp.next;
                secTmp.next = node.next;
                node.next = secTmp;
                secTmp = secNxt;
            } else {
                firNxt = firTmp.next;
                firTmp.next = node.next;
                node.next = firTmp;
                firTmp = firNxt;
            }
        }
        list(node);
    }
}


/**
 * 英雄人物结点
 */
class HeroNode {
    //英雄编号
    public int no;
    //英雄姓名
    public String name;
    //英雄昵称
    public String nickName;
    //下一个结点
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' + '}';
    }
}
