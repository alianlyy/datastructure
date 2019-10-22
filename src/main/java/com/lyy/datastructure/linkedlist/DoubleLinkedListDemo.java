package com.lyy.datastructure.linkedlist;

/**
 * 双向链表
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //创建结点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        //创建链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        //加入节点
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero4);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);

        //显示链表
        doubleLinkedList.list();

        //修改链表
        HeroNode2 hero5 = new HeroNode2(5, "老林", "小小");
        doubleLinkedList.update(hero5);
        System.out.println("修改后链表");
        doubleLinkedList.list();

        //删除链表
        doubleLinkedList.delete(3);
        System.out.println("删除后链表");
        doubleLinkedList.list();

        //链表结点个数
        System.out.printf("链表结点个数%d\n", doubleLinkedList.size());


    }
}

class DoubleLinkedList {
    //创建一个头结点 位置不动
    HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //添加结点
    public void add(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.prev = temp;
    }

    //按顺序加入
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                //已经找到链表最后，插入到temp节点后面
                break;
            } else if (temp.next.no > heroNode.no) {
                //插入到到temp节点后面
                break;
            } else if (temp.next.no < heroNode.no) {
                temp = temp.next;
            } else {
                System.out.printf("已存在节点编号%d,不能重复插入\n", heroNode.no);
                return;
            }
        }
        //将新节点插入到当前结点的后面
        //新节点的next指向当前结点的下一个结点
        heroNode.next = temp.next;
        if (temp.next != null) {
            //当前结点temp的next不为空，则当前结点的下一个结点的前一个结点指向新节点
            temp.next.prev = heroNode;
        }
        //当前结点的next指向新节点
        temp.next = heroNode;
        //新节点的prev指向当前结点temp
        heroNode.prev = temp;
    }

    //修改队列结点元素
    public void update(HeroNode2 heroNode) {
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                System.out.printf("没有找到编号%d的结点，无法修改~~\n", heroNode.no);
                return;
            }
            if (temp.no == heroNode.no) {
                temp.name = heroNode.name;
                temp.nickName = heroNode.nickName;
                return;
            }
            temp = temp.next;
        }
    }

    //删除节点元素
    public void delete(int no) {
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                System.out.printf("没有找到编号%d的结点，无法删除\n", no);
                return;
            }
            if (temp.no == no) {
                break;
            }
            temp = temp.next;
        }
        //当前temp就是要删除的结点
        //当前结点的前一个结点指向当前结点的下一个结点
        temp.prev.next = temp.next;
        //当前结点的下一个结点不为空，则当前结点的下一个结点的前一个结点指向当前结点的前一个结点
        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }
    }

    //显示链表[遍历链表]
    public void list() {
        if (head.next == null) {
            System.out.println("链表空");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            //输出节点
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //链表中结点个数
    public int size() {
        HeroNode2 temp = head.next;
        int length = 0;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }
}

/**
 * 英雄人物结点
 */
class HeroNode2 {
    //英雄编号
    public int no;
    //英雄姓名
    public String name;
    //英雄昵称
    public String nickName;
    //下一个结点
    public HeroNode2 next;
    //前一个结点
    public HeroNode2 prev;

    public HeroNode2(int no, String name, String nickName) {
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