package com.lyy.datastructure.recursion;

/**
 * 迷宫问题 小球走路
 * 0：未走过 1：墙 2:可以走 3:死路
 */
public class MiGong {
    public static void main(String[] args) {
        //用二维数组模拟迷宫
        int[][] map = new int[8][7];
        //使用1表示墙
        //上下外围墙 置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        //左右外围墙置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        map[1][2] = 1;
        map[2][2] = 1;

        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        //起点 arr[1][1] 终点arr[6][5]
        setWay(map, 1, 1);
        //输出走过后的地图
        System.out.println("走过后的地图");
        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 小球找路
     * 起点 arr[1][1] 终点arr[6][5]
     * 0：未走过，继续走
     * 1：墙，fasle
     * 2：可以走（已走过，无需再走）false
     * 3：死路，false
     * 定义一个规则：当前节点可走，继续走按照下 右 上 左 顺序执行
     * arr[6][5] =2 说明通路找到
     *
     * @param map 地图
     * @param i   当前行
     * @param j   当前列
     * @return 当前点是否可走
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            //0 - 未走过
            if (map[i][j] == 0) {
                //先假设可以走
                map[i][j] = 2;
                //向下走
                if (setWay(map, i + 1, j)) {
                    return true;
                }
                //向右走
                if (setWay(map, i, j + 1)) {
                    return true;
                }
                //向上走
                if (setWay(map, i - 1, j)) {
                    return true;
                }
                //向左走
                if (setWay(map, i, j - 1)) {
                    return true;
                }
                //下右上左都走不通 是死路
                map[i][j] = 3;
                return false;
            } else {
                //1，2，3 直接return false
                return false;
            }
        }
    }
}
